package com.ortega.webapp.servlet.swfe.controllers.facturas;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.FacturaService;
import com.ortega.webapp.servlet.swfe.services.FacturaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

@WebServlet("/facturas")
public class FacturaListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") == null) {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
            return;
        }

        try {
            int mesId = 0;
            if (httpSession.getAttribute("mesSeleccionado") != null) {
                MesEntity mes = (MesEntity) httpSession.getAttribute("mesSeleccionado");
                mesId = mes.getMesId();
            }

            List<FacturaEntity> facturas = facturaService.obtenerListaFacturas(
                    Integer.parseInt(httpSession.getAttribute("clienteId").toString()), mesId, "");

            // Parámetros de paginación
            int pagina = 1;
            int tamanoPagina = 10;
            if (req.getParameter("pagina") != null) {
                pagina = Integer.parseInt(req.getParameter("pagina"));
            }
            if (req.getParameter("tamanoPagina") != null) {
                tamanoPagina = Integer.parseInt(req.getParameter("tamanoPagina"));
            }

            int totalFacturas = facturas.size();
            int totalPaginas = (int) Math.ceil((double) totalFacturas / tamanoPagina);

            int desde = (pagina - 1) * tamanoPagina;
            int hasta = Math.min(desde + tamanoPagina, totalFacturas);

            List<FacturaEntity> facturasPagina = (desde < hasta) ? facturas.subList(desde, hasta) : Collections.emptyList();

            req.setAttribute("facturas", facturasPagina);
            req.setAttribute("paginaActual", pagina);
            req.setAttribute("totalPaginas", totalPaginas);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/facturas.jsp").forward(req, resp);
    }
}
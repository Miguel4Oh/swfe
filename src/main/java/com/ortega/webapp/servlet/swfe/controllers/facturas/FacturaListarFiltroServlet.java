package com.ortega.webapp.servlet.swfe.controllers.facturas;

import com.google.gson.Gson;
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
import java.util.List;

@WebServlet("/facturas/filtro")
public class FacturaListarFiltroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") != null) {
            try {
                String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";

                MesEntity mes = (MesEntity) httpSession.getAttribute("mesSeleccionado");

                List<FacturaEntity> facturas = facturaService.obtenerListaFacturas(
                        Integer.parseInt(httpSession.getAttribute("clienteId").toString()), mes.getMesId(),
                        filtro
                );

                resp.setContentType("application/json");
                resp.getWriter().write(new Gson().toJson(facturas));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
        }
    }
}

package com.ortega.webapp.servlet.swfe.controllers.facturas.ingresos;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.IngresoService;
import com.ortega.webapp.servlet.swfe.services.IngresoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ingresos/filtro")
public class IngresoListarFiltroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IngresoService ingresoService = new IngresoServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") != null) {
            try {
                FacturaEntity facturaEntity = new FacturaEntity();
                String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";

                MesEntity mes = (MesEntity) httpSession.getAttribute("mesSeleccionado");
                facturaEntity.setMesId(mes.getMesId());

                EjercicioEntity ejercicioEntity = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                facturaEntity.setEjercicioFiscalId(ejercicioEntity.getEjercicioFiscalComoInt());

                List<FacturaEntity> facturas = ingresoService.obtenerListaIngresosFiltro(
                        Integer.parseInt(httpSession.getAttribute("clienteId").toString()), facturaEntity,
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

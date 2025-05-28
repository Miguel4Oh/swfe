package com.ortega.webapp.servlet.swfe.controllers.facturas.ingresos;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/facturas/ingresos")
public class IngresoListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IngresoService ingresoService = new IngresoServiceImpl(connection);
        MesService mesService = new MesServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        List<MesEntity> meses = mesService.obtenerListaMeses("");
        req.setAttribute("meses", meses);

        if (httpSession.getAttribute("clienteId") != null) {
            List<FacturaEntity> facturas;
            FacturaEntity facturaEntity = new FacturaEntity();
            MesEntity mesEntity;

            try {
                if (httpSession.getAttribute("mesSeleccionado") != null && httpSession.getAttribute("ejercicioSeleccionado") != null) {
                    mesEntity = (MesEntity) httpSession.getAttribute("mesSeleccionado");
                    facturaEntity.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));
                    facturaEntity.setMesId(mesEntity.getMesId());

                    EjercicioEntity ejercicioEntity = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                    facturaEntity.setEjercicioFiscalId(ejercicioEntity.getEjercicioFiscalComoInt());
                }

                facturas = ingresoService.obtenerListaIngresos(facturaEntity);

                req.setAttribute("facturas", facturas);
                req.setAttribute("mesId", req.getParameter("mesId"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/ingresos.jsp").forward(req, resp);
    }
}

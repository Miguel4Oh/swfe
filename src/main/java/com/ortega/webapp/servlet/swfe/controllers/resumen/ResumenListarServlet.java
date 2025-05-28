package com.ortega.webapp.servlet.swfe.controllers.resumen;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.IngresosEgresosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenImpuestosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity;
import com.ortega.webapp.servlet.swfe.services.ResumenService;
import com.ortega.webapp.servlet.swfe.services.ResumenServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/resumen")
public class ResumenListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ResumenService resumenService = new ResumenServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        System.out.println("ResumenListarServlet.doGet() - clienteId: " + httpSession.getAttribute("clienteId"));

        try {
            if (httpSession.getAttribute("clienteId") != null) {
                List<IngresosEgresosEntity> resumenIngresosEgresosList = resumenService.obtenerIngresosEgresos(
                        Integer.parseInt(httpSession.getAttribute("clienteId").toString()),
                        ((EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado")).getEjercicioFiscalId()
                );

                List<ResumenImpuestosEntity> resumenImpuestosList = resumenService.obtenerResumenImpuestos(
                        Integer.parseInt(httpSession.getAttribute("clienteId").toString()),
                        ((EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado")).getEjercicioFiscalId()
                );

                List<ResumenIvaEntity> resumenIvaList = resumenService.obtenerResumenIva(
                        Integer.parseInt(httpSession.getAttribute("clienteId").toString()),
                        ((EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado")).getEjercicioFiscalId()
                );

                System.out.println("ResumenListarServlet.doGet() - resumenImpuestosList: " + resumenImpuestosList);
                System.out.println("ResumenListarServlet.doGet() - resumenIngresosEgresosList: " + resumenIngresosEgresosList);
                System.out.println("ResumenListarServlet.doGet() - resumenIvaList: " + resumenIvaList);

                req.setAttribute("ingresosEgresos", resumenIngresosEgresosList);
                req.setAttribute("impuestos", resumenImpuestosList);
                req.setAttribute("iva", resumenIvaList);
                getServletContext().getRequestDispatcher("/resumen.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

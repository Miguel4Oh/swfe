package com.ortega.webapp.servlet.swfe.controllers.tablasisr;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.ClienteService;
import com.ortega.webapp.servlet.swfe.services.ClienteServiceImpl;
import com.ortega.webapp.servlet.swfe.services.TablaIsrService;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/tablas/tabla-form")
public class TablaIsrFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);
        TablaIsrEntity tablaIsr = new TablaIsrEntity();

        Long tablaId;

        try {
            tablaId = Long.parseLong(req.getParameter("tabla-id"));
        } catch (NumberFormatException e) {
            tablaId = 0L;
        }

        if (tablaId > 0) {
            Optional<TablaIsrEntity> tablaBd = tablaIsrService.obtenerTablaIsr(tablaId);
            if (tablaBd.isPresent()) {
                tablaIsr = tablaBd.get();
            }
        }

        req.setAttribute("tabla", tablaIsr);
        getServletContext().getRequestDispatcher("/tabla-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);

        String[] meses = req.getParameterValues("mes_nueva[]");
        String[] limitesInferiores = req.getParameterValues("limiteInferior_nueva[]");
        String[] limitesSuperiores = req.getParameterValues("limiteSuperior_nueva[]");
        String[] cuotasFijas = req.getParameterValues("cuotaFija_nueva[]");
        String[] porcentajesExcedentes = req.getParameterValues("porcentajeExcedente_nueva[]");

        for (int i = 0; i < meses.length; i++) {
            TablaIsrEntity tablaIsr = new TablaIsrEntity();
            tablaIsr.setMesTabla(meses[i]);
            tablaIsr.setLimiteInferior(Double.parseDouble(limitesInferiores[i]));
            tablaIsr.setLimiteSuperior(Double.parseDouble(limitesSuperiores[i]));
            tablaIsr.setCuotaFija(Double.parseDouble(cuotasFijas[i]));
            tablaIsr.setPorcentajeExcedente(Double.parseDouble(porcentajesExcedentes[i]));

            tablaIsrService.actualizarGuardarTablaIsr(tablaIsr);
        }

        resp.sendRedirect(req.getContextPath() + "/tablas");
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = (Connection) req.getAttribute("connection");
//        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);
//        TablaIsrEntity tablaIsr = new TablaIsrEntity();
//
//        System.out.println("Nombre: " + req.getParameter("tablaId"));
//        System.out.println("ClienteId: " + req.getParameter("mes"));
//
//        if (!req.getParameter("tablaId").equals("null")) {
//            System.out.println("Entra en if");
//            tablaIsr.setTablaId(Long.parseLong(req.getParameter("tablaId")));
//        }else {
//            tablaIsr.setTablaId(0L);
//        }
//
//        tablaIsr.setMesTabla(req.getParameter("mes"));
//        tablaIsr.setLimiteInferior(Double.parseDouble(req.getParameter("limiteInferior")));
//        tablaIsr.setLimiteSuperior(Double.parseDouble(req.getParameter("limiteSuperior")));
//        tablaIsr.setCuotaFija(Double.parseDouble(req.getParameter("cuotaFija")));
//        tablaIsr.setPorcentajeExcedente(Double.parseDouble(req.getParameter("porcentajeExcedente")));
//
//        tablaIsrService.actualizarGuardarTablaIsr(tablaIsr);
//
//        resp.sendRedirect(req.getContextPath() + "/tablas");
//    }
}

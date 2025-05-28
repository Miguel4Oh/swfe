package com.ortega.webapp.servlet.swfe.controllers.facturas.ingresos;

import com.ortega.webapp.servlet.swfe.services.IngresoService;
import com.ortega.webapp.servlet.swfe.services.IngresoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/facturas/ingresos/saldar")
public class IngresoSaldarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        IngresoService ingresoService = new IngresoServiceImpl(connection);

        int ingresoId = Integer.parseInt(req.getParameter("factura-id"));
        int mesId = Integer.parseInt(req.getParameter("mes-id"));

        try {
            ingresoService.saldarIngreso(ingresoId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/facturas/ingresos?mesId=" + mesId);
    }
}

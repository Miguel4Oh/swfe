package com.ortega.webapp.servlet.swfe.controllers.facturas;

import com.ortega.webapp.servlet.swfe.services.FacturaService;
import com.ortega.webapp.servlet.swfe.services.FacturaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/facturas/eliminar")
public class FacturaEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        int facturaId;

        try {
            if (req.getParameter("factura-id") != null) {
                facturaId = Integer.parseInt(req.getParameter("factura-id"));
                facturaService.eliminarFactura(facturaId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/facturas").forward(req, resp);
    }
}

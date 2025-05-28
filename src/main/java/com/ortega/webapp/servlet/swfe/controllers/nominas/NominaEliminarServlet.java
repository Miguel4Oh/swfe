package com.ortega.webapp.servlet.swfe.controllers.nominas;

import com.ortega.webapp.servlet.swfe.services.NominaService;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/nomina/eliminar")
public class NominaEliminarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);

        int nominaId;

        try {
            if (req.getParameter("nomina-id") != null) {
                nominaId = Integer.parseInt(req.getParameter("nomina-id"));

                nominaService.eliminarNomina(nominaId);
            }

            resp.sendRedirect(req.getContextPath() + "/nomina");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

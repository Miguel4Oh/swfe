package com.ortega.webapp.servlet.swfe.controllers.tablasisr;

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

@WebServlet("/tablas/eliminar")
public class TablaIsrEliminarServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);
        Long tablaId = 0L;

        try {
            if (req.getParameter("tabla-id") != null) {
                tablaId = Long.parseLong(req.getParameter("tabla-id"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Optional<TablaIsrEntity> tablaIsr = tablaIsrService.obtenerTablaIsr(tablaId);

        if (tablaIsr.isPresent()) {
            String nombreTabla = tablaIsr.get().getMesTabla();
            tablaIsrService.eliminarTablaIsr(tablaId);

            String redirectUrl = "/tablas?nombreTabla=" + nombreTabla;

            resp.sendRedirect(getServletContext().getContextPath() + redirectUrl);
        }
    }
}

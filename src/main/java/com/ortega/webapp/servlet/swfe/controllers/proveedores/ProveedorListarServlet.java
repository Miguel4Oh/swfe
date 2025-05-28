package com.ortega.webapp.servlet.swfe.controllers.proveedores;

import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.ProveedorService;
import com.ortega.webapp.servlet.swfe.services.ProveedorServiceImpl;
import com.ortega.webapp.servlet.swfe.services.TablaIsrService;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/proveedores")
public class ProveedorListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProveedorService proveedorService = new ProveedorServiceImpl(connection);

        HttpSession httpSession = req.getSession();
        List<ProveedorEntity> tablasIsr = proveedorService.obtenerListaProveedores(Integer.parseInt(httpSession.getAttribute("clienteId").toString()), "");

        req.setAttribute("proveedores", tablasIsr);
        getServletContext().getRequestDispatcher("/proveedores.jsp").forward(req, resp);
    }
}

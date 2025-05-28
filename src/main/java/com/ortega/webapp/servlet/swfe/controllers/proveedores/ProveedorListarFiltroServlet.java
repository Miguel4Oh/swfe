package com.ortega.webapp.servlet.swfe.controllers.proveedores;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;
import com.ortega.webapp.servlet.swfe.services.ProveedorService;
import com.ortega.webapp.servlet.swfe.services.ProveedorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/proveedores/filtro")
public class ProveedorListarFiltroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProveedorService proveedorService = new ProveedorServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        try {
            String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";

            List<ProveedorEntity> proveedores = proveedorService.obtenerListaProveedores(Integer.parseInt(httpSession.getAttribute("clienteId").toString()), filtro);

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(proveedores));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

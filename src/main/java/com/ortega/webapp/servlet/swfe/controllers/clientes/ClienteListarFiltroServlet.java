package com.ortega.webapp.servlet.swfe.controllers.clientes;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.services.ClienteService;
import com.ortega.webapp.servlet.swfe.services.ClienteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/clientes/filtro")
public class ClienteListarFiltroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        try {
            String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";
            String userRole = httpSession.getAttribute("userRole").toString();

            List<ClienteEntity> clientes = clienteService
                    .obtenerClientes(httpSession.getAttribute("username").toString(), userRole, filtro);

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(clientes));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

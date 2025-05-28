package com.ortega.webapp.servlet.swfe.controllers.clientes;

import com.ortega.webapp.servlet.swfe.services.ClienteService;
import com.ortega.webapp.servlet.swfe.services.ClienteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/clientes/eliminar")
public class ClienteEliminarServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);
        int clienteId;

        try {
            if (req.getParameter("cliente-id") != null && !req.getParameter("cliente-id").isEmpty()) {
                clienteId = Integer.parseInt(req.getParameter("cliente-id"));
                clienteService.eliminarCliente(clienteId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/clientes").forward(req, resp);
    }

}

package com.ortega.webapp.servlet.swfe.controllers.clientes;

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

@WebServlet("/clientes/seleccionar")
public class ClienteSeleccionarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);


        HttpSession session = req.getSession();

        String userRole = session.getAttribute("userRole").toString();
        List<ClienteEntity> clientes = clienteService
                .obtenerClientes(session.getAttribute("username").toString(), userRole, "");

        req.setAttribute("clientes", clientes);
        //req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/seleccionar-cliente.jsp").forward(req, resp);
    }
}

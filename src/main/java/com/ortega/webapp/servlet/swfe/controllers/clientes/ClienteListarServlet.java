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

@WebServlet("/clientes")
public class ClienteListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);

        //LoginServiceSessionImpl loginSession = new LoginServiceSessionImpl();
        //Optional<String> usuario = loginSession.getUsername(req);

        HttpSession httpSession = req.getSession();

        String userRole = httpSession.getAttribute("userRole").toString();
        List<ClienteEntity> clientes = clienteService
                .obtenerClientes(httpSession.getAttribute("username").toString(), userRole, "");

        req.setAttribute("clientes", clientes);
        //req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/clientes.jsp").forward(req, resp);
    }
}

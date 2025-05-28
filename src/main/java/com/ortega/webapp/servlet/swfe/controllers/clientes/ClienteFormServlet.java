package com.ortega.webapp.servlet.swfe.controllers.clientes;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.services.ClienteService;
import com.ortega.webapp.servlet.swfe.services.ClienteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/clientes/cliente-form")
public class ClienteFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);
        ClienteEntity cliente = new ClienteEntity();
        int clienteId = 0;

        try {
            if (req.getParameter("clienteId") != null) {
                clienteId = Integer.parseInt(req.getParameter("cliente-id"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (clienteId > 0) {
            Optional<ClienteEntity> clienteBd = clienteService.obtenerCliente(clienteId);
            if (clienteBd.isPresent()) {
                cliente = clienteBd.get();
            }
        }
        req.setAttribute("cliente", cliente);
        req.setAttribute("usuarioId", req.getSession().getAttribute("username"));

        getServletContext().getRequestDispatcher("/cliente-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ClienteService clienteService = new ClienteServiceImpl(connection);
        ClienteEntity cliente = new ClienteEntity();

        if (req.getParameter("clienteId") != null && !req.getParameter("clienteId").isEmpty()) {
            cliente.setClienteId(Integer.parseInt(req.getParameter("clienteId")));
        }

        cliente.setNombreCliente(req.getParameter("nombre"));
        cliente.setRfcCliente(req.getParameter("rfc"));
        cliente.setRegimenFiscal(req.getParameter("regimenFiscal"));
        cliente.setRazonSocial(req.getParameter("razonSocial"));
        cliente.setUsuarioId(Integer.parseInt(req.getParameter("usuarioId")));

        clienteService.actualizarGuardarCliente(cliente);

        resp.sendRedirect(req.getContextPath() + "/clientes");
    }
}

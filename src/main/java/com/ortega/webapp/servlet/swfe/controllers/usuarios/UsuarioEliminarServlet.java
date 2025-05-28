package com.ortega.webapp.servlet.swfe.controllers.usuarios;

import com.ortega.webapp.servlet.swfe.services.UsuarioService;
import com.ortega.webapp.servlet.swfe.services.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);

        int usuarioId;

        try {
            usuarioId = Integer.parseInt(req.getParameter("usuario-id"));
        } catch (NumberFormatException e) {
            usuarioId = 0;
        }

        try {
            usuarioService.eliminarUsuario(usuarioId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/usuarios").forward(req, resp);
    }
}

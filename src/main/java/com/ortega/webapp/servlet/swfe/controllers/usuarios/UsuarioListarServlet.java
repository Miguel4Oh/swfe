package com.ortega.webapp.servlet.swfe.controllers.usuarios;

import com.ortega.webapp.servlet.swfe.models.UserEntity;
import com.ortega.webapp.servlet.swfe.services.UsuarioService;
import com.ortega.webapp.servlet.swfe.services.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("username") != null && httpSession.getAttribute("userRole").equals("1")) {
            try {
                List<UserEntity> usuarios = usuarioService.obtenerListaUsuarios();
                req.setAttribute("usuarios", usuarios);
                getServletContext().getRequestDispatcher("/usuarios.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }
}

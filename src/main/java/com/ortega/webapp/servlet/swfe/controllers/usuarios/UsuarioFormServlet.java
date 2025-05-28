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

@WebServlet("/usuarios/usuario-form")
public class UsuarioFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);
        UserEntity usuario = new UserEntity();

        int usuarioId;

        try {
            usuarioId = Integer.parseInt(req.getParameter("usuario-id"));
        } catch (NumberFormatException e) {
            usuarioId = 0;
        }

        System.out.println("usuarioId: " + usuarioId);

        if (usuarioId > 0) {
            try {
                usuario = usuarioService.obtenerUsuario(usuarioId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/usuario-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);
        UserEntity usuario = new UserEntity();
        HttpSession httpSession = req.getSession();

        usuario.setUsuarioId(Integer.parseInt(req.getParameter("usuarioId")));
        usuario.setUsername(req.getParameter("username"));
        usuario.setRoleId(req.getParameter("roleId"));
        usuario.setPassword(req.getParameter("password"));

        System.out.println("usuarioId: " + usuario);
        System.out.println(req.getParameter("action"));
        try {
            if (req.getParameter("action") != null && req.getParameter("action").equals("create")) {
                if(!usuarioService.guardarUsuario(usuario)) {
                    httpSession.setAttribute("mensaje", "error");
                }
            } else {
                usuarioService.actualizarUsuario(usuario);
            }
        } catch (Exception e) {
            httpSession.setAttribute("mensaje", "error");
            throw new RuntimeException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}

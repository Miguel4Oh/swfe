package com.ortega.webapp.servlet.swfe.controllers.usuarios;

import com.ortega.webapp.servlet.swfe.models.UserEntity;
import com.ortega.webapp.servlet.swfe.services.LoginService;
import com.ortega.webapp.servlet.swfe.services.LoginServiceImpl;
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
import java.util.Optional;


@WebServlet("/usuarios/contrasena")
public class ContrasenaFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);

        LoginService loginService = new LoginServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        String usuario = httpSession.getAttribute("username").toString();
        String contrasenaActual = req.getParameter("password_actual");
        String contrasenaNueva = req.getParameter("password_nueva");

        try {
            Optional<UserEntity> userEntity = loginService.login(usuario, contrasenaActual);

            System.out.println("userEntity: " + userEntity);
            if ( userEntity.isPresent()){
                usuarioService.cambiarContrasena(usuario, contrasenaActual, contrasenaNueva);
            } else {
                httpSession.setAttribute("mensaje", "errorContrasena");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/contrasena-form.jsp");
    }
}

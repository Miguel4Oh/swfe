package com.ortega.webapp.servlet.swfe.controllers.ejercicio;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.services.EjercicioService;
import com.ortega.webapp.servlet.swfe.services.EjercicioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ejercicios/listar")
public class EjercicioListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EjercicioService ejercicioService = new EjercicioServiceImpl(connection);

        try {
            List<EjercicioEntity> ejercicios = ejercicioService.listarEjercicios();

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(ejercicios));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


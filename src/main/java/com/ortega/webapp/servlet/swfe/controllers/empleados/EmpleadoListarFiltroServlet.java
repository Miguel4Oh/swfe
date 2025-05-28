package com.ortega.webapp.servlet.swfe.controllers.empleados;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.services.EmpleadoService;
import com.ortega.webapp.servlet.swfe.services.EmpleadoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/empleados/filtro")
public class EmpleadoListarFiltroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        try {
            String filtro = req.getParameter("filtro") != null ? req.getParameter("filtro") : "";

            List<EmpleadoEntity> empleadoEntity = empleadoService.obtenerListaEmpleados(Integer.parseInt(httpSession.getAttribute("clienteId").toString()), filtro);

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(empleadoEntity));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

package com.ortega.webapp.servlet.swfe.controllers.empleados;

import com.ortega.webapp.servlet.swfe.services.EmpleadoService;
import com.ortega.webapp.servlet.swfe.services.EmpleadoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/empleados/eliminar")
public class EmpleadoEliminarServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);
        int empleadoId = 0;

        try {
            if (req.getParameter("empleado-id") != null) {
                empleadoId = Integer.parseInt(req.getParameter("empleado-id"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        empleadoService.eliminarEmpleado(empleadoId);

        getServletContext().getRequestDispatcher("/empleados").forward(req, resp);
    }
}

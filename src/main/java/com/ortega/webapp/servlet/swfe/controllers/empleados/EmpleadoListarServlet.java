package com.ortega.webapp.servlet.swfe.controllers.empleados;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.services.ConceptoService;
import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoListarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        List<EmpleadoEntity> empleados = new ArrayList<>();

        if (httpSession.getAttribute("clienteId") != null) {
            empleados = empleadoService.obtenerListaEmpleados(Integer.parseInt(httpSession.getAttribute("clienteId").toString()), "");
        }

        req.setAttribute("empleados", empleados);
        getServletContext().getRequestDispatcher("/empleados.jsp").forward(req, resp);
    }
}

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

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/empleados/empleado-form")
public class EmpleadoFormServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);
        EmpleadoEntity empleado = new EmpleadoEntity();

        int empleadoId;

        try {
            empleadoId = Integer.parseInt(req.getParameter("empleado-id"));
        } catch (NumberFormatException e) {
            empleadoId = 0;
        }

        if (empleadoId > 0) {
            Optional<EmpleadoEntity> tablaBd = empleadoService.obtenerEmpleado(empleadoId);
            if (tablaBd.isPresent()) {
                empleado = tablaBd.get();
            }
        }

        req.setAttribute("empleado", empleado);
        getServletContext().getRequestDispatcher("/empleado-form.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);
        EmpleadoEntity empleado = new EmpleadoEntity();

        if (req.getParameter("empleadoId") != null && req.getParameter("clienteId") != null) {
            try {
                empleado.setEmpleadoId(Integer.parseInt(req.getParameter("empleadoId")));
                empleado.setNombre(req.getParameter("nombre"));
                empleado.setSueldo(Double.parseDouble(req.getParameter("sueldo")));
                empleado.setClienteId(Integer.parseInt(req.getParameter("clienteId")));

                empleadoService.actualizarGuardarEmpleado(empleado);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(req.getContextPath() + "/empleados");
    }
}

package com.ortega.webapp.servlet.swfe.controllers.nominas;

import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.services.NominaService;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/nomina/empleado")
public class NominaEmpleadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);
        NominaEntity nominaEntity = new NominaEntity();

        int nominaId = Integer.parseInt(req.getParameter("nomina-id"));

        try {
            nominaEntity = nominaService.obtenerNominaPorId(nominaId);
            req.setAttribute("nomina", nominaEntity);

            System.out.println("NominaEmpleadoServlet nominaEntity: " + nominaEntity);
            getServletContext().getRequestDispatcher("/nomina-form-empleado.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);
        NominaFormDTO nominaFormDTO = new NominaFormDTO();

        nominaFormDTO.setNominaId(Integer.parseInt(req.getParameter("nominaId")));
        nominaFormDTO.setSueldo(Double.parseDouble(req.getParameter("salario")));
        nominaFormDTO.setDiasLaborados(Integer.parseInt(req.getParameter("diasTrabajados")));

        try {
            nominaService.actualizarGuardarNomina(nominaFormDTO);
            resp.sendRedirect(req.getContextPath() + "/nomina");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

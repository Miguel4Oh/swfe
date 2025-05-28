package com.ortega.webapp.servlet.swfe.controllers.ejercicio;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.services.EjercicioService;
import com.ortega.webapp.servlet.swfe.services.EjercicioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ejercicios")
public class EjercicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EjercicioService ejercicioService = new EjercicioServiceImpl(connection);

        HttpSession session = req.getSession();
        int clienteId = Integer.parseInt(session.getAttribute("clienteId").toString());
        int ejercicioId = Integer.parseInt(req.getParameter("nuevo-ejercicio-input"));

        System.out.println("Ejercicio: " + ejercicioId);

        try {
            ejercicioService.guardarEjercicio(ejercicioId, clienteId);
            List<EjercicioEntity> ejerciciosCliente = ejercicioService.listarEjerciciosCliente(clienteId);

            for (EjercicioEntity ejercicio : ejerciciosCliente) {
                if (ejercicio.getEjercicioFiscalId() == ejercicioId) {
                    session.setAttribute("ejercicioSeleccionado", ejercicio);
                    break;
                }
            }

            session.setAttribute("ejerciciosCliente", ejerciciosCliente);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/facturas");
    }
}

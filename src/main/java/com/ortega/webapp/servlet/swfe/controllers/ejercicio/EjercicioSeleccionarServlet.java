package com.ortega.webapp.servlet.swfe.controllers.ejercicio;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/ejercicio/seleccionar")
public class EjercicioSeleccionarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        List<EjercicioEntity> ejercicios = (List<EjercicioEntity>) session.getAttribute("ejerciciosCliente");
        int ejercicioId = Integer.parseInt(req.getParameter("ejercicio-fiscal-id"));

        for (EjercicioEntity ejercicio : ejercicios) {
            if (ejercicio.getEjercicioFiscalId() == ejercicioId) {
                session.setAttribute("ejercicioSeleccionado", ejercicio);
                break;
            }
        }

        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\": \"ok\"}");
    }
}

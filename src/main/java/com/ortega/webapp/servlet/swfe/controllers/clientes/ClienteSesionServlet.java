package com.ortega.webapp.servlet.swfe.controllers.clientes;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.EjercicioService;
import com.ortega.webapp.servlet.swfe.services.EjercicioServiceImpl;
import com.ortega.webapp.servlet.swfe.services.MesService;
import com.ortega.webapp.servlet.swfe.services.MesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cliente/seleccionar")
public class ClienteSesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EjercicioService ejercicioService = new EjercicioServiceImpl(connection);
        MesService mesService = new MesServiceImpl(connection);

        HttpSession session = req.getSession();
        session.setAttribute("clienteId", req.getParameter("cliente-id"));
        session.setAttribute("nombreCliente", req.getParameter("nombre-cliente"));

        try {
            List<EjercicioEntity> ejercicios = ejercicioService.listarEjercicios();
            session.setAttribute("ejercicios", ejercicios);

            List<EjercicioEntity> ejerciciosCliente = ejercicioService.listarEjerciciosCliente(Integer.parseInt(req.getParameter("cliente-id")));
            List<MesEntity> meses = mesService.obtenerListaMeses("MES");

            if (ejerciciosCliente != null && !ejerciciosCliente.isEmpty()) {
                session.setAttribute("ejerciciosCliente", ejerciciosCliente);

                EjercicioEntity ultimoEjercicio = EjercicioEntity.obtenerEjercicioFiscalMasGrande(ejerciciosCliente);

                if (ultimoEjercicio != null) {
                    session.setAttribute("ejercicioSeleccionado", ultimoEjercicio);
                }
            }

            session.setAttribute("meses", meses);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ejercicio seleccionado: " + session.getAttribute("ejercicioSeleccionado"));

        resp.sendRedirect(req.getContextPath() + "/facturas");
    }
}

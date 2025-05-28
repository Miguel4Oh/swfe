package com.ortega.webapp.servlet.swfe.controllers.nominas;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/nomina/nomina-form")
public class NominaFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("NominaFormServlet doPost");
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);
        HttpSession session = req.getSession();

        if (session.getAttribute("clienteId") != null) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            }

            System.out.println("requestBody: " + requestBody);
            NominaFormDTO[] nominas;

            try {
                Gson gson = new Gson();
                nominas = gson.fromJson(requestBody.toString(), NominaFormDTO[].class);
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
                return;
            }

            for (NominaFormDTO nomina : nominas) {

                try {
                    EjercicioEntity ejercicio = (EjercicioEntity) session.getAttribute("ejercicioSeleccionado");
                    nomina.setClienteId(Integer.parseInt(session.getAttribute("clienteId").toString()));
                    nomina.setEjercicioFiscal(ejercicio.getEjercicioFiscalId());

                    nominaService.actualizarGuardarNomina(nomina);

                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("application/json");
                    resp.getWriter().write("{\"message\": \"Guardado exitoso\"}");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"message\": \"Error al guardar\"}");
                }
            }

        } else {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        EmpleadoService empleadoService = new EmpleadoServiceImpl(connection);
        MesService mesService = new MesServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        List<EmpleadoEntity> empleados = empleadoService.obtenerListaEmpleados(Integer.parseInt(httpSession.getAttribute("clienteId").toString()), "");
        List<MesEntity> periodos = mesService.obtenerListaMeses("MES");

        req.setAttribute("empleados", empleados);
        req.setAttribute("periodos", periodos);
        getServletContext().getRequestDispatcher("/nomina-form.jsp").forward(req, resp);
    }
}

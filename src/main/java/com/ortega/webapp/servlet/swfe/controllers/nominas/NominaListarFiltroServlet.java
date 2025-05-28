package com.ortega.webapp.servlet.swfe.controllers.nominas;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.services.NominaService;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/nomina/filtro")
public class NominaListarFiltroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        NominaService nominaService = new NominaServiceImpl(connection);
        NominaFormDTO nominaFormDTO = new NominaFormDTO();
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") != null) {
            try {
                if (!req.getParameter("quincena").isBlank()) {
                    System.out.println("quincena: " + req.getParameter("quincena"));
                    nominaFormDTO.setQuincena(Integer.parseInt(req.getParameter("quincena")));
                } else {
                    nominaFormDTO.setQuincena(0);
                }
                nominaFormDTO.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));

                EjercicioEntity ejercicioEntity = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                nominaFormDTO.setEjercicioFiscal(ejercicioEntity.getEjercicioFiscalId());
                nominaFormDTO.setMes(((MesEntity) httpSession.getAttribute("mesSeleccionado")).getMesId());

                List<NominaEntity> nominas = nominaService.obtenerListaNominas(nominaFormDTO);

                resp.setContentType("application/json");

                resp.getWriter().write(new Gson().toJson(nominas));

            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
        }
    }
}

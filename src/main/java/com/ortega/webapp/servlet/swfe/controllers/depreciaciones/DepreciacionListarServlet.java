package com.ortega.webapp.servlet.swfe.controllers.depreciaciones;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.ConceptoService;
import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
import com.ortega.webapp.servlet.swfe.services.DepreciacionService;
import com.ortega.webapp.servlet.swfe.services.DepreciacionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/depreciaciones")
public class DepreciacionListarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        DepreciacionService depreciacionService = new DepreciacionServiceImpl(connection);
        ConceptoService conceptoService = new ConceptoServiceImpl(connection);

        HttpSession httpSession = req.getSession();

        try {
            int mesSeleccionadoId = 0;
            String anioEjercicioFiscal = "";

            if(httpSession.getAttribute("ejercicioSeleccionado") != null) {
                EjercicioEntity ejercicioFiscal = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                anioEjercicioFiscal = ejercicioFiscal.getEjercicioFiscal();
            }

            if (httpSession.getAttribute("mesSeleccionado") != null) {
                MesEntity mesSeleccionado = (MesEntity) httpSession.getAttribute("mesSeleccionado");
                mesSeleccionadoId = mesSeleccionado.getMesId();
            }

            List<DepreciacionEntity> depreciaciones = depreciacionService.obtenerListaDepreciaciones(
                    Integer.parseInt(httpSession.getAttribute("clienteId").toString()),
                    anioEjercicioFiscal, mesSeleccionadoId);

            List<ConceptoDepreciacionEntity> conceptos = conceptoService.obtenerListaConceptos("");

            req.setAttribute("conceptos", conceptos);
            req.setAttribute("depreciaciones", depreciaciones);
            getServletContext().getRequestDispatcher("/depreciaciones.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/depreciaciones.jsp").forward(req, resp);
        }

    }
}

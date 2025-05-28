package com.ortega.webapp.servlet.swfe.controllers.conceptosdepreciaciones;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
import com.ortega.webapp.servlet.swfe.services.ConceptoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/conceptos/concepto-form")
public class ConceptoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ConceptoService conceptosService = new ConceptoServiceImpl(connection);
        ConceptoDepreciacionEntity concepto = new ConceptoDepreciacionEntity();

        int conceptoId;

        try {
            conceptoId = Integer.parseInt(req.getParameter("concepto-id"));
        } catch (NumberFormatException e) {
            conceptoId = 0;
        }

        if (conceptoId > 0) {
            Optional<ConceptoDepreciacionEntity> tablaBd = conceptosService.obtenerConcepto(conceptoId);
            if (tablaBd.isPresent()) {
                concepto = tablaBd.get();
            }
        }

        req.setAttribute("concepto", concepto);
        getServletContext().getRequestDispatcher("/concepto-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ConceptoService conceptosService = new ConceptoServiceImpl(connection);
        ConceptoDepreciacionEntity conceptoDepreciacion = new ConceptoDepreciacionEntity();

        conceptoDepreciacion.setConceptoDepreciacionId(Integer.parseInt(req.getParameter("conceptoId")));
        conceptoDepreciacion.setNombreDepreciacion(req.getParameter("concepto"));
        conceptoDepreciacion.setPorcentajeDepreciacion(Float.parseFloat(req.getParameter("porcentaje")));

        conceptosService.actualizarGuardarConcepto(conceptoDepreciacion);

        resp.sendRedirect(req.getContextPath() + "/conceptos");
    }
}

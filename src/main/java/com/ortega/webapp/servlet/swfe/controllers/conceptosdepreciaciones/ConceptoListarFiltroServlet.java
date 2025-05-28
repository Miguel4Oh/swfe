package com.ortega.webapp.servlet.swfe.controllers.conceptosdepreciaciones;

import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.services.ConceptoService;
import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/conceptos/filtro")
public class ConceptoListarFiltroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ConceptoService conceptoService = new ConceptoServiceImpl(connection);
        HttpSession session = req.getSession();

        try {
            String filtro = req.getParameter("filtro");

            List<ConceptoDepreciacionEntity> conceptos = conceptoService.obtenerListaConceptos(filtro);

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(conceptos));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

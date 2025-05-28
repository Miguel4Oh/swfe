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
import java.util.List;

@WebServlet("/conceptos")
public class ConceptoListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ConceptoService conceptoService = new ConceptoServiceImpl(connection);

        //LoginServiceSessionImpl loginSession = new LoginServiceSessionImpl();
        //Optional<String> usuario = loginSession.getUsername(req);

        List<ConceptoDepreciacionEntity> conceptos = conceptoService.obtenerListaConceptos("");

        req.setAttribute("conceptos", conceptos);
        //req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/conceptos.jsp").forward(req, resp);
    }
}

package com.ortega.webapp.servlet.swfe.controllers.conceptosdepreciaciones;

import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
import com.ortega.webapp.servlet.swfe.services.ConceptoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/conceptos/eliminar")
public class ConceptoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ConceptoService conceptosService = new ConceptoServiceImpl(connection);
        int conceptoId;

        try{
            conceptoId = Integer.parseInt(req.getParameter("concepto-id"));
        }catch (NumberFormatException e){
            conceptoId = 0;
        }

        conceptosService.eliminarConcepto(conceptoId);

        getServletContext().getRequestDispatcher("/conceptos").forward(req, resp);
    }
}

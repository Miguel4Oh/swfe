package com.ortega.webapp.servlet.swfe.controllers.tablasisr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.TablaIsrService;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/tablas/guardar")
public class TablasIsrGuardarServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        TablaIsrService tablaIsrService = new TablaIsrServiceImpl(connection);

        StringBuilder requestBody = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        TablaIsrEntity[] tablaIsr;

        try {
            tablaIsr = gson.fromJson(requestBody.toString(), TablaIsrEntity[].class);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
            return;
        }

        if (tablaIsr != null) {
            for (TablaIsrEntity tablaIsrEntity : tablaIsr) {
                tablaIsrService.actualizarGuardarTablaIsr(tablaIsrEntity);
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\":\"Tabla guardada correctamente\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
        }
    }
}

package com.ortega.webapp.servlet.swfe.controllers.depreciaciones;

import com.google.gson.*;
import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.DepreciacionService;
import com.ortega.webapp.servlet.swfe.services.DepreciacionServiceImpl;
import com.ortega.webapp.servlet.swfe.utils.DateAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/depreciaciones/actualizar")
public class DepreciacionActualizarServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DepreciacionActualizarServlet.doDelete");
        Connection connection = (Connection) req.getAttribute("connection");
        DepreciacionService depreciacionService = new DepreciacionServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        try {
            if (httpSession.getAttribute("clienteId") != null) {
                StringBuilder requestBody = new StringBuilder();
                String line;
                try (BufferedReader reader = req.getReader()) {
                    while ((line = reader.readLine()) != null) {
                        requestBody.append(line);
                    }
                }

                JsonElement jsonElement = JsonParser.parseString(requestBody.toString());

                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    for (JsonElement element : jsonArray) {
                        depreciacionService.eliminarDepreciacion(element.getAsInt());
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
                }

                resp.setContentType("application/json");
                resp.getWriter().write("{\"message\": \"Eliminación exitosa\"}");
            } else {
                resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DepreciacionActualizarServlet.doPost");

        Connection connection = (Connection) req.getAttribute("connection");
        DepreciacionService depreciacionService = new DepreciacionServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") != null) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateAdapter());
            Gson gson = gsonBuilder.create();

            DepreciacionEntity[] depreciacionEntities = null;

            try {

                depreciacionEntities = gson.fromJson(requestBody.toString(), DepreciacionEntity[].class);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }


            if (depreciacionEntities != null) {
                for (DepreciacionEntity depreciacionEntity : depreciacionEntities) {
                    try {
                        String ejercicioActual = ((EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado")).getEjercicioFiscal();
                        int mesId = ((MesEntity) httpSession.getAttribute("mesSeleccionado")).getMesId();

                        depreciacionEntity.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));

                        depreciacionService.actualizarGuardarDepreciacion(depreciacionEntity, ejercicioActual, mesId);
                    } catch (SQLException e ) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
            }

            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Actualización exitosa\"}");
        } else {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DepreciacionActualizarServlet.doPut");

        Connection connection = (Connection) req.getAttribute("connection");
        DepreciacionService depreciacionService = new DepreciacionServiceImpl(connection);
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute("clienteId") != null) {
            StringBuilder requestBody = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateAdapter());
            Gson gson = gsonBuilder.create();

            DepreciacionEntity[] depreciacionEntity = null;

            try {
                depreciacionEntity = gson.fromJson(requestBody.toString(), DepreciacionEntity[].class);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }

            if (depreciacionEntity != null) {
                for (DepreciacionEntity depreciacion : depreciacionEntity) {
                    try {
                        String ejercicioActual = ((EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado")).getEjercicioFiscal();
                        int mesId = ((MesEntity) httpSession.getAttribute("mesSeleccionado")).getMesId();

                        depreciacion.setClienteId(Integer.parseInt(httpSession.getAttribute("clienteId").toString()));

                        depreciacionService.actualizarGuardarDepreciacion(depreciacion, ejercicioActual, mesId);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                }

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
            }

            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Actualización exitosa\"}");
        } else {
            resp.sendRedirect(req.getContextPath() + "/clientes/seleccionar");
        }
    }
}

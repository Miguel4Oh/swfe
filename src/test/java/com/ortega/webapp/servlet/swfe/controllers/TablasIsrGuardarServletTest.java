package com.ortega.webapp.servlet.swfe.controllers;


import com.google.gson.Gson;
import com.ortega.webapp.servlet.swfe.controllers.tablasisr.TablasIsrGuardarServlet;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;

import static org.mockito.Mockito.*;

public class TablasIsrGuardarServletTest {

    private TablasIsrGuardarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;
    private ServletContext servletContext;

    @BeforeEach
    public void setUp() throws ServletException {
        servlet = new TablasIsrGuardarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        connection = mock(Connection.class);
        servletContext = mock(ServletContext.class);

        when(request.getAttribute("connection")).thenReturn(connection);

        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);
        servlet.init(config);
    }

    @Test
    public void testDoPostWithValidJson() throws ServletException, IOException {
        TablaIsrEntity tabla = new TablaIsrEntity();
        tabla.setTablaId(1L);
        Gson gson = new Gson();
        String json = gson.toJson(new TablaIsrEntity[]{tabla});

        BufferedReader reader = new BufferedReader(new StringReader(json));
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        try (MockedConstruction<TablaIsrServiceImpl> mocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> doNothing().when(mock).actualizarGuardarTablaIsr(any(TablaIsrEntity.class)))) {

            servlet.doPost(request, response);

            TablaIsrServiceImpl tablaService = mocked.constructed().get(0);
            verify(tablaService, times(1)).actualizarGuardarTablaIsr(any(TablaIsrEntity.class));
            verify(response).setStatus(HttpServletResponse.SC_OK);
            verify(writer).write("{\"message\":\"Tabla guardada correctamente\"}");
        }
    }

    @Test
    public void testDoPostWithInvalidJson() throws ServletException, IOException {
        String invalidJson = "esto no es un JSON válido";
        BufferedReader reader = new BufferedReader(new StringReader(invalidJson));
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(writer).write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
    }

    @Test
    public void testDoPostWithNullArrayJson() throws ServletException, IOException {
        String nullArrayJson = "null";
        BufferedReader reader = new BufferedReader(new StringReader(nullArrayJson));
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(writer).write("{\"message\":\"El cuerpo de la solicitud no es un JSON Array\"}");
    }
}
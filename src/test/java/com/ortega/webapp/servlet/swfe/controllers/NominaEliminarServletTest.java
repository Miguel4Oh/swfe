package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.nominas.NominaEliminarServlet;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NominaEliminarServletTest {

    private NominaEliminarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;

    @BeforeEach
    void setUp() {
        servlet = new NominaEliminarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        connection = mock(Connection.class);
    }

    @Test
    void testDoGet_WithValidNominaId_CallsEliminarNominaAndRedirects() throws Exception {
        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getParameter("nomina-id")).thenReturn("123");
        when(request.getContextPath()).thenReturn("/miapp");

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class,
                (mock, context) -> {
                    doNothing().when(mock).eliminarNomina(123);
                })) {

            servlet.doGet(request, response);

            NominaServiceImpl nominaService = mocked.constructed().get(0);
            verify(nominaService).eliminarNomina(123);
            verify(response).sendRedirect("/miapp/nomina");
        }
    }

    @Test
    void testDoGet_WithoutNominaId_OnlyRedirects() throws Exception {
        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getParameter("nomina-id")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/miapp");

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class)) {

            servlet.doGet(request, response);

            // No se debe llamar a eliminarNomina
            verify(mocked.constructed().get(0), never()).eliminarNomina(anyInt());
            verify(response).sendRedirect("/miapp/nomina");
        }
    }

    @Test
    void testDoGet_WithInvalidNominaId_ThrowsRuntimeException() {
        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getParameter("nomina-id")).thenReturn("abc"); // inválido
        when(request.getContextPath()).thenReturn("/miapp");

        try (MockedConstruction<NominaServiceImpl> ignored = mockConstruction(NominaServiceImpl.class)) {
            assertThrows(RuntimeException.class, () -> {
                servlet.doGet(request, response);
            });
        }
    }
}

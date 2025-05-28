package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.empleados.EmpleadoEliminarServlet;
import com.ortega.webapp.servlet.swfe.services.EmpleadoServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;


import java.sql.Connection;

import static org.mockito.Mockito.*;

public class EmpleadoEliminarServletTest {

    private EmpleadoEliminarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private ServletContext servletContext;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new EmpleadoEliminarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        servletContext = mock(ServletContext.class);
        connection = mock(Connection.class);

        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/empleados")).thenReturn(dispatcher);
        when(request.getAttribute("connection")).thenReturn(connection);

        servlet.init(config);
    }

    @Test
    public void testEliminarEmpleadoConIdValido() throws Exception {
        when(request.getParameter("empleado-id")).thenReturn("5");

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
            servlet.doGet(request, response);

            EmpleadoServiceImpl service = mocked.constructed().get(0);
            verify(service).eliminarEmpleado(5);
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testEliminarEmpleadoConParametroNull() throws Exception {
        when(request.getParameter("empleado-id")).thenReturn(null);

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
            servlet.doGet(request, response);

            EmpleadoServiceImpl service = mocked.constructed().get(0);
            verify(service).eliminarEmpleado(0); // default
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testEliminarEmpleadoConParametroInvalido() throws Exception {
        when(request.getParameter("empleado-id")).thenReturn("abc");

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
            servlet.doGet(request, response);

            EmpleadoServiceImpl service = mocked.constructed().get(0);
            verify(service).eliminarEmpleado(0); // por default tras NumberFormatException
            verify(dispatcher).forward(request, response);
        }
    }
}


package com.ortega.webapp.servlet.swfe.controllers;


import com.ortega.webapp.servlet.swfe.controllers.empleados.EmpleadoListarServlet;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.services.EmpleadoServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmpleadoListarServletTest {

    private EmpleadoListarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;
    private HttpSession session;
    private ServletContext servletContext;

    @BeforeEach
    public void setUp() throws ServletException {
        servlet = new EmpleadoListarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        connection = mock(Connection.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);

        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/empleados.jsp")).thenReturn(mock(jakarta.servlet.RequestDispatcher.class));


        // Configuración de ServletConfig
        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);
        servlet.init(config);
    }

    @Test
    public void testDoGetWithClienteIdAndEmployees() throws ServletException, IOException {
        // Simula que el clienteId está en la sesión y que hay empleados
        when(session.getAttribute("clienteId")).thenReturn(1);

        List<EmpleadoEntity> empleadosMock = new ArrayList<>();
        empleadosMock.add(new EmpleadoEntity()); // Simula un empleado

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaEmpleados(1, "")).thenReturn(empleadosMock))) {

            servlet.doGet(request, response);

            // Verificar que la lista de empleados fue obtenida correctamente
            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
            verify(empleadoServiceMock).obtenerListaEmpleados(1, "");

            // Verificar que se pasó la lista de empleados al JSP
            verify(request).setAttribute("empleados", empleadosMock);

            // Verificar que se hizo el forward a "/empleados.jsp"
            verify(servletContext.getRequestDispatcher("/empleados.jsp")).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithClienteIdButNoEmployees() throws ServletException, IOException {
        // Simula que el clienteId está en la sesión pero no hay empleados
        when(session.getAttribute("clienteId")).thenReturn(1);

        List<EmpleadoEntity> empleadosMock = new ArrayList<>();

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaEmpleados(1, "")).thenReturn(empleadosMock))) {

            servlet.doGet(request, response);

            // Verificar que se llamo al servicio para obtener la lista de empleados
            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
            verify(empleadoServiceMock).obtenerListaEmpleados(1, "");

            // Verificar que la lista de empleados es vacía
            verify(request).setAttribute("empleados", empleadosMock);

            // Verificar que se hizo el forward a "/empleados.jsp"
            verify(servletContext.getRequestDispatcher("/empleados.jsp")).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithNoClienteIdInSession() throws ServletException, IOException {
        // Simula que no hay clienteId en la sesión
        when(session.getAttribute("clienteId")).thenReturn(null);

        List<EmpleadoEntity> empleadosMock = new ArrayList<>();

        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaEmpleados(anyInt(), eq(""))).thenReturn(empleadosMock))) {

            servlet.doGet(request, response);

            // Verificar que no se hace llamada al servicio
            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
            verify(empleadoServiceMock, never()).obtenerListaEmpleados(anyInt(), eq(""));

            // Verificar que la lista de empleados sigue vacía
            verify(request).setAttribute("empleados", empleadosMock);

            // Verificar que se hizo el forward a "/empleados.jsp"
            verify(servletContext.getRequestDispatcher("/empleados.jsp")).forward(request, response);
        }
    }
}

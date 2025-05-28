package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.empleados.EmpleadoFormServlet;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.services.EmpleadoServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class EmpleadoFormServletTest {

    private EmpleadoFormServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private ServletContext servletContext;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        servlet = new EmpleadoFormServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        servletContext = mock(ServletContext.class);
        connection = mock(Connection.class);

        ServletConfig servletConfig = mock(ServletConfig.class);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/empleado-form.jsp")).thenReturn(dispatcher);
        when(request.getAttribute("connection")).thenReturn(connection);

        when(servletConfig.getServletContext()).thenReturn(servletContext);

        try {
            servlet.init(servletConfig);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void testDoGetWithValidEmpleadoId() throws ServletException, IOException {
//        int empleadoId = 1;
//        EmpleadoEntity empleado = new EmpleadoEntity();
//        empleado.setEmpleadoId(empleadoId);
//        empleado.setNombre("Juan Pérez");
//
//        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class,
//                (mock, context) -> when(mock.obtenerEmpleado(empleadoId)).thenReturn(Optional.of(empleado)))) {
//
//            when(request.getParameter("empleado-id")).thenReturn(String.valueOf(empleadoId));
//
//            servlet.doGet(request, response);
//
//            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
//            verify(empleadoServiceMock).obtenerEmpleado(empleadoId);
//            verify(request).setAttribute("empleado", empleado);
//            verify(dispatcher).forward(request, response);
//        }
//    }
//
//    @Test
//    public void testDoGetWithInvalidEmpleadoId() throws ServletException, IOException {
//        when(request.getParameter("empleado-id")).thenReturn("invalid");
//
//        servlet.doGet(request, response);
//
//        verify(request).setAttribute(eq("empleado"), argThat(empleado ->
//                empleado instanceof EmpleadoEntity &&
//                        ((EmpleadoEntity) empleado).getEmpleadoId() == 0 // o la lógica que esperes
//        ));
//
//        verify(servletContext.getRequestDispatcher("/empleado-form.jsp")).forward(request, response);
//    }
//
//    @Test
//    public void testDoPostWithValidParameters() throws ServletException, IOException {
//        String empleadoId = "1";
//        String nombre = "Juan Pérez";
//        String sueldo = "30000";
//        String clienteId = "10";
//
//        when(request.getParameter("empleado-id")).thenReturn(empleadoId);
//        when(request.getParameter("nombre")).thenReturn(nombre);
//        when(request.getParameter("sueldo")).thenReturn(sueldo);
//        when(request.getParameter("clienteId")).thenReturn(clienteId);
//        when(request.getContextPath()).thenReturn("");
//
//        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
//            servlet.doPost(request, response);
//
//            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
//            verify(empleadoServiceMock).actualizarGuardarEmpleado(argThat(e ->
//                    e.getEmpleadoId() == 1 &&
//                            "Juan Pérez".equals(e.getNombre()) &&
//                            e.getSueldo() == 30000.0 &&
//                            e.getClienteId() == 10
//            ));
//
//            verify(response).sendRedirect("/empleados");
//        }
//    }
//
//    @Test
//    public void testDoPostWithMissingParameters() throws ServletException, IOException {
//        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
//
//            when(request.getParameter("empleado-id")).thenReturn(null);
//            when(request.getParameter("nombre")).thenReturn("Juan Pérez");
//            when(request.getParameter("sueldo")).thenReturn("30000");
//            when(request.getParameter("clienteId")).thenReturn("10");
//
//            servlet.doPost(request, response);
//
//            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
//            verify(response).sendRedirect(anyString());
//            verify(empleadoServiceMock, never()).actualizarGuardarEmpleado(any());
//        }
//    }
//
//    @Test
//    public void testDoPostWithInvalidSueldoTriggersCatch() throws ServletException, IOException {
//        // Valores válidos excepto sueldo, que será inválido (no numérico)
//        when(request.getParameter("empleado-id")).thenReturn("1");
//        when(request.getParameter("nombre")).thenReturn("Juan Pérez");
//        when(request.getParameter("sueldo")).thenReturn("no-es-un-numero"); // <-- fuerza excepción
//        when(request.getParameter("clienteId")).thenReturn("10");
//        when(request.getContextPath()).thenReturn("");
//
//        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
//            servlet.doPost(request, response);
//
//            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
//
//            // Verificamos que NO se llama al método porque hubo excepción
//            verify(empleadoServiceMock, never()).actualizarGuardarEmpleado(any());
//
//            // Aun así, se hace el redirect (por el diseño actual)
//            verify(response).sendRedirect("/empleados");
//        }
//    }
//
//
//    @Test
//    public void testDoPostWithNullClienteIdOnly() throws ServletException, IOException {
//        when(request.getParameter("empleado-id")).thenReturn("1");
//        when(request.getParameter("nombre")).thenReturn("Juan Pérez");
//        when(request.getParameter("sueldo")).thenReturn("30000");
//        when(request.getParameter("clienteId")).thenReturn(null); // <--- la clave
//        when(request.getContextPath()).thenReturn("");
//
//        try (MockedConstruction<EmpleadoServiceImpl> mocked = mockConstruction(EmpleadoServiceImpl.class)) {
//            servlet.doPost(request, response);
//
//            EmpleadoServiceImpl empleadoServiceMock = mocked.constructed().get(0);
//
//            // Verificamos que NO se llama al método
//            verify(empleadoServiceMock, never()).actualizarGuardarEmpleado(any());
//
//            // Se hace el redirect igual
//            verify(response).sendRedirect("/empleados");
//        }
//    }

}

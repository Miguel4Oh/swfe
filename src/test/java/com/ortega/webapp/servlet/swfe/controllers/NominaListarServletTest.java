package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.nominas.NominaListarServlet;
import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.services.NominaServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class NominaListarServletTest {

    private NominaListarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext context;
    private RequestDispatcher dispatcher;

    private ServletConfig servletConfig;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new NominaListarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        context = mock(ServletContext.class);
        dispatcher = mock(RequestDispatcher.class);
        servletConfig = mock(ServletConfig.class);

        // Configurar mocks
        when(request.getSession()).thenReturn(session);
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/nomina.jsp")).thenReturn(dispatcher);

        // Inicializar el servlet
        servlet.init(servletConfig);
    }

    @Test
    public void testDoGet_WithFullSessionAttributes() throws Exception {
        EjercicioEntity ejercicio = mock(EjercicioEntity.class);
        MesEntity mes = mock(MesEntity.class);
        Connection connection = mock(Connection.class);

        List<NominaEntity> nominas = List.of(new NominaEntity(1, 500.0, 100.0, 50.0, 600.0, 0.0));

        when(request.getAttribute("connection")).thenReturn(connection);
        when(session.getAttribute("clienteId")).thenReturn("10");
        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(ejercicio.getEjercicioFiscalId()).thenReturn(2023);
        when(session.getAttribute("mesSeleccionado")).thenReturn(mes);
        when(mes.getMesId()).thenReturn(6);

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaNominas(any(NominaFormDTO.class))).thenReturn(nominas))) {

            servlet.doGet(request, response);

            verify(request).setAttribute(eq("totales"), anyMap());
            verify(request).setAttribute(eq("quincena"), eq(1));
            verify(request).setAttribute(eq("nominas"), eq(nominas));
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGet_WithoutMesSeleccionado() throws Exception {
        EjercicioEntity ejercicio = mock(EjercicioEntity.class);
        Connection connection = mock(Connection.class);
        List<NominaEntity> nominas = List.of(new NominaEntity(2, 300.0, 50.0, 30.0, 400.0, 0.0));

        when(request.getAttribute("connection")).thenReturn(connection);
        when(session.getAttribute("clienteId")).thenReturn("15");
        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(ejercicio.getEjercicioFiscalId()).thenReturn(2022);
        when(session.getAttribute("mesSeleccionado")).thenReturn(null);

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaNominas(any(NominaFormDTO.class))).thenReturn(nominas))) {

            servlet.doGet(request, response);

            verify(request).setAttribute(eq("totales"), anyMap());
            verify(request).setAttribute(eq("quincena"), eq(1));
            verify(request).setAttribute(eq("nominas"), eq(nominas));
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGet_ThrowsSQLException() throws Exception {
        EjercicioEntity ejercicio = mock(EjercicioEntity.class);
        Connection connection = mock(Connection.class);

        when(request.getAttribute("connection")).thenReturn(connection);
        when(session.getAttribute("clienteId")).thenReturn("20");
        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(ejercicio.getEjercicioFiscalId()).thenReturn(2021);
        when(session.getAttribute("mesSeleccionado")).thenReturn(null);

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaNominas(any(NominaFormDTO.class))).thenThrow(new SQLException("DB error")))) {

            // Espera una RuntimeException al lanzar SQLException
            assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
        }
    }

    @Test
    public void testDoGet_ThrowsSQLException2() throws Exception {
        EjercicioEntity ejercicio = mock(EjercicioEntity.class);
        Connection connection = mock(Connection.class);

        when(request.getAttribute("connection")).thenReturn(connection);
        when(session.getAttribute("clienteId")).thenReturn("20");
        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(null);
        when(ejercicio.getEjercicioFiscalId()).thenReturn(2021);
        when(session.getAttribute("mesSeleccionado")).thenReturn(null);

        try (MockedConstruction<NominaServiceImpl> mocked = mockConstruction(NominaServiceImpl.class,
                (mock, context) -> when(mock.obtenerListaNominas(any(NominaFormDTO.class))).thenThrow(new SQLException("DB error")))) {

            // Espera una RuntimeException al lanzar SQLException
            assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
        }
    }
}
package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.tablasisr.TablaIsrEliminarServlet;
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

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TablaIsrEliminarServletTest {

    private TablaIsrEliminarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection connection;
    private ServletContext servletContext;

    @BeforeEach
    public void setUp() throws ServletException {
        servlet = new TablaIsrEliminarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        connection = mock(Connection.class);
        servletContext = mock(ServletContext.class);

        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getContextPath()).thenReturn("");
        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);
        servlet.init(config);
    }

    @Test
    public void testDoGetWithValidIdAndTableExists() throws ServletException, IOException {
        TablaIsrEntity tablaMock = new TablaIsrEntity();
        tablaMock.setMesTabla("Enero");

        when(request.getParameter("tabla-id")).thenReturn("10");

        try (MockedConstruction<TablaIsrServiceImpl> mocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> {
                    when(mock.obtenerTablaIsr(10L)).thenReturn(Optional.of(tablaMock));
                    doNothing().when(mock).eliminarTablaIsr(10L);
                })) {

            servlet.doGet(request, response);

            TablaIsrServiceImpl service = mocked.constructed().get(0);
            verify(service).eliminarTablaIsr(10L);
            verify(response).sendRedirect("/tablas?nombreTabla=Enero");
        }
    }

    @Test
    public void testDoGetWithInvalidId() throws ServletException, IOException {
        when(request.getParameter("tabla-id")).thenReturn("abc");

        try (MockedConstruction<TablaIsrServiceImpl> mocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> {
                    when(mock.obtenerTablaIsr(0L)).thenReturn(Optional.empty());
                })) {

            servlet.doGet(request, response);

            TablaIsrServiceImpl service = mocked.constructed().get(0);
            verify(service, never()).eliminarTablaIsr(anyLong());
            verify(response, never()).sendRedirect(anyString());
        }
    }

    @Test
    public void testDoGetWithValidIdButTableDoesNotExist() throws ServletException, IOException {
        when(request.getParameter("tabla-id")).thenReturn("123");

        try (MockedConstruction<TablaIsrServiceImpl> mocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> when(mock.obtenerTablaIsr(123L)).thenReturn(Optional.empty()))) {

            servlet.doGet(request, response);

            TablaIsrServiceImpl service = mocked.constructed().get(0);
            verify(service, never()).eliminarTablaIsr(anyLong());
            verify(response, never()).sendRedirect(anyString());
        }
    }

    @Test
    public void testDoGetWithoutParameter() throws ServletException, IOException {
        when(request.getParameter("tabla-id")).thenReturn(null);

        try (MockedConstruction<TablaIsrServiceImpl> mocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> when(mock.obtenerTablaIsr(0L)).thenReturn(Optional.empty()))) {

            servlet.doGet(request, response);

            TablaIsrServiceImpl service = mocked.constructed().get(0);
            verify(service, never()).eliminarTablaIsr(anyLong());
            verify(response, never()).sendRedirect(anyString());
        }
    }
}
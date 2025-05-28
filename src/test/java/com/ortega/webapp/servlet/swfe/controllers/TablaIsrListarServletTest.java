package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.tablasisr.TablaIsrListarServlet;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.services.MesServiceImpl;
import com.ortega.webapp.servlet.swfe.services.TablaIsrServiceImpl;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TablaIsrListarServletTest {

    private TablaIsrListarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private ServletContext servletContext;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        servlet = new TablaIsrListarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        servletContext = mock(ServletContext.class);
        connection = mock(Connection.class);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/tablas.jsp")).thenReturn(dispatcher);
        when(request.getAttribute("connection")).thenReturn(connection);

        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);

        try {
            servlet.init(config);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void nombreNullTest() throws ServletException, IOException {
        when(request.getParameter("nombreTabla")).thenReturn(null);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("tablas"), anyList());
        verify(request).setAttribute(eq("meses"), anyList());
        verify(request).setAttribute(eq("periodoTabla"), any(MesEntity.class));
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void nombreValidoTest() throws ServletException, IOException {
        when(request.getParameter("nombreTabla")).thenReturn("ENERO");

        try (MockedConstruction<TablaIsrServiceImpl> tablaMocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> {
                    when(mock.obtenerListaTablasIsr("ENERO")).thenReturn(List.of(new TablaIsrEntity()));
                });
             MockedConstruction<MesServiceImpl> mesMocked = mockConstruction(MesServiceImpl.class,
                     (mock, context) -> {
                         when(mock.obtenerListaMeses("")).thenReturn(List.of(new MesEntity()));
                         when(mock.obtenerMesPorNombre("ENERO")).thenReturn(new MesEntity());
                     })
        ) {
            servlet.doGet(request, response);

            assertEquals(1, tablaMocked.constructed().size());
            assertEquals(1, mesMocked.constructed().size());

            verify(request).setAttribute(eq("tablas"), anyList());
            verify(request).setAttribute(eq("meses"), anyList());
            verify(request).setAttribute(eq("periodoTabla"), any(MesEntity.class));
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void exceptionTest() throws ServletException, IOException {
        when(request.getParameter("nombreTabla")).thenReturn("ENERO");

        try (MockedConstruction<TablaIsrServiceImpl> tablaMocked = mockConstruction(TablaIsrServiceImpl.class,
                (mock, context) -> {
                    when(mock.obtenerListaTablasIsr("ENERO")).thenThrow(new RuntimeException("Error"));
                });
             MockedConstruction<MesServiceImpl> mesMocked = mockConstruction(MesServiceImpl.class,
                     (mock, context) -> {
                         when(mock.obtenerListaMeses("")).thenReturn(Collections.emptyList());
                         when(mock.obtenerMesPorNombre("ENERO")).thenReturn(new MesEntity());
                     })
        ) {
            servlet.doGet(request, response);

            verify(request).setAttribute(eq("tablas"), anyList());
            verify(request).setAttribute(eq("meses"), anyList());
            verify(request).setAttribute(eq("periodoTabla"), any(MesEntity.class));
            verify(dispatcher).forward(request, response);
        }
    }
}

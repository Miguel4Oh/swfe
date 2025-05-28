package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.clientes.ClienteEliminarServlet;
import com.ortega.webapp.servlet.swfe.services.ClienteServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteEliminarServletTest {

    private ClienteEliminarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private ServletContext servletContext;
    private Connection connection;


    @BeforeEach
    public void setUp() {
        servlet = new ClienteEliminarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        servletContext = mock(ServletContext.class);
        connection = mock(Connection.class);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/clientes")).thenReturn(dispatcher);

        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        try {
            servlet.init(servletConfig);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoGetWithValidParameter() throws ServletException, IOException {
        try (MockedConstruction<ClienteServiceImpl> mocked = mockConstruction(ClienteServiceImpl.class,
                (mock, context) -> {
                    doNothing().when(mock).eliminarCliente(anyInt());
                })) {
            when(request.getParameter("cliente-id")).thenReturn("5");

            servlet.doGet(request, response);

            assertEquals(1, mocked.constructed().size());
            ClienteServiceImpl clienteServiceMock = mocked.constructed().get(0);
            verify(clienteServiceMock).eliminarCliente(5);

            verify(dispatcher).forward(request, response);
        }
    }



    @Test
    public void testDoGetWithInvalidParameter() throws ServletException, IOException {
        try (MockedConstruction<ClienteServiceImpl> mocked = mockConstruction(ClienteServiceImpl.class)) {
            when(request.getParameter("cliente-id")).thenReturn("invalid");

            servlet.doGet(request, response);

            assertEquals(1, mocked.constructed().size());
            ClienteServiceImpl clienteServiceMock = mocked.constructed().get(0);
            verify(clienteServiceMock, never()).eliminarCliente(anyInt());
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithEmptyParameter() throws ServletException, IOException {
        try (MockedConstruction<ClienteServiceImpl> mocked = mockConstruction(ClienteServiceImpl.class)) {
            when(request.getParameter("cliente-id")).thenReturn("");

            servlet.doGet(request, response);

            assertEquals(1, mocked.constructed().size());
            ClienteServiceImpl clienteServiceMock = mocked.constructed().get(0);
            verify(clienteServiceMock, never()).eliminarCliente(anyInt());
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithoutParameter() throws ServletException, IOException {
        try (MockedConstruction<ClienteServiceImpl> mocked = mockConstruction(ClienteServiceImpl.class)) {
            when(request.getParameter("cliente-id")).thenReturn(null);

            servlet.doGet(request, response);

            assertEquals(1, mocked.constructed().size());
            ClienteServiceImpl clienteServiceMock = mocked.constructed().get(0);
            verify(clienteServiceMock, never()).eliminarCliente(anyInt());
            verify(dispatcher).forward(request, response);
        }
    }
}

package com.ortega.webapp.servlet.swfe.controllers;

import com.ortega.webapp.servlet.swfe.controllers.depreciaciones.DepreciacionListarServlet;
import com.ortega.webapp.servlet.swfe.models.*;
import com.ortega.webapp.servlet.swfe.services.ConceptoServiceImpl;
import com.ortega.webapp.servlet.swfe.services.DepreciacionServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class DepreciacionListarServletTest {

    private DepreciacionListarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext servletContext;
    private RequestDispatcher dispatcher;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new DepreciacionListarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        dispatcher = mock(RequestDispatcher.class);
        connection = mock(Connection.class);

        when(request.getSession()).thenReturn(session);
        when(request.getAttribute("connection")).thenReturn(connection);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/depreciaciones.jsp")).thenReturn(dispatcher);

        ServletConfig config = mock(ServletConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);
        servlet.init(config);
    }

    @Test
    public void testDoGetWithAllValidAttributes() throws Exception {
        EjercicioEntity ejercicio = new EjercicioEntity();
        ejercicio.setEjercicioFiscal("2024");

        MesEntity mes = new MesEntity();
        mes.setMesId(4);

        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(session.getAttribute("mesSeleccionado")).thenReturn(mes);
        when(session.getAttribute("clienteId")).thenReturn(1);

        List<DepreciacionEntity> depreciaciones = Collections.emptyList();
        List<ConceptoDepreciacionEntity> conceptos = Collections.emptyList();

        try (
                MockedConstruction<DepreciacionServiceImpl> depMock = mockConstruction(DepreciacionServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaDepreciaciones(1, "2024", 4)).thenReturn(depreciaciones));
                MockedConstruction<ConceptoServiceImpl> conMock = mockConstruction(ConceptoServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaConceptos("")).thenReturn(conceptos))
        ) {
            servlet.doGet(request, response);

            verify(request).setAttribute("depreciaciones", depreciaciones);
            verify(request).setAttribute("conceptos", conceptos);
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithoutEjercicioSeleccionado() throws Exception {
        // "ejercicioSeleccionado" null → anioEjercicioFiscal = ""
        MesEntity mes = new MesEntity();
        mes.setMesId(3);

        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(null);
        when(session.getAttribute("mesSeleccionado")).thenReturn(mes);
        when(session.getAttribute("clienteId")).thenReturn(2);

        try (
                MockedConstruction<DepreciacionServiceImpl> depMock = mockConstruction(DepreciacionServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaDepreciaciones(2, "", 3)).thenReturn(Collections.emptyList()));
                MockedConstruction<ConceptoServiceImpl> conMock = mockConstruction(ConceptoServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaConceptos("")).thenReturn(Collections.emptyList()))
        ) {
            servlet.doGet(request, response);
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGetWithoutMesSeleccionado() throws Exception {
        // mesSeleccionado null → mesSeleccionadoId = 0
        EjercicioEntity ejercicio = new EjercicioEntity();
        ejercicio.setEjercicioFiscal("2023");

        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(session.getAttribute("mesSeleccionado")).thenReturn(null);
        when(session.getAttribute("clienteId")).thenReturn(5);

        try (
                MockedConstruction<DepreciacionServiceImpl> depMock = mockConstruction(DepreciacionServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaDepreciaciones(5, "2023", 0)).thenReturn(Collections.emptyList()));
                MockedConstruction<ConceptoServiceImpl> conMock = mockConstruction(ConceptoServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaConceptos("")).thenReturn(Collections.emptyList()))
        ) {
            servlet.doGet(request, response);
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    public void testDoGetHandlesServiceExceptionGracefully() throws Exception {
        EjercicioEntity ejercicio = new EjercicioEntity();
        ejercicio.setEjercicioFiscal("2022");

        MesEntity mes = new MesEntity();
        mes.setMesId(6);

        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(session.getAttribute("mesSeleccionado")).thenReturn(mes);
        when(session.getAttribute("clienteId")).thenReturn(10);

        try (
                MockedConstruction<DepreciacionServiceImpl> depMock = mockConstruction(DepreciacionServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaDepreciaciones(10, "2022", 6))
                                .thenThrow(new RuntimeException("Service failure")));
                MockedConstruction<ConceptoServiceImpl> conMock = mockConstruction(ConceptoServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaConceptos("")).thenReturn(Collections.emptyList()))
        ) {
            // Ejecutar el doGet
            servlet.doGet(request, response);

            // Verificar que se redirige a la página de error cuando ocurre una excepción
            verify(dispatcher).forward(request, response);  // Se espera que forward sea llamado, aún con la excepción
        }
    }


    @Test
    public void testDoGetWithEjercicioSeleccionado() throws Exception {
        // Preparar un ejercicio seleccionado válido
        EjercicioEntity ejercicio = new EjercicioEntity();
        ejercicio.setEjercicioFiscal("2024");

        // Simulamos también un mes seleccionado
        MesEntity mes = new MesEntity();
        mes.setMesId(5);

        // Mock para los atributos de la sesión
        when(session.getAttribute("ejercicioSeleccionado")).thenReturn(ejercicio);
        when(session.getAttribute("mesSeleccionado")).thenReturn(mes);
        when(session.getAttribute("clienteId")).thenReturn(1);

        // Mock de los servicios
        List<DepreciacionEntity> depreciaciones = Collections.emptyList();
        List<ConceptoDepreciacionEntity> conceptos = Collections.emptyList();

        try (
                MockedConstruction<DepreciacionServiceImpl> depMock = mockConstruction(DepreciacionServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaDepreciaciones(1, "2024", 5)).thenReturn(depreciaciones));
                MockedConstruction<ConceptoServiceImpl> conMock = mockConstruction(ConceptoServiceImpl.class,
                        (mock, context) -> when(mock.obtenerListaConceptos("")).thenReturn(conceptos))
        ) {
            // Llamamos al método doGet del servlet
            servlet.doGet(request, response);

            // Verificamos que los atributos se hayan establecido correctamente
            verify(request).setAttribute("depreciaciones", depreciaciones);
            verify(request).setAttribute("conceptos", conceptos);

            // Verificamos que se haya hecho el forward a la vista correspondiente
            verify(dispatcher).forward(request, response);
        }
    }


}

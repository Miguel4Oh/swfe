package com.ortega.webapp.servlet.swfe.controllers.facturas;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.services.FacturaService;
import com.ortega.webapp.servlet.swfe.services.FacturaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/facturas/archivo")
@MultipartConfig
public class FacturaArchivoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = (Connection) request.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        HttpSession httpSession = request.getSession();

        Part filePart = request.getPart("file");

        FacturaEntity factura = new FacturaEntity();

        try {
            if (filePart != null) {
                if (httpSession.getAttribute("ejercicioSeleccionado") != null && httpSession.getAttribute("mesSeleccionado") != null) {

                    EjercicioEntity ejercicio = (EjercicioEntity) httpSession.getAttribute("ejercicioSeleccionado");
                    MesEntity mes = (MesEntity) httpSession.getAttribute("mesSeleccionado");

                    factura.setEjercicioFiscalId(ejercicio.getEjercicioFiscalId());
                    factura.setMesId(mes.getMesId());
                    factura.setTipoFactura(request.getParameter("tipo"));

                    facturaService.guardarFacturas(filePart, request.getSession().getAttribute("clienteId").toString(), factura);
                    response.getWriter().write("Archivo guardado.");

                } else {
                    response.getWriter().write("Campos no informados");
                }
            } else {
                response.getWriter().write("No se seleccionó un archivo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

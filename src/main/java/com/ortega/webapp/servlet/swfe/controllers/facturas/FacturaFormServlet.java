package com.ortega.webapp.servlet.swfe.controllers.facturas;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity;
import com.ortega.webapp.servlet.swfe.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/facturas/factura-form")
public class FacturaFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        CatalogoService catalogoService = new CatalogoServiceImpl(connection);

        FacturaEntity factura = new FacturaEntity();

        int facturaId = 0;
        CatalogoEntity catalogos = null;
        Optional<CatalogoEntity> catalogosOptional = catalogoService.obtenerCatalogos();

        try {
            System.out.println("facturaId: " + req.getParameter("factura-id"));
            if (req.getParameter("factura-id") != null && !req.getParameter("factura-id").isEmpty()) {
                facturaId = Integer.parseInt(req.getParameter("factura-id"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (facturaId > 0) {
            Optional<FacturaEntity> tablaBd = facturaService.obtenerFactura(facturaId);
            if (tablaBd.isPresent()) {
                factura = tablaBd.get();
            }
        }

        if (catalogosOptional.isPresent()) {
            catalogos = catalogosOptional.get();
        }

        req.setAttribute("factura", factura);
        req.setAttribute("catalogos", catalogos);
        getServletContext().getRequestDispatcher("/factura-form.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        FacturaService facturaService = new FacturaServiceImpl(connection);
        FacturaEntity factura = new FacturaEntity();
        System.out.println("serie: " + req.getParameter("serie"));

        factura.setFacturaId(Integer.parseInt(req.getParameter("facturaId")));
        factura.setFolioFactura(req.getParameter("folioFactura"));
        factura.setFechaFactura(Date.valueOf(LocalDate.parse(req.getParameter("fechaFactura"))));

        if(req.getParameter("fechaCobranza") != null && !req.getParameter("fechaCobranza").isEmpty()) {
            factura.setFechaCobranza(Date.valueOf(LocalDate.parse(req.getParameter("fechaCobranza"))));
        }

        factura.setRfcEmisor(req.getParameter("rfcEmisor"));
        factura.setNombreEmisor(req.getParameter("nombreEmisor"));
        factura.setSubtotalFactura(Double.parseDouble(req.getParameter("subtotalFactura")));
        factura.setIvaFactura(Double.parseDouble(req.getParameter("ivaFactura")));

        if (req.getParameter("isrRetenido") != null && !req.getParameter("isrRetenido").isEmpty()) {
            factura.setIsrRetenido(Double.parseDouble(req.getParameter("isrRetenido")));
        }

        if (req.getParameter("ivaRetenido") != null && !req.getParameter("ivaRetenido").isEmpty()) {
            factura.setIvaRetenido(Double.parseDouble(req.getParameter("ivaRetenido")));
        }

        factura.setTotalFactura(Double.parseDouble(req.getParameter("totalFactura")));
        factura.setUuidFactura(req.getParameter("uuidFactura"));

        if (req.getParameter("tcFactura") != null && !req.getParameter("tcFactura").isEmpty()) {
            factura.setTcFactura(Double.parseDouble(req.getParameter("tcFactura")));
        }

        factura.setUsoCfdiReceptor(req.getParameter("usoCfdiReceptor"));
        factura.setClienteId(Integer.parseInt(req.getParameter("clienteId")));

        if (req.getParameter("tipoFactura") != null && !req.getParameter("tipoFactura").isBlank()) {
            factura.setTipoFactura(req.getParameter("tipoFactura"));
        }
        if (req.getParameter("serie") != null && !req.getParameter("serie").isEmpty()) {
            factura.setSerie(req.getParameter("serie"));
        }
        if(req.getParameter("moneda") != null && !req.getParameter("moneda").isEmpty()) {
            factura.setMoneda(req.getParameter("moneda"));
        }
        if(req.getParameter("estatus") != null && !req.getParameter("estatus").isEmpty()) {
            factura.setEstatus(req.getParameter("estatus"));
        }
        if(req.getParameter("efecto") != null && !req.getParameter("efecto").isEmpty()) {
            factura.setEfecto(req.getParameter("efecto"));
        }
        if(req.getParameter("metodoPago") != null && !req.getParameter("metodoPago").isEmpty()) {
            factura.setMetodoPago(req.getParameter("metodoPago"));
        }
        if(req.getParameter("formaPago") != null && !req.getParameter("formaPago").isEmpty()) {
            factura.setFormaPago(req.getParameter("formaPago"));
        }
        if(req.getParameter("formaPagoDeducible") != null && !req.getParameter("formaPagoDeducible").isEmpty()) {
            factura.setFormaPagoDeducible(req.getParameter("formaPagoDeducible"));
        }

        facturaService.actualizarGuardarFactura(factura);

        resp.sendRedirect(req.getContextPath() + "/facturas");
    }
}

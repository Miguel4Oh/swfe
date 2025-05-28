package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FacturaService {
    List<FacturaEntity> obtenerListaFacturas (int clienteId, int mesId, String filtroBusqueda);
    Optional<FacturaEntity> obtenerFactura (int facturaId);
    void actualizarGuardarFactura (FacturaEntity factura);
    void eliminarFactura (int facturaId);
    void guardarFacturas(Part filePart, String clienteid, FacturaEntity factura) throws IOException, SQLException;
    double obtenerSubtotalIngresos(ImpuestoEntity factura);
    double obtenerSubtotalDeducciones(ImpuestoEntity factura);
    double obtenerIvaIngresos(ImpuestoEntity factura);
    double obtenerIvaRetenidoIngresos(ImpuestoEntity factura);
    double obtenerIvaEgresos(ImpuestoEntity factura);
    double obtenerIsrRetenidoIngresos(ImpuestoEntity factura);
}

package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.EncabezadoEntity;

import java.sql.SQLException;
import java.util.List;

public interface FacturaRepository <T>{
    List<T> listarFacturas(int clienteId, int mesId, String filtroBusqueda) throws SQLException;
    T obtenerFactura (int facturaId) throws SQLException;
    void actualizarFactura (FacturaEntity factura) throws SQLException;
    void guardarFactura (FacturaEntity factura) throws SQLException;
    void eliminarFactura(int facturaId) throws SQLException;
    List<EncabezadoEntity> obtenerEncabezados() throws SQLException;
    T obtenerFacturaPorUUID(String uuid) throws SQLException;
    double obtenerSubtotalIngresos(ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerSubtotalDeducciones(ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerIvaIngresos (ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerIvaRetenidoIngresos (ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerIvaAcreditableEgresos (ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerIsrRetenidoIngresos (ImpuestoEntity impuestoEntity) throws SQLException;
}

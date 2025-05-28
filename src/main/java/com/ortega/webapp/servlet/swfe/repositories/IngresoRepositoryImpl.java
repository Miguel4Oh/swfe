package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngresoRepositoryImpl implements IngresoRepository {
    private final Connection connection;

    public IngresoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FacturaEntity> listarIngresos(FacturaEntity facturaEntity) throws SQLException {
        List<FacturaEntity> listaFacturas = new ArrayList<>();

        int clienteId = facturaEntity.getClienteId();
        int mesId = facturaEntity.getMesId();
        int ejercicioFiscalId = facturaEntity.getEjercicioFiscalId();
        int tipoFacturaId = 1;

        System.out.println("clienteId: " + clienteId);
        System.out.println("mesId: " + mesId);
        System.out.println("ejercicioFiscalId: " + ejercicioFiscalId);

        String query = "SELECT fac.factura_id, fac.folio_factura, fac.fecha_factura, fac.fecha_cobranza, fac.rfc_emisor, " +
                "fac.nombre_emisor, fac.subtotal_factura, fac.iva_factura, fac.isr_retenido, fac.iva_retenido, fac.total_factura, " +
                "fac.uuid_factura, fac.tc_factura, fac.uso_cfdi_receptor, fac.cliente_id, fac.ejercicio_fiscal_id, mes_id, " +
                "tf.nombre_tipo_factura, se.nombre_serie, mo.nombre_moneda, es.nombre_estatus, ef.nombre_efecto, " +
                "mp.nombre_metodo_pago, fp.nombre_forma_pago, fpd.nombre_forma_pago_deducible FROM facturas fac " +
                "left join tipo_factura tf on fac.tipo_factura_id = tf.tipo_factura_id " +
                "left join serie se on fac.serie_id = se.serie_id left join moneda mo on fac.moneda_id = mo.moneda_id " +
                "left join estatus es on fac.estatus_id = es.estatus_id left join efecto ef on fac.efecto_id = ef.efecto_id " +
                "left join metodo_pago mp on fac.metodo_pago_id = mp.metodo_pago_id " +
                "left join forma_pago fp on fac.forma_pago_id = fp.forma_pago_id " +
                "left join forma_pago_deducible fpd on fac.forma_pago_deducible_id = fpd.forma_pago_deducible_id " +
                "WHERE cliente_id = ? AND MONTH(fecha_cobranza) = ? AND YEAR(fecha_cobranza) = ? AND fac.tipo_factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, mesId);
            statement.setInt(3, ejercicioFiscalId);
            statement.setInt(4, tipoFacturaId);

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    FacturaEntity factura = mapearfactura(resultSet);
                    listaFacturas.add(factura);
                }
            }
        }

        System.out.println("salida lista facturas: " + listaFacturas);
        return listaFacturas;
    }

    @Override
    public void saldarIngreso(int ingresoId) throws SQLException {

        String query = "UPDATE facturas SET tipo_factura_id = 2 WHERE factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ingresoId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<FacturaEntity> listarIngresosFiltro(int clienteId, FacturaEntity facturaEntity, String filtro) throws SQLException {
        List<FacturaEntity> listaFacturas = new ArrayList<>();

        int mesId = facturaEntity.getMesId();
        int ejercicioFiscalId = facturaEntity.getEjercicioFiscalId();

        String query = "SELECT fac.factura_id, fac.folio_factura, fac.fecha_factura, fac.fecha_cobranza, fac.rfc_emisor, " +
                "fac.nombre_emisor, fac.subtotal_factura, fac.iva_factura, fac.isr_retenido, fac.iva_retenido, fac.total_factura, " +
                "fac.uuid_factura, fac.tc_factura, fac.uso_cfdi_receptor, fac.cliente_id, fac.ejercicio_fiscal_id, mes_id, " +
                "tf.nombre_tipo_factura, se.nombre_serie, mo.nombre_moneda, es.nombre_estatus, ef.nombre_efecto, " +
                "mp.nombre_metodo_pago, fp.nombre_forma_pago, fpd.nombre_forma_pago_deducible FROM facturas fac " +
                "left join tipo_factura tf on fac.tipo_factura_id = tf.tipo_factura_id " +
                "left join serie se on fac.serie_id = se.serie_id left join moneda mo on fac.moneda_id = mo.moneda_id " +
                "left join estatus es on fac.estatus_id = es.estatus_id left join efecto ef on fac.efecto_id = ef.efecto_id " +
                "left join metodo_pago mp on fac.metodo_pago_id = mp.metodo_pago_id " +
                "left join forma_pago fp on fac.forma_pago_id = fp.forma_pago_id " +
                "left join forma_pago_deducible fpd on fac.forma_pago_deducible_id = fpd.forma_pago_deducible_id " +
                "WHERE cliente_id = ? AND (folio_factura LIKE ? OR nombre_emisor LIKE ? OR rfc_emisor LIKE ?) " +
                "AND MONTH(fecha_cobranza) = ? AND YEAR(fecha_cobranza) = ? AND fac.tipo_factura_id = 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setString(2, "%" + filtro + "%");
            statement.setString(3, "%" + filtro + "%");
            statement.setString(4, "%" + filtro + "%");
            statement.setInt(5, mesId);
            statement.setInt(6, ejercicioFiscalId);

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    FacturaEntity factura = mapearfactura(resultSet);
                    listaFacturas.add(factura);
                }
            }
        }

        return listaFacturas;
    }

    private FacturaEntity mapearfactura(ResultSet resultSet) throws SQLException {
        System.out.println("mapearfactura");
        FacturaEntity factura = new FacturaEntity();

        factura.setFacturaId(resultSet.getInt("factura_id"));
        factura.setFolioFactura(resultSet.getString("folio_factura"));
        factura.setFechaFactura(resultSet.getDate("fecha_factura"));
        factura.setFechaCobranza(resultSet.getDate("fecha_cobranza"));
        factura.setRfcEmisor(resultSet.getString("rfc_emisor"));
        factura.setNombreEmisor(resultSet.getString("nombre_emisor"));
        factura.setSubtotalFactura(resultSet.getDouble("subtotal_factura"));
        factura.setIvaFactura(resultSet.getDouble("iva_factura"));
        factura.setIsrRetenido(resultSet.getDouble("isr_retenido"));
        factura.setIvaRetenido(resultSet.getDouble("iva_retenido"));
        factura.setTotalFactura(resultSet.getDouble("total_factura"));
        factura.setTcFactura(resultSet.getDouble("tc_factura"));
        factura.setUsoCfdiReceptor(resultSet.getString("uso_cfdi_receptor"));
        factura.setUuidFactura(resultSet.getString("uuid_factura"));
        factura.setClienteId(resultSet.getInt("cliente_id"));
        factura.setTipoFactura(resultSet.getString("nombre_tipo_factura"));
        factura.setSerie(resultSet.getString("nombre_serie"));
        factura.setMoneda(resultSet.getString("nombre_moneda"));
        factura.setEstatus(resultSet.getString("nombre_estatus"));
        factura.setEfecto(resultSet.getString("nombre_efecto"));
        factura.setMetodoPago(resultSet.getString("nombre_metodo_pago"));
        factura.setFormaPago(resultSet.getString("nombre_forma_pago"));
        factura.setFormaPagoDeducible(resultSet.getString("nombre_forma_pago_deducible"));


        return factura;
    }
}

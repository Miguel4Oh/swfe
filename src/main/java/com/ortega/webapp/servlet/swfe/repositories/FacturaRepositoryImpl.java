package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.catalogos.EncabezadoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepositoryImpl implements FacturaRepository<FacturaEntity> {
    private final Connection connection;

    public FacturaRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<FacturaEntity> listarFacturas(int clienteId, int mesId, String filtroBusqueda) throws SQLException {
        List<FacturaEntity> listaFacturas = new ArrayList<>();

        String query = "SELECT fac.factura_id, fac.folio_factura, fac.fecha_factura, fac.fecha_cobranza, fac.rfc_emisor, " +
                "fac.nombre_emisor, fac.subtotal_factura, fac.iva_factura, fac.isr_retenido, fac.iva_retenido, fac.total_factura, " +
                "fac.uuid_factura, fac.tc_factura, fac.uso_cfdi_receptor, fac.cliente_id, " +
                "tf.nombre_tipo_factura, se.nombre_serie, mo.nombre_moneda, es.nombre_estatus, ef.nombre_efecto, " +
                "mp.nombre_metodo_pago, fp.nombre_forma_pago, fpd.nombre_forma_pago_deducible FROM facturas fac " +
                "left join tipo_factura tf on fac.tipo_factura_id = tf.tipo_factura_id " +
                "left join serie se on fac.serie_id = se.serie_id left join moneda mo on fac.moneda_id = mo.moneda_id " +
                "left join estatus es on fac.estatus_id = es.estatus_id left join efecto ef on fac.efecto_id = ef.efecto_id " +
                "left join metodo_pago mp on fac.metodo_pago_id = mp.metodo_pago_id " +
                "left join forma_pago fp on fac.forma_pago_id = fp.forma_pago_id " +
                "left join forma_pago_deducible fpd on fac.forma_pago_deducible_id = fpd.forma_pago_deducible_id " +
                "WHERE cliente_id = ? AND mes_id = ? AND (folio_factura LIKE ? OR nombre_emisor LIKE ? OR rfc_emisor LIKE ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, mesId);
            statement.setString(3, "%" + filtroBusqueda + "%");
            statement.setString(4, "%" + filtroBusqueda + "%");
            statement.setString(5, "%" + filtroBusqueda + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FacturaEntity factura = mapearfactura(resultSet);
                    listaFacturas.add(factura);
                }
            }
        }

        System.out.println("listaFacturas: " + listaFacturas);
        return listaFacturas;
    }

    @Override
    public FacturaEntity obtenerFactura(int facturaId) throws SQLException {
        FacturaEntity factura = null;

        String query = "SELECT * FROM facturas WHERE factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(facturaId));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    factura = mapearfacturaTablaOriginal(resultSet);
                }
            }
        }
        System.out.println("factura: " + factura);
        return factura;
    }

    private FacturaEntity mapearfacturaTablaOriginal(ResultSet resultSet) throws SQLException {
        System.out.println("entro en mapear factura tabla original");
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
        factura.setUuidFactura(resultSet.getString("uuid_factura"));
        factura.setTcFactura(resultSet.getDouble("tc_factura"));
        factura.setUsoCfdiReceptor(resultSet.getString("uso_cfdi_receptor"));
        factura.setClienteId(resultSet.getInt("cliente_id"));
        factura.setTipoFactura(resultSet.getString("tipo_factura_id"));
        factura.setSerie(resultSet.getString("serie_id"));
        factura.setMoneda(resultSet.getString("moneda_id"));
        factura.setEstatus(resultSet.getString("estatus_id"));
        factura.setEfecto(resultSet.getString("efecto_id"));
        factura.setMetodoPago(resultSet.getString("metodo_pago_id"));
        factura.setFormaPago(resultSet.getString("forma_pago_id"));
        factura.setFormaPagoDeducible(resultSet.getString("forma_pago_deducible_id"));

        System.out.println("factura: " + factura);
        return factura;
    }

    @Override
    public void actualizarFactura(FacturaEntity factura) throws SQLException {
        String query = "UPDATE facturas SET folio_factura = ?, fecha_factura = ?, fecha_cobranza = ?, rfc_emisor = ?, " +
                "nombre_emisor = ?, subtotal_factura = ?, iva_factura = ?, isr_retenido = ?, iva_retenido = ?, total_factura = ?, " +
                "uuid_factura = ?, tc_factura = ?, uso_cfdi_receptor = ?, tipo_factura_id = ?, serie_id = ?, moneda_id = ?, " +
                "estatus_id = ?, efecto_id = ?, metodo_pago_id = ?, forma_pago_id = ?, forma_pago_deducible_id = ?, " +
                "mes_cobro_id = ? WHERE factura_id = ?";


        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, factura.getFolioFactura());
            statement.setDate(2, factura.getFechaFactura());
            statement.setDate(3, factura.getFechaCobranza());
            statement.setString(4, factura.getRfcEmisor());
            statement.setString(5, factura.getNombreEmisor());
            statement.setDouble(6, factura.getSubtotalFactura());
            statement.setDouble(7, factura.getIvaFactura());
            statement.setDouble(8, factura.getIsrRetenido());
            statement.setDouble(9, factura.getIvaRetenido());
            statement.setDouble(10, factura.getTotalFactura());
            statement.setString(11, factura.getUuidFactura());
            statement.setDouble(12, factura.getTcFactura());
            statement.setString(13, factura.getUsoCfdiReceptor());
            statement.setString(14, factura.getTipoFactura());
            statement.setString(15, factura.getSerie());
            statement.setString(16, factura.getMoneda());
            statement.setString(17, factura.getEstatus());
            statement.setString(18, factura.getEfecto());
            statement.setString(19, factura.getMetodoPago());
            statement.setString(20, factura.getFormaPago());
            statement.setString(21, factura.getFormaPagoDeducible());
            statement.setInt(22, factura.getMesCobroId());
            statement.setInt(23, factura.getFacturaId());

            statement.executeUpdate();
        }
        System.out.println("salio de actualizar factura");
    }

    @Override
    public void guardarFactura(FacturaEntity factura) throws SQLException {
        System.out.println("entro en guardar factura");

        String query = "INSERT INTO facturas (folio_factura, fecha_factura, fecha_cobranza, rfc_emisor, " +
                "nombre_emisor, subtotal_factura, iva_factura, isr_retenido, iva_retenido, total_factura, " +
                "uuid_factura, tc_factura, uso_cfdi_receptor, cliente_id, tipo_factura_id, serie_id, moneda_id, " +
                "estatus_id, efecto_id, metodo_pago_id, forma_pago_id, forma_pago_deducible_id, ejercicio_fiscal_id, " +
                "mes_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        System.out.println("Factura: " + factura);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try {
                statement.setString(1, factura.getFolioFactura());
                statement.setDate(2, factura.getFechaFactura());
                statement.setDate(3, factura.getFechaCobranza());
                statement.setString(4, factura.getRfcEmisor());
                statement.setString(5, factura.getNombreEmisor());
                statement.setDouble(6, factura.getSubtotalFactura());
                statement.setDouble(7, factura.getIvaFactura());
                statement.setDouble(8, factura.getIsrRetenido());
                statement.setDouble(9, factura.getIvaRetenido());
                statement.setDouble(10, factura.getTotalFactura());
                statement.setString(11, factura.getUuidFactura());
                statement.setDouble(12, factura.getTcFactura());
                statement.setString(13, factura.getUsoCfdiReceptor());
                statement.setInt(14, factura.getClienteId());
                statement.setString(15, factura.getTipoFactura());
                statement.setString(16, factura.getSerie());
                statement.setString(17, factura.getMoneda());
                statement.setString(18, factura.getEstatus());
                statement.setString(19, factura.getEfecto());
                statement.setString(20, factura.getMetodoPago());
                statement.setString(21, factura.getFormaPago());
                statement.setString(22, factura.getFormaPagoDeducible());
                statement.setInt(23, factura.getEjercicioFiscalId());
                statement.setInt(24, factura.getMesId());

            }catch (Exception e){
                System.out.println("Error al insertar factura: " + e);
            }

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarFactura(int facturaId) throws SQLException {
        String query = "DELETE FROM facturas WHERE factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, facturaId);
            statement.executeUpdate();
        }
    }

    private FacturaEntity mapearfactura(ResultSet resultSet) throws SQLException {
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
        factura.setSerie(resultSet.getString("nombre_serie") != null ? resultSet.getString("nombre_serie") : "");
        factura.setMoneda(resultSet.getString("nombre_moneda") != null ? resultSet.getString("nombre_moneda") : "XXX");
        factura.setEstatus(resultSet.getString("nombre_estatus"));
        factura.setEfecto(resultSet.getString("nombre_efecto"));
        factura.setMetodoPago(resultSet.getString("nombre_metodo_pago") != null ? resultSet.getString("nombre_metodo_pago") : "");
        factura.setFormaPago(resultSet.getString("nombre_forma_pago") != null ? resultSet.getString("nombre_forma_pago") : "");
        factura.setFormaPagoDeducible(resultSet.getString("nombre_forma_pago_deducible"));

        return factura;
    }

    @Override
    public List<EncabezadoEntity> obtenerEncabezados() throws SQLException {
        List<EncabezadoEntity> encabezados = new ArrayList<>();

        String query = "SELECT nombre_catalogo_encabezado, encabezado_especial FROM catalogo_encabezados";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    EncabezadoEntity encabezado = new EncabezadoEntity();
                    encabezado.setNombreEncabezado(resultSet.getString("nombre_catalogo_encabezado"));
                    encabezado.setEsEpecial(resultSet.getBoolean("encabezado_especial"));
                    encabezados.add(encabezado);
                }

                return encabezados;
            }
        }
    }

    @Override
    public FacturaEntity obtenerFacturaPorUUID(String uuid) throws SQLException {
        System.out.println("entro en obtener factura por uuid");
        FacturaEntity factura = null;

        String query = "SELECT * FROM facturas WHERE uuid_factura = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(uuid));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    factura = mapearfacturaTablaOriginal(resultSet);
                }
            }
        }
        return factura;
    }

    @Override
    public double obtenerSubtotalIngresos(ImpuestoEntity impuestoEntity) throws SQLException {
        double subtotalIngresos = 0;

        String query = "SELECT SUM(subtotal_factura) FROM facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND ejercicio_fiscal_id = ? AND tipo_factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());
            statement.setInt(4, 1);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subtotalIngresos = resultSet.getDouble(1);
                }
            }
        }

        return subtotalIngresos;
    }

    @Override
    public double obtenerSubtotalDeducciones(ImpuestoEntity impuestoEntity) throws SQLException {
        double subtotalDeducciones = 0;

        String query = "SELECT SUM(subtotal_factura) FROM facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND ejercicio_fiscal_id = ? AND tipo_factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());
            statement.setInt(4, 2);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subtotalDeducciones = resultSet.getDouble(1);
                }
            }
        }

        return subtotalDeducciones;
    }

    @Override
    public double obtenerIvaIngresos(ImpuestoEntity impuestoEntity) throws SQLException {
        double ivaIngresos = 0;

        String query = "SELECT SUM(iva_factura) from facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND ejercicio_fiscal_id = ? AND tipo_factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());
            statement.setInt(4, 1);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ivaIngresos = resultSet.getDouble(1);
                }
            }
        }

        return ivaIngresos;
    }

    @Override
    public double obtenerIvaRetenidoIngresos(ImpuestoEntity impuestoEntity) throws SQLException {
        double ivaRetenidoIngresos = 0;

        String query = "SELECT SUM(iva_retenido) FROM facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND tipo_factura_id = 1 AND ejercicio_fiscal_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ivaRetenidoIngresos = resultSet.getDouble(1);
                }
            }
        }

        return ivaRetenidoIngresos;
    }

    @Override
    public double obtenerIvaAcreditableEgresos(ImpuestoEntity impuestoEntity) throws SQLException {
        double ivaAcreditableEgresos = 0;

        String query = "SELECT SUM(iva_factura) FROM facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND ejercicio_fiscal_id = ? AND tipo_factura_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());
            statement.setInt(4, 2);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ivaAcreditableEgresos = resultSet.getDouble(1);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return ivaAcreditableEgresos;
    }

    @Override
    public double obtenerIsrRetenidoIngresos(ImpuestoEntity impuestoEntity) throws SQLException {
        double isrRetenidoIngresos = 0;

        String query = "SELECT SUM(isr_retenido) FROM facturas WHERE cliente_id = ? AND mes_cobro_id = ? AND ejercicio_fiscal_id = ? AND tipo_factura_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, impuestoEntity.getClienteId());
            statement.setInt(2, impuestoEntity.getMesId());
            statement.setInt(3, impuestoEntity.getEjercicioId());
            statement.setInt(4, 1);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    isrRetenidoIngresos = resultSet.getDouble(1);
                }
            }
        }
        return isrRetenidoIngresos;
    }
}

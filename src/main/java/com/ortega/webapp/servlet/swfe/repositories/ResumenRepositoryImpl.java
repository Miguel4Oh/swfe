package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.resumen.IngresosEgresosEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResumenRepositoryImpl implements ResumenRepository {
    private final Connection connection;

    public ResumenRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<String, Double> obtenerIngresos(int clienteId, int ejercicioId, int mesId) throws SQLException {
        Map<String, Double> ingresosEgresosMap = new HashMap<>();

        String query = "SELECT SUM(subtotal_factura) AS ingresos, SUM(iva_factura) AS iva_ingresos FROM facturas WHERE tipo_factura_id = 1 " +
                "AND cliente_id = ? AND ejercicio_fiscal_id = ? AND mes_cobro_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, ejercicioId);
            statement.setInt(3, mesId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ingresosEgresosMap.put("ingresos", resultSet.getDouble("ingresos"));
                    ingresosEgresosMap.put("iva_ingresos", resultSet.getDouble("iva_ingresos"));
                }
            }
        }

        System.out.println("ingresosEgresosMap: --ingresos " + ingresosEgresosMap);
        return ingresosEgresosMap;
    }

    @Override
    public Map<String, Double> obtenerEgresos(int clienteId, int ejercicioId, int mesId) throws SQLException {
        Map<String, Double> ingresosEgresosMap = new HashMap<>();

        String query = "SELECT SUM(subtotal_factura) AS egresos, SUM(iva_factura) AS iva_egresos FROM facturas WHERE tipo_factura_id = 2 " +
                "AND cliente_id = ? AND ejercicio_fiscal_id = ? AND mes_cobro_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, ejercicioId);
            statement.setInt(3, mesId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ingresosEgresosMap.put("egresos", resultSet.getDouble("egresos"));
                    ingresosEgresosMap.put("iva_egresos", resultSet.getDouble("iva_egresos"));
                }
            }
        }

        System.out.println("ingresosEgresosMap: --egresos " + ingresosEgresosMap);
        return ingresosEgresosMap;
    }

    @Override
    public Map<String, Double> obtnerIvaIngresos(int clienteId, int ejercicioId, int mesId) throws SQLException {
        Map<String, Double> ingresosEgresosMap = new HashMap<>();

        String query = "SELECT SUM(iva_factura) AS iva_ingresos FROM facturas WHERE tipo_factura_id = 1 " +
                "AND cliente_id = ? AND ejercicio_fiscal_id = ? AND mes_cobro_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, ejercicioId);
            statement.setInt(3, mesId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ingresosEgresosMap.put("iva_ingresos", resultSet.getDouble("iva_ingresos"));
                }
            }
        }

        return ingresosEgresosMap;
    }

    @Override
    public Map<String, Double> obtnerIvaEgresos(int clienteId, int ejercicioId, int mesId) throws SQLException {
        Map<String, Double> ingresosEgresosMap = new HashMap<>();

        String query = "SELECT SUM(iva_factura) AS iva_egresos FROM facturas WHERE tipo_factura_id = 2 " +
                "AND cliente_id = ? AND ejercicio_fiscal_id = ? AND mes_cobro_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, ejercicioId);
            statement.setInt(3, mesId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ingresosEgresosMap.put("iva_egresos", resultSet.getDouble("iva_egresos"));
                }
            }
        }

        return ingresosEgresosMap;
    }
}

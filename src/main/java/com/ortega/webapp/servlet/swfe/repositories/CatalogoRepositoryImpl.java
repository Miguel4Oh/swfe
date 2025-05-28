package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoRepositoryImpl implements CatalogoRepository<CatalogoEntity> {
    private final Connection connection;

    public CatalogoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CatalogoEntity listarCatalogos() throws SQLException {
        CatalogoEntity catalogo = new CatalogoEntity();

        catalogo.setTiposFactura(listarTipoFactura());
        catalogo.setSeries(listarSeries());
        catalogo.setMonedas(listarMonedas());
        catalogo.setEstatus(listarEstatus());
        catalogo.setEfectos(listarEfectos());
        catalogo.setMetodosPago(listarMetodosPago());
        catalogo.setFormasPago(listarFormasPago());
        catalogo.setFormasPagoDeducible(listarFormasPagoDeducible());

        return catalogo;
    }

    private Map<String, Object> listarTipoFactura() throws SQLException {
        Map<String, Object> tipoFactura = new HashMap<>();

        String query = "SELECT * FROM tipo_factura";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tipoFactura.put(resultSet.getString("tipo_factura_id"), resultSet.getString("nombre_tipo_factura"));
                }
            }
        }
        return tipoFactura;
    }

    private Map<String, Object> listarSeries() throws SQLException {
        Map<String, Object> serie = new HashMap<>();

        String query = "SELECT * FROM serie";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    serie.put(resultSet.getString("serie_id"), resultSet.getString("nombre_serie"));
                }
            }
        }
        return serie;
    }

    private Map<String, Object> listarMonedas() throws SQLException {
        Map<String, Object> moneda = new HashMap<>();

        String query = "SELECT * FROM moneda";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    moneda.put(resultSet.getString("moneda_id"), resultSet.getString("nombre_moneda"));
                }
            }
        }
        return moneda;
    }

    private Map<String, Object> listarEstatus() throws SQLException {
        Map<String, Object> estatus = new HashMap<>();

        String query = "SELECT * FROM estatus";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    estatus.put(resultSet.getString("estatus_id"), resultSet.getString("nombre_estatus"));
                }
            }
        }
        return estatus;
    }

    private Map<String, Object> listarEfectos() throws SQLException {
        Map<String, Object> efecto = new HashMap<>();

        String query = "SELECT * FROM efecto";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    efecto.put(resultSet.getString("efecto_id"), resultSet.getString("nombre_efecto"));
                }
            }
        }
        return efecto;
    }

    private Map<String, Object> listarMetodosPago() throws SQLException {
        Map<String, Object> metodoPago = new HashMap<>();

        String query = "SELECT * FROM metodo_pago";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    metodoPago.put(resultSet.getString("metodo_pago_id"), resultSet.getString("nombre_metodo_pago"));
                }
            }
        }
        return metodoPago;
    }

    private Map<String, Object> listarFormasPago() throws SQLException {
        Map<String, Object> formaPago = new HashMap<>();

        String query = "SELECT * FROM forma_pago";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    formaPago.put(resultSet.getString("forma_pago_id"), resultSet.getString("nombre_forma_pago"));
                }
            }
        }
        return formaPago;
    }

    private Map<String, Object> listarFormasPagoDeducible() throws SQLException {
        Map<String, Object> formaPagoDeducible = new HashMap<>();

        String query = "SELECT * FROM forma_pago_deducible";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    formaPagoDeducible.put(resultSet.getString("forma_pago_deducible_id"), resultSet.getString("nombre_forma_pago_deducible"));
                }
            }
        }
        return formaPagoDeducible;
    }
}

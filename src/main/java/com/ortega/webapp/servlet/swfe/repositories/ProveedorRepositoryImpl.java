package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepositoryImpl implements ProveedorRepository<ProveedorEntity> {
    private final Connection connection;

    public ProveedorRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ProveedorEntity> listarProveedores(int clienteId, String filtroBusqueda) throws SQLException {
        List<ProveedorEntity> proveedorLista = new ArrayList<>();

        String query = "SELECT * FROM proveedores WHERE cliente_id = ? AND (rfc_proveedor LIKE ? OR nombre_proveedor LIKE ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setString(2, "%" + filtroBusqueda + "%");
            statement.setString(3, "%" + filtroBusqueda + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ProveedorEntity proveedor = mapearProveedor(resultSet);
                    proveedorLista.add(proveedor);
                }
            }
        }
        return proveedorLista;
    }

    @Override
    public ProveedorEntity obtenerProveedor(int proveedorId) throws SQLException {
        ProveedorEntity proveedor = null;

        String query = "SELECT * FROM proveedores WHERE proveedor_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, proveedorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    proveedor = mapearProveedor(resultSet);
                }
            }
        }
        return proveedor;
    }

    @Override
    public void actualizarProveedor(ProveedorEntity proveedor) throws SQLException {

        String query = "UPDATE proveedores SET rfc_proveedor = ?, nombre_proveedor = ? WHERE proveedor_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(proveedor.getRfcProveedor()));
            statement.setString(2, String.valueOf(proveedor.getNombreProveedor()));
            statement.setInt(3, proveedor.getProveedorId());

            statement.executeUpdate();
        }
    }

    @Override
    public void guardarProveedor(ProveedorEntity proveedor) throws SQLException {
        String query = "INSERT INTO proveedores (rfc_proveedor, nombre_proveedor, cliente_id) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(proveedor.getRfcProveedor()));
            statement.setString(2, String.valueOf(proveedor.getNombreProveedor()));
            statement.setInt(3, proveedor.getClienteId());

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarProveedor(int proveedorId) throws SQLException {
        String query = "DELETE FROM proveedores WHERE proveedor_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, proveedorId);
            statement.executeUpdate();
            System.out.println("Se ejecuto query");
        }
    }

    private ProveedorEntity mapearProveedor(ResultSet resultSet) throws SQLException {
        ProveedorEntity proveedor = new ProveedorEntity();

        proveedor.setProveedorId(resultSet.getInt("proveedor_id"));
        proveedor.setRfcProveedor(resultSet.getString("rfc_proveedor"));
        proveedor.setNombreProveedor(resultSet.getString("nombre_proveedor"));
        proveedor.setClienteId(resultSet.getInt("cliente_id"));

        return proveedor;
    }
}

package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository<ClienteEntity> {
    private final Connection connection;

    public ClienteRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ClienteEntity> listarClientes(String usuario, String userRole, String filtroBusqueda) throws SQLException {
        List<ClienteEntity> clientes = new ArrayList<>();

        String query;

        if (!userRole.equals("1")) {
            query = "SELECT * FROM clientes WHERE usuario_id = ? AND (nombre_cliente LIKE ? OR rfc_cliente LIKE ? OR razon_social LIKE ?)";
        } else {
            query = "SELECT * FROM clientes WHERE (nombre_cliente LIKE ? OR rfc_cliente LIKE ? OR razon_social LIKE ?)";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (!userRole.equals("1")) {
                statement.setString(1, usuario);
                statement.setString(2, "%" + filtroBusqueda + "%");
                statement.setString(3, "%" + filtroBusqueda + "%");
                statement.setString(4, "%" + filtroBusqueda + "%");
            } else {
                statement.setString(1, "%" + filtroBusqueda + "%");
                statement.setString(2, "%" + filtroBusqueda + "%");
                statement.setString(3, "%" + filtroBusqueda + "%");
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ClienteEntity cliente = obtenerCliente(resultSet);
                    clientes.add(cliente);
                }
            }
        }

        return clientes;
    }

    @Override
    public ClienteEntity obtenerCliente(int clienteId) throws SQLException {
        ClienteEntity cliente = null;

        String query = "SELECT * FROM clientes WHERE cliente_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(clienteId));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = obtenerCliente(resultSet);
                }
            }
        }
        return cliente;
    }

    @Override
    public void actualizarCliente(ClienteEntity cliente) throws SQLException {


        String query = "UPDATE clientes SET rfc_cliente = ?, nombre_cliente = ?, razon_social = ?, regimen_fiscal = ?, usuario_id = ? WHERE cliente_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(cliente.getRfcCliente()));
            statement.setString(2, String.valueOf(cliente.getNombreCliente()));
            statement.setString(3, String.valueOf(cliente.getRazonSocial()));
            statement.setString(4, String.valueOf(cliente.getRegimenFiscal()));
            statement.setString(5, String.valueOf(cliente.getUsuarioId()));
            statement.setString(6, String.valueOf(cliente.getClienteId()));

            statement.executeUpdate();
        }
    }

    @Override
    public void guardarCliente(ClienteEntity cliente) throws SQLException {
        System.out.println("usuarioId: " + cliente.getUsuarioId());

        String query = "INSERT INTO clientes (rfc_cliente, nombre_cliente, razon_social, regimen_fiscal, usuario_id) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(cliente.getRfcCliente()));
            statement.setString(2, String.valueOf(cliente.getNombreCliente()));
            statement.setString(3, String.valueOf(cliente.getRazonSocial()));
            statement.setString(4, String.valueOf(cliente.getRegimenFiscal()));
            statement.setString(5, String.valueOf(cliente.getUsuarioId()));

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarCliente(int clienteId) throws SQLException {
        System.out.println("Repository clienteId: " + clienteId);
        String query = "DELETE FROM clientes WHERE cliente_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, clienteId);
            statement.executeUpdate();
            System.out.println("Se ejecuto query");
        }
    }

    private ClienteEntity obtenerCliente(ResultSet resultSet) throws SQLException {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setClienteId(resultSet.getInt("cliente_id"));
        cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
        cliente.setRfcCliente(resultSet.getString("rfc_cliente"));
        cliente.setRazonSocial(resultSet.getString("razon_social"));
        cliente.setRegimenFiscal(resultSet.getString("regimen_fiscal"));
        cliente.setUsuarioId(resultSet.getInt("usuario_id"));

        return cliente;
    }
}

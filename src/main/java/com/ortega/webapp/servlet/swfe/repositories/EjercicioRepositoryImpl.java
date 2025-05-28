package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjercicioRepositoryImpl implements EjercicioRepository<EjercicioEntity> {
    private final Connection connection;

    public EjercicioRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<EjercicioEntity> listarEjercicios() throws SQLException {
        List<EjercicioEntity> ejercicios = new ArrayList<>();

        String query = "SELECT * FROM ejercicios_fiscales";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EjercicioEntity ejercicio = new EjercicioEntity();
                    ejercicio.setEjercicioFiscalId(resultSet.getInt("ejercicio_fiscal_id"));
                    ejercicio.setEjercicioFiscal(resultSet.getString("ejercicio_fiscal"));
                    ejercicios.add(ejercicio);
                }
            }
        }


        return ejercicios;
    }

    @Override
    public List<EjercicioEntity> listarEjerciciosCliente(int clienteId) throws SQLException {
        List<EjercicioEntity> ejercicios = new ArrayList<>();

        String query = "SELECT ef.* FROM ejercicios_fiscales ef INNER JOIN ejercicios_fiscales_cliente efc ON ef.ejercicio_fiscal_id = efc.ejercicio_fiscal_id WHERE efc.cliente_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EjercicioEntity ejercicio = new EjercicioEntity();
                    ejercicio.setEjercicioFiscalId(resultSet.getInt("ejercicio_fiscal_id"));
                    ejercicio.setEjercicioFiscal(resultSet.getString("ejercicio_fiscal"));
                    ejercicios.add(ejercicio);
                }
            }
        }

        return ejercicios;
    }

    @Override
    public void guardarEjercicio(int ejercicioId, int clienteId) throws SQLException {
        String query = "INSERT INTO ejercicios_fiscales_cliente (cliente_id, ejercicio_fiscal_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, ejercicioId);
            statement.executeUpdate();
        }

    }
}

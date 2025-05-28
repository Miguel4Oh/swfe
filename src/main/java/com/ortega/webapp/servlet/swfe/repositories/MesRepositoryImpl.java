package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.MesEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesRepositoryImpl implements MesRepository {
    private final Connection connection;

    public MesRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<MesEntity> listarMeses(String consulta) throws SQLException {
        List<MesEntity> mesEntityList = new ArrayList<>();

        String query = "SELECT * FROM meses WHERE tipo_mes LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + consulta + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MesEntity mes = mapearCampos(resultSet);
                    mesEntityList.add(mes);
                }
            }
        }
        return mesEntityList;
    }

    @Override
    public Object obtenerMesPorNombre(String nombreMes) throws SQLException {
        MesEntity mes = new MesEntity();

        String query = "SELECT * FROM meses WHERE nombre_mes = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreMes);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    mes = mapearCampos(resultSet);
                }
            }
        }

        return mes;
    }

    @Override
    public MesEntity obtenerMesPorId(int mesId) throws SQLException {
        MesEntity mes = new MesEntity();

        String query = "SELECT * FROM meses WHERE mes_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, mesId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    mes = mapearCampos(resultSet);
                }
            }
        }

        return mes;
    }

    private MesEntity mapearCampos(ResultSet resultSet) throws SQLException {
        MesEntity mes = new MesEntity();
        mes.setMesId(resultSet.getInt("mes_id"));
        mes.setNombreMes(resultSet.getString("nombre_mes"));
        mes.setTipoMes(resultSet.getString("tipo_mes"));
        return mes;
    }
}

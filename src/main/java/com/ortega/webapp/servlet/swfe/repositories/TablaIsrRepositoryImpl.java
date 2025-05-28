package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablaIsrRepositoryImpl implements TablaIsrRepository<TablaIsrEntity> {
    private final Connection connection;

    public TablaIsrRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TablaIsrEntity> listarTablasIsr(String mesTabla) throws SQLException {
        System.out.println("mesTabla: " + mesTabla);
        List<TablaIsrEntity> tablaIsrEntityList = new ArrayList<>();

        String query = "SELECT * FROM tablas_isr WHERE mes_tabla = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, mesTabla);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TablaIsrEntity tablaIsr = mapearCampos(resultSet);
                    tablaIsrEntityList.add(tablaIsr);
                }
            }
        }
        System.out.println("tablaIsrEntityList: " + tablaIsrEntityList);
        return tablaIsrEntityList;
    }

    @Override
    public TablaIsrEntity obtenerTablaIsr(Long tablaIsrId) throws SQLException {
        TablaIsrEntity tablaIsr = null;

        String query = "SELECT * FROM tablas_isr WHERE tabla_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(tablaIsrId));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tablaIsr = mapearCampos(resultSet);
                }
            }
        }
        return tablaIsr;
    }

    @Override
    public void actualizarTablaIsr(TablaIsrEntity tablaIsr) throws SQLException {

        String query = "UPDATE tablas_isr SET mes_tabla = ?, limite_inferior = ?, limite_superior = ?, cuota_fija = ?, porcentaje_excedente = ? WHERE tabla_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tablaIsr.getMesTabla());
            statement.setDouble(2, tablaIsr.getLimiteInferior());
            statement.setDouble(3, tablaIsr.getLimiteSuperior());
            statement.setDouble(4, tablaIsr.getCuotaFija());
            statement.setDouble(5, tablaIsr.getPorcentajeExcedente());
            statement.setLong(6, tablaIsr.getTablaId());

            statement.executeUpdate();
        }
    }

    @Override
    public void guardarTablaIsr(TablaIsrEntity tablaIsr) throws SQLException {

        String query = "INSERT INTO tablas_isr (mes_tabla, limite_inferior, limite_superior, cuota_fija, porcentaje_excedente) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tablaIsr.getMesTabla());
            statement.setDouble(2, tablaIsr.getLimiteInferior());
            statement.setDouble(3, tablaIsr.getLimiteSuperior());
            statement.setDouble(4, tablaIsr.getCuotaFija());
            statement.setDouble(5, tablaIsr.getPorcentajeExcedente());

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarTablaIsr(Long tablaIsrId) throws SQLException {
        String query = "DELETE FROM tablas_isr WHERE tabla_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, tablaIsrId);
            statement.executeUpdate();
        }
    }

    @Override
    public TablaIsrEntity obtenerLimiteIsr(double monto, int mesId) {
        TablaIsrEntity tablaIsr = new TablaIsrEntity();

        String query = "SELECT * FROM tablas_isr WHERE mes_id = ? AND limite_inferior < ? AND limite_superior > ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, mesId);
            statement.setDouble(2, monto);
            statement.setDouble(3, monto);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tablaIsr = mapearCampos(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("consulta tablaIsr: " + tablaIsr);

        return tablaIsr;
    }

    private TablaIsrEntity mapearCampos(ResultSet resultSet) throws SQLException {
        TablaIsrEntity tablaIsr = new TablaIsrEntity();

        tablaIsr.setTablaId(resultSet.getLong("tabla_id"));
        tablaIsr.setMesTabla(resultSet.getString("mes_tabla"));
        tablaIsr.setLimiteInferior(resultSet.getDouble("limite_inferior"));
        tablaIsr.setLimiteSuperior(resultSet.getDouble("limite_superior"));
        tablaIsr.setCuotaFija(resultSet.getDouble("cuota_fija"));
        tablaIsr.setPorcentajeExcedente(resultSet.getDouble("porcentaje_excedente"));

        return tablaIsr;
    }
}

package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConceptoDepreciacionRepositotyImpl implements ConceptoDepreciacionRepository<ConceptoDepreciacionEntity> {
    private final Connection connection;

    public ConceptoDepreciacionRepositotyImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ConceptoDepreciacionEntity> listarConceptos(String filtroBusqueda) throws SQLException {
        List<ConceptoDepreciacionEntity> conceptosDepreciacionLista = new ArrayList<>();

        String query = "SELECT * FROM conceptos_depreciacion WHERE nombre_depreciacion LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + filtroBusqueda + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ConceptoDepreciacionEntity conceptoDepreciacion = mapearConceptos(resultSet);
                    conceptosDepreciacionLista.add(conceptoDepreciacion);
                }
            }
        }
        return conceptosDepreciacionLista;
    }

    @Override
    public ConceptoDepreciacionEntity obtenerConcepto(int conceptoId) throws SQLException {
        ConceptoDepreciacionEntity conceptoDepreciacion = null;

        String query = "SELECT * FROM conceptos_depreciacion WHERE concepto_depreciacion_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, conceptoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    conceptoDepreciacion = mapearConceptos(resultSet);
                }
            }
        }
        return conceptoDepreciacion;
    }

    @Override
    public ConceptoDepreciacionEntity obtenerConceptoPorNombre(String nombreConcepto) throws SQLException {
        ConceptoDepreciacionEntity conceptoDepreciacion = null;

        String query = "SELECT * FROM conceptos_depreciacion WHERE nombre_depreciacion = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreConcepto);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    conceptoDepreciacion = mapearConceptos(resultSet);
                }
            }
        }
        return conceptoDepreciacion;
    }

    @Override
    public void actualizarConcepto(ConceptoDepreciacionEntity conceptoDepreciacion) throws SQLException {

        String query = "UPDATE conceptos_depreciacion SET nombre_depreciacion = ?, porcentaje_depreciacion = ? WHERE concepto_depreciacion_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(conceptoDepreciacion.getNombreDepreciacion()));
            statement.setString(2, String.valueOf(conceptoDepreciacion.getPorcentajeDepreciacion()));
            statement.setFloat(3, conceptoDepreciacion.getConceptoDepreciacionId());

            statement.executeUpdate();
        }
    }

    @Override
    public void guardarConcepto(ConceptoDepreciacionEntity conceptoDepreciacion) throws SQLException {

        String query = "INSERT INTO conceptos_depreciacion (nombre_depreciacion, porcentaje_depreciacion) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, conceptoDepreciacion.getNombreDepreciacion());
            statement.setFloat(2, conceptoDepreciacion.getPorcentajeDepreciacion());

            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarConcepto(int conceptoId) throws SQLException {
        String query = "DELETE FROM conceptos_depreciacion WHERE concepto_depreciacion_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, conceptoId);
            statement.executeUpdate();
        }
    }

    private ConceptoDepreciacionEntity mapearConceptos(ResultSet resultSet) throws SQLException {
        ConceptoDepreciacionEntity conceptoDepreciacion = new ConceptoDepreciacionEntity();

        conceptoDepreciacion.setConceptoDepreciacionId(resultSet.getInt("concepto_depreciacion_id"));
        conceptoDepreciacion.setNombreDepreciacion(resultSet.getString("nombre_depreciacion"));
        conceptoDepreciacion.setPorcentajeDepreciacion(resultSet.getFloat("porcentaje_depreciacion"));

        return conceptoDepreciacion;
    }
}

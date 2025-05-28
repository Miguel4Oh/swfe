package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepreciacionRepositoryImpl implements DepreciacionRepository<DepreciacionEntity>{
    private final Connection connection;

    public DepreciacionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DepreciacionEntity> listarDepreciaciones(int clienteId, String filtroBusqueda, int mesId) throws SQLException {
        List<DepreciacionEntity> depreciacionesLista = new ArrayList<>();

        int ejercicioFiscal = Integer.parseInt(filtroBusqueda);

        String query = "SELECT * FROM depreciaciones WHERE cliente_id = ? AND ejercicio_fiscal = ? AND mes_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clienteId);
            preparedStatement.setInt(2, ejercicioFiscal);
            preparedStatement.setInt(3, mesId);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    DepreciacionEntity depreciacion = mapearDepreciacion(resultSet);
                    depreciacionesLista.add(depreciacion);
                }
            }

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return depreciacionesLista;
    }

    @Override
    public DepreciacionEntity obtenerDepreciacion(int depreciacionId) throws SQLException {
        return null;
    }

    @Override
    public void actualizarDepreciacion(DepreciacionEntity depreciacion) throws SQLException {
        System.out.println("Actualizando depreciacion");
        System.out.println("Depreciacion: " + depreciacion);

        String query = "UPDATE depreciaciones SET concepto_depreciacion = ?, fecha_adquisicion = ?, monto_inversion = ?, meses_uso_ejercicio_anterior = ?, meses_uso_ejercicio_actual = ?, porcentaje_depreciacion = ?, depreciacion_acumulada_inicio = ?, depreciacion_acumulada_fin = ?, depreciacion_pendiente_inicio = ?, depreciacion_pendiente_fin = ?, monto_deducible = ?, monto_no_deducible = ?, depreciacion_mensual = ?, ejercicio_fiscal = ?, cliente_id = ? WHERE depreciacion_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, depreciacion.getConceptoDepreciacion());
            preparedStatement.setDate(2, depreciacion.getFechaAdquisicion());
            preparedStatement.setDouble(3, depreciacion.getMontoInversion());
            preparedStatement.setInt(4, depreciacion.getMesesUsoEjercicioAnterior());
            preparedStatement.setInt(5, depreciacion.getMesesUsoEjercicioActual());
            preparedStatement.setDouble(6, depreciacion.getPorcentajeDepreciacion());
            preparedStatement.setDouble(7, depreciacion.getDepreciacionAcumuladaInicio());
            preparedStatement.setDouble(8, depreciacion.getDepreciacionAcumuladaFin());
            preparedStatement.setDouble(9, depreciacion.getDepreciacionPendienteInicio());
            preparedStatement.setDouble(10, depreciacion.getDepreciacionPendienteFin());
            preparedStatement.setDouble(11, depreciacion.getMontoDeducible());
            preparedStatement.setDouble(12, depreciacion.getMontoNoDeducible());
            preparedStatement.setDouble(13, depreciacion.getDepreciacionMensual());
            preparedStatement.setInt(14, depreciacion.getEjercicioFiscal());
            preparedStatement.setInt(15, depreciacion.getClienteId());
            preparedStatement.setInt(16, depreciacion.getDepreciacionId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void guardarDepreciacion(DepreciacionEntity depreciacion, int mesId) throws SQLException {
        System.out.println("Guardando depreciacion");
        System.out.println("Depreciacion: " + depreciacion);

        String query = "INSERT INTO depreciaciones (concepto_depreciacion, fecha_adquisicion, monto_inversion, " +
                "meses_uso_ejercicio_anterior, meses_uso_ejercicio_actual, porcentaje_depreciacion, " +
                "depreciacion_acumulada_inicio, depreciacion_acumulada_fin, depreciacion_pendiente_inicio, " +
                "depreciacion_pendiente_fin, monto_deducible, monto_no_deducible, depreciacion_mensual, " +
                "ejercicio_fiscal, cliente_id, mes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, depreciacion.getConceptoDepreciacion());
            preparedStatement.setDate(2, depreciacion.getFechaAdquisicion());
            preparedStatement.setDouble(3, depreciacion.getMontoInversion());
            preparedStatement.setInt(4, depreciacion.getMesesUsoEjercicioAnterior());
            preparedStatement.setInt(5, depreciacion.getMesesUsoEjercicioActual());
            preparedStatement.setDouble(6, depreciacion.getPorcentajeDepreciacion());
            preparedStatement.setDouble(7, depreciacion.getDepreciacionAcumuladaInicio());
            preparedStatement.setDouble(8, depreciacion.getDepreciacionAcumuladaFin());
            preparedStatement.setDouble(9, depreciacion.getDepreciacionPendienteInicio());
            preparedStatement.setDouble(10, depreciacion.getDepreciacionPendienteFin());
            preparedStatement.setDouble(11, depreciacion.getMontoDeducible());
            preparedStatement.setDouble(12, depreciacion.getMontoNoDeducible());
            preparedStatement.setDouble(13, depreciacion.getDepreciacionMensual());
            preparedStatement.setInt(14, depreciacion.getEjercicioFiscal());
            preparedStatement.setInt(15, depreciacion.getClienteId());
            preparedStatement.setInt(16, mesId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void eliminarDepreciacion(int depreciacionId) throws SQLException {
        String query = "DELETE FROM depreciaciones WHERE depreciacion_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, depreciacionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public double obtenerDepreciacionMensual(int clienteId, int mesId) throws SQLException {

        String query = "SELECT SUM(depreciacion_mensual) FROM depreciaciones WHERE cliente_id = ? AND mes_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clienteId);
            preparedStatement.setInt(2, mesId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DepreciacionEntity mapearDepreciacion(ResultSet resultSet) throws SQLException {
        DepreciacionEntity depreciacion = new DepreciacionEntity();

        depreciacion.setDepreciacionId(resultSet.getInt("depreciacion_id"));
        depreciacion.setConceptoDepreciacion(resultSet.getString("concepto_depreciacion"));
        depreciacion.setFechaAdquisicion(resultSet.getDate("fecha_adquisicion"));
        depreciacion.setPorcentajeDepreciacion(resultSet.getDouble("porcentaje_depreciacion"));
        depreciacion.setMontoInversion(resultSet.getDouble("monto_inversion"));
        depreciacion.setMesesUsoEjercicioAnterior(resultSet.getInt("meses_uso_ejercicio_anterior"));
        depreciacion.setMesesUsoEjercicioActual(resultSet.getInt("meses_uso_ejercicio_actual"));
        depreciacion.setPorcentajeDepreciacion(resultSet.getDouble("porcentaje_depreciacion"));
        depreciacion.setDepreciacionAcumuladaInicio(resultSet.getDouble("depreciacion_acumulada_inicio"));
        depreciacion.setDepreciacionAcumuladaFin(resultSet.getDouble("depreciacion_acumulada_fin"));
        depreciacion.setDepreciacionPendienteInicio(resultSet.getDouble("depreciacion_pendiente_inicio"));
        depreciacion.setDepreciacionPendienteFin(resultSet.getDouble("depreciacion_pendiente_fin"));
        depreciacion.setMontoDeducible(resultSet.getDouble("monto_deducible"));
        depreciacion.setMontoNoDeducible(resultSet.getDouble("monto_no_deducible"));
        depreciacion.setClienteId(resultSet.getInt("cliente_id"));

        return depreciacion;
    }
}

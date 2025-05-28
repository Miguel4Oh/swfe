package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryImpl implements EmpleadoRepository<EmpleadoEntity> {
    private final Connection connection;

    public EmpleadoRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<EmpleadoEntity> listarEmpleados(int clienteId, String filtroBusqueda) throws SQLException {
        List<EmpleadoEntity> empleadoLista = new ArrayList<>();

        String query = "SELECT * FROM empleados WHERE cliente_id = ? AND nombre_empleado LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clienteId);
            statement.setString(2, "%" + filtroBusqueda + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EmpleadoEntity empleado = mapearEmpleado(resultSet);
                    empleadoLista.add(empleado);
                }
            }
        }

        return empleadoLista;
    }

    @Override
    public EmpleadoEntity obtenerEmpleado(int empleadoId) throws SQLException {
        EmpleadoEntity empleado = null;

        String query = "SELECT * FROM empleados WHERE empleado_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empleadoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    empleado = mapearEmpleado(resultSet);
                }
            }
        }
        return empleado;
    }

    @Override
    public void actualizarEmpleado(EmpleadoEntity empleado) throws SQLException {
        String query = "UPDATE empleados SET nombre_empleado = ?, sueldo_empleado = ? WHERE empleado_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado.getNombre());
            statement.setDouble(2, empleado.getSueldo());
            statement.setInt(3, empleado.getEmpleadoId());

            statement.executeUpdate();
        }
    }

    @Override
    public void guardarEmpleado(EmpleadoEntity empleado) throws SQLException {
        System.out.println("empleado.getClienteId(): " + empleado.getClienteId());
        String query = "INSERT INTO empleados (nombre_empleado, sueldo_empleado, cliente_id, fecha_ingreso) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado.getNombre());
            statement.setDouble(2, empleado.getSueldo());
            statement.setInt(3, empleado.getClienteId());
            statement.setDate(4, obtenerFecha());

            statement.executeUpdate();
        }
    }

    private Date obtenerFecha() {
        Date fecha = null;

        try {
            String fechaString = "2023-07-05";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(fechaString);
            fecha = new java.sql.Date(utilDate.getTime());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return fecha;
    }

    @Override
    public void eliminarEmpleado(int empleadoId) throws SQLException {
        String query = "DELETE FROM empleados WHERE empleado_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, empleadoId);
            statement.executeUpdate();
        }
    }

    private EmpleadoEntity mapearEmpleado(ResultSet resultSet) throws SQLException {
        EmpleadoEntity empleado = new EmpleadoEntity();

        empleado.setEmpleadoId(resultSet.getInt("empleado_id"));
        empleado.setNombre(resultSet.getString("nombre_empleado"));
        empleado.setSueldo(resultSet.getDouble("sueldo_empleado"));
        empleado.setClienteId(resultSet.getInt("cliente_id"));

        return empleado;
    }
}

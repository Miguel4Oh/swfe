package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.repositories.EmpleadoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepositoryImpl empleadoRepository;

    public EmpleadoServiceImpl(Connection connection) {
        this.empleadoRepository = new EmpleadoRepositoryImpl(connection);
    }

    @Override
    public List<EmpleadoEntity> obtenerListaEmpleados(int clienteId, String filtroBusqueda) {
        try {
            return empleadoRepository.listarEmpleados(clienteId, filtroBusqueda);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<EmpleadoEntity> obtenerEmpleado(int empleadoId) {
        try {
            return Optional.ofNullable((EmpleadoEntity) empleadoRepository.obtenerEmpleado(empleadoId));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarGuardarEmpleado(EmpleadoEntity empleado) {
        if (empleado.getEmpleadoId() > 0) {
            try {
                empleadoRepository.actualizarEmpleado(empleado);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        } else {
            try {
                empleadoRepository.guardarEmpleado(empleado);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }
    }

    @Override
    public void eliminarEmpleado(int empleadoId) {
        try {
            empleadoRepository.eliminarEmpleado(empleadoId);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}

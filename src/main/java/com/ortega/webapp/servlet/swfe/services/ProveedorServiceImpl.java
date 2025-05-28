package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;
import com.ortega.webapp.servlet.swfe.repositories.ClienteRepositoryImpl;
import com.ortega.webapp.servlet.swfe.repositories.ProveedorRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepositoryImpl proveedorRepository;

    public ProveedorServiceImpl(Connection connection) {
        this.proveedorRepository = new ProveedorRepositoryImpl(connection);
    }

    @Override
    public List<ProveedorEntity> obtenerListaProveedores(int clienteId, String filtroBusqueda) {
        try {
            return proveedorRepository.listarProveedores(clienteId, filtroBusqueda);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<ProveedorEntity> obtenerProveedor(int proveedorId) {
        try {
            return Optional.ofNullable((ProveedorEntity) proveedorRepository.obtenerProveedor(proveedorId));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarGuardarProveedor(ProveedorEntity proveedor) {
        if (proveedor.getProveedorId() > 0) {
            try {
                proveedorRepository.actualizarProveedor(proveedor);
            }catch (SQLException throwables){
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }else {
            try {
                proveedorRepository.guardarProveedor(proveedor);
            }catch (SQLException throwables){
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }
    }

    @Override
    public void eliminarProveedor(int proveedorId) {
        try {
            proveedorRepository.eliminarProveedor(proveedorId);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}

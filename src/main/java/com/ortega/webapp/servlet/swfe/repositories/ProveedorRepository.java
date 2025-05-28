package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;

import java.sql.SQLException;
import java.util.List;

public interface ProveedorRepository <T>{
    List<T> listarProveedores(int clienteId, String filtroBusqueda) throws SQLException;
    T obtenerProveedor (int prveedorId) throws SQLException;
    void actualizarProveedor (ProveedorEntity proveedor) throws SQLException;
    void guardarProveedor (ProveedorEntity proveedor) throws SQLException;
    void eliminarProveedor(int proveedorId) throws SQLException;
}

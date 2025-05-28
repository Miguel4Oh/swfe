package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;

import java.sql.SQLException;
import java.util.List;

public interface ClienteRepository <T>{
    List<T> listarClientes(String usuario, String userRole, String filtroBusqueda) throws SQLException;
    T obtenerCliente (int clienteId) throws SQLException;
    void actualizarCliente (ClienteEntity cliente) throws SQLException;
    void guardarCliente (ClienteEntity cliente) throws SQLException;
    void eliminarCliente(int clienteId) throws SQLException;
}

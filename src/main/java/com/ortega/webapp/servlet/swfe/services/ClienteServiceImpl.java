package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.repositories.ClienteRepository;
import com.ortega.webapp.servlet.swfe.repositories.ClienteRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepositoryImpl clienteRepository;

    public ClienteServiceImpl(Connection connection) {
        this.clienteRepository = new ClienteRepositoryImpl(connection);
    }

    @Override
    public List<ClienteEntity> obtenerClientes(String usuario, String userRole, String filtroBusqueda) {
        System.out.println("Usuario ClienteService: " + usuario);
        try {
            return clienteRepository.listarClientes(usuario, userRole, filtroBusqueda);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<ClienteEntity> obtenerCliente(int clienteId) {
        try {
            return Optional.ofNullable((ClienteEntity) clienteRepository.obtenerCliente(clienteId));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarGuardarCliente(ClienteEntity cliente) {
        if (cliente.getClienteId() > 0) {
            try {
                clienteRepository.actualizarCliente(cliente);
            }catch (SQLException throwables){
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }else {
            try {
                clienteRepository.guardarCliente(cliente);
            }catch (SQLException throwables){
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }
    }

    @Override
    public void eliminarCliente(int clienteId) {
        try {
            clienteRepository.eliminarCliente(clienteId);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}

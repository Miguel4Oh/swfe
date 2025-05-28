package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteEntity> obtenerClientes (String user, String userRole, String filtroBusqueda);
    Optional<ClienteEntity> obtenerCliente (int clienteId);
    void actualizarGuardarCliente (ClienteEntity cliente);
    void eliminarCliente (int clienteId);
}

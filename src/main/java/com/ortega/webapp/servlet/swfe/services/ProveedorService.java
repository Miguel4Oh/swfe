package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.ProveedorEntity;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    List<ProveedorEntity> obtenerListaProveedores (int clienteId, String filtroBusqueda);
    Optional<ProveedorEntity> obtenerProveedor (int proveedorId);
    void actualizarGuardarProveedor (ProveedorEntity proveedor);
    void eliminarProveedor (int proveedorId);
}

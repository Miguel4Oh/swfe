package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<EmpleadoEntity> obtenerListaEmpleados (int clienteId, String filtroBusqueda);
    Optional<EmpleadoEntity> obtenerEmpleado (int empleadoId);
    void actualizarGuardarEmpleado (EmpleadoEntity empleado);
    void eliminarEmpleado (int empleadoId);
}

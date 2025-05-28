package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ClienteEntity;
import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;

import java.sql.SQLException;
import java.util.List;

public interface EmpleadoRepository <T>{
    List<T> listarEmpleados(int clienteId, String filtroBusqueda) throws SQLException;
    T obtenerEmpleado (int empleadoId) throws SQLException;
    void actualizarEmpleado (EmpleadoEntity empleado) throws SQLException;
    void guardarEmpleado(EmpleadoEntity empleado) throws SQLException;
    void eliminarEmpleado(int empleadoId) throws SQLException;
}

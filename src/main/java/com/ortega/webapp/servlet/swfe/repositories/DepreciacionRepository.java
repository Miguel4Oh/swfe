package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;

import java.sql.SQLException;
import java.util.List;

public interface DepreciacionRepository <T>{
    List<T> listarDepreciaciones(int clienteId, String filtroBusqueda, int mesId) throws SQLException;
    T obtenerDepreciacion(int depreciacionId) throws SQLException;
    void actualizarDepreciacion(DepreciacionEntity depreciacion) throws SQLException;
    void guardarDepreciacion(DepreciacionEntity depreciacion, int mesId) throws SQLException;
    void eliminarDepreciacion(int depreciacionId) throws SQLException;
    double obtenerDepreciacionMensual(int clienteId, int mesId) throws SQLException;
}

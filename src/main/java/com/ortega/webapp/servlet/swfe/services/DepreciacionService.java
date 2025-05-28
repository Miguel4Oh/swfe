package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DepreciacionService {
    List<DepreciacionEntity> obtenerListaDepreciaciones(int clienteId, String filtroBusqueda, int mesId);
    Optional<DepreciacionEntity> obtenerDepreciacion(int depreciacionId);
    void actualizarGuardarDepreciacion(DepreciacionEntity depreciacion, String ejercicioAnterior, int mesId) throws SQLException;
    void eliminarDepreciacion(int depreciacionId);
    double obtenerDepreciacionMensual(ImpuestoEntity impuestoEntity);
}

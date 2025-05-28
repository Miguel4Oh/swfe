package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;

import java.sql.SQLException;
import java.util.List;

public interface IngresoRepository <T>{
    List<T> listarIngresos(FacturaEntity facturaEntity) throws SQLException;
    List<T> listarIngresosFiltro(int clienteId, FacturaEntity facturaEntity, String filtro) throws SQLException;
    void saldarIngreso(int ingresoId) throws SQLException;
}

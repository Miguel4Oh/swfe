package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;

import java.sql.SQLException;
import java.util.List;

public interface ConceptoDepreciacionRepository<T> {
    List<T> listarConceptos(String filtroBusqueda) throws SQLException;
    T obtenerConcepto (int conceptoId) throws SQLException;
    T obtenerConceptoPorNombre (String nombreConcepto) throws SQLException;
    void actualizarConcepto (ConceptoDepreciacionEntity conceptoDepreciacion) throws SQLException;
    void guardarConcepto (ConceptoDepreciacionEntity conceptoDepreciacion) throws SQLException;
    void eliminarConcepto(int conceptoId) throws SQLException;
}

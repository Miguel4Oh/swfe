package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;

import java.util.List;
import java.util.Optional;

public interface ConceptoService {
    List<ConceptoDepreciacionEntity> obtenerListaConceptos (String filtroBusqueda);
    Optional<ConceptoDepreciacionEntity> obtenerConcepto (int conceptoId);
    void actualizarGuardarConcepto (ConceptoDepreciacionEntity concepto);
    void eliminarConcepto (int conceptoId);
}

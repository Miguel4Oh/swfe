package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;

import java.util.List;

public interface IngresoService {
    List<FacturaEntity> obtenerListaIngresos (FacturaEntity facturaEntity);
    List<FacturaEntity> obtenerListaIngresosFiltro (int clienteId, FacturaEntity facturaEntity, String filtro);
    void saldarIngreso (int ingresoId);
}

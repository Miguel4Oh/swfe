package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;

import java.util.List;

public interface EgresoService {
    List<FacturaEntity> obtenerListaEgresos (FacturaEntity facturaEntity);
    List<FacturaEntity> obtenerListaEgresosFiltro (int clienteId, FacturaEntity facturaEntity, String filtro);
}

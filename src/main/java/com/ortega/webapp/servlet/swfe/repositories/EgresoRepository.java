package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;

import java.util.List;

public interface EgresoRepository <T>{
    List<FacturaEntity> listarEgresos(FacturaEntity facturaEntity) throws Exception;
    List<FacturaEntity> listarEgresosFiltro(int clienteId, FacturaEntity facturaEntity, String filtro) throws Exception;
}

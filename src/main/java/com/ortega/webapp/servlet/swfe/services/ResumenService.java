package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.resumen.IngresosEgresosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenImpuestosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity;

import java.util.List;

public interface ResumenService {

    List<IngresosEgresosEntity> obtenerIngresosEgresos(int clienteId, int ejercicioId);
    List<ResumenImpuestosEntity> obtenerResumenImpuestos(int clienteId, int ejercicioId);
    List<ResumenIvaEntity> obtenerResumenIva(int clienteId, int ejercicioId);

}

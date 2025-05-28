package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;

import java.sql.SQLException;
import java.util.List;

public interface NominaService {
    NominaEntity obtenerNominaPorId(int nominaId) throws SQLException;
    List<NominaEntity> obtenerListaNominas(NominaFormDTO nomina) throws SQLException;
    void actualizarGuardarNomina (NominaFormDTO nomina) throws SQLException;
    void eliminarNomina(int nominaId) throws SQLException;
    double obtenerNominaMensual(ImpuestoEntity impuesto) throws SQLException;
    double obtenerIsrMensual(ImpuestoEntity impuesto) throws SQLException;
    double obtenerSubsidioMensual(ImpuestoEntity impuesto) throws SQLException;

}

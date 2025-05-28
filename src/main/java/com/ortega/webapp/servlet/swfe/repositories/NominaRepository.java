package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import org.apache.poi.ss.formula.functions.T;

import java.sql.SQLException;
import java.util.List;

public interface NominaRepository <t> {
    List<T> listarNominas(int clienteId, String filtroBusqueda) throws SQLException;
    NominaEntity obtenerNomina (int nominaId) throws SQLException;
    List<NominaEntity> obtenerNomina (NominaFormDTO nominaEntity) throws SQLException;
    void actualizarNomina (NominaEntity nomina) throws SQLException;
    void guardarNomina (NominaEntity nomina) throws SQLException;
    void eliminarNomina(int nominaId) throws SQLException;
    double obtenerNominaMensual(ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerIsrMensual(ImpuestoEntity impuestoEntity) throws SQLException;
    double obtenerSubsidioMensual(ImpuestoEntity impuestoEntity) throws SQLException;
}

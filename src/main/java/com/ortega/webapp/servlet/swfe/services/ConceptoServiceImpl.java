package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.repositories.ConceptoDepreciacionRepositotyImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConceptoServiceImpl implements ConceptoService {
    private final ConceptoDepreciacionRepositotyImpl conceptoDepreciacionRepositoty;

    public ConceptoServiceImpl(Connection connection) {
        this.conceptoDepreciacionRepositoty = new ConceptoDepreciacionRepositotyImpl(connection);
    }

    @Override
    public List<ConceptoDepreciacionEntity> obtenerListaConceptos(String filtroBusqueda) {
        try {
            return conceptoDepreciacionRepositoty.listarConceptos(filtroBusqueda);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<ConceptoDepreciacionEntity> obtenerConcepto(int conceptoId) {
        try {
            return Optional.ofNullable(conceptoDepreciacionRepositoty.obtenerConcepto(conceptoId));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarGuardarConcepto(ConceptoDepreciacionEntity concepto) {
        if (concepto.getConceptoDepreciacionId() > 0) {
            try {
                conceptoDepreciacionRepositoty.actualizarConcepto(concepto);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        } else {
            try {
                conceptoDepreciacionRepositoty.guardarConcepto(concepto);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }
    }

    @Override
    public void eliminarConcepto(int conceptoId) {
        try {
            conceptoDepreciacionRepositoty.eliminarConcepto(conceptoId);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}

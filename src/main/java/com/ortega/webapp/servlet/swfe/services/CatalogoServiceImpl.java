package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity;
import com.ortega.webapp.servlet.swfe.repositories.CatalogoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CatalogoServiceImpl implements CatalogoService{
    private final CatalogoRepositoryImpl catalogoRepository;

    public CatalogoServiceImpl(Connection connection) {
        this.catalogoRepository = new CatalogoRepositoryImpl(connection);
    }

    @Override
    public Optional<CatalogoEntity> obtenerCatalogos() {
        try {
            return Optional.ofNullable(catalogoRepository.listarCatalogos());
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }
}

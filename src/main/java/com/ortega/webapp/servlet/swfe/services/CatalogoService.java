package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.catalogos.CatalogoEntity;

import java.util.Optional;

public interface CatalogoService {
    Optional<CatalogoEntity> obtenerCatalogos();
}

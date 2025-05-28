package com.ortega.webapp.servlet.swfe.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CatalogoRepository <T>{

    T listarCatalogos() throws SQLException;
}

package com.ortega.webapp.servlet.swfe.repositories;

import java.sql.SQLException;
import java.util.List;

public interface MesRepository <T>{
    List<T> listarMeses(String consutla) throws SQLException;
    T obtenerMesPorNombre(String nombreMes) throws SQLException;
    T obtenerMesPorId(int mesId) throws SQLException;
}

package com.ortega.webapp.servlet.swfe.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ResumenRepository <T>{

    Map<String, Double> obtenerIngresos(int clienteId, int ejercicioId, int mesId) throws SQLException;
    Map<String, Double> obtenerEgresos(int clienteId, int ejercicioId, int mesId) throws SQLException;
    Map<String, Double> obtnerIvaIngresos(int clienteId, int ejercicioId, int mesId) throws SQLException;
    Map<String, Double> obtnerIvaEgresos(int clienteId, int ejercicioId, int mesId) throws SQLException;

}

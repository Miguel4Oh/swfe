package com.ortega.webapp.servlet.swfe.repositories;

import java.sql.SQLException;
import java.util.List;

public interface EjercicioRepository <T>{

    List<T> listarEjercicios() throws SQLException;
    List<T> listarEjerciciosCliente(int clienteId) throws SQLException;
    void guardarEjercicio(int ejercicioId, int clienteId) throws SQLException;
}

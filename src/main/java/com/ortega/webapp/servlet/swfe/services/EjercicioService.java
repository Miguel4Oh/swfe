package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;

import java.sql.SQLException;
import java.util.List;

public interface EjercicioService {

    List<EjercicioEntity> listarEjercicios() throws SQLException;
    List<EjercicioEntity> listarEjerciciosCliente(int clienteId) throws SQLException;
    void guardarEjercicio(int ejercicioId, int clienteId) throws SQLException;
}

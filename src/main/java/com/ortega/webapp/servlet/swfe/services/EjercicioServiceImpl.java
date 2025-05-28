package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.EjercicioEntity;
import com.ortega.webapp.servlet.swfe.repositories.EjercicioRepositoryImpl;
import com.ortega.webapp.servlet.swfe.utils.exepciones.EjercicioExistenteException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EjercicioServiceImpl implements EjercicioService {
    private final EjercicioRepositoryImpl ejercicioRepository;

    public EjercicioServiceImpl(Connection connection) {
        this.ejercicioRepository = new EjercicioRepositoryImpl(connection);
    }

    @Override
    public List<EjercicioEntity> listarEjercicios() throws SQLException {

        try {
            return ejercicioRepository.listarEjercicios();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<EjercicioEntity> listarEjerciciosCliente(int clienteId) throws SQLException {
        try {
            return ejercicioRepository.listarEjerciciosCliente(clienteId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void guardarEjercicio(int ejercicioId, int clienteId) throws SQLException {
        try {
            List<EjercicioEntity> ejerciciosDelCliente = ejercicioRepository.listarEjerciciosCliente(clienteId);

            System.out.println("Ejercicios del cliente: " + ejerciciosDelCliente);

            for (EjercicioEntity ejercicio : ejerciciosDelCliente) {
                if (ejercicio.getEjercicioFiscalId() == ejercicioId) {
                    throw new EjercicioExistenteException("El ejercicio ya existe para el cliente");
                }
            }

            ejercicioRepository.guardarEjercicio(ejercicioId, clienteId);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EjercicioExistenteException e) {
            throw new RuntimeException(e);
        }
    }
}

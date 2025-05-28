package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.repositories.MesRepository;
import com.ortega.webapp.servlet.swfe.repositories.MesRepositoryImpl;

import java.sql.Connection;
import java.util.List;

public class MesServiceImpl implements MesService{
    private final MesRepositoryImpl mesRepository;

    public MesServiceImpl(Connection connection) {
        this.mesRepository = new MesRepositoryImpl(connection);
    }

    @Override
    public List<MesEntity> obtenerListaMeses(String consulta) {
        try {
            return mesRepository.listarMeses(consulta);
        } catch (Exception exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public MesEntity obtenerMesPorNombre(String nombreMes) {
        MesEntity mes = new MesEntity();

        try {
            mes = (MesEntity) mesRepository.obtenerMesPorNombre(nombreMes);
        } catch (Exception exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return mes;
    }

    @Override
    public MesEntity obtenerMesPorId(int mesId) {
        MesEntity mesEntity = new MesEntity();

        try {
            mesEntity = (MesEntity) mesRepository.obtenerMesPorId(mesId);
        } catch (Exception exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }

        return mesEntity;
    }
}

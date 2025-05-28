package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.repositories.EgresoRepositoryImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EgresoServiceImpl implements EgresoService {
    private final EgresoRepositoryImpl repository;

    public EgresoServiceImpl(Connection connection) {
        this.repository = new EgresoRepositoryImpl(connection);
    }

    @Override
    public List<FacturaEntity> obtenerListaEgresos(FacturaEntity facturaEntity) {

        try {
            return repository.listarEgresos(facturaEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public List<FacturaEntity> obtenerListaEgresosFiltro(int clienteId, FacturaEntity facturaEntity, String filtro) {
        List<FacturaEntity> facturas = new ArrayList<>();

        try {
            facturas = repository.listarEgresosFiltro(clienteId, facturaEntity, filtro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturas;
    }
}

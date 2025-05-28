package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.FacturaEntity;
import com.ortega.webapp.servlet.swfe.repositories.IngresoRepositoryImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class IngresoServiceImpl implements IngresoService {
    private final IngresoRepositoryImpl ingresoRepository;

    public IngresoServiceImpl(Connection connection) {
        this.ingresoRepository = new IngresoRepositoryImpl(connection);
    }

    @Override
    public List<FacturaEntity> obtenerListaIngresos(FacturaEntity facturaEntity) {
        List<FacturaEntity> facturas = new ArrayList<>();

        try {
            facturas = ingresoRepository.listarIngresos(facturaEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturas;
    }

    @Override
    public void saldarIngreso(int ingresoId) {

        try {
            ingresoRepository.saldarIngreso(ingresoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FacturaEntity> obtenerListaIngresosFiltro(int clienteId, FacturaEntity facturaEntity, String filtro) {
        List<FacturaEntity> facturas = new ArrayList<>();

        try {
            facturas = ingresoRepository.listarIngresosFiltro(clienteId, facturaEntity, filtro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturas;
    }
}

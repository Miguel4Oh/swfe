package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.MesEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.IngresosEgresosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenImpuestosEntity;
import com.ortega.webapp.servlet.swfe.models.resumen.ResumenIvaEntity;
import com.ortega.webapp.servlet.swfe.repositories.ResumenRepositoryImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumenServiceImpl implements ResumenService {
    private final ResumenRepositoryImpl resumenRepositoryImpl;
    private final MesService mesService;
    private final ImpuestoService impuestoService;

    public ResumenServiceImpl(Connection connection) {
        this.resumenRepositoryImpl = new ResumenRepositoryImpl(connection);
        this.mesService = new MesServiceImpl(connection);
        this.impuestoService = new ImpuestoServiceImpl(connection);
    }

    @Override
    public List<IngresosEgresosEntity> obtenerIngresosEgresos(int clienteId, int ejercicioId) {
        List<IngresosEgresosEntity> resumenIngresosEgresosList = new ArrayList<>();

        try {
            for (int i = 1; i <= 12; i++) {
                IngresosEgresosEntity ingresosEgresosEntity = new IngresosEgresosEntity();

                Map<String, Double> totalesIngreso = resumenRepositoryImpl.obtenerIngresos(clienteId, ejercicioId, i);
                Map<String, Double> totalesEgreso = resumenRepositoryImpl.obtenerEgresos(clienteId, ejercicioId, i);
                MesEntity mesEntity = mesService.obtenerMesPorId(i);

                ingresosEgresosEntity.setMesId(mesEntity.getMesId());
                ingresosEgresosEntity.setMes(mesEntity.getNombreMes());
                ingresosEgresosEntity.setIngreso(totalesIngreso.get("ingresos"));
                ingresosEgresosEntity.setIvaIngreso(totalesIngreso.get("iva_ingresos"));
                ingresosEgresosEntity.setEgreso(totalesEgreso.get("egresos"));
                ingresosEgresosEntity.setIvaEgreso(totalesEgreso.get("iva_egresos"));

                resumenIngresosEgresosList.add(ingresosEgresosEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resumenIngresosEgresosList;
    }

    @Override
    public List<ResumenImpuestosEntity> obtenerResumenImpuestos(int clienteId, int ejercicioId) {
        List<ResumenImpuestosEntity> resumenImpuestosList = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            ResumenImpuestosEntity resumenImpuestosEntity = new ResumenImpuestosEntity();
            MesEntity mesEntity = mesService.obtenerMesPorId(i);
            ImpuestoEntity impuestoEntity = new ImpuestoEntity();

            impuestoEntity.setClienteId(clienteId);
            impuestoEntity.setEjercicioId(ejercicioId);
            impuestoEntity.setMesId(mesEntity.getMesId());

            try {
                ImpuestoEntity impuesto = impuestoService.calcularImpuesto(impuestoEntity);
                resumenImpuestosEntity.setMesId(mesEntity.getMesId());
                resumenImpuestosEntity.setMes(mesEntity.getNombreMes());
                resumenImpuestosEntity.setImpuesto(impuesto.getImpuestoACargoMes());
                resumenImpuestosEntity.setSalarios(impuesto.getIsrSalarioPorPagar());
                resumenImpuestosEntity.setIva(impuesto.getImpuestoCargoFavor());

                resumenImpuestosList.add(resumenImpuestosEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resumenImpuestosList;
    }

    @Override
    public List<ResumenIvaEntity> obtenerResumenIva(int clienteId, int ejercicioId) {
        List<ResumenIvaEntity> resumenIvaList = new ArrayList<>();

        double saldosFavor = 0;

        try {
            for (int i = 1; i <= 12; i++) {
                ResumenIvaEntity resumenIvaEntity = new ResumenIvaEntity();
                MesEntity mesEntity = mesService.obtenerMesPorId(i);

                Map<String, Double> ivaIngreso = resumenRepositoryImpl.obtnerIvaIngresos(clienteId, ejercicioId, i);
                Map<String, Double> ivaEgreso = resumenRepositoryImpl.obtnerIvaEgresos(clienteId, ejercicioId, i);
                Map<String, Double> totalesIngreso = resumenRepositoryImpl.obtenerIngresos(clienteId, ejercicioId, i);
                Map<String, Double> totalesEgreso = resumenRepositoryImpl.obtenerEgresos(clienteId, ejercicioId, i);

                resumenIvaEntity.setMesId(mesEntity.getMesId());
                resumenIvaEntity.setMes(mesEntity.getNombreMes());
                resumenIvaEntity.setIngresos(totalesIngreso.get("ingresos"));
                resumenIvaEntity.setEgresos(totalesEgreso.get("egresos"));
                resumenIvaEntity.setIvaCausado(ivaIngreso.get("iva_ingresos"));
                resumenIvaEntity.setIvaAcreditado(ivaEgreso.get("iva_egresos"));

                resumenIvaEntity.setSaldosFavor(saldosFavor);

                if (resumenIvaEntity.getIvaAcreditado() > resumenIvaEntity.getIvaCausado()) {
                    resumenIvaEntity.setImpuestoCargo(0);
                    resumenIvaEntity.setImpuestoFavor(resumenIvaEntity.getIvaAcreditado() - resumenIvaEntity.getIvaCausado());

                    resumenIvaEntity.setImpuestoAuditoriaCargo(0);
                    resumenIvaEntity.setImpuestoAuditoriaFavor(saldosFavor + resumenIvaEntity.getImpuestoFavor());
                } else {
                    resumenIvaEntity.setImpuestoCargo(resumenIvaEntity.getIvaCausado() - resumenIvaEntity.getIvaAcreditado());
                    resumenIvaEntity.setImpuestoFavor(0);

                    resumenIvaEntity.setImpuestoAuditoriaCargo(resumenIvaEntity.getImpuestoCargo() - saldosFavor);
                    resumenIvaEntity.setImpuestoAuditoriaFavor(0);
                }

                saldosFavor = resumenIvaEntity.getImpuestoAuditoriaFavor();

                resumenIvaList.add(resumenIvaEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resumenIvaList;
    }
}

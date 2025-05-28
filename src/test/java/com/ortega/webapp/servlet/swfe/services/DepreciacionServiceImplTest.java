package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class DepreciacionServiceImplTest {
    Connection connection = null;
    DepreciacionServiceImpl depreciacionService = new DepreciacionServiceImpl(connection);

    @Test
    void calcularMesesDeUsoEjercicioAnteriorTest(){

        Date fechaAdquisicion = Date.valueOf("2023-01-01");
        String anioEjercicioAnterior = "2023";

        int mesesUsoEjercicioAnterior = depreciacionService.calcularMesesDeUsoEjercicioAnterior(fechaAdquisicion, anioEjercicioAnterior);

        assertEquals(11, mesesUsoEjercicioAnterior);
    }

    @Test
    void calcularDepreciacionAcumuladaInicioTest(){
        DepreciacionEntity depreciacion = new DepreciacionEntity();

        double montoInversion = 1000;
        double porcentajeDepreciacion = 10;
        int mesesUsoEjercicioAnterior = 11;

        depreciacion.setMontoInversion(montoInversion);
        depreciacion.setPorcentajeDepreciacion(porcentajeDepreciacion);
        depreciacion.setMesesUsoEjercicioAnterior(mesesUsoEjercicioAnterior);

        double depreciacionAcumuladaInicio = depreciacionService.calcularDepreciacionAcumuladaInicio(depreciacion);

        assertEquals(92, depreciacionAcumuladaInicio);
    }

    @Test
    void calcularMesesDeUsoEjercicioActualTest(){
        DepreciacionEntity depreciacion = new DepreciacionEntity();
        depreciacion.setFechaAdquisicion(Date.valueOf("2024-01-01"));

        String anioEjercicioActual = "2024";

        int mesesUsoEjercicioActual = depreciacionService.calcularMesesDeUsoEjercicioActual(depreciacion, anioEjercicioActual);

        assertEquals(11, mesesUsoEjercicioActual);
    }
}
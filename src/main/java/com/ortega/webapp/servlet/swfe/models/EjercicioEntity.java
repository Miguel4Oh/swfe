package com.ortega.webapp.servlet.swfe.models;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EjercicioEntity {
    private int ejercicioFiscalId;
    private String ejercicioFiscal;

    public EjercicioEntity() {
    }

    public EjercicioEntity(int ejercicioFiscalId, String ejercicioFiscal) {
        this.ejercicioFiscalId = ejercicioFiscalId;
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public int getEjercicioFiscalId() {
        return ejercicioFiscalId;
    }

    public void setEjercicioFiscalId(int ejercicioFiscalId) {
        this.ejercicioFiscalId = ejercicioFiscalId;
    }

    public String getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(String ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    @Override
    public String toString() {
        return "EjercicioEntity{" +
                "ejercicioFiscalId=" + ejercicioFiscalId +
                ", ejercicioFiscal='" + ejercicioFiscal + '\'' +
                '}';
    }

    public int getEjercicioFiscalComoInt() {
        try {
            return Integer.parseInt(ejercicioFiscal);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static EjercicioEntity obtenerEjercicioFiscalMasGrande(List<EjercicioEntity> ejercicios) {
        Optional<EjercicioEntity> ejercicioMax = ejercicios.stream()
                .max(Comparator.comparingInt(EjercicioEntity::getEjercicioFiscalComoInt));
        return ejercicioMax.orElse(null);
    }
}

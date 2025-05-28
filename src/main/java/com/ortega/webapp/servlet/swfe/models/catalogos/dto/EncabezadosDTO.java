package com.ortega.webapp.servlet.swfe.models.catalogos.dto;

import java.util.List;

public class EncabezadosDTO {

    private List<String> columnas;
    private List<String> columnasEspecial;

    public EncabezadosDTO(List<String> columnas, List<String> columnasEspecial) {
        this.columnas = columnas;
        this.columnasEspecial = columnasEspecial;
    }

    public EncabezadosDTO() {
    }

    public List<String> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<String> columnas) {
        this.columnas = columnas;
    }

    public List<String> getColumnasEspecial() {
        return columnasEspecial;
    }

    public void setColumnasEspecial(List<String> columnasEspecial) {
        this.columnasEspecial = columnasEspecial;
    }

    @Override
    public String toString() {
        return "EncabezadosDTO{" +
                "columnas=" + columnas +
                ", columnasEspecial=" + columnasEspecial +
                '}';
    }
}

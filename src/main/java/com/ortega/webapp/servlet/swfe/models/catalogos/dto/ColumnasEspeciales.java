package com.ortega.webapp.servlet.swfe.models.catalogos.dto;

public class ColumnasEspeciales {

    private int columnaEmisor;
    private int columnaImportes;
    private int columnaIva16;
    private int columnaComprobante;
    private int columnaReceptor;

    public ColumnasEspeciales() {
        this.columnaEmisor = 0;
        this.columnaImportes = 0;
        this.columnaIva16 = 0;
        this.columnaComprobante = 0;
        this.columnaReceptor = 0;
    }

    public int getColumnaEmisor() {
        return columnaEmisor;
    }

    public void setColumnaEmisor(int columnaEmisor) {
        this.columnaEmisor = columnaEmisor;
    }

    public int getColumnaImportes() {
        return columnaImportes;
    }

    public void setColumnaImportes(int columnaImportes) {
        this.columnaImportes = columnaImportes;
    }

    public int getColumnaIva16() {
        return columnaIva16;
    }

    public void setColumnaIva16(int columnaIva16) {
        this.columnaIva16 = columnaIva16;
    }

    public int getColumnaComprobante() {
        return columnaComprobante;
    }

    public void setColumnaComprobante(int columnaComprobante) {
        this.columnaComprobante = columnaComprobante;
    }

    public int getColumnaReceptor() {
        return columnaReceptor;
    }

    public void setColumnaReceptor(int columnaReceptor) {
        this.columnaReceptor = columnaReceptor;
    }

    @Override
    public String toString() {
        return "ColumnasEspeciales{" +
                "columnaEmisor=" + columnaEmisor +
                ", columnaImportes=" + columnaImportes +
                ", columnaIva16=" + columnaIva16 +
                ", comprobante=" + columnaComprobante +
                ", receptor=" + columnaReceptor +
                '}';
    }
}

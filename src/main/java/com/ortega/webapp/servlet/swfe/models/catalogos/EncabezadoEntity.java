package com.ortega.webapp.servlet.swfe.models.catalogos;

public class EncabezadoEntity {

    private String nombreEncabezado;
    private boolean esEpecial;

    public EncabezadoEntity(String nombreEncabezado, boolean esEpecial) {
        this.nombreEncabezado = nombreEncabezado;
        this.esEpecial = esEpecial;
    }

    public EncabezadoEntity() {
    }

    public String getNombreEncabezado() {
        return nombreEncabezado;
    }

    public void setNombreEncabezado(String nombreEncabezado) {
        this.nombreEncabezado = nombreEncabezado;
    }

    public boolean getEsEpecial() {
        return esEpecial;
    }

    public void setEsEpecial(boolean esEpecial) {
        this.esEpecial = esEpecial;
    }

    @Override
    public String toString() {
        return "EncabezadoEntity{" +
                "nombreEncabezado='" + nombreEncabezado + '\'' +
                ", especial=" + esEpecial +
                '}';
    }
}

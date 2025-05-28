package com.ortega.webapp.servlet.swfe.models.catalogos;

public class EfectoEntity {

    private int efectoId;
    private String nombreEfecto;

    public EfectoEntity() {
    }

    public EfectoEntity(int efectoId, String nombreEfecto) {
        this.efectoId = efectoId;
        this.nombreEfecto = nombreEfecto;
    }

    public int getEfectoId() {
        return efectoId;
    }

    public void setEfectoId(int efectoId) {
        this.efectoId = efectoId;
    }

    public String getNombreEfecto() {
        return nombreEfecto;
    }

    public void setNombreEfecto(String nombreEfecto) {
        this.nombreEfecto = nombreEfecto;
    }

    @Override
    public String toString() {
        return "EfectoEntity{" +
                "efectoId=" + efectoId +
                ", nombreEfecto='" + nombreEfecto + '\'' +
                '}';
    }
}

package com.ortega.webapp.servlet.swfe.models;

public class MesEntity {

    private int mesId;
    private String nombreMes;
    private String tipoMes;

    public MesEntity() {
    }

    public MesEntity(int idMes, String nombreMes) {
        this.mesId = idMes;
        this.nombreMes = nombreMes;
    }

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public String getTipoMes() {
        return tipoMes;
    }

    public void setTipoMes(String tipoMes) {
        this.tipoMes = tipoMes;
    }

    @Override
    public String toString() {
        return "MesEntity{" +
                "mesId=" + mesId +
                ", nombreMes='" + nombreMes + '\'' +
                ", tipoMes='" + tipoMes + '\'' +
                '}';
    }
}

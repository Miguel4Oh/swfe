package com.ortega.webapp.servlet.swfe.models.catalogos;

public class EstatusEntity {

    private int estatusId;
    private String nombreEstatus;

    public EstatusEntity() {
    }

    public EstatusEntity(int estatusId, String nombreEstatus) {
        this.estatusId = estatusId;
        this.nombreEstatus = nombreEstatus;
    }

    public int getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(int estatusId) {
        this.estatusId = estatusId;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }
}

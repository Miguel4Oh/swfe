package com.ortega.webapp.servlet.swfe.models.catalogos;

public class MonedaEntity {

    private int monedaId;
    private String nombreMoneda;

    public MonedaEntity() {
    }

    public MonedaEntity(int monedaId, String nombreMoneda) {
        this.monedaId = monedaId;
        this.nombreMoneda = nombreMoneda;
    }

    public int getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(int monedaId) {
        this.monedaId = monedaId;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }
}

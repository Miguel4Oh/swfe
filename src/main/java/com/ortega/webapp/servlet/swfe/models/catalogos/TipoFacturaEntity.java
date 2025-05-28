package com.ortega.webapp.servlet.swfe.models.catalogos;

public class TipoFacturaEntity {

    private int tipoFacturaId;
    private String nombreTipoFactura;

    public TipoFacturaEntity() {
    }

    public TipoFacturaEntity(int tipoFacturaId, String nombreTipoFactura) {
        this.tipoFacturaId = tipoFacturaId;
        this.nombreTipoFactura = nombreTipoFactura;
    }

    public int getTipoFacturaId() {
        return tipoFacturaId;
    }

    public void setTipoFacturaId(int tipoFacturaId) {
        this.tipoFacturaId = tipoFacturaId;
    }

    public String getNombreTipoFactura() {
        return nombreTipoFactura;
    }

    public void setNombreTipoFactura(String nombreTipoFactura) {
        this.nombreTipoFactura = nombreTipoFactura;
    }

    @Override
    public String toString() {
        return "TipoFacturaEntity{" +
                "tipoFacturaId=" + tipoFacturaId +
                ", nombreTipoFactura='" + nombreTipoFactura + '\'' +
                '}';
    }
}

package com.ortega.webapp.servlet.swfe.models.catalogos;

public class MetodoPagoEntity {

    private int metodoPagoId;
    private String nombreMetodoPago;

    public MetodoPagoEntity() {
    }

    public MetodoPagoEntity(int metodoPagoId, String nombreMetodoPago) {
        this.metodoPagoId = metodoPagoId;
        this.nombreMetodoPago = nombreMetodoPago;
    }

    public int getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(int metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public String getNombreMetodoPago() {
        return nombreMetodoPago;
    }

    public void setNombreMetodoPago(String nombreMetodoPago) {
        this.nombreMetodoPago = nombreMetodoPago;
    }
}

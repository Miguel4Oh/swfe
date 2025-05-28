package com.ortega.webapp.servlet.swfe.models.catalogos;

public class FormaPagoEntity {

    private int formaPagoId;
    private String nombreFormaPago;

    public FormaPagoEntity() {
    }

    public FormaPagoEntity(int formaPagoId, String nombreFormaPago) {
        this.formaPagoId = formaPagoId;
        this.nombreFormaPago = nombreFormaPago;
    }

    public int getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(int formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public String getNombreFormaPago() {
        return nombreFormaPago;
    }

    public void setNombreFormaPago(String nombreFormaPago) {
        this.nombreFormaPago = nombreFormaPago;
    }
}

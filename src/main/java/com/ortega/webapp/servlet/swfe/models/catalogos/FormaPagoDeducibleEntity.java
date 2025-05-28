package com.ortega.webapp.servlet.swfe.models.catalogos;

public class FormaPagoDeducibleEntity {

    private int formaPagoDeduibleId;
    private String nombreFormaPagoDeduible;
    private String deducible;

    public FormaPagoDeducibleEntity() {
    }

    public FormaPagoDeducibleEntity(int formaPagoDeduibleId, String nombreFormaPagoDeduible, String deducible) {
        this.formaPagoDeduibleId = formaPagoDeduibleId;
        this.nombreFormaPagoDeduible = nombreFormaPagoDeduible;
        this.deducible = deducible;
    }

    public int getFormaPagoDeduibleId() {
        return formaPagoDeduibleId;
    }

    public void setFormaPagoDeduibleId(int formaPagoDeduibleId) {
        this.formaPagoDeduibleId = formaPagoDeduibleId;
    }

    public String getNombreFormaPagoDeduible() {
        return nombreFormaPagoDeduible;
    }

    public void setNombreFormaPagoDeduible(String nombreFormaPagoDeduible) {
        this.nombreFormaPagoDeduible = nombreFormaPagoDeduible;
    }

    public String getDeducible() {
        return deducible;
    }

    public void setDeducible(String deducible) {
        this.deducible = deducible;
    }
}

package com.ortega.webapp.servlet.swfe.models.resumen;

public class IngresosEgresosEntity {

    private int mesId;
    private String mes;
    private double ingreso;
    private double egreso;
    private double ivaIngreso;
    private double ivaEgreso;

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getEgreso() {
        return egreso;
    }

    public void setEgreso(double egreso) {
        this.egreso = egreso;
    }

    public double getIvaIngreso() {
        return ivaIngreso;
    }

    public void setIvaIngreso(double ivaIngreso) {
        this.ivaIngreso = ivaIngreso;
    }

    public double getIvaEgreso() {
        return ivaEgreso;
    }

    public void setIvaEgreso(double ivaEgreso) {
        this.ivaEgreso = ivaEgreso;
    }

    @Override
    public String toString() {
        return "IngresosEgresosEntity{" +
                "mes='" + mes + '\'' +
                ", ingreso=" + ingreso +
                ", egreso=" + egreso +
                ", ivaIngreso=" + ivaIngreso +
                ", ivaEgreso=" + ivaEgreso +
                '}';
    }
}

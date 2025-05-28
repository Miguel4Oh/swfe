package com.ortega.webapp.servlet.swfe.models.resumen;

public class ResumenImpuestosEntity {

    private int mesId;
    private String mes;
    private double impuesto;
    private double iva;
    private double salarios;

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

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSalarios() {
        return salarios;
    }

    public void setSalarios(double salarios) {
        this.salarios = salarios;
    }


    @Override
    public String toString() {
        return "ResumenImpuestosEntity{" +
                "mesId=" + mesId +
                ", mes='" + mes + '\'' +
                ", impuesto=" + impuesto +
                ", iva=" + iva +
                ", salarios=" + salarios +
                '}';
    }
}

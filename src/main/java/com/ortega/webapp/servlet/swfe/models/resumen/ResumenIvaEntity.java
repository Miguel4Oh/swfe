package com.ortega.webapp.servlet.swfe.models.resumen;

public class ResumenIvaEntity {

    private int mesId;
    private String mes;
    private double ingresos;
    private double ivaCausado;
    private double egresos;
    private double ivaAcreditado;
    private double impuestoCargo;
    private double impuestoFavor;
    private double saldosFavor;
    private double impuestoAuditoriaCargo;
    private double impuestoAuditoriaFavor;

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

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getIvaCausado() {
        return ivaCausado;
    }

    public void setIvaCausado(double ivaCausado) {
        this.ivaCausado = ivaCausado;
    }

    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }

    public double getIvaAcreditado() {
        return ivaAcreditado;
    }

    public void setIvaAcreditado(double ivaAcreditado) {
        this.ivaAcreditado = ivaAcreditado;
    }

    public double getImpuestoCargo() {
        return impuestoCargo;
    }

    public void setImpuestoCargo(double impuestoCargo) {
        this.impuestoCargo = impuestoCargo;
    }

    public double getImpuestoFavor() {
        return impuestoFavor;
    }

    public void setImpuestoFavor(double impuestoFavor) {
        this.impuestoFavor = impuestoFavor;
    }

    public double getSaldosFavor() {
        return saldosFavor;
    }

    public void setSaldosFavor(double saldosFavor) {
        this.saldosFavor = saldosFavor;
    }

    public double getImpuestoAuditoriaCargo() {
        return impuestoAuditoriaCargo;
    }

    public void setImpuestoAuditoriaCargo(double impuestoAuditoriaCargo) {
        this.impuestoAuditoriaCargo = impuestoAuditoriaCargo;
    }

    public double getImpuestoAuditoriaFavor() {
        return impuestoAuditoriaFavor;
    }

    public void setImpuestoAuditoriaFavor(double impuestoAuditoriaFavor) {
        this.impuestoAuditoriaFavor = impuestoAuditoriaFavor;
    }

    @Override
    public String toString() {
        return "ResumenIvaEntity{" +
                "mesId=" + mesId +
                ", mes='" + mes + '\'' +
                ", ingresos=" + ingresos +
                ", ivaCausado=" + ivaCausado +
                ", egresos=" + egresos +
                ", ivaAcreditado=" + ivaAcreditado +
                ", impuestoCargo=" + impuestoCargo +
                ", impuestoFavor=" + impuestoFavor +
                ", saldosFavor=" + saldosFavor +
                ", impuestoAuditoriaCargo=" + impuestoAuditoriaCargo +
                ", impuestoAuditoriaFavor=" + impuestoAuditoriaFavor +
                '}';
    }
}

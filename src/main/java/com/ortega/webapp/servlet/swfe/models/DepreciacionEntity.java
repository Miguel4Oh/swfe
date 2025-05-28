package com.ortega.webapp.servlet.swfe.models;

import java.sql.Date;

public class DepreciacionEntity {

    private int clienteId;
    private int depreciacionId;
    private String conceptoDepreciacion;
    private Date fechaAdquisicion;
    private double montoInversion;
    private int mesesUsoEjercicioAnterior;
    private int mesesUsoEjercicioActual;
    private double porcentajeDepreciacion;
    private double depreciacionAcumuladaInicio;
    private double depreciacionAcumuladaFin;
    private double depreciacionPendienteInicio;
    private double depreciacionPendienteFin;
    private double montoDeducible;
    private double montoNoDeducible;
    private int depreciacionMensual;
    private int ejercicioFiscal;

    public DepreciacionEntity() {
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDepreciacionId() {
        return depreciacionId;
    }

    public void setDepreciacionId(int depreciacionId) {
        this.depreciacionId = depreciacionId;
    }

    public String getConceptoDepreciacion() {
        return conceptoDepreciacion;
    }

    public void setConceptoDepreciacion(String conceptoDepreciacion) {
        this.conceptoDepreciacion = conceptoDepreciacion;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public double getMontoInversion() {
        return montoInversion;
    }

    public void setMontoInversion(double montoInversion) {
        this.montoInversion = montoInversion;
    }

    public int getMesesUsoEjercicioAnterior() {
        return mesesUsoEjercicioAnterior;
    }

    public void setMesesUsoEjercicioAnterior(int mesesUsoEjercicioAnterior) {
        this.mesesUsoEjercicioAnterior = mesesUsoEjercicioAnterior;
    }

    public int getMesesUsoEjercicioActual() {
        return mesesUsoEjercicioActual;
    }

    public void setMesesUsoEjercicioActual(int mesesUsoEjercicioActual) {
        this.mesesUsoEjercicioActual = mesesUsoEjercicioActual;
    }

    public double getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    public void setPorcentajeDepreciacion(double porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }

    public double getDepreciacionAcumuladaInicio() {
        return depreciacionAcumuladaInicio;
    }

    public void setDepreciacionAcumuladaInicio(double depreciacionAcumuladaInicio) {
        this.depreciacionAcumuladaInicio = depreciacionAcumuladaInicio;
    }

    public double getDepreciacionAcumuladaFin() {
        return depreciacionAcumuladaFin;
    }

    public void setDepreciacionAcumuladaFin(double depreciacionAcumuladaFin) {
        this.depreciacionAcumuladaFin = depreciacionAcumuladaFin;
    }

    public double getDepreciacionPendienteInicio() {
        return depreciacionPendienteInicio;
    }

    public void setDepreciacionPendienteInicio(double depreciacionPendienteInicio) {
        this.depreciacionPendienteInicio = depreciacionPendienteInicio;
    }

    public double getDepreciacionPendienteFin() {
        return depreciacionPendienteFin;
    }

    public void setDepreciacionPendienteFin(double depreciacionPendienteFin) {
        this.depreciacionPendienteFin = depreciacionPendienteFin;
    }

    public double getMontoDeducible() {
        return montoDeducible;
    }

    public void setMontoDeducible(double montoDeducible) {
        this.montoDeducible = montoDeducible;
    }

    public double getMontoNoDeducible() {
        return montoNoDeducible;
    }

    public void setMontoNoDeducible(double montoNoDeducible) {
        this.montoNoDeducible = montoNoDeducible;
    }

    public int getDepreciacionMensual() {
        return depreciacionMensual;
    }

    public void setDepreciacionMensual(int depreciacionMensual) {
        this.depreciacionMensual = depreciacionMensual;
    }

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    @Override
    public String toString() {
        return "DepreciacionEntity{" +
                "clienteId=" + clienteId +
                ", depreciacionId=" + depreciacionId +
                ", conceptoDepreciacion='" + conceptoDepreciacion + '\'' +
                ", fechaAdquisicion=" + fechaAdquisicion +
                ", montoInversion=" + montoInversion +
                ", mesesUsoEjercicioAnterior=" + mesesUsoEjercicioAnterior +
                ", mesesUsoEjercicioActual=" + mesesUsoEjercicioActual +
                ", porcentajeDepreciacion=" + porcentajeDepreciacion +
                ", depreciacionAcumuladaInicio=" + depreciacionAcumuladaInicio +
                ", depreciacionAcumuladaFin=" + depreciacionAcumuladaFin +
                ", depreciacionPendienteInicio=" + depreciacionPendienteInicio +
                ", depreciacionPendienteFin=" + depreciacionPendienteFin +
                ", montoDeducible=" + montoDeducible +
                ", montoNoDeducible=" + montoNoDeducible +
                ", depreciacionMensual=" + depreciacionMensual +
                ", ejercicioFiscal=" + ejercicioFiscal +
                '}';
    }
}

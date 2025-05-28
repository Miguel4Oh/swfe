package com.ortega.webapp.servlet.swfe.models.nomina;

public class NominaFormDTO {

    private int clienteId;
    private int nominaId;
    private int empleadoId;
    private double sueldo;
    private int diasLaborados;
    private int mes;
    private int quincena;
    private double subsidio;
    private int ejercicioFiscal;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getNominaId() {
        return nominaId;
    }

    public void setNominaId(int nominaId) {
        this.nominaId = nominaId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(int diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getQuincena() {
        return quincena;
    }

    public void setQuincena(int quincena) {
        this.quincena = quincena;
    }

    public double getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(double subsidio) {
        this.subsidio = subsidio;
    }

    public int getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(int ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    @Override
    public String toString() {
        return "NominaFormDTO{" +
                "clienteId=" + clienteId +
                ", nominaId=" + nominaId +
                ", empleadoId=" + empleadoId +
                ", sueldo=" + sueldo +
                ", diasLaborados=" + diasLaborados +
                ", mes=" + mes +
                ", quincena=" + quincena +
                ", subsidio=" + subsidio +
                ", ejercicioFiscal=" + ejercicioFiscal +
                '}';
    }
}

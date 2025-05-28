package com.ortega.webapp.servlet.swfe.models.nomina;

public class NominaEntity {

    private int nominaid;
    private String empeladoId;
    private String nombreEmpleado;
    private int numeroQuincena;
    private double imssNomina;
    private double isrSalario;
    private double subsidioNomina;
    private double salarioNeto;
    private double finiquitoNomina;
    private int clienteId;
    private int mesId;
    private double salario;
    private int ejercicioFiscalId;
    private int diasLaborados;

    public NominaEntity() {
    }

    public NominaEntity(int i, double v, double v1, double v2, double v3, double v4) {
        this.nominaid = i;
        this.imssNomina = v;
        this.isrSalario = v1;
        this.subsidioNomina = v2;
        this.salarioNeto = v3;
        this.finiquitoNomina = v4;
    }


    public int getNominaid() {
        return nominaid;
    }

    public void setNominaid(int nominaid) {
        this.nominaid = nominaid;
    }

    public String getEmpeladoId() {
        return empeladoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public void setEmpeladoId(String empeladoId) {
        this.empeladoId = empeladoId;
    }

    public int getNumeroQuincena() {
        return numeroQuincena;
    }

    public void setNumeroQuincena(int numeroQuincena) {
        this.numeroQuincena = numeroQuincena;
    }

    public double getImssNomina() {
        return imssNomina;
    }

    public void setImssNomina(double imssNomina) {
        this.imssNomina = imssNomina;
    }

    public double getIsrSalario() {
        return isrSalario;
    }

    public void setIsrSalario(double isrSalario) {
        this.isrSalario = isrSalario;
    }

    public double getSubsidioNomina() {
        return subsidioNomina;
    }

    public void setSubsidioNomina(double subsidioNomina) {
        this.subsidioNomina = subsidioNomina;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }

    public double getFiniquitoNomina() {
        return finiquitoNomina;
    }

    public void setFiniquitoNomina(double finiquitoNomina) {
        this.finiquitoNomina = finiquitoNomina;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getEjercicioFiscalId() {
        return ejercicioFiscalId;
    }

    public void setEjercicioFiscalId(int ejercicioFiscalId) {
        this.ejercicioFiscalId = ejercicioFiscalId;
    }

    public int getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(int diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    @Override
    public String toString() {
        return "NominaEntity{" +
                "nominaid=" + nominaid +
                ", empeladoId='" + empeladoId + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", numeroQuincena=" + numeroQuincena +
                ", imssNomina=" + imssNomina +
                ", isrSalario=" + isrSalario +
                ", subsidioNomina=" + subsidioNomina +
                ", salarioNeto=" + salarioNeto +
                ", finiquitoNomina=" + finiquitoNomina +
                ", clienteId=" + clienteId +
                ", mesId=" + mesId +
                ", salario=" + salario +
                ", ejercicioFiscalId=" + ejercicioFiscalId +
                ", diasLaborados=" + diasLaborados +
                '}';
    }
}

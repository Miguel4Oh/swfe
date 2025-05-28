package com.ortega.webapp.servlet.swfe.models;

public class TablaIsrEntity {
    private Long tablaId;
    private String mesTabla;
    private double limiteInferior;
    private double limiteSuperior;
    private double cuotaFija;
    private double porcentajeExcedente;

    public TablaIsrEntity() {
    }

    public Long getTablaId() {
        return tablaId;
    }

    public void setTablaId(Long tablaId) {
        this.tablaId = tablaId;
    }

    public String getMesTabla() {
        return mesTabla;
    }

    public void setMesTabla(String mesTabla) {
        this.mesTabla = mesTabla;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double getCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(double cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public double getPorcentajeExcedente() {
        return porcentajeExcedente;
    }

    public void setPorcentajeExcedente(double porcentajeExcedente) {
        this.porcentajeExcedente = porcentajeExcedente;
    }

    @Override
    public String toString() {
        return "TablaIsrEntity{" +
                "tablaId=" + tablaId +
                ", mesTabla='" + mesTabla + '\'' +
                ", limiteInferior=" + limiteInferior +
                ", limiteSuperior=" + limiteSuperior +
                ", cuotaFija=" + cuotaFija +
                ", porcentajeExcedente=" + porcentajeExcedente +
                '}';
    }
}

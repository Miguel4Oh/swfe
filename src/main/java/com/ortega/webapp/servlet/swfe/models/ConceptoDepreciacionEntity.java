package com.ortega.webapp.servlet.swfe.models;

public class ConceptoDepreciacionEntity {
    private int conceptoDepreciacionId;
    private String nombreDepreciacion;
    private float porcentajeDepreciacion;

    public ConceptoDepreciacionEntity() {
    }

    public int getConceptoDepreciacionId() {
        return conceptoDepreciacionId;
    }

    public void setConceptoDepreciacionId(int conceptoDepreciacionId) {
        this.conceptoDepreciacionId = conceptoDepreciacionId;
    }

    public String getNombreDepreciacion() {
        return nombreDepreciacion;
    }

    public void setNombreDepreciacion(String nombreDepreciacion) {
        this.nombreDepreciacion = nombreDepreciacion;
    }

    public float getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    public void setPorcentajeDepreciacion(float porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }
}

package com.ortega.webapp.servlet.swfe.models.catalogos;

public class SerieEntity {

    private int serieId;
    private String nombreSerie;

    public SerieEntity() {
    }

    public SerieEntity(int serieId, String nombreSerie) {
        this.serieId = serieId;
        this.nombreSerie = nombreSerie;
    }

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }
}

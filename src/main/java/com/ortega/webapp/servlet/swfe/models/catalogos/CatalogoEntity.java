package com.ortega.webapp.servlet.swfe.models.catalogos;

import java.util.List;
import java.util.Map;

public class CatalogoEntity {

    Map<String, Object> tiposFactura;
    Map<String, Object> series;
    Map<String, Object> monedas;
    Map<String, Object> estatus;
    Map<String, Object> efectos;
    Map<String, Object> metodosPago;
    Map<String, Object> formasPago;
    Map<String, Object> formasPagoDeducible;

    public CatalogoEntity() {
    }

    public Map<String, Object> getTiposFactura() {
        return tiposFactura;
    }

    public void setTiposFactura(Map<String, Object> tiposFactura) {
        this.tiposFactura = tiposFactura;
    }

    public Map<String, Object> getSeries() {
        return series;
    }

    public void setSeries(Map<String, Object> series) {
        this.series = series;
    }

    public Map<String, Object> getMonedas() {
        return monedas;
    }

    public void setMonedas(Map<String, Object> monedas) {
        this.monedas = monedas;
    }

    public Map<String, Object> getEstatus() {
        return estatus;
    }

    public void setEstatus(Map<String, Object> estatus) {
        this.estatus = estatus;
    }

    public Map<String, Object> getEfectos() {
        return efectos;
    }

    public void setEfectos(Map<String, Object> efectos) {
        this.efectos = efectos;
    }

    public Map<String, Object> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(Map<String, Object> metodosPago) {
        this.metodosPago = metodosPago;
    }

    public Map<String, Object> getFormasPago() {
        return formasPago;
    }

    public void setFormasPago(Map<String, Object> formasPago) {
        this.formasPago = formasPago;
    }

    public Map<String, Object> getFormasPagoDeducible() {
        return formasPagoDeducible;
    }

    public void setFormasPagoDeducible(Map<String, Object> formasPagoDeducible) {
        this.formasPagoDeducible = formasPagoDeducible;
    }
}

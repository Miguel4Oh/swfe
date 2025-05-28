package com.ortega.webapp.servlet.swfe.models;

import java.sql.Date;

public class FacturaEntity {
    private int facturaId;
    private String folioFactura;
    private Date fechaFactura;
    private Date fechaCobranza;
    private String rfcEmisor;
    private String nombreEmisor;
    private double subtotalFactura;
    private double ivaFactura;
    private double isrRetenido;
    private double ivaRetenido;
    private double totalFactura;
    private String uuidFactura;
    private double tcFactura;
    private String usoCfdiReceptor;
    private int clienteId;
    private int usuarioId;
    private String tipoFactura;
    private String serie;
    private String moneda;
    private String estatus;
    private String efecto;
    private String metodoPago;
    private String formaPago;
    private String formaPagoDeducible;
    private int ejercicioFiscalId;
    private int mesId;
    private int mesCobroId;

    public FacturaEntity() {
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public String getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(String folioFactura) {
        this.folioFactura = folioFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public java.sql.Date getFechaCobranza() {
        return fechaCobranza;
    }

    public void setFechaCobranza(Date fechaCobranza) {
        this.fechaCobranza = fechaCobranza;
    }

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public double getSubtotalFactura() {
        return subtotalFactura;
    }

    public void setSubtotalFactura(double subtotalFactura) {
        this.subtotalFactura = subtotalFactura;
    }

    public double getIvaFactura() {
        return ivaFactura;
    }

    public void setIvaFactura(double ivaFactura) {
        this.ivaFactura = ivaFactura;
    }

    public double getIsrRetenido() {
        return isrRetenido;
    }

    public void setIsrRetenido(double isrRetenido) {
        this.isrRetenido = isrRetenido;
    }

    public double getIvaRetenido() {
        return ivaRetenido;
    }

    public void setIvaRetenido(double ivaRetenido) {
        this.ivaRetenido = ivaRetenido;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getUuidFactura() {
        return uuidFactura;
    }

    public void setUuidFactura(String uuidFactura) {
        this.uuidFactura = uuidFactura;
    }

    public double getTcFactura() {
        return tcFactura;
    }

    public void setTcFactura(double tcFactura) {
        this.tcFactura = tcFactura;
    }

    public String getUsoCfdiReceptor() {
        return usoCfdiReceptor;
    }

    public void setUsoCfdiReceptor(String usoCfdiReceptor) {
        this.usoCfdiReceptor = usoCfdiReceptor;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFormaPagoDeducible() {
        return formaPagoDeducible;
    }

    public void setFormaPagoDeducible(String formaPagoDeducible) {
        this.formaPagoDeducible = formaPagoDeducible;
    }

    public int getEjercicioFiscalId() {
        return ejercicioFiscalId;
    }

    public void setEjercicioFiscalId(int ejercicioFiscalId) {
        this.ejercicioFiscalId = ejercicioFiscalId;
    }

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public int getMesCobroId() {
        return mesCobroId;
    }

    public void setMesCobroId(int mesCobroId) {
        this.mesCobroId = mesCobroId;
    }

    @Override
    public String toString() {
        return "FacturaEntity{" +
                "facturaId=" + facturaId +
                ", folioFactura='" + folioFactura + '\'' +
                ", fechaFactura=" + fechaFactura +
                ", fechaCobranza=" + fechaCobranza +
                ", rfcEmisor='" + rfcEmisor + '\'' +
                ", nombreEmisor='" + nombreEmisor + '\'' +
                ", subtotalFactura=" + subtotalFactura +
                ", ivaFactura=" + ivaFactura +
                ", isrRetenido=" + isrRetenido +
                ", ivaRetenido=" + ivaRetenido +
                ", totalFactura=" + totalFactura +
                ", uuidFactura='" + uuidFactura + '\'' +
                ", tcFactura=" + tcFactura +
                ", usoCfdiReceptor='" + usoCfdiReceptor + '\'' +
                ", clienteId=" + clienteId +
                ", usuarioId=" + usuarioId +
                ", tipoFactura='" + tipoFactura + '\'' +
                ", serie='" + serie + '\'' +
                ", moneda='" + moneda + '\'' +
                ", estatus='" + estatus + '\'' +
                ", efecto='" + efecto + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", formaPagoDeducible='" + formaPagoDeducible + '\'' +
                ", ejercicioFiscalId=" + ejercicioFiscalId +
                ", mesId=" + mesId +
                ", mesCobroId=" + mesCobroId +
                '}';
    }
}

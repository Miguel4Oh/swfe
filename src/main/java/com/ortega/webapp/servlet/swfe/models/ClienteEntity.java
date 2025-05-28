package com.ortega.webapp.servlet.swfe.models;

public class ClienteEntity {

    private int clienteId;
    private String rfcCliente;
    private String nombreCliente;
    private String razonSocial;
    private String regimenFiscal;
    private int usuarioId;

    public ClienteEntity() {
    }

    public ClienteEntity(int clienteId, String rfcCliente, String nombreCliente, String razonSocial, String regimenFiscal, int usuarioId) {
        this.clienteId = clienteId;
        this.rfcCliente = rfcCliente;
        this.nombreCliente = nombreCliente;
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
        this.usuarioId = usuarioId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getRfcCliente() {
        return rfcCliente;
    }

    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}

package com.ortega.webapp.servlet.swfe.models;

public class ImpuestoEntity {
    private int clienteId;
    private int mesId;
    private int ejercicioId;
    private double ingresoMesAnterior;
    private double ingresosCobrados;
    private double deduccionesMesAnterior;
    private double deduccionesAutorizadasPagadas;
    private double gastosNomina;
    private double depreciacion;
    private double deduccionesDelMes;
    private double totalDePeriodo;
    private double basePagoProvisional;
    private double limiteInferior;
    private double excedenteLimiteInferior;
    private double porcentajeExcedenteLimiteInferior;
    private double impuestoMarginal;
    private double cuotaFija;
    private double impuesto;
    private double reduccionDeTreinta;
    private double impuestoReducido;
    private double impuestoACargo;
    private double pagosProvisionalesConAnterioridad;
    private double diezRetencionMesesAnteriores;
    private double diezRetenidoMes;
    private double impuestoACargoMes;
    private double compensacion;
    private double actualizaciones;
    private double recargos;
    private double baseDelImpuesto;
    private double ivaCobrado;
    private double retenciones;
    private double ivaAcreditable;
    private double ivaAFavorPeriodo;
    private double SaldoAFavor;
    private double impuestoCargoFavor;
    private double isrSalarios;
    private double subsidio;
    private double subsidioPeriodosAnteriores;
    private double isrSalarioPorPagar;
    private double total;

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

    public int getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(int ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public double getIngresoMesAnterior() {
        return ingresoMesAnterior;
    }

    public void setIngresoMesAnterior(double ingresoMesAnterior) {
        this.ingresoMesAnterior = ingresoMesAnterior;
    }

    public double getIngresosCobrados() {
        return ingresosCobrados;
    }

    public void setIngresosCobrados(double ingresosCobrados) {
        this.ingresosCobrados = ingresosCobrados;
    }

    public double getDeduccionesMesAnterior() {
        return deduccionesMesAnterior;
    }

    public void setDeduccionesMesAnterior(double deduccionesMesAnterior) {
        this.deduccionesMesAnterior = deduccionesMesAnterior;
    }

    public double getDeduccionesAutorizadasPagadas() {
        return deduccionesAutorizadasPagadas;
    }

    public void setDeduccionesAutorizadasPagadas(double deduccionesAutorizadasPagadas) {
        this.deduccionesAutorizadasPagadas = deduccionesAutorizadasPagadas;
    }

    public double getGastosNomina() {
        return gastosNomina;
    }

    public void setGastosNomina(double gastosNomina) {
        this.gastosNomina = gastosNomina;
    }

    public double getDepreciacion() {
        return depreciacion;
    }

    public void setDepreciacion(double depreciacion) {
        this.depreciacion = depreciacion;
    }

    public double getDeduccionesDelMes() {
        return deduccionesDelMes;
    }

    public void setDeduccionesDelMes(double deduccionesDelMes) {
        this.deduccionesDelMes = deduccionesDelMes;
    }

    public double getTotalDePeriodo() {
        return totalDePeriodo;
    }

    public void setTotalDePeriodo(double totalDePeriodo) {
        this.totalDePeriodo = totalDePeriodo;
    }

    public double getBasePagoProvisional() {
        return basePagoProvisional;
    }

    public void setBasePagoProvisional(double basePagoProvisional) {
        this.basePagoProvisional = basePagoProvisional;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getExcedenteLimiteInferior() {
        return excedenteLimiteInferior;
    }

    public void setExcedenteLimiteInferior(double excedenteLimiteInferior) {
        this.excedenteLimiteInferior = excedenteLimiteInferior;
    }

    public double getPorcentajeExcedenteLimiteInferior() {
        return porcentajeExcedenteLimiteInferior;
    }

    public void setPorcentajeExcedenteLimiteInferior(double porcentajeExcedenteLimiteInferior) {
        this.porcentajeExcedenteLimiteInferior = porcentajeExcedenteLimiteInferior;
    }

    public double getImpuestoMarginal() {
        return impuestoMarginal;
    }

    public void setImpuestoMarginal(double impuestoMarginal) {
        this.impuestoMarginal = impuestoMarginal;
    }

    public double getCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(double cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getReduccionDeTreinta() {
        return reduccionDeTreinta;
    }

    public void setReduccionDeTreinta(double reduccionDeTreinta) {
        this.reduccionDeTreinta = reduccionDeTreinta;
    }

    public double getImpuestoReducido() {
        return impuestoReducido;
    }

    public void setImpuestoReducido(double impuestoReducido) {
        this.impuestoReducido = impuestoReducido;
    }

    public double getImpuestoACargo() {
        return impuestoACargo;
    }

    public void setImpuestoACargo(double impuestoACargo) {
        this.impuestoACargo = impuestoACargo;
    }

    public double getPagosProvisionalesConAnterioridad() {
        return pagosProvisionalesConAnterioridad;
    }

    public void setPagosProvisionalesConAnterioridad(double pagosProvisionalesConAnterioridad) {
        this.pagosProvisionalesConAnterioridad = pagosProvisionalesConAnterioridad;
    }

    public double getDiezRetencionMesesAnteriores() {
        return diezRetencionMesesAnteriores;
    }

    public void setDiezRetencionMesesAnteriores(double diezRetencionMesesAnteriores) {
        this.diezRetencionMesesAnteriores = diezRetencionMesesAnteriores;
    }

    public double getDiezRetenidoMes() {
        return diezRetenidoMes;
    }

    public void setDiezRetenidoMes(double diezRetenidoMes) {
        this.diezRetenidoMes = diezRetenidoMes;
    }

    public double getImpuestoACargoMes() {
        return impuestoACargoMes;
    }

    public void setImpuestoACargoMes(double impuestoACargoMes) {
        this.impuestoACargoMes = impuestoACargoMes;
    }

    public double getCompensacion() {
        return compensacion;
    }

    public void setCompensacion(double compensacion) {
        this.compensacion = compensacion;
    }

    public double getActualizaciones() {
        return actualizaciones;
    }

    public void setActualizaciones(double actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public double getRecargos() {
        return recargos;
    }

    public void setRecargos(double recargos) {
        this.recargos = recargos;
    }

    public double getBaseDelImpuesto() {
        return baseDelImpuesto;
    }

    public void setBaseDelImpuesto(double baseDelImpuesto) {
        this.baseDelImpuesto = baseDelImpuesto;
    }

    public double getIvaCobrado() {
        return ivaCobrado;
    }

    public void setIvaCobrado(double ivaCobrado) {
        this.ivaCobrado = ivaCobrado;
    }

    public double getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(double retenciones) {
        this.retenciones = retenciones;
    }

    public double getIvaAcreditable() {
        return ivaAcreditable;
    }

    public void setIvaAcreditable(double ivaAcreditable) {
        this.ivaAcreditable = ivaAcreditable;
    }

    public double getIvaAFavorPeriodo() {
        return ivaAFavorPeriodo;
    }

    public void setIvaAFavorPeriodo(double ivaAFavorPeriodo) {
        this.ivaAFavorPeriodo = ivaAFavorPeriodo;
    }

    public double getSaldoAFavor() {
        return SaldoAFavor;
    }

    public void setSaldoAFavor(double saldoAFavor) {
        SaldoAFavor = saldoAFavor;
    }

    public double getImpuestoCargoFavor() {
        return impuestoCargoFavor;
    }

    public void setImpuestoCargoFavor(double impuestoCargoFavor) {
        this.impuestoCargoFavor = impuestoCargoFavor;
    }

    public double getIsrSalarios() {
        return isrSalarios;
    }

    public void setIsrSalarios(double isrSalarios) {
        this.isrSalarios = isrSalarios;
    }

    public double getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(double subsidio) {
        this.subsidio = subsidio;
    }

    public double getSubsidioPeriodosAnteriores() {
        return subsidioPeriodosAnteriores;
    }

    public void setSubsidioPeriodosAnteriores(double subsidioPeriodosAnteriores) {
        this.subsidioPeriodosAnteriores = subsidioPeriodosAnteriores;
    }

    public double getIsrSalarioPorPagar() {
        return isrSalarioPorPagar;
    }

    public void setIsrSalarioPorPagar(double isrSalarioPorPagar) {
        this.isrSalarioPorPagar = isrSalarioPorPagar;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ImpuestoEntity{" +
                "clienteId=" + clienteId +
                ", mesId=" + mesId +
                ", periodoId=" + ejercicioId +
                ", ingresoMesAnterior=" + ingresoMesAnterior +
                ", ingresosCobrados=" + ingresosCobrados +
                ", deduccionesMesAnterior=" + deduccionesMesAnterior +
                ", deduccionesAutorizadasPagadas=" + deduccionesAutorizadasPagadas +
                ", gastosNomina=" + gastosNomina +
                ", depreciacion=" + depreciacion +
                ", deduccionesDelMes=" + deduccionesDelMes +
                ", totalDePeriodo=" + totalDePeriodo +
                ", basePagoProvisional=" + basePagoProvisional +
                ", limiteInferior=" + limiteInferior +
                ", excedenteLimiteInferior=" + excedenteLimiteInferior +
                ", porcentajeExcedenteLimiteInferior=" + porcentajeExcedenteLimiteInferior +
                ", impuestoMarginal=" + impuestoMarginal +
                ", cuotaFija=" + cuotaFija +
                ", impuesto=" + impuesto +
                ", reduccionDeTreinta=" + reduccionDeTreinta +
                ", impuestoReducido=" + impuestoReducido +
                ", impuestoACargo=" + impuestoACargo +
                ", pagosProvisionalesConAnterioridad=" + pagosProvisionalesConAnterioridad +
                ", diezRetencionMesesAnteriores=" + diezRetencionMesesAnteriores +
                ", diezRetenidoMes=" + diezRetenidoMes +
                ", impuestoACargoMes=" + impuestoACargoMes +
                ", compensacion=" + compensacion +
                ", actualizaciones=" + actualizaciones +
                ", recargos=" + recargos +
                ", baseDelImpuesto=" + baseDelImpuesto +
                ", ivaCobrado=" + ivaCobrado +
                ", retenciones=" + retenciones +
                ", ivaAcreditable=" + ivaAcreditable +
                ", ivaAFavorPeriodo=" + ivaAFavorPeriodo +
                ", SaldoAFavor=" + SaldoAFavor +
                ", getImpuestoACargo=" + impuestoCargoFavor +
                ", isrSalarios=" + isrSalarios +
                ", subsidio=" + subsidio +
                ", subsidioPeriodosAnteriores=" + subsidioPeriodosAnteriores +
                ", isrSalarioPorPagar=" + isrSalarioPorPagar +
                ", total=" + total +
                '}';
    }
}

package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;

import java.sql.Connection;

public class ImpuestoServiceImpl implements ImpuestoService {
    private final FacturaServiceImpl facturaService;
    private final NominaServiceImpl nominaService;
    private final DepreciacionServiceImpl depreciacionService;
    private final TablaIsrServiceImpl tablaIsrService;

    public ImpuestoServiceImpl(Connection connection) {
        this.facturaService = new FacturaServiceImpl(connection);
        this.nominaService = new NominaServiceImpl(connection);
        this.depreciacionService = new DepreciacionServiceImpl(connection);
        this.tablaIsrService = new TablaIsrServiceImpl(connection);
    }

    @Override
    public ImpuestoEntity calcularImpuesto(ImpuestoEntity impuestoEntity) {

        impuestoEntity.setBasePagoProvisional(obtenerIngresos(impuestoEntity) - obtenerDeducciones(impuestoEntity));
        impuestoEntity.setImpuestoReducido(obtenerImpuestoReducido(impuestoEntity));
        impuestoEntity.setImpuestoACargoMes(obtenerImpuestoCargoMes(impuestoEntity));
        impuestoEntity.setImpuestoCargoFavor(obtenerImpuestoCargoFavor(impuestoEntity));
        impuestoEntity.setIsrSalarioPorPagar(obtenerIsrSalarioMensualPorPagar(impuestoEntity));

        impuestoEntity.setTotal(impuestoEntity.getImpuestoACargoMes() + impuestoEntity.getImpuestoCargoFavor() + impuestoEntity.getIsrSalarioPorPagar());

        return impuestoEntity;
    }

    public double obtenerIsrSalarioMensualPorPagar(ImpuestoEntity impuestoEntity) {
        double isrSalarioMensualPorPagar = 0;

        try {
            impuestoEntity.setIsrSalarios(nominaService.obtenerIsrMensual(impuestoEntity));
            impuestoEntity.setSubsidio(nominaService.obtenerSubsidioMensual(impuestoEntity));
            impuestoEntity.setSubsidioPeriodosAnteriores(0);

            isrSalarioMensualPorPagar = impuestoEntity.getIsrSalarios() - impuestoEntity.getSubsidio();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isrSalarioMensualPorPagar;
    }

    public double obtenerImpuestoCargoFavor(ImpuestoEntity impuestoEntity) {
        double impuestoCargoFavor = 0;

        impuestoEntity.setBaseDelImpuesto(facturaService.obtenerSubtotalIngresos(impuestoEntity));
        impuestoEntity.setIvaCobrado(facturaService.obtenerIvaIngresos(impuestoEntity));
        impuestoEntity.setRetenciones(facturaService.obtenerIvaRetenidoIngresos(impuestoEntity));
        impuestoEntity.setIvaAcreditable(facturaService.obtenerIvaEgresos(impuestoEntity));
        impuestoEntity.setIvaAFavorPeriodo(impuestoEntity.getIvaCobrado() - impuestoEntity.getIvaAcreditable());


        if (impuestoEntity.getMesId() == 1 || impuestoEntity.getIvaAFavorPeriodo() < 0) {
            impuestoEntity.setSaldoAFavor(0);
        } else {
            impuestoEntity.setSaldoAFavor(obtenerSaldoFavorPendiente(impuestoEntity));
        }

        if (impuestoEntity.getIvaAFavorPeriodo() - impuestoEntity.getSaldoAFavor() < 1) {
            impuestoEntity.setImpuestoCargoFavor(0);
        } else {
            impuestoEntity.setImpuestoCargoFavor(impuestoEntity.getIvaAFavorPeriodo() - impuestoEntity.getSaldoAFavor());
            impuestoCargoFavor = impuestoEntity.getIvaAFavorPeriodo() - impuestoEntity.getSaldoAFavor();
        }

        return impuestoCargoFavor;
    }

    public double obtenerSaldoFavorPendiente(ImpuestoEntity impuestoEntity) {
        double saldoFavorPendiente = 0;

        try {
            int mesId = impuestoEntity.getMesId();
            impuestoEntity.setMesId(mesId - 1);

            double ivaCobrado = facturaService.obtenerIvaIngresos(impuestoEntity);
            double ivaAcreditable = facturaService.obtenerIvaEgresos(impuestoEntity);

            saldoFavorPendiente = ivaCobrado - ivaAcreditable;

            impuestoEntity.setMesId(mesId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saldoFavorPendiente;
    }

    public double obtenerImpuestoCargoMes(ImpuestoEntity impuestoEntity) {
        double impuestoCargoMes = 0;

        if (impuestoEntity.getMesId() <= 0) {
            impuestoEntity.setPagosProvisionalesConAnterioridad(0);
        }

        impuestoEntity.setDiezRetencionMesesAnteriores(0);
        impuestoEntity.setDiezRetenidoMes(0);

        double sumaImpuestoACargo = impuestoEntity.getImpuestoACargo() + impuestoEntity.getPagosProvisionalesConAnterioridad()
                + impuestoEntity.getDiezRetencionMesesAnteriores() + impuestoEntity.getDiezRetenidoMes();

        if (sumaImpuestoACargo < 1) {
            impuestoEntity.setImpuestoACargoMes(0);
            impuestoCargoMes = 0;
        } else {
            impuestoEntity.setImpuestoACargoMes(sumaImpuestoACargo);
            impuestoCargoMes = sumaImpuestoACargo;
        }

        return impuestoCargoMes;
    }

    double obtenerImpuestoReducido(ImpuestoEntity impuestoEntity) {
        TablaIsrEntity tablaIsr = obtenerLimiteIsr(impuestoEntity);

        impuestoEntity.setLimiteInferior(tablaIsr.getLimiteInferior());
        impuestoEntity.setPorcentajeExcedenteLimiteInferior(tablaIsr.getPorcentajeExcedente() / 100);
        impuestoEntity.setCuotaFija(tablaIsr.getCuotaFija());

        impuestoEntity.setExcedenteLimiteInferior(impuestoEntity.getBasePagoProvisional() - impuestoEntity.getLimiteInferior());
        impuestoEntity.setImpuestoMarginal(impuestoEntity.getExcedenteLimiteInferior() * impuestoEntity.getPorcentajeExcedenteLimiteInferior());
        impuestoEntity.setImpuesto(impuestoEntity.getImpuestoMarginal() + impuestoEntity.getCuotaFija());
        impuestoEntity.setReduccionDeTreinta(0);
        impuestoEntity.setImpuestoReducido(impuestoEntity.getImpuesto());

        impuestoEntity.setImpuestoACargo(impuestoEntity.getImpuestoReducido());

        return impuestoEntity.getImpuestoReducido();
    }

    private TablaIsrEntity obtenerLimiteIsr(ImpuestoEntity impuestoEntity) {
        TablaIsrEntity tablaIsr;

        tablaIsr = tablaIsrService.obtenerLimiteIsr(impuestoEntity.getBasePagoProvisional(), impuestoEntity.getMesId());

        return tablaIsr;
    }

    private double obtenerDeducciones(ImpuestoEntity impuestoEntity) {
        double subtotalDeduccionesAnterior = 0;
        double subtotalDeducciones = 0;
        double depreciacion = 0;
        double nomina = 0;

        try {
            subtotalDeducciones = facturaService.obtenerSubtotalDeducciones(impuestoEntity);
            impuestoEntity.setDeduccionesAutorizadasPagadas(subtotalDeducciones);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (impuestoEntity.getMesId() > 1) {
            try {
                impuestoEntity.setMesId(impuestoEntity.getMesId() - 1);
                subtotalDeduccionesAnterior = facturaService.obtenerSubtotalDeducciones(impuestoEntity);
                impuestoEntity.setDeduccionesMesAnterior(subtotalDeduccionesAnterior);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            depreciacion = depreciacionService.obtenerDepreciacionMensual(impuestoEntity);
            impuestoEntity.setDepreciacion(depreciacion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            nomina = nominaService.obtenerNominaMensual(impuestoEntity);
            impuestoEntity.setGastosNomina(nomina);
        } catch (Exception e) {
            e.printStackTrace();
        }

        impuestoEntity.setDeduccionesDelMes(subtotalDeducciones + subtotalDeduccionesAnterior + depreciacion + nomina);

        return subtotalDeducciones + subtotalDeduccionesAnterior + depreciacion + nomina;
    }

    private double obtenerIngresos(ImpuestoEntity impuestoEntity) {
        double subtotalIngresosAnterior = 0;
        double subtotalIngresos = 0;

        try {
            subtotalIngresos = facturaService.obtenerSubtotalIngresos(impuestoEntity);
            impuestoEntity.setIngresosCobrados(subtotalIngresos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (impuestoEntity.getMesId() > 1) {
            try {
                impuestoEntity.setMesId(impuestoEntity.getMesId() - 1);
                subtotalIngresosAnterior = facturaService.obtenerSubtotalIngresos(impuestoEntity);
                impuestoEntity.setIngresoMesAnterior(subtotalIngresosAnterior);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        impuestoEntity.setTotalDePeriodo(subtotalIngresos + subtotalIngresosAnterior);

        return subtotalIngresos + subtotalIngresosAnterior;
    }


}

package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.EmpleadoEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaEntity;
import com.ortega.webapp.servlet.swfe.models.nomina.NominaFormDTO;
import com.ortega.webapp.servlet.swfe.repositories.EmpleadoRepositoryImpl;
import com.ortega.webapp.servlet.swfe.repositories.NominaRepositoryImpl;
import com.ortega.webapp.servlet.swfe.repositories.TablaIsrRepositoryImpl;
import jakarta.ws.rs.PUT;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NominaServiceImpl implements NominaService {
    private final TablaIsrRepositoryImpl tablaIsrRepository;
    private final NominaRepositoryImpl nominaRepository;

    public NominaServiceImpl(Connection connection) {
        this.tablaIsrRepository = new TablaIsrRepositoryImpl(connection);
        this.nominaRepository = new NominaRepositoryImpl(connection);
    }

    @Override
    public NominaEntity obtenerNominaPorId(int nominaId) throws SQLException {
        NominaEntity nominaEntity = nominaRepository.obtenerNomina(nominaId);

        return nominaEntity;
    }

    @Override
    public List<NominaEntity> obtenerListaNominas(NominaFormDTO nomina) throws SQLException {
        try {
            List<NominaEntity> nominaEntity = nominaRepository.obtenerNomina(nomina);

            return nominaEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actualizarGuardarNomina(NominaFormDTO nomina) throws SQLException {
        System.out.println("NominaServiceImpl.actualizarGuardarNomina " + nomina);

        if (nomina.getNominaId() == 0) {

            try {
                NominaEntity nominaEntity = calcularNomina(nomina);
                System.out.println("NominaServiceImpl.GuardarNomina nominaEntity " + nominaEntity);

                nominaRepository.guardarNomina(nominaEntity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            NominaEntity nominaEntity = new NominaEntity();

            double isr = calcularIsr(nomina.getSueldo() * nomina.getDiasLaborados());
            double imss = calcularImss(nomina);

            nominaEntity.setNominaid(nomina.getNominaId());
            nominaEntity.setImssNomina(imss);
            nominaEntity.setIsrSalario(isr);
            nominaEntity.setSalarioNeto(nomina.getSueldo() * nomina.getDiasLaborados() - isr - imss);
            nominaEntity.setDiasLaborados(nomina.getDiasLaborados());

            nominaRepository.actualizarNomina(nominaEntity);
        }
    }

    @Override
    public void eliminarNomina(int nominaId) throws SQLException {

        nominaRepository.eliminarNomina(nominaId);
    }

    private NominaEntity calcularNomina(NominaFormDTO nomina) throws SQLException {
        NominaEntity nominaEntity = new NominaEntity();
        double percepciones = nomina.getSueldo() * nomina.getDiasLaborados();

        System.out.println("NominaServiceImpl.calcularNomina percepciones " + percepciones);

        nominaEntity.setEmpeladoId(String.valueOf(nomina.getEmpleadoId()));
        nominaEntity.setSalario(nomina.getSueldo());
        nominaEntity.setSubsidioNomina(234.20);

        double isr = calcularIsr(percepciones);
        double imss = calcularImss(nomina);

        nominaEntity.setIsrSalario(isr);
        nominaEntity.setImssNomina(imss);
        nominaEntity.setSalarioNeto(percepciones - isr - imss);
        nominaEntity.setClienteId(nomina.getClienteId());
        nominaEntity.setMesId(nomina.getMes());
        nominaEntity.setNumeroQuincena(nomina.getQuincena());
        nominaEntity.setEjercicioFiscalId(nomina.getEjercicioFiscal());
        nominaEntity.setDiasLaborados(nomina.getDiasLaborados());

        return nominaEntity;
    }

    @Override
    public double obtenerNominaMensual(ImpuestoEntity impuesto) throws SQLException {
        double nominaMensual = 0;

        try {
            nominaMensual = nominaRepository.obtenerNominaMensual(impuesto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nominaMensual;
    }

    @Override
    public double obtenerIsrMensual(ImpuestoEntity impuesto) throws SQLException {
        double isrMensual = 0;

        try {
            isrMensual = nominaRepository.obtenerIsrMensual(impuesto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isrMensual;
    }

    @Override
    public double obtenerSubsidioMensual(ImpuestoEntity impuesto) throws SQLException {
        double subsidioMensual = 0;

        try {
            subsidioMensual = nominaRepository.obtenerSubsidioMensual(impuesto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subsidioMensual;
    }

    public double calcularImss(NominaFormDTO nomina) throws SQLException {
        int diasLaborados = nomina.getDiasLaborados();

        double salarioBaseCotizacion = 1.0493 * nomina.getSueldo();
        double exdedentePatronal = 0;

        if (salarioBaseCotizacion > 339.42) {
            exdedentePatronal = salarioBaseCotizacion * diasLaborados * (0.40 / 100);
        }

        double prestacionesDinero = salarioBaseCotizacion * diasLaborados * (0.25 / 100);
        double prestacionoEnEspecie = salarioBaseCotizacion * diasLaborados * (0.375 / 100);
        double invalidezVida = salarioBaseCotizacion * diasLaborados * (0.625 / 100);
        double cesantiaVejez = salarioBaseCotizacion * diasLaborados * (1.125 / 100);

        return exdedentePatronal + prestacionesDinero + prestacionoEnEspecie + invalidezVida + cesantiaVejez;
    }

    public double calcularIsr(double percepciones) throws SQLException {
        List<TablaIsrEntity> tablaIsr = tablaIsrRepository.listarTablasIsr("QUINCENAL");
        TablaIsrEntity tablaIsrEntitySelecionada = null;

        System.out.println("Percepciones: " + percepciones);

        for (TablaIsrEntity tablaIsrEntity : tablaIsr) {
            if (percepciones >= tablaIsrEntity.getLimiteInferior()) {
                if (percepciones <= tablaIsrEntity.getLimiteSuperior() || tablaIsrEntity.getLimiteSuperior() == 0) {
                    tablaIsrEntitySelecionada = tablaIsrEntity;
                    break;
                }
            }
        }

        System.out.println("NominaServiceImpl.calcularIsr tablaIsrEntitySelecionada " + tablaIsrEntitySelecionada);

        double excedentePocentaje = percepciones - tablaIsrEntitySelecionada.getLimiteInferior();
        double isrExcedente = excedentePocentaje * (tablaIsrEntitySelecionada.getPorcentajeExcedente() / 100);
        double isrDeterminado = isrExcedente + tablaIsrEntitySelecionada.getCuotaFija();

        return isrDeterminado - 234.20;
    }

}

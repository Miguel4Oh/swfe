package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.ConceptoDepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.DepreciacionEntity;
import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.repositories.ConceptoDepreciacionRepositotyImpl;
import com.ortega.webapp.servlet.swfe.repositories.DepreciacionRepositoryImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DepreciacionServiceImpl implements DepreciacionService {
    private final DepreciacionRepositoryImpl depreciacionRepository;
    private final ConceptoDepreciacionRepositotyImpl conceptoDepreciacionRepositoty;

    public DepreciacionServiceImpl(Connection connection) {
        this.depreciacionRepository = new DepreciacionRepositoryImpl(connection);
        this.conceptoDepreciacionRepositoty = new ConceptoDepreciacionRepositotyImpl(connection);
    }

    @Override
    public List<DepreciacionEntity> obtenerListaDepreciaciones(int clienteId, String filtroBusqueda, int mesId) {
        try {
            return depreciacionRepository.listarDepreciaciones(clienteId, filtroBusqueda, mesId);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<DepreciacionEntity> obtenerDepreciacion(int depreciacionId) {
        return Optional.empty();
    }

    @Override
    public void actualizarGuardarDepreciacion(DepreciacionEntity depreciacion, String ejercicioAnterior, int mesId) throws SQLException {
        System.out.println("entre a actualizarGuardarDepreciacion");
        System.out.println("Depreciacion service: " + depreciacion);
        ConceptoDepreciacionEntity conceptoDepreciacion;

        if (depreciacion.getDepreciacionId() > 0) {
            try {
                conceptoDepreciacion = conceptoDepreciacionRepositoty.obtenerConceptoPorNombre(depreciacion.getConceptoDepreciacion());
                depreciacion.setConceptoDepreciacion(conceptoDepreciacion.getNombreDepreciacion());
                depreciacion.setPorcentajeDepreciacion(conceptoDepreciacion.getPorcentajeDepreciacion());

                calcularDepreciacion(depreciacion, ejercicioAnterior);
                depreciacionRepository.actualizarDepreciacion(depreciacion);
            } catch (Exception e) {
                throw new ServiceJdbcException(e.getMessage(), e.getCause());
            }
        } else {
            try {
                conceptoDepreciacion = conceptoDepreciacionRepositoty.obtenerConcepto(Integer.parseInt(depreciacion.getConceptoDepreciacion()));
                depreciacion.setConceptoDepreciacion(conceptoDepreciacion.getNombreDepreciacion());
                depreciacion.setPorcentajeDepreciacion(conceptoDepreciacion.getPorcentajeDepreciacion());

                calcularDepreciacion(depreciacion, ejercicioAnterior);
                depreciacionRepository.guardarDepreciacion(depreciacion, mesId);
            } catch (Exception e) {
                throw new ServiceJdbcException(e.getMessage(), e.getCause());
            }
        }
    }

    @Override
    public void eliminarDepreciacion(int depreciacionId) {
        try {
            depreciacionRepository.eliminarDepreciacion(depreciacionId);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public double obtenerDepreciacionMensual(ImpuestoEntity impuestoEntity) {

        try {
            return depreciacionRepository.obtenerDepreciacionMensual(impuestoEntity.getClienteId(), impuestoEntity.getMesId());
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    private void calcularDepreciacion(DepreciacionEntity depreciacion, String ejercicioActual) throws SQLException {
        String ejercicioAnterior = String.valueOf(Integer.parseInt(ejercicioActual) - 1);

        depreciacion.setMesesUsoEjercicioAnterior(calcularMesesDeUsoEjercicioAnterior(depreciacion.getFechaAdquisicion(), ejercicioAnterior));
        depreciacion.setDepreciacionAcumuladaInicio(calcularDepreciacionAcumuladaInicio(depreciacion));
        depreciacion.setDepreciacionPendienteInicio(depreciacion.getMontoInversion() - depreciacion.getDepreciacionAcumuladaInicio());
        depreciacion.setMesesUsoEjercicioActual(calcularMesesDeUsoEjercicioActual(depreciacion, ejercicioActual));
        depreciacion.setMontoDeducible(calcualrDepreciacionDeducible(depreciacion));

        depreciacion.setDepreciacionAcumuladaFin(depreciacion.getDepreciacionAcumuladaInicio() + depreciacion.getMontoDeducible());
        depreciacion.setDepreciacionPendienteFin(depreciacion.getMontoInversion() - depreciacion.getDepreciacionAcumuladaFin());
        depreciacion.setDepreciacionMensual((int) (depreciacion.getMontoDeducible() / depreciacion.getMesesUsoEjercicioActual()));
        depreciacion.setEjercicioFiscal(Integer.parseInt(ejercicioActual));
    }


    public int calcualrDepreciacionDeducible(DepreciacionEntity depreciacion) {
        double montoInversion = depreciacion.getMontoInversion();
        double porcentajeDepreciacion = depreciacion.getPorcentajeDepreciacion();
        int mesesUsoEjercicioActual = depreciacion.getMesesUsoEjercicioActual();

        int deducible = (int) Math.round(((montoInversion * (porcentajeDepreciacion / 100)) / 12) * mesesUsoEjercicioActual);

        if (deducible < depreciacion.getDepreciacionPendienteInicio() - 1) {
            return deducible;
        } else {
            return (int) Math.round(depreciacion.getDepreciacionPendienteInicio() - 1);
        }
    }

    public int calcularMesesDeUsoEjercicioActual(DepreciacionEntity depreciacion, String ejercicioActual) {
        LocalDate fechaAdquisicion = depreciacion.getFechaAdquisicion().toLocalDate();

        if (fechaAdquisicion.getYear() == Integer.parseInt(ejercicioActual)) {
            return 12 - fechaAdquisicion.getMonthValue();
        }

        return 12;
    }

    public double calcularDepreciacionAcumuladaInicio(DepreciacionEntity depreciacion) {
        double depreciacionAcumuladaInicio;

        double porcentajeDepreciacion = depreciacion.getPorcentajeDepreciacion();
        double montoInversion = depreciacion.getMontoInversion();
        int mesesDeUsoEjercicioAnterior = depreciacion.getMesesUsoEjercicioAnterior();

        double decimalPorcentajeDepreciacion = porcentajeDepreciacion / 100;

        depreciacionAcumuladaInicio = ((montoInversion * decimalPorcentajeDepreciacion) / 12) * mesesDeUsoEjercicioAnterior;

        return Math.round(depreciacionAcumuladaInicio);
    }

    public int calcularMesesDeUsoEjercicioAnterior(Date fechaAdquisicion, String ejercicioAnterior) {
        LocalDate fechaAdquisicionLocalDate = fechaAdquisicion.toLocalDate();

        LocalDate fechaFinalEjercicioAnterior = LocalDate.of(Integer.parseInt(ejercicioAnterior), 12, 31);

        long diferenciaMeses = fechaAdquisicionLocalDate.until(fechaFinalEjercicioAnterior).toTotalMonths();

        if (diferenciaMeses < 0) {
            return 0;
        }

        return (int) diferenciaMeses;
    }
}

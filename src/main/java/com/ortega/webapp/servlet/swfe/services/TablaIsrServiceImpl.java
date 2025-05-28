package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;
import com.ortega.webapp.servlet.swfe.repositories.TablaIsrRepository;
import com.ortega.webapp.servlet.swfe.repositories.TablaIsrRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TablaIsrServiceImpl implements TablaIsrService {
    private final TablaIsrRepositoryImpl tablaIsrRepository;

    public TablaIsrServiceImpl(Connection connection) {
        this.tablaIsrRepository = new TablaIsrRepositoryImpl(connection);
    }

    @Override
    public List<TablaIsrEntity> obtenerListaTablasIsr(String mesTabla) {
        try {
            return tablaIsrRepository.listarTablasIsr(mesTabla);
        } catch (SQLException exception) {
            throw new ServiceJdbcException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public Optional<TablaIsrEntity> obtenerTablaIsr(Long tablaId) {
        try {
            return Optional.ofNullable((TablaIsrEntity) tablaIsrRepository.obtenerTablaIsr(tablaId));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void actualizarGuardarTablaIsr(TablaIsrEntity tablaIsr) {
        if (tablaIsr.getTablaId() > 0) {
            try {
                tablaIsrRepository.actualizarTablaIsr(tablaIsr);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        } else {
            try {
                tablaIsrRepository.guardarTablaIsr(tablaIsr);
            } catch (SQLException throwables) {
                throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
            }
        }
    }

    @Override
    public void eliminarTablaIsr(Long tablaId) {
        try {
            tablaIsrRepository.eliminarTablaIsr(tablaId);
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public TablaIsrEntity obtenerLimiteIsr(double monto, int mesId) {
        TablaIsrEntity tablaIsr = new TablaIsrEntity();

        try {
            tablaIsr = tablaIsrRepository.obtenerLimiteIsr(monto, mesId);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }

        return tablaIsr;
    }
}

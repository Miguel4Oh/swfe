package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.ImpuestoEntity;
import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;

import java.sql.SQLException;
import java.util.List;

public interface TablaIsrRepository <T>{
    List<T> listarTablasIsr(String mesTabla) throws SQLException;
    T obtenerTablaIsr (Long tablaIsrId) throws SQLException;
    void actualizarTablaIsr (TablaIsrEntity tablaIsr) throws SQLException;
    void guardarTablaIsr (TablaIsrEntity tablaIsr) throws SQLException;
    void eliminarTablaIsr(Long tablaIsrId) throws SQLException;
    TablaIsrEntity obtenerLimiteIsr (double monto, int mesId);

}

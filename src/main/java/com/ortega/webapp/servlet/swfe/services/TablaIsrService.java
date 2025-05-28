package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.TablaIsrEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TablaIsrService {
    List<TablaIsrEntity> obtenerListaTablasIsr (String mesTabla);
    Optional<TablaIsrEntity> obtenerTablaIsr (Long tablaId);
    void actualizarGuardarTablaIsr (TablaIsrEntity tablaIsr);
    void eliminarTablaIsr(Long tablaId);
    TablaIsrEntity obtenerLimiteIsr (double monto, int mesId);
}

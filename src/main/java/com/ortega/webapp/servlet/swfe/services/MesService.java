package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.MesEntity;

import java.util.List;

public interface MesService {
    List<MesEntity> obtenerListaMeses(String consulta);
    MesEntity obtenerMesPorNombre(String nombreMes);
    MesEntity obtenerMesPorId(int mesId);

}

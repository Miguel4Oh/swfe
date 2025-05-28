package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.UserEntity;

import java.util.List;

public interface UsuarioService {

    List<UserEntity> obtenerListaUsuarios() throws Exception;
    UserEntity obtenerUsuario(int usuarioId) throws Exception;
    boolean guardarUsuario(UserEntity usuario) throws Exception;
    void actualizarUsuario(UserEntity usuario) throws Exception;
    void eliminarUsuario(int usuarioId) throws Exception;
    void cambiarContrasena(String usuario, String contrasenaActual, String contrasenaNueva) throws Exception;
}

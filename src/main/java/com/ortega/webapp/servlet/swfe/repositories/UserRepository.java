package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository <T> {
    T findByUsernameAndPass(String username, String password) throws SQLException;
    List<T> listarUsuarios() throws SQLException;
    UserEntity obtenerUsuario(String usuarioId) throws SQLException;
    void guardarUsuario(T usuario) throws SQLException;
    void actualizarUsuario(T usuario) throws SQLException;
    void eliminarUsuario(int usuarioId) throws SQLException;
    void cambiarContrasena(String usuario, String contrasenaActual, String contrasenaNueva) throws SQLException;
}

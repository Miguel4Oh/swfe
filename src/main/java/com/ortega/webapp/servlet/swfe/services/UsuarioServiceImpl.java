package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.UserEntity;
import com.ortega.webapp.servlet.swfe.repositories.UserRepository;
import com.ortega.webapp.servlet.swfe.repositories.UserRepositoryImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private UserRepository userRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
    }

    @Override
    public List<UserEntity> obtenerListaUsuarios() throws Exception {
        List<UserEntity> usuarios = new ArrayList<>();

        try {
            usuarios = userRepository.listarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public UserEntity obtenerUsuario(int usuarioId) throws Exception {
        UserEntity usuario = null;

        try {
            if (usuarioId > 0) {
                usuario = userRepository.obtenerUsuario(String.valueOf(usuarioId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean guardarUsuario(UserEntity usuario) throws Exception {
        boolean resultado = false;

        try {
            UserEntity userEntity = userRepository.obtenerUsuario(usuario.getUsername());

            if (userEntity == null) {
                userRepository.guardarUsuario(usuario);
                resultado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("resultado: " + resultado);
        return resultado;
    }

    @Override
    public void actualizarUsuario(UserEntity usuario) throws Exception {
        try {
            userRepository.actualizarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int usuarioId) throws Exception {
        try {
            if (usuarioId > 0) {
                userRepository.eliminarUsuario(usuarioId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cambiarContrasena(String usuario, String contrasenaActual, String contrasenaNueva) throws Exception {

        try {
            userRepository.cambiarContrasena(usuario, contrasenaActual, contrasenaNueva);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

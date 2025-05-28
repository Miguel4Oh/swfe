package com.ortega.webapp.servlet.swfe.repositories;

import com.ortega.webapp.servlet.swfe.models.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository<UserEntity> {
    private Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserEntity findByUsernameAndPass(String username, String password) throws SQLException {
        UserEntity user = null;

        String query = "SELECT usr.usuario_id, usr.nombre_usuario, usr.contrasena_usuario, rol.rol_id " +
                "FROM usuarios usr INNER JOIN usuarios_roles rol ON usr.usuario_id = rol.usuario_id " +
                "WHERE usr.usuario_id = ? AND usr.contrasena_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()){
                    user = new UserEntity();

                    user.setUsuarioId(resultSet.getInt("usuario_id"));
                    user.setUsername(resultSet.getString("nombre_usuario"));
                    user.setPassword(resultSet.getString("contrasena_usuario"));
                    user.setRoleId(resultSet.getString("rol_id"));
                }
            }
        }
        return user;
    }

    @Override
    public List<UserEntity> listarUsuarios() throws SQLException {
        List<UserEntity> usuarios = new ArrayList<>();

        String query = "SELECT usr.usuario_id, usr.nombre_usuario, usr.contrasena_usuario, rol.rol_id " +
                "FROM usuarios usr INNER JOIN usuarios_roles rol ON usr.usuario_id = rol.usuario_id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserEntity user = new UserEntity();
                    user.setUsuarioId(resultSet.getInt("usuario_id"));
                    user.setUsername(resultSet.getString("nombre_usuario"));
                    user.setPassword(resultSet.getString("contrasena_usuario"));
                    user.setRoleId(resultSet.getString("rol_id"));
                    usuarios.add(user);
                }
            }
        }

        return usuarios;
    }

    @Override
    public UserEntity obtenerUsuario(String usuarioId) throws SQLException {
        UserEntity user = null;

        String query = "SELECT usr.usuario_id, usr.nombre_usuario, usr.contrasena_usuario, rol.rol_id " +
                "FROM usuarios usr INNER JOIN usuarios_roles rol ON usr.usuario_id = rol.usuario_id " +
                "WHERE usr.usuario_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuarioId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserEntity();
                    user.setUsuarioId(resultSet.getInt("usuario_id"));
                    user.setUsername(resultSet.getString("nombre_usuario"));
                    user.setPassword(resultSet.getString("contrasena_usuario"));
                    user.setRoleId(resultSet.getString("rol_id"));
                }
            }
        }

        return user;
    }

    @Override
    public void guardarUsuario(UserEntity usuario) throws SQLException {
        System.out.println("guardarUsuario: " + usuario);
        String query = "INSERT INTO usuarios (usuario_id, nombre_usuario, contrasena_usuario) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuario.getUsuarioId());
            statement.setString(2, usuario.getUsername());
            statement.setString(3, usuario.getPassword());
            statement.executeUpdate();
        }

        String query2 = "INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query2)) {
            statement.setInt(1, usuario.getUsuarioId());
            statement.setString(2, usuario.getRoleId());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizarUsuario(UserEntity usuario) throws SQLException {
        System.out.println("actualizarUsuario: " + usuario);
        String query = "UPDATE usuarios SET usuario_id = ?, nombre_usuario = ? WHERE usuario_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuario.getUsuarioId());
            statement.setString(2, usuario.getUsername());
            statement.setInt(3, usuario.getUsuarioId());
            statement.executeUpdate();
        }

        String query2 = "UPDATE usuarios_roles SET rol_id = ? WHERE usuario_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query2)) {
            statement.setString(1, usuario.getRoleId());
            statement.setInt(2, usuario.getUsuarioId());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarUsuario(int usuarioId) throws SQLException {
        String query = "DELETE FROM usuarios_roles WHERE usuario_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuarioId);
            statement.executeUpdate();
        }

        String query2 = "DELETE FROM usuarios WHERE usuario_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query2)) {
            statement.setInt(1, usuarioId);
            statement.executeUpdate();
        }
    }

    @Override
    public void cambiarContrasena(String usuario, String contrasenaActual, String contrasenaNueva) throws SQLException {
        String query = "UPDATE usuarios SET contrasena_usuario = ? WHERE nombre_usuario = ? AND contrasena_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contrasenaNueva);
            statement.setString(2, usuario);
            statement.setString(3, contrasenaActual);
            statement.executeUpdate();
        }
    }
}

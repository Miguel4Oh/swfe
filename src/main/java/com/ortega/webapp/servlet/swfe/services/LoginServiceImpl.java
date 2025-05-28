package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.UserEntity;
import com.ortega.webapp.servlet.swfe.repositories.UserRepository;
import com.ortega.webapp.servlet.swfe.repositories.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    private UserRepository<UserEntity> userRepository;

    public LoginServiceImpl(Connection connection) {
        this.userRepository = new UserRepositoryImpl(connection);
    }

    @Override
    public Optional<UserEntity> login(String username, String password) {

        try {
            return Optional.ofNullable(userRepository.findByUsernameAndPass(username, password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}

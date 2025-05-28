package com.ortega.webapp.servlet.swfe.services;

import com.ortega.webapp.servlet.swfe.models.UserEntity;

import java.util.Optional;

public interface LoginService {

    Optional<UserEntity> login(String username, String password);
}

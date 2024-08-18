package com.projet.app.services;

import com.projet.app.dto.RegisterDto;
import com.projet.app.model.UserEntity;

public interface AuthService {
	UserEntity createUser(RegisterDto registerDto);
}

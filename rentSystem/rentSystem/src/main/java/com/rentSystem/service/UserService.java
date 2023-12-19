package com.rentSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rentSystem.dto.UserRegistrationDto;
import com.rentSystem.model.User;

public interface UserService extends UserDetailsService{
	 User save(UserRegistrationDto registrationDto);
	}

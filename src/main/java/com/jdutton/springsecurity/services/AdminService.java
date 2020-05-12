package com.jdutton.springsecurity.services;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.jdutton.springsecurity.data.UserRepository;
import com.jdutton.springsecurity.domain.User;

@Service
public class AdminService {

	private UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Secured({"ROLE_ADMIN", "ROLE_SUPERUSER"})
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}

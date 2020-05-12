package com.jdutton.springsecurity.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdutton.springsecurity.data.SecurizedUser;
import com.jdutton.springsecurity.data.UserRepository;
import com.jdutton.springsecurity.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		final User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User name not found");
		}

		return new ModelMapper().map(user, SecurizedUser.class);
		// return new SecurizedUser();
	}

}

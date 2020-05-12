package com.jdutton.springsecurity.data;

import org.springframework.security.core.userdetails.UserDetails;

import com.jdutton.springsecurity.domain.User;

public class SecurizedUser extends User implements UserDetails {

	private static final long serialVersionUID = 5385095047851544522L;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

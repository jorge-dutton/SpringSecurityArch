package com.jdutton.springsecurity.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class WebSecurityTest {

	@Test
	void encrypt_password() {
		PasswordEncoder passEnc = new BCryptPasswordEncoder();
		System.out.println(passEnc.encode("jdutton"));

	}

}

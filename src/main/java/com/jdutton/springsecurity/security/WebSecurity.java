package com.jdutton.springsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	public WebSecurity(final UserDetailsService userDetailsService,
			final PasswordEncoder passwordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// The following is a basic inMemory authentication model
		// it's not to be used in enterprise apps
		//@formatter:off
//		auth.inMemoryAuthentication()
//			.passwordEncoder(passwordEncoder)
//			.withUser("jdutton")
//			.password("$2a$10$9WujuwN/5rOHus9LPv2Z.e5OulQoceJ/TUlAFIpkeOqPlfrNT.gWW")
//			.roles("USER", "ADMIN");
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		//@formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Following disables CSRF validation (not recommended) and
		// givez Authorization to any request with role ADMIN to access /admin/
		// path
		// and for the rest of the requests from role "USER"
		//@formatter:off
		http.authorizeRequests()
			.antMatchers("/admin/**")
			.hasAnyRole("ADMIN")
			.anyRequest()
			.hasAnyRole("USER")
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/dashboard")
			.permitAll()
			.and()
			.sessionManagement()
			.maximumSessions(1);
		//@formatter:on
	}
}

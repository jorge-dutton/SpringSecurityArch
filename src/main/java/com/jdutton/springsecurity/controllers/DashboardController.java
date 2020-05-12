package com.jdutton.springsecurity.controllers;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.jdutton.springsecurity.domain.User;
import com.jdutton.springsecurity.services.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardController {

	private AdminService adminService;

	public DashboardController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@GetMapping("/dashboard")
	public String getDashboard(@AuthenticationPrincipal final User user,
			final ModelMap model) {
		model.put("user", user);
		List<User> users = adminService.findAllUsers();
		log.info("Users " + users);
		return "dashboard";
	}
}

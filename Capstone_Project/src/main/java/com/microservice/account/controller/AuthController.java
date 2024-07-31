package com.microservice.account.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.UserInfo;
import com.microservice.account.service.UserInfoService;

@RestController
public class AuthController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/capstone/login")
	@CrossOrigin(origins = {"http://localhost:3000"})
	public UserInfo login(Principal principal) {
		
		String username = principal.getName();
		
		UserInfo userInfo = (UserInfo) userInfoService.loadUserByUsername(username);
		
		return userInfo;
	}
}

package com.smsapi.masterdbapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.masterdbapi.model.UserModel;
import com.smsapi.masterdbapi.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserModel authDTO) {
		UserModel authOTPVerifyResponseModel = authService.login(authDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	}
	

	
	
	
}
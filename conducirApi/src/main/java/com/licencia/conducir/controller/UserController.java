package com.licencia.conducir.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.licencia.conducir.util.Auth;

@RestController
@RequestMapping("/users")
public class UserController {
	private final Auth auth;
	
	public UserController(Auth auth) {
		this.auth=auth;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestHeader("authorization") String authorization){
		if (!auth.validate(authorization)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}else {
			return ResponseEntity.ok().build();
		}
	}	
}

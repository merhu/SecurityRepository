package com.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.object.TokenRequestData;
import com.util.Authenticator;
import com.util.JWTHelper;

@RestController
@RequestMapping("/token") //http://localhost:8081/account/token
public class TokenController {
	public static String appUserToken;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> getToken(@RequestBody TokenRequestData tokenRequestData) throws IOException {
		System.out.println("Parsing token");
		String username = tokenRequestData.getName();
		String password = tokenRequestData.getPassword();
		System.out.println(username + password);
		if (username != null && username.length() > 0 
				&& password != null && password.length() > 0 
				&& Authenticator.checkPassword(username, password)) {
			String token = JWTHelper.createToken("test");
			ResponseEntity<?> response = ResponseEntity.ok(token);
			return response;			
		}
		// bad request
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();		
	}
	
	public static String getAppUserToken() {
		return appUserToken = JWTHelper.createToken("test");
	}
}
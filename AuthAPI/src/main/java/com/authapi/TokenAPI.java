package com.authapi;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webage.util.JWTHelper;

@RestController
@RequestMapping("/token") //http://localhost:8081/account/token
public class TokenAPI {
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> getToken(@RequestBody TokenRequestData tokenRequestData) throws IOException {
		
		String username = tokenRequestData.getName();
		String password = tokenRequestData.getPassword();
		
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
}
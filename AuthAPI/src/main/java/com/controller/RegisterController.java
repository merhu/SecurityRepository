package com.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.object.Customer;
import com.util.Authenticator;

@RestController
@RequestMapping("/register") //http://localhost:8081/account/register
public class RegisterController {

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody Customer newCustomer) throws IOException {


		String username = newCustomer.getName();
		String password = newCustomer.getPass();
		String email = newCustomer.getEmail();

		if (username != null && username.length() > 0 
				&& password != null && password.length() > 0
				&& email != null && email.length() > 0) {

			URL obj = new URL("http://localhost:8080/api/customers/register");
			System.out.println(obj);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			System.out.println("Set request method to post");
			con.setDoInput(true);
			con.setDoOutput(true);
			
			OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
			wr.write(newCustomer.toString());
			System.out.println("Sending to database: " + newCustomer.toString());
			wr.flush();
			wr.close();
			con.connect();
			System.out.println("Initiate connection");

			ResponseEntity<?> response = ResponseEntity.ok().build();
			return response;
		}
		// bad request
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}


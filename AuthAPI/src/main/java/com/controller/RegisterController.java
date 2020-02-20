package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.object.Customer;
import com.object.CustomerFactory;


@RestController
@RequestMapping("/register")
public class RegisterController {

	@PostMapping
	public ResponseEntity<?> registerCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) throws Exception {
		if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			// Reject we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		String json_string = CustomerFactory.getCustomerAsJSONString(newCustomer);
		postNewCustomerToCustomerAPI(json_string);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}

	private void postNewCustomerToCustomerAPI(String json_string) {
		try {

			URL url = new URL("http://localhost:8080/api/customers");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
	  		String token = TokenController.getAppUserToken();
	  		conn.setRequestProperty("authorization", "Bearer " + token);
	  		// conn.setRequestProperty("tokencheck", "false");

			OutputStream os = conn.getOutputStream();
			os.write(json_string.getBytes());
			os.flush();
/*
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}*/

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
//package com.controller;
//
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.object.Customer;
//
//@RestController
//@RequestMapping("/register") //http://localhost:8081/account/register
//public class RegisterController {
//
//	@PostMapping
//	public ResponseEntity<?> registerUser(@RequestBody Customer newCustomer, UriComponentsBuilder url) throws IOException {
//
//		JSONObject jo = new JSONObject(); 
//		String username = newCustomer.getName();
//		String password = newCustomer.getPass();
//		String email = newCustomer.getEmail();
//
//		if (username != null && username.length() > 0 
//				&& password != null && password.length() > 0
//				&& email != null && email.length() > 0) {
//
//
//	        try {
//				jo.put("name", username);
//		        jo.put("email", email);
//		        jo.put("password", password);
//		        jo.put("id", newCustomer.getId());
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} 
//			
//			URL obj = new URL("http://localhost:8080/api/customers/register");
//			System.out.println(obj);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//			con.setRequestMethod("POST");
//			System.out.println("Set request method to post");
//			con.setDoInput(true);
//			con.setDoOutput(true);
//			
//			OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
//			wr.write(newCustomer.toString());
//			System.out.println("Sending to database: " + newCustomer.toString());
//			wr.flush();
//			wr.close();
//			con.connect();
//			System.out.println("Initiate connection");
//
//			ResponseEntity<?> response = ResponseEntity.ok().build();
//			return response;
//		}
//		// bad request
//		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	}
//}
//

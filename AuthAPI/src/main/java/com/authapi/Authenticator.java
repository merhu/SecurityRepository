package com.authapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authenticator {
	
	public static boolean checkUser(String username) throws IOException {
		//verify name with database
		URL obj = new URL("http://localhost:8080/api/customers/" + username);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.connect();
		
		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = null;
			StringBuffer response = new StringBuffer();
			boolean validName = false;

			do {
				if (inputLine.equalsIgnoreCase(username)) {
					response.append(inputLine);
					validName = true;
				}
			}while (!validName || (inputLine = in.readLine()) != null);
			in.close();
			System.out.println(response.toString());
		}
		
		
		if( (username != null && username.length() > 0) &&
			( username.equalsIgnoreCase("john") 
		    || username.equalsIgnoreCase("susan"))) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkPassword(String username, String password) throws IOException {
		if(checkUser(username)) {
			if(username.equalsIgnoreCase("john") && password.equals("pass")) {
				return true;
			}
			if(username.equalsIgnoreCase("susan") && password.equals("pass")) {
				return true;
			}			
		}else {
			return false;
		}
		return false;
	}
	
}

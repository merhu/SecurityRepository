package com.authapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authenticator {
	private static String name, pass;

	public static boolean checkUser(String username) throws IOException {
		//verify name with database
		URL obj = new URL("http://localhost:8080/api/customers/" + username);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.connect();

		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String[] inputLine = in.readLine().split(",");
			name = inputLine[0];
			pass = inputLine[1];
		}

		if( (username != null && username.length() > 0) &&
				(username.equalsIgnoreCase(name))) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean checkPassword(String username, String password) throws IOException {
		if(checkUser(username) && (password.equalsIgnoreCase(pass))) {
			return true;
		} else {
			return false;
		}
	}
}
package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Authenticator {
	private static String name, password;
	static String strURL = null;

	public static boolean checkUser(String username) throws IOException {
		//verify name with database
		strURL = System.getenv("apicustomersaddress");

		if (strURL == null) {
			strURL = "localhost:8080";
		}
		URL obj = new URL("http://" + strURL + "/api/customers/"+ username);
		System.out.println(obj);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.connect();
		String input, data = null;
		String[] inputLine;
		String delimiters = ",|\"|:";

		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("Get connection established");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			System.out.println("Receiving from database");

			while ((input = in.readLine()) != null) {
				data = input;
			}

			if (data != null) {
				System.out.println("Data received: " + data);
				inputLine = data.split(delimiters);
				name = inputLine[8];
				password = inputLine[14];
			}
		}
		if( (username != null && username.length() > 0) &&
				(username.equalsIgnoreCase(name))) {
			return true;
		}else {
			System.out.println("passed in: " + username + "\ndatabase name: " + name);
			return false;
		}
	}

	public static boolean checkPassword(String username, String oldPassword) throws IOException {
		if(checkUser(username) && (oldPassword.equalsIgnoreCase(password))) {
			System.out.println("User has been authenticated");
			return true;
		} else {
			System.out.println("passed in: " + oldPassword + "\ndatabase name: " + password);
			return false;
		}
	}
}
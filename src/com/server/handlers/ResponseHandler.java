package com.server.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResponseHandler{

	// Gets the users password.
	public String getUserPassword(String username){
		File userData = new File("Settings//" + username + ".yml");
		String password = null;

		// Checks the username.
		if(checkUsername(username)){
			try{
				BufferedReader reader = new BufferedReader(new FileReader(userData));
				while((password = reader.readLine()) != null){
					String[] parts = password.split("Password: ");
					return parts[1];
				}
			}catch(IOException e){
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
		return null;
	}

	// Checks if the user exists.
	public boolean checkUsername(String username){
		File userData = new File("Settings//" + username + ".yml");

		// If the players file exists.
		if(!userData.exists()){
			return false;
		}else{
			return true;
		}
	}

}

package com.server.Handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResponseHandler{


	// Gets the users password.
	public String getUserPassword(String username){
		File userData = new File("Settings//" + username + ".yml");
		String password = null;
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
		if(!userData.exists()){
			return false;
		}else{
			return true;
		}
	}

	// Checks if it is the correct password.
	public boolean checkUserPassword(String username, String password){
		if(getUserPassword(username).equals(null)){
			return false;
		}else{
			if(getUserPassword(username).equalsIgnoreCase(password)){
				return true;
			}else{
				return false;
			}
		}

	}

}

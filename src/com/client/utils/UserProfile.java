package com.client.utils;

public class UserProfile{

	private String username;
	private String passwordHash;
	private int clientID;

	// Constructor
	public UserProfile(String username, String passwordHash, int clientID){
		this.username = username;
		this.passwordHash = passwordHash;
		this.clientID = clientID;
	}

	// Gets the username
	public String getUsername(){
		return username;
	}

	// Gets the password hash
	public String getPasswordHash(){
		return passwordHash;
	}

	// Sets the username
	public void setUsername(String name){
		username = name;
	}

	// Sets users client ID
	public void setClientID(int id){
		clientID = id;
	}

	// Gets users client ID
	public int getClientID(){
		return clientID;
	}

}

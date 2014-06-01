package com.client.Handlers;

import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler{

	// Methods to create connection with server, does not handle
	// request/response ONLY Socket Handling!!!

	private static Socket socket;
	private static PrintWriter printWriter;
	public boolean connected = false;

	
	public void connectToServer(){
		try{
			socket = new Socket("72.231.199.200", 63450);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("Client");
			System.out.println("Connected to the server.");
			connected = true;
		}catch(Exception e){
			System.out.println("Failed to connect!");
			connected = false;
		}
	}
	
	//Checks if connected
	public boolean isConnected(){
		return true;
	}
	
	public Socket getSocket(){
		return socket;
		
	}
	
	

}

package com.client.Handlers;

import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler{

	// Methods to create connection with server, does not handle
	// request/response ONLY Socket Handling!!!

	private static Socket socket;
	private static PrintWriter printWriter;
	public boolean connected;
	
	
	public void connectToServer(){
		try{
			socket = new Socket("localhost", 63450);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("CLIENT");
			System.out.println("Connected to the server.");
			connected = true;
		}catch(Exception e){
			System.out.println("Failed to connect!");
			connected = false;
		}
	}
	
	
	public Socket getSocket(){
		return socket;
		
	}
	
	

}

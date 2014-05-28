package com.client.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.client.Engine.Engine;
import com.client.Screens.LoadScreen;
import com.server.Handlers.ResponseHandler;

public class RequestHandler{

	// Send a request to the server, use a header to tell the server what to do
	// with the data

	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;
	

	public boolean dataCorrect = false;
	int interval = 0;
	int times = 0;
	long time = System.currentTimeMillis();
	String inputline = null;
	
	ConnectionHandler connectionHandler = new ConnectionHandler();
	ResponseHandler responseHandler = new ResponseHandler();
	
	Engine engine;
	public RequestHandler(Engine engine){
		this.engine = engine;
	}
	
	public void checkUserData(Socket clientSocket, String username, char[] password){
		if(connectionHandler.isConnected()){
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("Login");
				printWriter.println("Username: " + username + " Password: " + password.toString());
				while((inputline = bufferedReader.readLine()) != null){
					if(inputline.equalsIgnoreCase("Valid")){
						new LoadScreen(engine);
						interval = 11;
					}else if(inputline.equalsIgnoreCase("Invalid")){
						System.exit(0);
						interval = 11;
					}	
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}

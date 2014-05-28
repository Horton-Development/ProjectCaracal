package com.server.Engine;

import com.server.Handlers.ConfigHandler;
import com.server.Handlers.ConnectionHandler;
import com.server.Handlers.ResponseHandler;

public class Engine implements Runnable {
	
	//Main server loop, waiting for input/requests and process with handlers

	Thread thread = new Thread(this);
	public boolean running = false;
	public int clientID;
	ConfigHandler configHandler = new ConfigHandler();
	ResponseHandler responseHandler = new ResponseHandler();
	ConnectionHandler connectionHandler = new ConnectionHandler();
	
	public Engine(){
		configHandler.createDefaultDirectory();
		System.out.println("Server Started!");
		thread.start();
	}

	
	public void run(){
		connectionHandler.createConnection();
	}


	
	
	
}

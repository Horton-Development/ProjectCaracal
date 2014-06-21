package com.server.engine;

import com.server.handlers.ConfigHandler;
import com.server.handlers.ResponseHandler;
import com.server.handlers.ServerConnectionHandler;
import com.server.handlers.StartupHandler;

public class Engine implements Runnable{

	// Main server loop, waiting for input/requests and process with handlers

	Thread thread = new Thread(this);

	public boolean running = false;
	public int clientID;

	ConfigHandler configHandler = new ConfigHandler();
	ResponseHandler responseHandler = new ResponseHandler();
	StartupHandler startupHandler;
	ServerConnectionHandler connectionHandler = new ServerConnectionHandler();

	// Constructor
	public Engine(StartupHandler startupHandler){
		this.startupHandler = startupHandler;
		configHandler.createDefaultDirectory();
		System.out.println("Server Started!");
		thread.start();
	}

	// Starts the server engine
	public void run(){
		connectionHandler.run();
	}

}

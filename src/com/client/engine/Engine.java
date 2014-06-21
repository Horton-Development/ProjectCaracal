package com.client.engine;

import com.client.handlers.ErrorHandler;
import com.client.handlers.ResourceLoadHandler;
import com.client.handlers.StateOutputHandler;
import com.client.screens.LoadScreen;
import com.client.screens.LoginScreen;

public class Engine implements Runnable{

	Thread thread = new Thread(this);

	public static int fps;
	public boolean running;
	LoginScreen loginScreen = new LoginScreen(this);

	// Output handler
	StateOutputHandler handler = new StateOutputHandler(this);

	// Constructor
	public Engine(){
		thread.start();
		new LoadScreen(this);
		ResourceLoadHandler resourceLoadHandler = new ResourceLoadHandler();
		resourceLoadHandler.loadFiles();
	}

	// Starts/runs the engine
	public void run(){
		running = true;
		while(running){
			long lastFrame = System.currentTimeMillis();
			int frames = 0;
			frames++;

			try{
				Thread.sleep(2);
			}catch(InterruptedException e){
				// Calls error handler for the error
				ErrorHandler.handleError(e);
			}

			// Fps manager
			if(System.currentTimeMillis() - 1000 >= lastFrame){
				fps = frames;
				frames = 0;
				lastFrame = System.currentTimeMillis();
			}
		}

	}

}

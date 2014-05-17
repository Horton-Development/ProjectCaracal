package com.client.Engine;

import com.client.Handlers.ErrorHandler;
import com.client.Handlers.StateOutputHandler;
import com.client.Screens.LoginScreen;


public class Engine implements Runnable{
	
	Thread thread = new Thread(this);
	
	public static int fps;
	public boolean running;
	
	//Output handler
	StateOutputHandler handler = new StateOutputHandler(this);
	
	
	//Constructor
	public Engine(){
		thread.start();
		new LoginScreen(this);
	}

	//Starts/runs the engine
	public void run() {
		
		running = true;
		long lastFrame = System.currentTimeMillis();
		int frames = 0;
		frames++;
		
		try {
			Thread.sleep(2);
		}catch(InterruptedException e){
			
			//Calls error handler for the error
			ErrorHandler.handleError(e);
		}
		
		//Fps manager
		if(System.currentTimeMillis() - 1000 >= lastFrame){
			fps = frames;
			frames = 0;
			lastFrame = System.currentTimeMillis();
		}
		
		//Recursive method
		if(running){
			run();
		}else{
			System.exit(0);
		}
		
	}
	
	
}

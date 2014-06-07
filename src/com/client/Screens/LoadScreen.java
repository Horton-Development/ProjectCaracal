package com.client.Screens;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.client.Engine.Engine;
import com.client.Handlers.DimensionHandler;
import com.client.Handlers.ResolutionHandler;

public class LoadScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen
	Thread thread = new Thread(this);

	private static final long serialVersionUID = 1L;

	// Load screen constructor
	public LoadScreen(Engine engine){
		super(engine);
		thread.start();
	}

	//Runs the loading screen.
	public void run(){
		
	}
	
}

package com.client.Screens;

import javax.swing.JFrame;

import com.client.Engine.Engine;
import com.client.Handlers.DimensionHandler;
import com.client.Handlers.ResolutionHandler;

public class LoadScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen
	Screen screen = new Screen(engine);
	Thread thread = new Thread(this);
	DimensionHandler dimensionHandler = new DimensionHandler(screen);

	private static final long serialVersionUID = 1L;

	// Load screen constructor
	public LoadScreen(Engine engine){
		super(engine);
		thread.start();
	}

	//Runs the loading screen.
	public void run(){
		new JFrame();
		this.setResizable(false);
		this.setTitle("ProjectCaracal");
		setSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setSize(){
		if(dimensionHandler.determineResolution(this.getWidth(), this.getHeight()).equals(ResolutionHandler.SMALL)){
			this.setSize(ResolutionHandler.SMALL.getWidth(), ResolutionHandler.SMALL.getHeight());
		}
		else if(dimensionHandler.determineResolution(this.getWidth(), this.getHeight()).equals(ResolutionHandler.MEDIUM)){
			this.setSize(ResolutionHandler.MEDIUM.getWidth(), ResolutionHandler.MEDIUM.getHeight());
		}
		else if(dimensionHandler.determineResolution(this.getWidth(), this.getHeight()).equals(ResolutionHandler.LARGE)){
			this.setSize(ResolutionHandler.LARGE.getWidth(), ResolutionHandler.LARGE.getHeight());
		}else{
			this.setSize(ResolutionHandler.SMALL.getWidth(), ResolutionHandler.SMALL.getHeight());
		}
	}

}

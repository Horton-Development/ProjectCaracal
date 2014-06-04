package com.client.Screens;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.client.Engine.Engine;
import com.client.Handlers.DimensionHandler;
import com.client.Handlers.ResolutionHandler;

public class LoadScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen
	Screen screen = new Screen(engine);
	Thread thread = new Thread(this);
	DimensionHandler dimensionHandler = new DimensionHandler(screen);
	int value = 0;
	
	MenuScreen menuScreen;
	
	JProgressBar progressBar = new JProgressBar();

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
		this.setLayout(new FlowLayout());
		this.add(progressBar);
		setSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		setProgress();
	}
	
	
	private void setProgress(){
		progressBar.setMaximum(19);
		for(int interval = 0; interval < 20; interval ++){
			try{
				thread.sleep(200);
				value = interval;
				System.out.println(value);
				progressBar.setValue(value);
				if(interval == 19){
					progressBar.setVisible(false);
					new MenuScreen(progressBar);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	//Sets the size of the screen.
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

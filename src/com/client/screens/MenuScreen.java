package com.client.screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.client.engine.Engine;
import com.client.handlers.DimensionHandler;
import com.client.handlers.ResolutionHandler;

public class MenuScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen
	Thread thread = new Thread(this);
	DimensionHandler dimensionHandler = new DimensionHandler(this);
	JFrame frame = new JFrame();

	JButton button1 = new JButton("New Game");
	JButton button2 = new JButton("Load Game");
	JButton button3 = new JButton("Exit");

	boolean running;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private static final long serialVersionUID = 1L;

	// Load screen constructor
	public MenuScreen(Engine engine){
		super(engine);
		thread.start();
	}

	// Runs the loading screen.
	public void run(){
		setSize();
		setTitle();
		setLayout();
		setVisable(true);
		running = true;
		while(running){

		}
	}

	// Sets screen size
	private void setSize(){
		if(dimensionHandler.determineResolution(screenSize.width, screenSize.height).equals(ResolutionHandler.SMALL)){
			frame.setSize(ResolutionHandler.SMALL.getWidth(), ResolutionHandler.SMALL.getHeight());
		}else if(dimensionHandler.determineResolution(screenSize.width, screenSize.height).equals(ResolutionHandler.MEDIUM)){
			frame.setSize(ResolutionHandler.MEDIUM.getWidth(), ResolutionHandler.MEDIUM.getHeight());
		}else if(dimensionHandler.determineResolution(screenSize.width, screenSize.height).equals(ResolutionHandler.LARGE)){
			frame.setSize(ResolutionHandler.LARGE.getWidth(), ResolutionHandler.LARGE.getHeight());
		}
	}

	// Sets the title.
	private void setTitle(){
		frame.setTitle("ProjectCaracal");
	}

	// Sets the screen visible
	private void setVisable(boolean visible){
		frame.setVisible(visible);
	}
	
	private void setLayout(){
		frame.setLayout(new FlowLayout());
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
	}

}

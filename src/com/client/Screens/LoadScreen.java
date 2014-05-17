package com.client.Screens;

import javax.swing.JFrame;

import com.client.Engine.Engine;

public class LoadScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen

	Thread thread = new Thread(this);

	private static final long serialVersionUID = 1L;

	// Load screen constructor
	public LoadScreen(Engine engine){
		super(engine);
		thread.start();
	}

	public void run(){
		new JFrame();
		this.setResizable(false);
		this.setTitle("ProjectCaracal");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

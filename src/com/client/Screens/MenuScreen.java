package com.client.Screens;

import java.awt.Graphics;

import javax.swing.JPanel;


public class MenuScreen extends JPanel implements Runnable{

	// Contains "links" to different screens

	private static final long serialVersionUID = 1L;
	
	Thread thread = new Thread(this);
	
	
	public MenuScreen(){
		thread.start();
	}

	public void paint(Graphics g){
		
	}
	
	
	public void run(){
		
	}
	
	
}

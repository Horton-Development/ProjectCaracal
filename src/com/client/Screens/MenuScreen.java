package com.client.Screens;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.client.Engine.Engine;


public class MenuScreen extends JPanel implements Runnable{

	// Contains "links" to different screens

	Thread thread = new Thread(this);
	
	boolean running;
	
	public MenuScreen(JProgressBar progressBar){
		this.remove(progressBar);
		thread.start();
	}

	public void paint(Graphics g){
		System.out.println("test");
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	
	public void run(){
		running = true;
		while(running){
			try{
				thread.sleep(200);
				repaint();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
}

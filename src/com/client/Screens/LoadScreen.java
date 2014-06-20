package com.client.Screens;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.client.Engine.Engine;

public class LoadScreen extends Screen implements Runnable{

	// Use ResourceLoadHandler.load(<args>) to load resources behind this screen


	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);

	boolean running;

	JFrame frame = new JFrame();

	JProgressBar progress = new JProgressBar();

	// Constructor
	public LoadScreen(Engine engine){
		super(engine);
		thread.start();
	}

	@Override
	public void run(){
		progress.setMaximum(19);
		setFrame();
		running = true;
		while(running){
			for(int x = 0; x < 20; x++){
				try{
					Thread.sleep(200);
					progress.setValue(x);
				}catch(InterruptedException ex){
					System.out.println(ex);
				}finally{
					if(progress.getValue() == 19){
						running = false;
						frame.dispose();
						new MenuScreen(engine);
					}
				}
			}
		}
	}

	// Sets all variables for the frame.
	private void setFrame(){
		frame.setSize(300, 300);
		frame.setTitle("LOADING");
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.add(progress);
		frame.setVisible(true);
	}

}

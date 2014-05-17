package com.game.client.engine;

import javax.swing.JFrame;

import com.game.client.response.Dimensions;


public class Frame extends JFrame{

	/**
	 * Frame class
	 */
	
	
	//Main starting method
	public static void main(String args){
		new Frame(args);
	}
	
	public Frame(String size){
		new JFrame();
		if(size.equalsIgnoreCase("Small")){
			this.setSize(Dimensions.SMALL.getWidth(), Dimensions.SMALL.getHeight());
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		else if(size.equalsIgnoreCase("Medium")){
			this.setSize(Dimensions.MEDIUM.getWidth(), Dimensions.MEDIUM.getHeight());
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		else if(size.equalsIgnoreCase("Large")){
			this.setSize(Dimensions.LARGE.getWidth(), Dimensions.LARGE.getHeight());
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
	
	
}

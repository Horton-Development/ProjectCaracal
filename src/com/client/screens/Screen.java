package com.client.screens;

import javax.swing.JFrame;

import com.client.engine.Engine;

public class Screen extends JFrame{

	// Properties common to all screens

	// Behaviours common to all screens

	private static final long serialVersionUID = 1L;

	Engine engine;

	// Screen superclass constructor
	public Screen(Engine engine){
		this.engine = engine;
	}

}

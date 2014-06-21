package com.client.handlers;

import com.client.engine.Engine;

public class StartupHandler{

	// Main class method
	public static void main(String[] args){
		new StartupHandler();
	}

	// Loads the game files and starts the engine
	public StartupHandler(){
		loadGameFiles();
		new Engine();
	}

	private void loadGameFiles(){
		// Loads game values such as; Game Environment, Game Dimensions, Stats
	}

}

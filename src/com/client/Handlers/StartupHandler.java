package com.client.Handlers;

import com.client.Engine.Engine;

public class StartupHandler {

	//Main class method
	public static void main(String[] args){
		new StartupHandler();
	}
	
	//Loads the game files and starts the engine
	public StartupHandler(){
		loadGameFiles();
		new Engine();
	}


	private void loadGameFiles() {
		//Loads game values such as; Game Environment, Game Dimensions, Stats
	}

	
	
	

}

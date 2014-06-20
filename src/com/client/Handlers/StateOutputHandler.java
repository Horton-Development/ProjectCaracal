package com.client.Handlers;

import com.client.Engine.Engine;

public class StateOutputHandler{

	public StateOutputHandler(Engine engine){

	}

	// Returns the game fps
	public int getFPS(){
		return Engine.fps;
	}
	

}

package com.client.handlers;

import com.client.engine.Engine;

public class StateOutputHandler{

	public StateOutputHandler(Engine engine){

	}

	// Returns the game fps
	public int getFPS(){
		return Engine.fps;
	}

}

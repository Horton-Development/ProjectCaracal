package com.client.handlers;

import com.client.screens.Screen;

public class DimensionHandler{

	Screen screen;

	public DimensionHandler(Screen screen){
		this.screen = screen;
	}

	// Returns the correct resolution
	public ResolutionHandler determineResolution(int x, int y){
		int smallX = x - 640;
		int smallY = y - 480;
		int mediumX = x - 800;
		int mediumY = y - 600;
		int largeX = x - 1280;
		int largeY = y - 960;
		int small = smallX + smallY;
		int medium = mediumX + mediumY;
		int large = largeX + largeY;
		// Checks the closest resolution to fit the screen
		if(small < medium && small < large){
			return ResolutionHandler.SMALL;
		}else if(medium < small && medium < large){
			return ResolutionHandler.MEDIUM;
		}else if(large < small && large < medium){
			return ResolutionHandler.LARGE;
		}else{
			return ResolutionHandler.SMALL;
		}
	}

}

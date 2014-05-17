package com.client.Handlers;

import java.io.File;

public class ConfigHandler{

	ErrorHandler errorHandler = new ErrorHandler();
	
	//Returns the directory
	public File getDir(){
		File config = new File("config.yml");
		return config.getParentFile();
	}
	
	public void getResolution(){
		File config = new File("config.yml");
		//Gets the resolution
	}
	
	
}

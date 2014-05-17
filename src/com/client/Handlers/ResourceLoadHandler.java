package com.client.Handlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoadHandler{

	// Methods for loading in resources such as image files
	
	ErrorHandler errorHandler = new ErrorHandler();
	
	public BufferedImage playButton;
	public BufferedImage attackIcon;
	public BufferedImage selectIcon;
	
	//Loads images
	public void loadFiles(){
		try{
			playButton = ImageIO.read(new File("res//art//interface//buttons//PlayGame_Normal.png"));
			attackIcon = ImageIO.read(new File("res//art//interface//pointers//attack.png"));
			selectIcon = ImageIO.read(new File("res//art//interface//pointers//select.png"));
		}catch(IOException e){
			errorHandler.handleError(e);
		}
	}

}

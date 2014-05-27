package com.server.Handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class ConfigHandler{

	
	//Checks if the directory exists
	public boolean doesDirectoryExist(String dir){
		File file = new File(dir);
		if(file.exists()){
			if(file.isDirectory()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//Checks if the directory exists
	public boolean doesDirectoryExist(File dir){
		File file = new File(dir.getAbsolutePath());
		if(file.exists()){
			if(file.isDirectory()){
				return true;
			}else{
				return false;
			}
		}else{
			if(file.isDirectory()){
				return true;
			}else{
				return false;
			}
		}
	}
	
	
	//Creates the default directory
	public void createDefaultDirectory(){
		File file = new File("Settings");
		if(doesDirectoryExist(file)){
			
		}else{
			try{
				file.mkdir();
				System.out.println("Created default directory.");
			}catch(Exception e){
				System.out.println("Failed to create default settings directory.");
			}
		}
	}
	
	

	//Creates a new directory
	public void createDirectory(String dir){
		File file = new File(dir);
		try{
			file.mkdir();
		}catch(InvalidPathException e){
			System.out.println("Failed to create directory " + dir + ".");
		}
	}
	
	//Creates a new file.
	public void createFile(String fileName, String dir){
		File directory = new File(dir);
		File newFile = new File(dir + "//" + fileName + ".yml");
		if(!directory.exists()){
			createDirectory(dir);
		}else{
			try{
				newFile.createNewFile();
				System.out.println("Created new file " + fileName + ".yml");
			}catch(IOException e){
				System.out.println("Failed to create file " + fileName + ".yml" + " under the directory " + dir + ".");
			}
		}
	}
	
	
	
	
}

package com.server.Handlers;

import java.io.IOException;

public class ErrorHandler {
	
	//Exception handling, process exceptions and assemble string via StringWriter. Display to screen via OutputHandler

	  //Handles IO Exception errors
    public static void handleError(IOException e) {
        System.out.println(e);
    }

    //Handles server errors.
    public void handleError(String error) {
        if(error == "Server"){
            System.out.println("Error: A program is already started on port! Try using another port!");
        }
        else if(error == "Client"){
            //Connection error.
        }else{
            
        }
    }

	public static void handleError(Exception e){
		System.out.println(e);
	}
}

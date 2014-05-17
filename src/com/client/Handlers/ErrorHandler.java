package com.client.Handlers;

import java.io.IOException;

public class ErrorHandler{

	// Exception handling, process exceptions and assemble string via
	// StringWriter. Display to screen via OutputHandler

	// Handles the error
	public static void handleError(InterruptedException e){
		System.out.println(e);
	}

	public void handleError(IOException e){
		System.out.println(e);
	}

}

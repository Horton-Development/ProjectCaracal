
package com.server.Handlers;

import javax.swing.JFrame;

import com.server.Engine.Engine;


public class StartupHandler extends JFrame{
    
	private static final long serialVersionUID = 1L;
	
	ConnectionHandler connectionHandler = new ConnectionHandler();
	ErrorHandler errorHandler = new ErrorHandler();
	
    public static boolean running = false;
    
    //Main starting method
    public static void main(String[] args){
    	new Engine();
    	new StartupHandler();
    }
    
    //Sets the frame size and starts a connection
    public StartupHandler(){
        this.setSize(400, 400);
        this.setTitle("ProjectCaracal (Server)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}


package com.server.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionHandler {
    
	
	
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static String inputLine;
    public int clientID;
    String[] username;
    String[] password;
    boolean running;

    ErrorHandler errorHandler = new ErrorHandler();
    
    //Starts the server connection
    public void createConnection(){
    	ResponseHandler responseHandler = new ResponseHandler();
    	//States that the server is started
    	try{
    		System.out.println("Waiting for client connection...");
    		serverSocket = new ServerSocket(63450);
    		running = true;
    		while(running){
        		clientSocket = serverSocket.accept();
        		bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    			inputLine = bufferedReader.readLine();
    			if(inputLine.equalsIgnoreCase("Client")){
    				clientID = clientID + 1;
    				System.out.println("A new client has connected! Assigning client id of " + clientID);
    			}else if(inputLine.equalsIgnoreCase("Login")){
    				System.out.println("Client " + clientID + " is calling for a login request.");
    				inputLine = bufferedReader.readLine();
    				username = inputLine.split("-");
    				if(responseHandler.checkUsername(username[0])){
    					if(responseHandler.getUserPassword(username[0]).equals(username[1])){
    						PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
    						writer.println("Valid");
    					}else{
    						PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
    						writer.println("Invalid");
    					}
    				}else{
    					PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
						writer.println("Invalid");
    				}
    			}
    		}
    		
    	}catch(IOException e){
             e.printStackTrace();
    	}
        
        
    }
    
    //Checks for a client connection
    public void checkForConnection(){
    	
		
	}
    
    
    //Ends the connection
    public void endConnection(){
        
    	//Checks if the server connection is closed
        if(!serverSocket.isClosed()){
            try {
                serverSocket.close();
            }catch(IOException e){
                ErrorHandler.handleError(e);
            }
        }else{
            System.out.println("Error: Connection is already closed.");
        }
    }
    
}

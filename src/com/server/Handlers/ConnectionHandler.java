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
    			
    			//If the message was client.
    			if(inputLine.equalsIgnoreCase("Client")){
    				clientID = clientID + 1;
    				System.out.println("A new client has connected! Assigning client id of " + clientID);
    			}
    			
    			//If the message was a login event.
    			else if(inputLine.equalsIgnoreCase("Login")){
    				inputLine = bufferedReader.readLine();
    				username = inputLine.split("-");
    				System.out.println("Client " + clientID + " is calling for a login request.");
    				//Checks if the username is valid.
        			if(responseHandler.checkUsername(username[0])){
        				
        				//Checks if the password is valid.
        				if(responseHandler.getUserPassword(username[0]).equals(username[1])){
        					PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        					writer.println("Valid");
        					System.out.println(username[0] + " has logged in!");
        				}else{
        					PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        					writer.println("Invalid");
        					System.out.println("Client " + clientID + " tried logging in but failed due to wrong credentials.");
        				}
        			}else{
        				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
    					writer.println("Invalid");
    					System.out.println("Client " + clientID + " tried logging in but failed due to wrong credentials.");
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
            	
            }
        }else{
            System.out.println("Error: Connection is already closed.");
        }
    }
    
}
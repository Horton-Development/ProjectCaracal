
package com.server.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionHandler {
    
	
	
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static String inputLine;
    public int clientID;

    ErrorHandler errorHandler = new ErrorHandler();
    
    //Starts the server connection
    public void createConnection(){
    	//States that the server is started
    	try{
    		System.out.println("Waiting for client connection...");
    		serverSocket = new ServerSocket(63450);
    		clientSocket = serverSocket.accept();
    		bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		while((inputLine = bufferedReader.readLine()) != null){
				if(inputLine.equalsIgnoreCase("Client")){
					clientID = clientID + 1;
					System.out.println("A new client has connected! Assigning client with id " + clientID);
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

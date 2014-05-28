
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
    			System.out.println(inputLine);
    			if(inputLine.equalsIgnoreCase("Client")){
					clientID = clientID + 1;
					System.out.println("A new client has connected! Assigning client with id " + clientID);
					checkForConnection();
    			}else{
    				checkForConnection();
				}
    			
				
				
			}
    		
    	}catch(IOException e){
             e.printStackTrace();
    	}
        
        
    }
    
    //Checks for a client connection
    public void checkForConnection(){
    	System.out.println("test");
    	try{
    		System.out.println("test2");
			while((inputLine = bufferedReader.readLine()) != null){
				if(inputLine.equalsIgnoreCase("Login")){
					System.out.println("Requesting a login check.");
					while((inputLine = bufferedReader.readLine()) != null){
						System.out.println(inputLine);
						ResponseHandler responseHandler = new ResponseHandler();
						username = inputLine.split("Username: ");
						password = inputLine.split("Password: ");
						PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
						if(responseHandler.checkUsername(username[1])){
							if(responseHandler.getUserPassword(username[1]).equals(inputLine)){
								printWriter.println("Valid");
							}else{
								printWriter.println("Invalid");
							}
						}
					}
					
				}
			}
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

package com.server.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.common.utils.BCrypt;
import com.common.utils.UserProfile;



public class ConnectionHandler {
    
	public ServerSocket serverSocket;
	public Socket clientSocket;
	private BufferedReader bufferedReader;
	private int serverClientID = 0;
	private int clientID = 0;
	
	String inputLine;
	boolean running;
	
	UserProfile userProfile;
	
	//Constructor
	public ConnectionHandler(){
		
	}
	
	//Creates a socket connection
	public void createConnection(StartupHandler startupHandler){
		Date date2 = new Date(System.currentTimeMillis());
		SimpleDateFormat format2 = new SimpleDateFormat("EEE, HH:mm:ss a");
		startupHandler.textArea.append("(" + format2.format(date2) + ") Server started!\n(" + format2.format(date2) + ") Waiting for connection...\n");
		ResponseHandler responseHandler = new ResponseHandler();
		BCrypt bCrypt = new BCrypt();
		try{
			serverSocket = new ServerSocket(63450);
			running = true;
			while(running){
				clientSocket = serverSocket.accept();
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				inputLine = bufferedReader.readLine();
				
				//Checks the input line.
				switch(inputLine){
					
					//If the input line is case Client.
					case "CLIENT":
						startupHandler.textArea.append("(" + format.format(date) + ") A new client has connected! Assigning client id of " + clientID + ".\n");
						serverClientID = clientID;
						clientID++;
						break;
						
					//If the input line is case Login.	
					case "LOGIN":
						startupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " is requesting a login.\n");
						String[] parts = inputLine.split("~");
						new UserProfile(parts[0], parts[1], clientID);
						
						//Checks the username.
						if(responseHandler.checkUsername(parts[0])){
							
							//Checks the password.
							if(responseHandler.getUserPassword(parts[0]).equals(parts[1])){
								sendValidMessage(clientSocket);
								startupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " has logged in as " + parts[0] + "!\n");
							}else{
								startupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
								sendInvalidMessage(clientSocket);
							}
						}else{
							startupHandler.textArea.append(format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
							sendInvalidMessage(clientSocket);
						}
				}
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//Sends a message stating the password is invalid.
	private void sendInvalidMessage(Socket clientSocket2){
		try{
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("Invalid");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	//Sends a message stating the password is valid.
	private void sendValidMessage(Socket clientSocket){
		try{
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("Valid");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	

	//Ends the socket connection
	public void endConnection(ServerSocket socket, Socket clientSocket){
		try{
			clientSocket.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
    
}
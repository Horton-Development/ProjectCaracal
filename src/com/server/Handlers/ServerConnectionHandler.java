package com.server.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.common.utils.BCrypt;
import com.common.utils.UserProfile;

public class ServerConnectionHandler implements Runnable{
	
	private final int PORT = 63450;
	private StartupHandler startupHandler;
	private boolean running = true;


	public ServerConnectionHandler(StartupHandler startupHandler){
		this.startupHandler = startupHandler;
	}

	@Override
	public void run(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
		StartupHandler.textArea.append("(" + format.format(date) + ") Server started!\n(" + format.format(date) + ") Waiting for connection...\n");
		try{
			ServerSocket serverSocket = new ServerSocket(PORT);
			while(running){
				Socket clientSocket = serverSocket.accept();
				ConnectionProcessor connectionProcessor = new ConnectionProcessor(startupHandler, clientSocket);
				new Thread(connectionProcessor).start();
			}
			StartupHandler.textArea.append("(" + format.format(date) + ") Server Stopped!\n");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public void stop(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
		this.running = false;
		StartupHandler.textArea.append("(" + format.format(date) + ") Server stopping...\n");
	}	
	
	
	
}

class ConnectionProcessor implements Runnable{
	private Socket socket;
	private StartupHandler startupHandler;
	private BufferedReader bufferedReader;
	private int serverClientID = 0;
	private int clientID = 0;
	String inputLine;
	
	
	ConnectionProcessor(StartupHandler startupHandler, Socket socket){
		this.startupHandler = startupHandler;
		this.socket = socket;
	}

	@Override
	public void run(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
		try{
			try{
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				inputLine = bufferedReader.readLine();
				ResponseHandler responseHandler = new ResponseHandler();
				if(inputLine.equals("CLIENT") || inputLine.equals("LOGIN")){
					//Checks the input line.
					switch(inputLine){
						
						//If the input line is case Client.
						case "CLIENT":
							StartupHandler.textArea.append("(" + format.format(date) + ") A new client has connected! Assigning client id of " + clientID + ".\n");
							serverClientID = clientID;
							clientID++;
							break;
							
						//If the input line is case Login.	
						case "LOGIN":
							Thread.sleep(2000);
							StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " is requesting a login.\n");
								
					}
				}else{
					String[] parts = inputLine.split("_");
					System.out.println(inputLine);
					System.out.println(parts[1]);
					
					//Checks the username.
					if(responseHandler.checkUsername(parts[0])){
						new UserProfile(parts[0], BCrypt.hashpw(parts[1], BCrypt.gensalt()), clientID);
						//Checks the password.
						if(responseHandler.getUserPassword(parts[0]).equals(parts[1])){
							sendValidMessage(socket);
							StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " has logged in as " + parts[0] + "!\n");
						}else{
							StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
							sendInvalidMessage(socket);
						}
					}else{
						StartupHandler.textArea.append(format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
						sendInvalidMessage(socket);
					}
				}
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//Sends a message stating the password is invalid.
	private void sendInvalidMessage(Socket clientSocket){
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
	
}

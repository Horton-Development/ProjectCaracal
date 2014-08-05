package com.server.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.client.utils.BCrypt;
import com.client.utils.UserProfile;

public class ServerConnectionHandler implements Runnable{

	private final int PORT = 63450;
	private StartupHandler startupHandler;
	private boolean running = true;

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
			serverSocket.close();
			StartupHandler.textArea.append("(" + format.format(date) + ") Server Stopped!\n");
		}catch(IOException e){

		}

	}

	public void stop(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
		running = false;
		ConnectionProcessor connectionProcessor = new ConnectionProcessor(startupHandler, new Socket());
		connectionProcessor.stop();
		StartupHandler.textArea.append("(" + format.format(date) + ") Server stopping...\n");
	}

}

class ConnectionProcessor implements Runnable{
	private Socket socket;
	private StartupHandler startupHandler;

	private BufferedReader bufferedReader;

	public boolean running;

	String previousMessage;

	private int serverClientID = 0;
	private int clientID = 1;

	String inputLine;

	DecryptHandler decryptHandler = new DecryptHandler();

	// Constructor
	public ConnectionProcessor(StartupHandler startupHandler, Socket socket){
		this.startupHandler = startupHandler;
		this.socket = socket;
	}

	@Override
	public void run(){
		running = true;
		while(running){
			checkMessage();
		}
	}

	// Checks the message.
	private void checkMessage(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("EEE, HH:mm:ss a");
		if(socket.isClosed()){

		}else{
			try{
				try{
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					inputLine = bufferedReader.readLine();
					ResponseHandler responseHandler = new ResponseHandler();
					ConfigHandler configHandler = new ConfigHandler();
					System.out.println(inputLine);
					if(inputLine.equals("CLIENT") || inputLine.equals("LOGIN") || inputLine.equals("CREATE")){
						// Checks the input line.
						switch(inputLine){

						// If the input line is case Client.
							case "CLIENT":
								StartupHandler.textArea.append("(" + format.format(date) + ") A new client has connected! Assigning client id of " + clientID + ".\n");
								clientID++;
								previousMessage = "CLIENT";
								break;

							// If the input line is case Login.
							case "LOGIN":
								Thread.sleep(2000);
								StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " is requesting a login.\n");
								previousMessage = "LOGIN";
								break;

							case "CREATE":
								previousMessage = "CREATE";
								Thread.sleep(2000);
								StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " is requesting an account creation.\n");
								break;
						}
					}else if(inputLine.contains("_")){
						String[] parts = inputLine.split("_");
						System.out.println(inputLine);
						System.out.println(parts[1]);
						if(previousMessage.equals("CREATE")){
							String username = parts[0];
							String password = parts[1];
							configHandler.createFile(username, "Settings");
							configHandler.createNewAccountFile(username, "Settings", password);
							sendSuccessMessage(socket);
							bufferedReader.close();
						}else{
							// Checks the username.
							if(responseHandler.checkUsername(parts[0])){
								new UserProfile(parts[0], BCrypt.hashpw(parts[1], BCrypt.gensalt()), clientID);
								// Checks the password.
								if(decryptHandler.decryptPassword(parts[0], parts[1])){
									sendValidMessage(socket);
									StartupHandler.textArea.append("(" + format.format(date) + ") Client " + serverClientID + " has logged in as " + parts[0] + "!\n");
								}else{
									StartupHandler.textArea.append(parts[1] + "(" + format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
									sendInvalidMessage(socket);
								}
							}else{
								StartupHandler.textArea.append(format.format(date) + ") Client " + serverClientID + " typed in the wrong credentials!\n");
								sendInvalidMessage(socket);
							}
						}
					}else{
						System.out.println(inputLine);
					}

				}catch(InterruptedException e){
					e.printStackTrace();
				}

			}catch(IOException e){
				e.printStackTrace();
			}
		}

	}

	// Sends a message stating the password is valid.
	private void sendValidMessage(Socket clientSocket){
		try{
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("Valid");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// Sends a message stating the password is invalid.
	private void sendInvalidMessage(Socket clientSocket){
		try{
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("Invalid");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	// Sends a message stating the password is invalid.
	private void sendSuccessMessage(Socket clientSocket){
		try{
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("Success");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void stop(){
		running = false;
	}

}

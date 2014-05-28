package com.client.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.client.Engine.Engine;
import com.client.Screens.LoadScreen;
import com.server.Handlers.ResponseHandler;

public class RequestHandler implements Runnable{

	// Send a request to the server, use a header to tell the server what to do
	// with the data

	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;
	

	public boolean dataCorrect = false;
	int interval = 0;
	int times = 0;
	long time = System.currentTimeMillis();
	String inputline = null;
	Thread thread = new Thread(this);
	
	ConnectionHandler connectionHandler = new ConnectionHandler();
	ResponseHandler responseHandler = new ResponseHandler();
	
	Engine engine;
	public RequestHandler(Engine engine){
		this.engine = engine;
	}
	
	public void checkUserData(Socket clientSocket, String username, char[] password){
		if(connectionHandler.isConnected()){
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("Login");
				sendCredentials(clientSocket, username, password);
				thread.start();
						
			
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	
	
	public void run(){
		System.out.println("Waiting for server response!");
		try{
			System.out.println(interval);
			inputline = bufferedReader.readLine();
			System.out.println(interval);
			for(interval = 0; interval < 10; interval++){
				try{
					if(inputline.equalsIgnoreCase("Valid")){
						System.out.println("Valid");
						new LoadScreen(engine);
						interval = 11;
					}else if(inputline.equalsIgnoreCase("Invalid")){
						System.out.println("Invalid");
						System.exit(0);
						interval = 11;
					}
					thread.sleep(200);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
					
			}
		}catch(IOException ex){
			
		}
		
	}
	
	public void sendCredentials(Socket clientSocket, String username2, char[] password2){
		try{
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			printWriter.println( username2 + "-" + String.valueOf(password2));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}

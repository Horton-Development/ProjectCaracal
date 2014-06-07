package com.client.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.client.Engine.Engine;
import com.client.Screens.LoadScreen;
import com.client.Screens.LoginScreen;
import com.common.utils.BCrypt;
import com.server.Handlers.ResponseHandler;

public class RequestHandler implements Runnable{

	// Send a request to the server, use a header to tell the server what to do
	// with the data

	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;
	

	public boolean dataCorrect;
	int interval = 0;
	int times = 0;
	long time = System.currentTimeMillis();
	boolean running = true;
	String inputline = null;
	Thread thread = new Thread(this);
	
	ConnectionHandler connectionHandler = new ConnectionHandler();
	ResponseHandler responseHandler = new ResponseHandler();
	BCrypt bCrypt = new BCrypt();
	LoginScreen loginScreen;
	
	Engine engine;
	public RequestHandler(Engine engine, LoginScreen loginScreen){
		this.engine = engine;
		this.loginScreen = loginScreen;
	}
	
	public void checkUserData(Socket clientSocket, String username, String string){
		if(username.isEmpty() || String.valueOf(string).isEmpty()){
			
		}else{
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("LOGIN");
				sendCredentials(clientSocket, username, string);
				running = true;
				thread.start();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}

	
	
	public void run(){
		interval = 0;
		System.out.println("Waiting for server response!");
		try{
			inputline = bufferedReader.readLine();
			while(running){
				try{
					if(inputline.equalsIgnoreCase("Valid")){
						System.out.println("Valid");
						loginScreen.shutdown();
						new LoadScreen(engine);
						running = false;
					}else if(inputline.equalsIgnoreCase("Invalid")){
						System.out.println("Invalid");
						running = false;
					}
					thread.sleep(200);
				}catch(InterruptedException e){
					e.printStackTrace();
					interval = 11;
				}	
			}
		}catch(IOException ex){
			
		}
		
	}
	
	public void sendCredentials(Socket clientSocket, String username2, String password){
		try{
			System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			printWriter.println(username2 + "_" + BCrypt.hashpw(password, BCrypt.gensalt()));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}

package com.client.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.client.engine.Engine;
import com.client.screens.CreateAccountScreen;
import com.client.screens.LoadScreen;
import com.client.screens.LoginScreen;
import com.common.utils.BCrypt;
import com.server.handlers.ResponseHandler;

public class RequestHandler implements Runnable{

	// Send a request to the server, use a header to tell the server what to do
	// with the data

	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;

	Engine engine;

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
	LoginScreen loginScreen = new LoginScreen(engine);
	CreateAccountScreen accountScreen = new CreateAccountScreen(engine);

	// Constructor.
	public RequestHandler(Engine engine){
		this.engine = engine;
	}

	// Sends the data to the server to be validated.
	public void checkUserData(Socket clientSocket, String username, String string){
		if(username.isEmpty() || String.valueOf(string).isEmpty()){

		}else{
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("LOGIN");
				running = true;
				sendCredentials(clientSocket, username, string);
				thread.start();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
	}

	// Sends a create account request.
	public void createAccount(Socket clientSocket, String username, String string){
		if(username.isEmpty() || String.valueOf(string).isEmpty()){
			JOptionPane.showMessageDialog(null, "Please input username and password fields.", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("CREATE");
				running = true;
				sendAccountCreation(clientSocket, username, string);
				thread.start();
			}catch(IOException e){
				e.printStackTrace();
			}

		}
	}

	// Scans for a server response.
	public void run(){
		interval = 0;
		System.out.println("Waiting for server response!");
		try{
			inputline = bufferedReader.readLine();
			while(running){
				try{
					if(inputline.equalsIgnoreCase("Valid")){
						running = false;
						System.out.println("Valid");
						loginScreen.shutdown();
						new LoadScreen(engine);
					}else if(inputline.equalsIgnoreCase("Invalid")){
						System.out.println("Invalid");
						running = false;
					}else if(inputline.equalsIgnoreCase("Success")){
						JOptionPane.showMessageDialog(null, "Account Created!", "Server", JOptionPane.INFORMATION_MESSAGE);
						running = false;
						accountScreen.dispose();
						loginScreen.start();
					}
					Thread.sleep(200);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}catch(IOException ex){

		}

	}

	public void sendCredentials(Socket clientSocket, String username2, String password){
		try{
			System.out.println(username2 + "_" + BCrypt.hashpw(password, BCrypt.gensalt()));
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			printWriter.println(username2 + "_" + BCrypt.hashpw(password, BCrypt.gensalt()));
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public void sendAccountCreation(Socket clientSocket, String username2, String password){
		try{
			System.out.println(username2 + "_" + password);
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			printWriter.println(username2 + "_" + password);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}

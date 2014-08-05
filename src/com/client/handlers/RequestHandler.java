package com.client.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.client.screens.Game;
import com.client.screens.Main;
import com.client.utils.BCrypt;

public class RequestHandler{

	// Send a request to the server, use a header to tell the server what to do
	// with the data

	private static PrintWriter printWriter;
	private static BufferedReader bufferedReader;

	Main main;

	public boolean dataCorrect;

	int interval = 0;
	int times = 0;

	long time = System.currentTimeMillis();

	boolean running = true;

	String inputline = "**";


	ConnectionHandler connectionHandler = new ConnectionHandler(main);
	BCrypt bCrypt = new BCrypt();

	// Sends the data to the server to be validated.
	public void checkUserData(Socket clientSocket, String username, String string){
		if(username.isEmpty() || String.valueOf(string).isEmpty()){
			JOptionPane.showMessageDialog(null, "Please input username and password fields.", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			try{
				printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printWriter.println("LOGIN");
				running = true;
				sendCredentials(clientSocket, username, string);
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
				printWriter.println("CREATE");
				running = true;
				try{
					Thread.sleep(2000);
					System.out.println(username + "_" + string);
					printWriter.println(username + "_" + string);
					run(clientSocket);
				}catch(InterruptedException e){
					e.printStackTrace();
				}

			}catch(IOException e){
				e.printStackTrace();
			}

		}
	}
	
	public void sendCredentials(Socket clientSocket, String username2, String password){
		try{
			System.out.println(username2 + "_" + BCrypt.hashpw(password, BCrypt.gensalt()));
			printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			printWriter.println(username2 + "_" + BCrypt.hashpw(password, BCrypt.gensalt()));
			run(clientSocket);
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	// Scans for a server response.
	public void run(Socket clientSocket){
		interval = 0;
		System.out.println("Waiting for server response!");
		try{
			while(running){
				bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				inputline = bufferedReader.readLine();
				try{
					if(inputline.equals("**")){

					}else if(inputline.equalsIgnoreCase("Valid")){
						System.out.println("Valid");
						new Game();
						running = false;
					}else if(inputline.equalsIgnoreCase("Invalid")){
						System.out.println("Invalid");
						running = false;
					}else if(inputline.equalsIgnoreCase("Success")){
						Main.progressBar_1.setValue(100);
						Main.progressBar_1.setString("Account Created!");
						Main.btnNewButton.setEnabled(false);
						running = false;
					}
					Thread.sleep(200);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}catch(IOException e1){
			e1.printStackTrace();
		}
		

	}

}

package com.client.handlers;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.client.screens.Main;

public class ConnectionHandler{

	// Methods to create connection with server, does not handle
	// request/response ONLY Socket Handling!!!

	private static Socket socket;
	private static PrintWriter printWriter;
	public boolean connected;
	Main main;
	
	public ConnectionHandler(Main main){
		this.main = main;
	}

	// Connects to the login server.
	public void connectToServer(){
		try{
			RequestHandler requestHandler = new RequestHandler();
			socket = new Socket("localhost", 63450);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("CLIENT");
			System.out.println("Connected to the server.");
			requestHandler.checkUserData(socket, Main.txtRoot.getText(), String.valueOf(Main.passwordField.getPassword()));
		}catch(Exception e){
			System.out.println("Failed to connect!");
			Main.progressBar.setString("Failed to connect!");
			Main.btnLogin.setText("Re-Connect");
		}
	}

	// Returns the socket.
	public Socket getSocket(){
		return socket;

	}

}

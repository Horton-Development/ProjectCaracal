package com.client.Handlers;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.client.Screens.LoginScreen;

public class ConnectionHandler{

	// Methods to create connection with server, does not handle
	// request/response ONLY Socket Handling!!!

	private static Socket socket;
	private static PrintWriter printWriter;
	public boolean connected;
	
	//Connects to the login server.
	public void connectToServer(LoginScreen screen){
		try{
			socket = new Socket("localhost", 63450);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("CLIENT");
			System.out.println("Connected to the server.");
			JOptionPane.showMessageDialog(null, "Connected to the server.", "Server", JOptionPane.INFORMATION_MESSAGE);
			connected = true;
			screen.reconnect.setEnabled(false);
			screen.reconnect.setVisible(false);
			screen.button.setEnabled(true);
			screen.createAccount.setEnabled(true);
		}catch(Exception e){
			screen.button.setEnabled(false);
			screen.createAccount.setEnabled(false);
			System.out.println("Failed to connect!");
			JOptionPane.showMessageDialog(null, "Failed to connect!", "Server", JOptionPane.ERROR_MESSAGE);
			connected = false;
			screen.reconnect.setVisible(true);
			screen.reconnect.setEnabled(true);
		}
	}
	
	//Returns the socket.
	public Socket getSocket(){
		return socket;
		
	}
	
	

}

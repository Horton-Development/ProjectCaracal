package com.client.handlers;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.client.engine.Engine;
import com.client.screens.LoginScreen;

public class ConnectionHandler{

	// Methods to create connection with server, does not handle
	// request/response ONLY Socket Handling!!!

	private static Socket socket;
	private static PrintWriter printWriter;
	public boolean connected;

	// Connects to the login server.
	public void connectToServer(LoginScreen screen, Engine engine){
		try{
			socket = new Socket("localhost", 63450);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println("CLIENT");
			System.out.println("Connected to the server.");
			JOptionPane.showMessageDialog(null, "Connected to the server.", "Server", JOptionPane.INFORMATION_MESSAGE);
			new LoginScreen(engine).connected = true;
			screen.reconnect.setEnabled(false);
			screen.reconnect.setVisible(false);
			screen.button.setEnabled(true);
			screen.createAccount.setEnabled(true);
		}catch(Exception e){
			screen.button.setEnabled(false);
			screen.createAccount.setEnabled(false);
			new LoginScreen(engine).connected = false;
			System.out.println("Failed to connect!");
			JOptionPane.showMessageDialog(null, "Failed to connect!", "Server", JOptionPane.ERROR_MESSAGE);
			screen.reconnect.setVisible(true);
			screen.reconnect.setEnabled(true);
		}
	}

	// Returns the socket.
	public Socket getSocket(){
		return socket;

	}

}

package com.client.Screens;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.client.Engine.Engine;
import com.client.Handlers.ConnectionHandler;
import com.client.Handlers.RequestHandler;

public class LoginScreen extends Screen implements Runnable, ActionListener{
	/*
	 * Screen used to take user input for login. Should implement
	 * ConnectionHandler to initialise Connection with Server Use
	 * RequestHandler.sendData() to send data stream to server Use
	 * ResponseHandler.waitForResponse() to wait for server response
	 * (success/failed login) Proceed to menu screen
	 */

	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);

	RequestHandler requestHandler = new RequestHandler(engine, this);
	ConnectionHandler connectionHandler = new ConnectionHandler();

	// Java Components
	JTextField textField = new JTextField(8);
	JLabel usernameLabel = new JLabel("Username:");
	JLabel passwordLabel = new JLabel("Password:");
	JLabel invalidLabel = new JLabel("Invalid Password!");
	JPasswordField passwordField = new JPasswordField(8);
	JButton button = new JButton("Login");
	String username;
	char[] password;

	// Constructor
	public LoginScreen(Engine engine){
		super(engine);
		thread.start();
	}

	// Runs the login screen
	public void run(){

		// If the engine is running
		if(engine.running){
			button.addActionListener(this);
			this.setLayout(new FlowLayout());
			this.add(usernameLabel);
			this.add(textField);
			this.add(passwordLabel);
			this.add(passwordField);
			this.add(button);
			this.setSize(400, 110);
			this.setTitle("ProjectCaracal");
			this.setResizable(false);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(true);
			connectionHandler.connectToServer();
		}else{

		}
	}

	// Action Listener
	public void actionPerformed(ActionEvent e){

		//If the action performed was the clicking of the button
		if(e.getActionCommand().equals(button.getActionCommand())){
			username = textField.getText();
			password = passwordField.getPassword();

			//If they are connected to the server.
			if(connectionHandler.connected){
				try{
					requestHandler.checkUserData(new Socket("localhost", 63450), username, String.valueOf(password));
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}else{
				System.out.println("Could not make a connection to the server.");
			}
		}
	}

	public void shutdown(){
		this.dispose();
	}

}
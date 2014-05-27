package com.client.Screens;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.client.Engine.Engine;
import com.client.Handlers.ConnectionHandler;

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
	
	ConnectionHandler connectionHandler = new ConnectionHandler();

	// Java Components
	JTextField textField = new JTextField(8);
	JLabel usernameLabel = new JLabel("Username:");
	JLabel passwordLabel = new JLabel("Password:");
	JPasswordField passwordField = new JPasswordField(8);
	JButton button = new JButton("Login");

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
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
		}else{

		}
	}

	// Action Listener
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals(button.getActionCommand())){
			connectionHandler.connectToServer();
			if(connectionHandler.connected){
				new LoadScreen(engine);
			}else{
				System.exit(0);
			}
		}
	}

}

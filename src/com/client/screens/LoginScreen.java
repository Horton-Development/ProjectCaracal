package com.client.screens;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.client.engine.Engine;
import com.client.handlers.ConnectionHandler;
import com.client.handlers.RequestHandler;

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

	// Java Components
	JTextField textField = new JTextField(8);
	JLabel usernameLabel = new JLabel("Username:");
	JLabel passwordLabel = new JLabel("Password:");
	JLabel invalidLabel = new JLabel("Invalid Password!");
	JPasswordField passwordField = new JPasswordField(8);
	
	public boolean connected;

	public JButton button = new JButton("Login");
	public JButton createAccount = new JButton("Create Account");
	public JButton reconnect = new JButton("Reconnect");

	String username;

	char[] password;

	// Constructor
	public LoginScreen(Engine engine){
		super(engine);
	}

	public void start(){
		thread.start();
	}

	// Runs the login screen
	public void run(){
		ConnectionHandler connectionHandler = new ConnectionHandler();
		setSize();
		setTitle();
		addParametersToComponents();
		setLayout();
		addComponents();
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		connectionHandler.connectToServer(this, engine);
	}

	// Adds parameters to the components
	public void addParametersToComponents(){
		createAccount.setEnabled(false);
		button.setEnabled(false);
		createAccount.setActionCommand("Create");
		button.setActionCommand("Login");
		reconnect.setActionCommand("Reconnect");
		createAccount.addActionListener(this);
		reconnect.addActionListener(this);
		button.addActionListener(this);
		reconnect.setVisible(false);
	}

	// Sets the layout
	public void setLayout(){
		this.setLayout(new FlowLayout());
	}

	// Sets the size
	public void setSize(){
		this.setSize(400, 110);
	}

	// Sets the title
	public void setTitle(){
		this.setTitle("ProjectCaracal");
	}

	// Adds components
	public void addComponents(){
		this.add(usernameLabel);
		this.add(textField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(button);
		this.add(createAccount);
		this.add(reconnect);
	}

	// Action Listener
	public void actionPerformed(ActionEvent e){
		ConnectionHandler connectionHandler = new ConnectionHandler();
		RequestHandler requestHandler = new RequestHandler(engine);
		// If the action performed was the clicking of the button
		if(e.getActionCommand().equals("Login")){
			connected = true;
			username = textField.getText();
			password = passwordField.getPassword();

			// If they are connected to the server.
			if(connected){
				try{
					requestHandler.checkUserData(new Socket("localhost", 63450), username, String.valueOf(password));
					shutdown();
				}catch(UnknownHostException e1){
					e1.printStackTrace();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Could not make a connection to the server.", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Could not make a connection to the server.");
			}
		}else if(e.getActionCommand().equals("Create")){
			new CreateAccountScreen(engine).start();
			shutdown();
		}else if(e.getActionCommand().equals("Reconnect")){
			connectionHandler.connectToServer(this, engine);
		}
	}

	public void shutdown(){
		this.dispose();
	}

}
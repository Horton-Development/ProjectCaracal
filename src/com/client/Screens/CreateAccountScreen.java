
package com.client.Screens;

import com.client.Engine.Engine;
import com.client.Handlers.RequestHandler;
import com.server.Handlers.ConfigHandler;
import com.server.Handlers.ResponseHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class CreateAccountScreen extends Screen implements Runnable, ActionListener{
    
    private static final long serialVersionUID = 1L;
    
    JButton createAccount = new JButton("Create Account");
    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel retypePasswordLabel = new JLabel("Retype Password: ");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField verifyPasswordField = new JPasswordField();
    
    FlowLayout layout = new FlowLayout();
    
    Thread thread = new Thread(this);
    
    boolean running;
    
    ConfigHandler configHandler = new ConfigHandler();
    
    
    
    
    
    //Constructor
    public CreateAccountScreen(Engine engine){
        super(engine);
    }

    public void start(){
    	running = true;
        thread.start();
    }
    
    @Override
    public void run() {
        new JFrame();
        this.setSize(250, 160);
        this.setLayout(layout);
        addParametersToComponents();
        this.setResizable(false);
        this.setTitle("Create Account");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    //Adds parameters to the components.
    private void addParametersToComponents(){
    	addVariableToButtons();
        usernameField.setColumns(10);
        passwordField.setColumns(10);
        verifyPasswordField.setColumns(10);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(retypePasswordLabel);
        this.add(verifyPasswordField);
        this.add(createAccount);
    }
    
    private void addVariableToButtons(){
    	createAccount.addActionListener(this);
    	createAccount.setActionCommand("CREATE");
    }
    
    public void shutdown(){
    	this.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e){
		ResponseHandler responseHandler = new ResponseHandler();
		RequestHandler requestHandler = new RequestHandler(engine);
		if(e.getActionCommand().equals("CREATE")){
			if(usernameField.getText().equals(null) || passwordField.getPassword().equals(null) || verifyPasswordField.getPassword().equals(null)){
				
			}else{
				if(responseHandler.checkUsername(usernameField.getText())){
					JOptionPane.showMessageDialog(null, "Username already in use.");
				}else{
					String username = usernameField.getText();
					char[] password = passwordField.getPassword();
					try{
						requestHandler.createAccount(new Socket("localhost", 63450), username, String.valueOf(password));
					}catch(UnknownHostException e1){
						e1.printStackTrace();
					}catch(IOException e1){
						e1.printStackTrace();
					}
				}
				
			}
			
		}
	}
    
    
    
    
}

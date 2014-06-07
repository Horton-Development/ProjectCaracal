
package com.server.Handlers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.server.Engine.Engine;


public class StartupHandler extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	
	ConnectionHandler connectionHandler = new ConnectionHandler();
	ErrorHandler errorHandler = new ErrorHandler();
	JButton startButton = new JButton("Start Server");
	JButton stopButton = new JButton("Stop Server");
	JTextArea textArea = new JTextArea();
	
    boolean running = false;
    
    //Main starting method
    public static void main(String[] args){
    	new StartupHandler();
    }
    
    //Sets the frame size and starts a connection
    public StartupHandler(){
    	jComponentSetup();
    	jFrameSetup();
        toggleButtton();
    }

    //When an action is performed
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equalsIgnoreCase("Start")){
			stopButton.setEnabled(true);
			startButton.setEnabled(false);
			new Engine(this);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Stop")){
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			connectionHandler.endConnection(connectionHandler.serverSocket);
		}
	}
	
	//Sets up the server jframe
	private void jFrameSetup(){
		this.setSize(600, 500);
        this.setTitle("ProjectCaracal (Server)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(startButton, BorderLayout.PAGE_START);
        this.add(stopButton, BorderLayout.PAGE_END);
        this.add(textArea, BorderLayout.CENTER);
        this.setVisible(true);
        this.setResizable(false);
	}
	
	//Sets up the server components
	private void jComponentSetup(){
		textArea.setEditable(false);
    	textArea.setColumns(1);
    	textArea.setRows(20);
    	textArea.setFont(new Font("Arial", Font.PLAIN, 18));
    	textArea.setAutoscrolls(true);
    	startButton.addActionListener(this);
        startButton.setActionCommand("Start");
        stopButton.addActionListener(this);
        stopButton.setActionCommand("Stop");
	}
	
	//Toggles the stop button
	private void toggleButtton(){
		if(stopButton.isEnabled()){
			stopButton.setEnabled(false);
		}else{
			stopButton.setEnabled(true);
		}
	}
    
}

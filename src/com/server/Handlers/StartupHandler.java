
package com.server.Handlers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    	textArea.setEditable(false);
    	startButton.addActionListener(this);
        startButton.setActionCommand("Start");
        stopButton.addActionListener(this);
        stopButton.setActionCommand("Stop");
    	this.setSize(800, 500);
        this.setTitle("ProjectCaracal (Server)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.add(startButton);
        this.add(stopButton);
        this.add(textArea);
        this.setVisible(true);
        this.setResizable(false);
        stopButton.setEnabled(false);
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
			connectionHandler.endConnection();
		}
	}
    
}

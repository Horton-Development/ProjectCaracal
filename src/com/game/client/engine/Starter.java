package com.game.client.engine;

import java.applet.Applet;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Starter extends JApplet {
	private String[] description = { "640/480", "800/600", "1280/960", "Fullscreen" };

	private JComboBox c = new JComboBox();
	
	private JLabel l = new JLabel("Please Select Resolution.");

	private JButton b = new JButton("Start Game!");

	private int count = 0;

	public void init() {
		for (int i = 0; i < 4; i++)
			c.addItem(description[count++]);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(b)) {
						
						if(c.getSelectedItem().equals("640/480")){
							Frame.main("Small");
							
						}else if(c.getSelectedItem().equals("800/600")){
							Frame.main("Medium");
							
						}else if(c.getSelectedItem().equals("1280/960")){
							Frame.main("Large");
							
						}else if(c.getSelectedItem().equals("Fullscreen")){
							Frame.main("Fullscreen");
							System.exit(0);
							
						}
					}
				}
			});

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(l);
		cp.add(c);
		cp.add(b);
	}

	public static void main(String[] args) {
		run(new Starter(), 250, 125);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		frame.setTitle("Game");
		applet.init();
		applet.start();
		frame.setVisible(true);
	}
} // /:~


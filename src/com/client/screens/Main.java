package com.client.screens;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.client.handlers.ConnectionHandler;
import com.client.handlers.RequestHandler;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.server.handlers.ResponseHandler;

public class Main extends JFrame{
	public static JTextField txtRoot;
	public static JPasswordField passwordField;
	public static JTextField textField;
	public static JPasswordField passwordField_1;
	public static JPasswordField passwordField_2;
	public static JTextField textField_1;
	public static JProgressBar progressBar = new JProgressBar();
	public static JProgressBar progressBar_1 = new JProgressBar();
	public static JButton btnLogin = new JButton("Login");
	public static JButton btnNewButton = new JButton("Create Account");
	protected Main Main;
	public static JPanel panel = new JPanel();
	public static JPanel panel_1 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					Main frame = new Main();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main(){
		setLocationByPlatform(true);
		setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		setSize(new Dimension(390, 289));
		setResizable(false);
		setTitle("Project Caracal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 384, 254);
		getContentPane().add(tabbedPane);

		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {ColumnSpec.decode("max(50dlu;default)"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(161dlu;default)"),}, new RowSpec[] {RowSpec.decode("max(20dlu;default)"), FormFactory.NARROW_LINE_GAP_ROWSPEC, RowSpec.decode("max(14dlu;default)"), FormFactory.NARROW_LINE_GAP_ROWSPEC, RowSpec.decode("max(39dlu;default)"), FormFactory.NARROW_LINE_GAP_ROWSPEC, RowSpec.decode("max(57dlu;default)"),}));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblUsername, "1, 1, right, default");

		txtRoot = new JTextField();
		txtRoot.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		txtRoot.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtRoot, "3, 1, 1, 2, center, center");
		txtRoot.setColumns(18);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPassword, "1, 3, right, default");

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		passwordField.setColumns(18);
		panel.add(passwordField, "3, 3, center, center");

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel.add(btnExit, "1, 5, fill, fill");

		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ConnectionHandler connectionHandler = new ConnectionHandler(Main);
				connectionHandler.connectToServer();
			}
		});
		btnLogin.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel.add(btnLogin, "3, 5, fill, fill");

		progressBar.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		progressBar.setString("No Connection....");
		progressBar.setStringPainted(true);
		panel.add(progressBar, "1, 7, 3, 1, fill, fill");

		tabbedPane.addTab("New Account", null, panel_1, null);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),}, new RowSpec[] {FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, RowSpec.decode("max(52dlu;default)"),}));

		JLabel lblDesiredUsername = new JLabel("Desired Username:");
		lblDesiredUsername.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblDesiredUsername, "1, 2");

		textField = new JTextField();
		textField.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(textField, "3, 2, center, default");
		textField.setColumns(16);

		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblPassword_1, "1, 4, right, default");

		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(16);
		passwordField_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(passwordField_1, "3, 4, center, default");

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblConfirmPassword, "1, 6");

		passwordField_2 = new JPasswordField();
		passwordField_2.setColumns(16);
		passwordField_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(passwordField_2, "3, 6, center, default");

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblEmail, "1, 8, right, default");

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(textField_1, "3, 8, center, default");
		textField_1.setColumns(16);

		
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ResponseHandler responseHandler = new ResponseHandler();
				RequestHandler requestHandler = new RequestHandler();
				if(textField.getText().equals(null) || passwordField_1.getPassword().equals(null) || passwordField_2.getPassword().equals(null)){

				}else{
					if(responseHandler.checkUsername(textField.getText())){
						JOptionPane.showMessageDialog(null, "Username already in use.");
					}else{
						String username = textField.getText();
						char[] password = passwordField_1.getPassword();
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
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(btnNewButton, "1, 10, 3, 1, fill, fill");

		progressBar_1.setString("");
		progressBar_1.setStringPainted(true);
		progressBar_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(progressBar_1, "1, 12, 3, 1, fill, fill");
	}

	private class SwingAction extends AbstractAction{
		public SwingAction(){
			putValue(SMALL_ICON, null);
			putValue(ACTION_COMMAND_KEY, "CREATE");
			putValue(NAME, "Create new account");
			putValue(SHORT_DESCRIPTION, "Creates a new account.");
		}

		public void actionPerformed(ActionEvent e){
		}
	}

	private class SwingAction_1 extends AbstractAction{
		public SwingAction_1(){
			putValue(ACTION_COMMAND_KEY, "EXIT");
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exits the program.");
		}

		public void actionPerformed(ActionEvent e){
		}
	}

	private class SwingAction_2 extends AbstractAction{
		public SwingAction_2(){
			putValue(NAME, "Create new account");
			putValue(SHORT_DESCRIPTION, "Switches to create new account screen.");
		}

		public void actionPerformed(ActionEvent e){
		}
	}
}

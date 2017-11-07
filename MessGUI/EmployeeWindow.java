package MessGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.SpringLayout;
import javax.swing.JDesktopPane;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import Mess.EmployeeLogin;
import SWD.SendEmail;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class EmployeeWindow {

	public JFrame frmEmployeeLogin;
	private JPasswordField passwordField;
	private JFormattedTextField formattedTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeWindow window = new EmployeeWindow();
					window.frmEmployeeLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeLogin = new JFrame();
		frmEmployeeLogin.setResizable(false);
		frmEmployeeLogin.setTitle("Employee Login");
		frmEmployeeLogin.setBounds(100, 100, 450, 300);
		frmEmployeeLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = formattedTextField.getText();
				String pass = passwordField.getText();
				boolean flag = EmployeeLogin.validateLogin(id, pass);
				if(flag == true){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								frmEmployeeLogin.setVisible(false);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
				}
				
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 155, SpringLayout.WEST, frmEmployeeLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -78, SpringLayout.SOUTH, frmEmployeeLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -156, SpringLayout.EAST, frmEmployeeLogin.getContentPane());
		frmEmployeeLogin.getContentPane().setLayout(springLayout);
		frmEmployeeLogin.getContentPane().add(btnNewButton);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmEmployeeLogin.getContentPane().add(desktopPane);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 18, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -129, SpringLayout.EAST, frmEmployeeLogin.getContentPane());
		frmEmployeeLogin.getContentPane().add(passwordField);
		
		formattedTextField = new JFormattedTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, formattedTextField, -206, SpringLayout.SOUTH, frmEmployeeLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 14, SpringLayout.SOUTH, formattedTextField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, formattedTextField);
		springLayout.putConstraint(SpringLayout.WEST, formattedTextField, 182, SpringLayout.WEST, frmEmployeeLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, formattedTextField, -129, SpringLayout.EAST, frmEmployeeLogin.getContentPane());
		frmEmployeeLogin.getContentPane().add(formattedTextField);
		
		JLabel lblUsername = new JLabel("ID No.");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 3, SpringLayout.NORTH, formattedTextField);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -6, SpringLayout.WEST, formattedTextField);
		frmEmployeeLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 3, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -6, SpringLayout.WEST, passwordField);
		frmEmployeeLogin.getContentPane().add(lblPassword);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setToolTipText("Enter your ID no. in the field provided.\r\n E-mail containing your previously set password will be sent to you.\r\n");
		springLayout.putConstraint(SpringLayout.NORTH, btnForgotPassword, 16, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnForgotPassword, 0, SpringLayout.WEST, btnNewButton);
		btnForgotPassword.setVerticalAlignment(SwingConstants.BOTTOM);
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String id = formattedTextField.getText();
					
					try{
						String query = "SELECT * FROM employee WHERE emp_id ='"+ id + "'";
						ResultSet rs = stmt.executeQuery(query);
						String e=null, p=null;
						while(rs.next()){
						e = rs.getString("email");
						p = rs.getString("password");
						}
						
						p = p + " is your password for SWD.";
						
						SendEmail fp = new SendEmail();
						fp.sendmail(e, "Password for SWD", p);
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Invalid ID no.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		frmEmployeeLogin.getContentPane().add(btnForgotPassword);
		
	}
}

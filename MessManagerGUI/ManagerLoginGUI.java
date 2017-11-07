package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Mess.MessManager;
import SWD.SendEmail;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;

public class ManagerLoginGUI {

	public JFrame frmManagerLogin;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JButton btnForgotPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLoginGUI window = new ManagerLoginGUI();
					window.frmManagerLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerLoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagerLogin = new JFrame();
		frmManagerLogin.setResizable(false);
		frmManagerLogin.setTitle("Manager Login");
		frmManagerLogin.setBounds(100, 100, 300, 300);
		frmManagerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagerLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(109, 50, 138, 20);
		frmManagerLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(28, 84, 66, 14);
		frmManagerLogin.getContentPane().add(lblPassword);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(109, 81, 138, 20);
		frmManagerLogin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MessManager.validateLogin(textField.getText(),textField_1.getText())){
					lblNewLabel.setText("");
					ManagerOptions window1 = new ManagerOptions(textField.getText(), textField_1.getText());
					window1.frmManagerOptions.setVisible(true);
					frmManagerLogin.dispose();
				}
				else{
					lblNewLabel.setText("Invalid ID or Password");
				}
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnLogin.setBounds(109, 112, 138, 23);
		frmManagerLogin.getContentPane().add(btnLogin);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(28, 53, 64, 14);
		frmManagerLogin.getContentPane().add(lblId);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 146, 197, 14);
		frmManagerLogin.getContentPane().add(lblNewLabel);
		
		btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String id = textField.getText();
					
					try{
						String query = "SELECT * FROM manager WHERE id ='"+ id + "'";
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
		btnForgotPassword.setBounds(109, 174, 138, 23);
		frmManagerLogin.getContentPane().add(btnForgotPassword);
	}
}

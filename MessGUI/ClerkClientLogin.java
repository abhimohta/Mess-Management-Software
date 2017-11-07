package MessGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import Mess.EmployeeClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClerkClientLogin {

	public JFrame frmClerkClientLogin;
	private final JLabel lblId = new JLabel("ID");
	private JTextField textField;
	private JPasswordField passwordField;
	static Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkClientLogin window = new ClerkClientLogin();
					window.frmClerkClientLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClerkClientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClerkClientLogin = new JFrame();
		frmClerkClientLogin.setTitle("Clerk Client Login");
		frmClerkClientLogin.setResizable(false);
		frmClerkClientLogin.setBounds(100, 100, 450, 300);
		frmClerkClientLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClerkClientLogin.getContentPane().setLayout(null);
		lblId.setBounds(132, 50, 58, 31);
		frmClerkClientLogin.getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(132, 92, 58, 14);
		frmClerkClientLogin.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(200, 55, 110, 20);
		frmClerkClientLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 89, 110, 20);
		frmClerkClientLogin.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField.getText();
				String pass = passwordField.getText();
				boolean flag = false;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					
/*					String query = "SELECT * FROM employee WHERE emp_id ='"+id+"' AND password ='"+ pass + "'"  ;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
*/
					
					String query = "SELECT * FROM employee WHERE emp_id = ? AND password = ?" ;
		            java.sql.PreparedStatement stmt =  conn.prepareStatement(query);
		            stmt.setString(1, id);
		            stmt.setString(2, pass);
		            ResultSet rs = stmt.executeQuery();
		            
						String d;
					while(rs.next()){
						d = rs.getString("designation");
						if(d.equals("clerk")){
						flag = true;
						String s = "Name: "+rs.getString("name")+" ID: "+rs.getString("emp_id") + " Designation: "+rs.getString("designation");
						JOptionPane.showMessageDialog(null, s, "", JOptionPane.INFORMATION_MESSAGE);
						EmployeeClient.designation = rs.getString("designation");
						}
					}
					
					
					
					if(flag == true){
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ClerkScan window = new ClerkScan();
									window.frmClerkClient.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									frmClerkClientLogin.setVisible(false);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					
					if(flag == false)
						JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database disconnected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(177, 131, 89, 65);
		frmClerkClientLogin.getContentPane().add(btnLogin);
	}

}

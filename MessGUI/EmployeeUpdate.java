package MessGUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Mess.Cook;
import Mess.Dishwasher;
import Mess.Employee;
import Mess.Janitor;
import Mess.Waiter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import Mess.Clerk;
public class EmployeeUpdate {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static String ID ;
	private static String PASS;
	private static String DESIGNATION;
	static Employee employee;
	/**
	 * Launch the application.
	 */
	static Connection conn = null;
	private JButton btnNewButton;
	private JButton btnUpdate;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeUpdate window = new EmployeeUpdate(null,null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeUpdate(String ID, String PASS, String DESIGNATION) {
		EmployeeUpdate.ID = ID;
		EmployeeUpdate.PASS = PASS;
		EmployeeUpdate.DESIGNATION = DESIGNATION;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
			
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		String query = "SELECT * FROM employee WHERE emp_id ='"+ID+"' AND password ='"+ PASS + "'" ;
		Statement stmt = null;
		String tf1="";
		String tfpass="";
		String tf2="";
		String tf3="";
		String tf4="";
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				tf1 = rs.getString("name");	
				tf2 = rs.getString("address");	
				tf3 = rs.getString("mobileno");
				tf4 = rs.getString("email");	
				tfpass= rs.getString("password");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(58, 10, 60, 20);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField(tf1);
		textField.setEditable(false);
		textField.setBounds(214, 10, 159, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(58, 56, 55, 16);
		frame.getContentPane().add(lblAddress);
		
		textField_2 = new JTextField(tf2);
		textField_2.setEditable(false);
		textField_2.setBounds(214, 54, 159, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblmob = new JLabel("Mobile Number");
		lblmob.setBounds(58, 97, 117, 22);
		frame.getContentPane().add(lblmob);

		textField_3 = new JTextField(tf3);
		textField_3.setEditable(false);
		textField_3.setBounds(214, 98, 159, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(58, 143, 55, 16);
		frame.getContentPane().add(lblEmail);
		
		textField_4 = new JTextField(tf4);
		textField_4.setEditable(false);
		textField_4.setBounds(214, 141, 159, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnUpdate = new JButton("  Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String s1 = textField.getText();
					//System.out.println(s1);
					String update1 = "UPDATE employee SET name = '"+s1+"' WHERE emp_id = '"+ID+"'";
					int rows1 = stmt.executeUpdate(update1);
					
				} catch (SQLException h) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String s2 = textField_2.getText();
					String update2 = "UPDATE employee SET address = '"+s2+"' WHERE emp_id = '"+ID+"'";
					int rows2 = stmt.executeUpdate(update2);
				} catch (SQLException h) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String s3 = textField_3.getText();
						String update3 = "UPDATE employee SET mobileno = '"+s3+"' WHERE emp_id = '"+ID+"'";
						int rows3 = stmt.executeUpdate(update3);
				} catch (SQLException h) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String s4 = textField_4.getText();
						String update4 = "UPDATE employee SET email = '"+s4+"' WHERE emp_id = '"+ID+"'";
						int rows4 = stmt.executeUpdate(update4);
				} catch (SQLException h) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				textField.setEditable(false);
				textField_2.setEditable(false);
				textField_3.setEditable(false);
				textField_4.setEditable(false);

				JOptionPane.showMessageDialog(null, "Details updated!", "", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnUpdate.setBounds(80, 234, 98, 26);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		btnNewButton.setBounds(214, 234, 98, 26);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UpdatePassword dialog = new UpdatePassword(ID, PASS);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(214, 183, 159, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnEditDetails = new JButton("Edit Details");
		btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
			}
		});
		btnEditDetails.setBounds(58, 183, 120, 23);
		frame.getContentPane().add(btnEditDetails);
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
}

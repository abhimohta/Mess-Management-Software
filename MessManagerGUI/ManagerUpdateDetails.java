package MessManagerGUI;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Mess.MessManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerUpdateDetails {

	JFrame frmUpdateManagerDetails;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private static String ID;
	private static String PASS;

	/**
	 * Launch the application.
	 */
	
	static Connection conn = null;
	private JButton btnCancel;
	private JButton btnEditDetails;
	private JButton btnUpdatePassword;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUpdateDetails window = new ManagerUpdateDetails("111", "wer");
					window.frmUpdateManagerDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerUpdateDetails(String ID, String PASS) {
		ManagerUpdateDetails.ID = ID;
		ManagerUpdateDetails.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpdateManagerDetails = new JFrame();
		frmUpdateManagerDetails.setResizable(false);
		frmUpdateManagerDetails.setTitle("Update Manager Details");
		frmUpdateManagerDetails.setBounds(100, 100, 300, 301);
		frmUpdateManagerDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpdateManagerDetails.getContentPane().setLayout(null);
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "SELECT * FROM manager WHERE id ='"+ID+"' AND password ='"+ PASS + "'" ;
		Statement stmt = null;
		String tf="";
		String tf1="";
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
				tf = rs.getString("name");	
				tf1 = rs.getString("mobile");	
				tf2 = rs.getString("address");	
				tf3 = rs.getString("password");
				tf4 = rs.getString("email");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(20, 29, 70, 14);
		frmUpdateManagerDetails.getContentPane().add(lblName);
		
		textField = new JTextField(tf);
		textField.setEditable(false);
		textField.setBounds(100, 26, 163, 20);
		frmUpdateManagerDetails.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMobileNumber = new JLabel("Mobile No.");
		lblMobileNumber.setBounds(20, 60, 70, 14);
		frmUpdateManagerDetails.getContentPane().add(lblMobileNumber);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(20, 85, 70, 14);
		frmUpdateManagerDetails.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField(tf1);
		textField_1.setEditable(false);
		textField_1.setBounds(100, 54, 163, 20);
		frmUpdateManagerDetails.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(tf2);
		textField_2.setEditable(false);
		textField_2.setBounds(100, 82, 163, 20);
		frmUpdateManagerDetails.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setBounds(20, 116, 70, 14);
		frmUpdateManagerDetails.getContentPane().add(lblEmailId);
		
		textField_4 = new JTextField(tf4);
		textField_4.setEditable(false);
		textField_4.setBounds(100, 113, 163, 20);
		frmUpdateManagerDetails.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessManager.updateName(textField.getText(), ID);
				MessManager.updateMobile(textField_1.getText(), ID);
				MessManager.updateAddress(textField_2.getText(), ID);
				//MessManager.updatePassword(textField_3.getText(), ID);
				MessManager.updateEmail(textField_4.getText(), ID);
				textField.setEditable(false);
				textField_1.setEditable(false);
				textField_2.setEditable(false);
				//textField_3.setEditable(false);
				textField_4.setEditable(false);
				JOptionPane.showMessageDialog(null, "Details Updated.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnUpdate.setBounds(75, 178, 144, 23);
		frmUpdateManagerDetails.getContentPane().add(btnUpdate);
		
		btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUpdateManagerDetails.dispose();
			}
		});
		btnCancel.setBounds(75, 246, 144, 23);
		frmUpdateManagerDetails.getContentPane().add(btnCancel);
		
		btnEditDetails = new JButton("Edit Details");
		btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				//textField_3.setEditable(true);
				textField_4.setEditable(true);
			}
		});
		btnEditDetails.setBounds(75, 144, 144, 23);
		frmUpdateManagerDetails.getContentPane().add(btnEditDetails);
		
		btnUpdatePassword = new JButton("Update Password");
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PassUpdate dialog = new PassUpdate(ID, PASS);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnUpdatePassword.setBounds(75, 212, 144, 23);
		frmUpdateManagerDetails.getContentPane().add(btnUpdatePassword);
	}

}

package StudentGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import SWD.SWDAdmin;
import SWD.SendEmail;
import User.Student;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JToggleButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;

import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class student {

	public JFrame frame;
	private JTextField IDNumber;
	private JTextField Password;
	Student S = new Student();
	private String id,pwd;
	private JTextField textFieldID;
	private JTextField textFieldName;

	private boolean applied;
	private ResultSet rs1,rs2;
	
	private Date start, end, current;
	private JTextField pwdField;
	private JTextField pwdField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student window = new student();
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
	public student() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 619, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panel = new JPanel();
		panel.setToolTipText("Enter ID");
		frame.getContentPane().add(panel, "name_437347720537392");
		panel.setLayout(null);
		

		final JLabel label1 = new JLabel("");
		label1.setBounds(80, 68, 208, 14);
		panel.add(label1);
		
		
		IDNumber = new JTextField();
		IDNumber.setToolTipText("Enter ID");
		IDNumber.setBounds(80, 37, 86, 20);
		panel.add(IDNumber);
		IDNumber.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID Number");
		lblNewLabel.setBounds(77, 12, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(202, 12, 86, 14);
		panel.add(lblNewLabel_1);
		
		Password = new JPasswordField();
		Password.setToolTipText("Enter Password");
		Password.setBounds(202, 37, 86, 20);
		panel.add(Password);
		
		final JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_437350917428216");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Profile", null, panel_3, null);
		panel_3.setLayout(null);
		
		final JFormattedTextField NameTextField = new JFormattedTextField();
		NameTextField.setEditable(false);
		NameTextField.setBounds(101, 44, 219, 20);
		panel_3.add(NameTextField);
		
		final JFormattedTextField IDTextField = new JFormattedTextField();
		IDTextField.setEditable(false);
		IDTextField.setBounds(101, 13, 219, 20);
		panel_3.add(IDTextField);
		
		final JFormattedTextField EmailTextField = new JFormattedTextField();
		EmailTextField.setEditable(false);
		EmailTextField.setBounds(101, 71, 219, 20);
		panel_3.add(EmailTextField);
		
		final JFormattedTextField MobileTextField = new JFormattedTextField();
		MobileTextField.setEditable(false);
		MobileTextField.setBounds(101, 102, 219, 20);
		panel_3.add(MobileTextField);
		
		final JFormattedTextField AddressTextField = new JFormattedTextField();
		AddressTextField.setEditable(false);
		AddressTextField.setBounds(101, 133, 219, 20);
		panel_3.add(AddressTextField);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 47, 46, 14);
		panel_3.add(lblName);
		
		JLabel lblIdNumber = new JLabel("ID Number");
		lblIdNumber.setBounds(10, 16, 67, 14);
		panel_3.add(lblIdNumber);
		
		JButton UpdateDetails = new JButton("Update Profile");
		UpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(NameTextField.isEditable()){

					String name = NameTextField.getText();
					NameTextField.setText(name);
					SWDAdmin.updateName(name, id);
					NameTextField.setEditable(false);
					
					String mobile = MobileTextField.getText();
					MobileTextField.setText(mobile);
					SWDAdmin.updateMobile(mobile, id);
					MobileTextField.setEditable(false);
					
					String add = AddressTextField.getText();
					AddressTextField.setText(add);
					SWDAdmin.updateAddress(add, id);
					AddressTextField.setEditable(false);
					
					String mail = EmailTextField.getText();
					EmailTextField.setText(mail);
					SWDAdmin.updateEmail(mail, id);
					EmailTextField.setEditable(false);
				}
				else{
				NameTextField.setEditable(true);
				MobileTextField.setEditable(true);
				AddressTextField.setEditable(true);
				EmailTextField.setEditable(true);
				}
				
			}
		});
		UpdateDetails.setBounds(101, 244, 219, 23);
		panel_3.add(UpdateDetails);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = null;
				pwd = null;
				panel_1.setVisible(false);
				panel.setVisible(true);
				Password.setText("");
			}
		});
		btnLogOut.setBounds(363, 62, 210, 23);
		panel_3.add(btnLogOut);
		
		JButton button_2 = new JButton("Search Student");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				search newWindow = new search();
				newWindow.setVisible(true);
				
			}
		});
		button_2.setBounds(363, 11, 210, 23);
		panel_3.add(button_2);
		
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 78, 46, 14);
		panel_3.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(10, 105, 46, 14);
		panel_3.add(lblMobile);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 136, 95, 14);
		panel_3.add(lblAddress);
		
		final JFormattedTextField LeaveTextField = new JFormattedTextField();
		LeaveTextField.setEditable(false);
		LeaveTextField.setBounds(101, 164, 219, 20);
		panel_3.add(LeaveTextField);
		
		final JFormattedTextField BillTextField = new JFormattedTextField();
		BillTextField.setEditable(false);
		BillTextField.setBounds(101, 195, 219, 20);
		panel_3.add(BillTextField);
		
		JLabel lblLeaveStatus = new JLabel("Leave Status");
		lblLeaveStatus.setBounds(10, 167, 95, 14);
		panel_3.add(lblLeaveStatus);
		
		JLabel lblBill = new JLabel("Bill");
		lblBill.setBounds(10, 198, 46, 14);
		panel_3.add(lblBill);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.RED);
		tabbedPane.addTab("Apply Leave", null, panel_4, null);
		
		
		
		
		final JLabel error1 = new JLabel("");
		error1.setForeground(Color.RED);
		error1.setBounds(331, 81, 173, 14);
		panel_4.add(error1);
	
		final JLabel error2 = new JLabel("");
		error2.setForeground(Color.RED);
		error2.setBounds(331, 130, 163, 14);
		panel_4.add(error2);
		
		final JLabel error3 = new JLabel("");
		error3.setForeground(Color.RED);
		error3.setBounds(325, 191, 263, 14);
		panel_4.add(error3);
		
		final JLabel confirmation = new JLabel("");
		confirmation.setForeground(Color.BLUE);
		confirmation.setBounds(331, 191, 243, 14);
		panel_4.add(confirmation);
		
		final JDateChooser dateChooser = new JDateChooser();
		
		dateChooser.setBounds(160, 81, 155, 20);
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Start Date");
		lblNewLabel_2.setBounds(40, 81, 84, 20);
		panel_4.add(lblNewLabel_2);
		panel_4.add(dateChooser);
		
		JLabel lblNewLabel_3 = new JLabel("End Date");
		lblNewLabel_3.setBounds(40, 130, 84, 14);
		panel_4.add(lblNewLabel_3);
		
		final JDateChooser dateChooser_1 = new JDateChooser();
		
		dateChooser_1.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
			
			}
		});
		dateChooser_1.setBounds(160, 130, 155, 20);
		panel_4.add(dateChooser_1);
		
		JButton btnNewButton_1 = new JButton("Apply Leave");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				error1.setText("");
				error2.setText("");
				error3.setText("");
				confirmation.setText("");
				//dateChooser.cleanup();
				//dateChooser_1.cleanup();
				
				start = dateChooser.getDate();	
				end = dateChooser_1.getDate();
				current = new Date();
				
				current.setTime(current.getTime() - 24*60*60*1000);
				
				//System.out.println(end.getTime());
				//System.out.println(start.getTime());
				
				if(current.compareTo(start) > 0){
					error1.setText("Start date not valid");
					return;
				}
				
				if(end.compareTo(start) < 0){
					
					error2.setText("End date not valid");
					return;
					
				}
				
				if(Math.abs(start.getTime() - end.getTime()) < 24*60*60){
					error2.setText("Dates cannot be equal");
					return;
				}
			

				
				
				rs2 = SWDAdmin.approveLeave(start, end, id);
				
				boolean flag = true;
					try {
						while(rs2.next()){
							dateChooser.setCalendar(null);
							dateChooser_1.setCalendar(null);
							flag = false;
							error3.setText("Already applied for leave with leaveID: "+rs2.getString("leave_id"));
							return;
						
						}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			if(flag)
				confirmation.setText("Leave applied");
				
			dateChooser.setCalendar(null);
			dateChooser_1.setCalendar(null);
			
			
			
			}
		});
		btnNewButton_1.setBounds(160, 187, 155, 23);
		panel_4.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Change Password", null, panel_2, null);
		panel_2.setLayout(null);
		
		pwdField = new JTextField();
		pwdField.setBounds(185, 24, 193, 20);
		panel_2.add(pwdField);
		pwdField.setColumns(10);
		
		pwdField_1 = new JTextField();
		pwdField_1.setBounds(185, 97, 193, 20);
		panel_2.add(pwdField_1);
		pwdField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Enter new password");
		lblNewLabel_4.setBounds(10, 27, 124, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Re-enter new password");
		lblNewLabel_5.setBounds(10, 100, 124, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel pwd1 = new JLabel("minimum 8 characters, both upper and lower case alphabets");
		pwd1.setForeground(new Color(255, 165, 0));
		pwd1.setBounds(185, 47, 296, 20);
		panel_2.add(pwd1);
		
		final JLabel pwd3 = new JLabel("");
		pwd3.setForeground(Color.RED);
		pwd3.setBounds(389, 100, 199, 14);
		panel_2.add(pwd3);
	
		JLabel pwd2 = new JLabel("and atleast 1 numeric digit and special character");
		pwd2.setForeground(new Color(255, 165, 0));
		pwd2.setBounds(185, 67, 284, 14);
		panel_2.add(pwd2);
		
		final JLabel pwd4 = new JLabel("");
		pwd4.setForeground(Color.RED);
		pwd4.setBounds(388, 27, 200, 14);
		panel_2.add(pwd4);
		
		final JLabel pwd5 = new JLabel("");
		pwd5.setForeground(Color.BLUE);
		pwd5.setBounds(185, 216, 193, 14);
		panel_2.add(pwd5);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String checker = pwdField_1.getText().toString();
				String password = pwdField.getText().toString();
				pwd3.setText("");	
				pwd4.setText("");
				pwd5.setText("");
				
						Pattern pass = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
						Matcher match = pass.matcher(password);
						
						if(match.find()){
							pwd4.setText("");
							pwd3.setText("");
						}
						
						else{
							pwd4.setText("Invalid password, please try again.");
							pwdField.setText("");
							pwdField_1.setText("");
							return;
						}
			
						if(checker.equals(password)){
							SWDAdmin.updatePassword(password,id);
							pwd5.setText("Password updated.");
							pwd4.setText("");
							pwd3.setText("");
							pwdField.setText("");
							pwdField_1.setText("");
						}
						else{
							pwd3.setText("Not matching, please try again.");
							pwd4.setText("");
							pwdField_1.setText("");
							return;
						}
				
				
			}
		});
		btnUpdate.setBounds(185, 158, 89, 23);
		panel_2.add(btnUpdate);
		
	

		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 id = IDNumber.getText();
				 pwd = Password.getText();
				S.SWDLogin(id, pwd);
				
				if(S.logged_in){
					rs1=SWDAdmin.viewDetails(id);
					panel_1.setVisible(true);
					panel.setVisible(false);
					
					try {
						while(rs1.next()){
						NameTextField.setText(rs1.getString("name"));
						MobileTextField.setText(rs1.getString("mobileno"));
						EmailTextField.setText(rs1.getString("email"));
						AddressTextField.setText(rs1.getString("address"));
						IDTextField.setText(rs1.getString("id"));
						BillTextField.setText(rs1.getString("bill"));
						
						if(rs1.getInt("active") == 1)
							LeaveTextField.setText("Not On Leave");
						
						else
							LeaveTextField.setText("On Leave");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
					label1.setText("Invalid combination!");
				}
				
				
					
			}
		});
		btnNewButton.setBounds(312, 34, 89, 25);
		panel.add(btnNewButton);
		
		final JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.menu);
		frame.getContentPane().add(panel_6, "name_498496089090174");
		panel_6.setLayout(null);
		
		
		
		JButton btnSearchStudent_1 = new JButton("Search Student");
		btnSearchStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				search newWindow = new search();
				newWindow.setVisible(true);
				
				
			}
		});
		btnSearchStudent_1.setBounds(433, 32, 129, 25);
		panel.add(btnSearchStudent_1);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Sylfaen", Font.BOLD, 42));
		lblWelcome.setBounds(159, 155, 242, 46);
		panel.add(lblWelcome);
		
		JButton btnForgot = new JButton("Forgot Password");
		btnForgot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					String id = IDNumber.getText();
					//System.out.println(id);
					try{
						String query = "SELECT * FROM test WHERE id ='"+ id + "'";
						ResultSet rs = stmt.executeQuery(query);
						String e=null, p=null;
						while(rs.next()){
						e = rs.getString("email");
						p = rs.getString("pwd");
						}
						
						p = p + " is your password for SWD.";
						
						SendEmail fp = new SendEmail();
						fp.sendmail(e, "Password for SWD", p);
					}
					catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Invalid ID no.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnForgot.setBounds(312, 279, 158, 23);
		panel.add(btnForgot);
		
		
		
		JButton button = new JButton("Search");
		button.setBounds(434, 35, 159, 23);
		panel_6.add(button);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(31, 36, 159, 20);
		textFieldID.setColumns(10);
		panel_6.add(textFieldID);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(228, 36, 159, 20);
		textFieldName.setColumns(10);
		panel_6.add(textFieldName);
		
		JLabel lblIdNumber_1 = new JLabel("ID Number");
		lblIdNumber_1.setBounds(35, 11, 62, 14);
		panel_6.add(lblIdNumber_1);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(228, 11, 77, 14);
		panel_6.add(lblName_1);
		
		
	}
}

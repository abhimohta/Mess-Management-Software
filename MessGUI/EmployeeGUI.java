package MessGUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Mess.Clerk;
import Mess.Cook;
import Mess.Dishwasher;
import Mess.Employee;
import Mess.EmployeeClient;
import Mess.Janitor;
import Mess.Waiter;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.Color;

public class EmployeeGUI {

	public JFrame frmAccount;
	public Object frame;
	public static String id;
	static Connection conn = null;
	
	/**
	 * Launch the application.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGUI window = new EmployeeGUI();
					window.frmAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EmployeeClient.access();
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public EmployeeGUI() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frmAccount = new JFrame();
		frmAccount.setForeground(Color.BLUE);
		frmAccount.setTitle("Account");
		frmAccount.setResizable(false);
		frmAccount.setBounds(100, 100, 450, 300);
		frmAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccount.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JButton btnApplyForLeave = new JButton("Apply for Leave");
		btnApplyForLeave.setForeground(Color.BLUE);
		btnApplyForLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Dates dialog = new Dates();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				//catch (Exception e) {
					//e.printStackTrace();
				//}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Database disconnected.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frmAccount.getContentPane().add(btnApplyForLeave);
		
		JButton btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setForeground(Color.BLUE);
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						String pass = null;
						String desig = null;
						try {
							
							String query = "SELECT * FROM employee WHERE emp_id = '"+id+"'"; 
							conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
							Statement stmt = conn.createStatement();
							
							ResultSet rs = stmt.executeQuery(query);
							
							while(rs.next()){
									pass = rs.getString("password");
									desig = rs.getString("designation");
								}
						}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
					}
						try {
							EmployeeUpdate window = new EmployeeUpdate(id,pass,desig);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		frmAccount.getContentPane().add(btnUpdateDetails);
		
		JButton btnCheckLeaveStatus = new JButton("Check Leave Status");
		btnCheckLeaveStatus.setForeground(Color.BLUE);
		btnCheckLeaveStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String query = "SELECT * FROM employee WHERE emp_id = '"+id+"'"; 
					conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()){
						
						if(rs.getInt("leave_applied") == 1){
							
							
							//System.out.println("Leave pending with leave id" + rs.getString("recent_leave_id"));
							JOptionPane.showMessageDialog(null, "Leave pending with leave id " + rs.getString("recent_leave_id"), "", JOptionPane.INFORMATION_MESSAGE);
							
							return;
							
							
							
						}
				
						else if(rs.getInt("leave_applied") == 0){
							
							
							if(rs.getInt("recent_leave_id") == 0){
								//System.out.println("No leave applied yet");
								JOptionPane.showMessageDialog(null, "No leave applied yet", "", JOptionPane.INFORMATION_MESSAGE);
					}
							else{
								
								int x = rs.getInt("recent_leave_id");
								Statement stmt1 = conn.createStatement();
								String query1 = "SELECT * FROM empleave_records WHERE leave_id ='"+x+"'";
								
								ResultSet rs1 = stmt1.executeQuery(query1);
								
								while(rs1.next()){
									
									if(rs1.getInt("status") == 1){
										//System.out.println("Leave approved for leave id: '"+x+"'");
									JOptionPane.showMessageDialog(null, "Leave approved for leave id : '"+x+"'", "", JOptionPane.INFORMATION_MESSAGE);
									}
									else if(rs1.getInt("status") == 0){
										//System.out.println("Leave cancelled for leave id: '"+x+"'");
										JOptionPane.showMessageDialog(null, "Leave cancelled for leave id : '"+x+"'", "", JOptionPane.INFORMATION_MESSAGE);	
									}
								}
							}
							
						}
					} 
					
					
			}catch (SQLException e) {
						// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		frmAccount.getContentPane().add(btnCheckLeaveStatus);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.RED);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frmAccount, "Are you sure you wish to logout?","", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								frmAccount.setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
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
			}
		});
		frmAccount.getContentPane().add(btnLogout);
	}
}

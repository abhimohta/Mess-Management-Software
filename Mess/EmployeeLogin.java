package Mess;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import MessGUI.EmployeeGUI;

public class EmployeeLogin {

	static Connection conn = null;
	
	
public static boolean validateLogin(final String idnumber,String password){
		
		boolean flag = false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
/*			String query = "SELECT * FROM employee WHERE emp_id ='"+idnumber+"' AND password ='"+ password + "'" ;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
*/
			
			String query = "SELECT * FROM employee WHERE emp_id = ? AND password = ?" ;
            java.sql.PreparedStatement stmt =  conn.prepareStatement(query);
            stmt.setString(1, idnumber);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
				
			while(rs.next()){
				flag = true;
				String s = "Name: "+rs.getString("name")+" ID: "+rs.getString("emp_id") + " Designation: "+rs.getString("designation");
				JOptionPane.showMessageDialog(null, s, "", JOptionPane.INFORMATION_MESSAGE);
				EmployeeClient.designation = rs.getString("designation");
			}
			
			
			
			if(flag == true){
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmployeeGUI window = new EmployeeGUI();
							window.id = idnumber;
							window.frmAccount.setVisible(true);
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
		
		
		return flag;
		
	}

	
	
	public static void main(String[] args) {
		
		
		

	}

}

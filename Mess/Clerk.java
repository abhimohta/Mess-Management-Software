package Mess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;




public class Clerk implements Employee {
	
	static Connection conn = null;
	//public String Responsibility();
	//public String getName();
	//public String getMob();
	//public boolean markAttendance();
	///public boolean recSalary();
	//public boolean setName(String n);
	//public boolean setMob(String m);
	//public boolean takeMoney();
	//public boolean scanCard();
	
	@Override
	public void applyForLeave(Date start,Date end,String id){
		
		java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());
			
		try {
			
			String query1 = "SELECT * FROM employee WHERE emp_id = '"+id+"'"; 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query1);
			
			while(rs.next()){
				
				if(rs.getInt("leave_applied") == 1){
					
					
					System.out.println("Cannot apply for a new leave. Previous leave pending with leave id" + rs.getString("recent_leave_id"));
					
					return;
				}
				
				else break;
				
				
			}	
			
			//conn1 = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			String update = "INSERT INTO empleave_pending (start_date,end_date,emp_id) VALUES ('"+sqlStartDate+"','"+sqlEndDate+"','"+id+"')"; 
			Statement stmt1 = conn.createStatement();
			int rows = stmt1.executeUpdate(update);
			
			
			String query2 = "SELECT * FROM empleave_pending WHERE emp_id = '"+id+"'"; 
			Statement stmt2 = conn.createStatement();
			
			ResultSet rs1 = stmt2.executeQuery(query2);
			
			while(rs1.next()){
				
				int leave_id = rs1.getInt("leave_id");
				
				
				update = "UPDATE employee SET recent_leave_id = '"+leave_id+"',leave_applied = 1 WHERE emp_id = '"+id+"'";
				Statement stmt3 = conn.createStatement();
				rows = stmt3.executeUpdate(update);
				
			}
			
			
		
			
			//System.out.println("util start"+start+"util end" + end);
			//System.out.println("sql start"+sqlStartDate+"sql end" + sqlEndDate);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@Override
	public void leaveStatus(String id){
		
try {
			
			String query = "SELECT * FROM employee WHERE emp_id = '"+id+"'"; 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				if(rs.getInt("leave_applied") == 1){
					
					
					//System.out.println("Leave pending with leave id" + rs.getString("recent_leave_id"));
					JOptionPane.showMessageDialog(null, "Leave pending with leave id" + rs.getString("recent_leave_id"), "", JOptionPane.INFORMATION_MESSAGE);
					
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
							JOptionPane.showMessageDialog(null, "Leave approved for leave id: '"+x+"'", "", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(rs1.getInt("status") == 0){
								//System.out.println("Leave cancelled for leave id: '"+x+"'");
								JOptionPane.showMessageDialog(null, "Leave cancelled for leave id: '"+x+"'", "", JOptionPane.INFORMATION_MESSAGE);	
							}
						}
					}
					
				}
			} 
			
			
	}catch (SQLException e) {
				// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
			}	
		
		
		
	}
	
	/*	else if(rs.getInt("leave_applied") == 0){
	
	
	if(rs.getString("recent_leave_id").equals("NULL")){
		
		System.out.println("No leave appled yet");
		return;
	}
	
	
	else
		String 
	
	
}

*/
	
	@Override
	public void updateName(String name,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE employee SET name = '"+name+"' WHERE emp_id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void updateMobile(String mob,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE employee SET mobileno = '"+mob+"' WHERE emp_id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void updateAddress(String address,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE employee SET address = '"+address+"' WHERE emp_id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
public void updatePassword(String password,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE employee SET password = '"+password+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateEmail(String email,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE employee SET email = '"+email+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

	public static void scanCard(String id) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		int bill =0,charge = 0,active = -1,flag = 0;
		String meal;
		
		String query = "SELECT * FROM test WHERE id = '"+id+"'"; 
		conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
		
			bill = rs.getInt("bill");
			active = rs.getInt("active");
			flag = rs.getInt("flag");
		}		
		
		if(active == -1){
			JOptionPane.showMessageDialog(null,"Student doesn't exist.","",JOptionPane.PLAIN_MESSAGE);
			return;
		}
		
		if(active == 0){
			
			JOptionPane.showMessageDialog(null,"Student on leave","",JOptionPane.PLAIN_MESSAGE);
			return;
			
		}
		
		if(flag == 0){
			
			JOptionPane.showMessageDialog(null,"Meal already consumed","",JOptionPane.PLAIN_MESSAGE);
			return;
		}
		
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		
		Date seven = parser.parse("07:00");
		Date ten = parser.parse("10:00");
		Date eleven = parser.parse("11:00");
		Date fifteen = parser.parse("15:00");
		
		Date date = new Date();
		if (date.after(seven) && date.before(ten))
		    meal = "breakfast";
			
		else if (date.after(eleven) && date.before(fifteen))
		   meal = "lunch";
		
		else
			meal = "dinner";
		
		JOptionPane.showMessageDialog(null,"Student can eat: "+meal,"",JOptionPane.PLAIN_MESSAGE);
		
		String query1 = "SELECT * FROM rates WHERE meal = '"+meal+"'"; 
		Statement stmt1 = conn.createStatement();
		
		ResultSet rs1 = stmt1.executeQuery(query1);
	
		
		while(rs1.next()){
		
			charge = rs1.getInt("price");
		}
		
		int total = bill + charge;
		
		String update = "UPDATE test SET flag = 0, bill ='"+total+"' WHERE id = '"+id+"'";
		Statement stmt2 = conn.createStatement();
		
		int rows = stmt2.executeUpdate(update);
		
	}

public static void takeMoney() throws ParseException, SQLException {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		String meal;
		int charge = 0;
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		
		Date seven = parser.parse("07:00");
		Date ten = parser.parse("10:00");
		Date eleven = parser.parse("11:00");
		Date fifteen = parser.parse("15:00");
		
		if (date.after(seven) && date.before(ten))
		    meal = "breakfast";
			
		else if (date.after(eleven) && date.before(fifteen))
		   meal = "lunch";
		
		else
			meal = "dinner";
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
		String query1 = "SELECT * FROM rates WHERE meal = '"+meal+"'"; 
		Statement stmt1 = conn.createStatement();
		
		ResultSet rs1 = stmt1.executeQuery(query1);
	
		
		while(rs1.next()){
		
			charge = rs1.getInt("price");
		}
		
		String update = "INSERT INTO guest(date,meal,charge) VALUES('"+sqlDate+"','"+meal+"','"+charge+"') "; 
	
		JOptionPane.showMessageDialog(null,"Welcome Guest | Please Pay: Rs."+charge,"",JOptionPane.PLAIN_MESSAGE);
		
		Statement stmt = conn.createStatement();
		
		int rows = stmt.executeUpdate(update);
		
	
	
	}

	
}



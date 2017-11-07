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



public class Janitor implements Employee {
	
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
					
					
					System.out.println("Leave pending with leave id" + rs.getString("recent_leave_id"));
					
					return;
					
					
					
				}
		
				else if(rs.getInt("leave_applied") == 0){
					
					
					if(rs.getInt("recent_leave_id") == 0)
						System.out.println("No leave applied yet");
					
					else{
						
						int x = rs.getInt("recent_leave_id");
						Statement stmt1 = conn.createStatement();
						String query1 = "SELECT * FROM empleave_records WHERE leave_id ='"+x+"'";
						
						ResultSet rs1 = stmt1.executeQuery(query1);
						
						while(rs1.next()){
							
							if(rs1.getInt("status") == 1)
								System.out.println("Leave approved for leave id: '"+x+"'");
							
							else if(rs1.getInt("status") == 0)
								System.out.println("Leave cancelled for leave id: '"+x+"'");
							
						}
					}
					
				}
			} 
			
			
	}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}

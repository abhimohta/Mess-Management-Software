package Mess;

//student(flag,id,name,add,mob,active,) onleave(start date , end date,) employee messmenu 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;


public class MessManager {
	//public String getName();
	//public boolean setName(String name);
	//public String getMob();
	//public boolean setMob(String mob);
	//public int getBalance();
	//public boolean paySalary(Employee e);
	//public boolean putOnLeave(Employee e);
	//public boolean doTransaction(int m);
	static Connection conn = null;
	
	public static boolean validateLogin(String idnumber,String password){
		
		boolean flag = false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
				
/*			String query = "SELECT * FROM manager WHERE id ='"+idnumber+"' AND password ='"+ password + "'" ;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
*/
			String query = "SELECT * FROM manager WHERE id = ? AND password = ?" ;
            PreparedStatement stmt =  conn.prepareStatement(query);
            stmt.setString(1, idnumber);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				flag = true;
				System.out.println("Name: "+rs.getString("name")+" ID: "+rs.getString("id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
		
	}
	
	
	public static void updateMess() throws ParseException{
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt1 = conn.createStatement();
			
			String update1 = "UPDATE test SET flag = 1, active = 1"; //we are making all flags 1 everytime we update
			int rows = stmt1.executeUpdate(update1);
			
			Date date = new Date();
			Date end,start;
			String update2_1,update2_2;
			String query = "SELECT * FROM onleave"; 
			ResultSet rs = stmt1.executeQuery(query);
			Statement stmt2 = conn.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()){
				
			String r = rs.getString("id");
			
			String s = rs.getString("start_date"); 
			
			String e = rs.getString("end_date");	
			//System.out.println(s);
			
			end = sdf.parse(e);
			start = sdf.parse(s);
			//System.out.println(end.toString());
			//System.out.println(date.toString());
			
				if(date.compareTo(start) >= 0){  //checks if today > start date and only then change flags or remove from table 
			
			
					if(date.compareTo(end) < 0){	//if leave has not ended
				
						update2_1 = "UPDATE test SET active = 0 WHERE id = '"+r+"'"; //make active 0
						rows = stmt2.executeUpdate(update2_1);
				
					}
			
					else if(date.compareTo(end) >= 0){	//if leave has ended
			
						update2_2 = "DELETE FROM onleave WHERE id ='"+r+"'";//remove from onleave table//active already 1
						rows = stmt2.executeUpdate(update2_2);
			
					}
				
			
				}
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateName(String name,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE manager SET name = '"+name+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static void updateMobile(String mob,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE manager SET mobile = '"+mob+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
		
	public static void updateAddress(String address,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE manager SET address = '"+address+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
		
	public static void updatePassword(String password,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE manager SET password = '"+password+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void updateEmail(String email,String id){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE manager SET email = '"+email+"' WHERE id = '"+id+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void approveLeave(String id) {
		
		boolean flag = false;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM empleave_pending WHERE emp_id = '"+id+"'";
			
			ResultSet rs = stmt.executeQuery(query);	
			
			while(rs.next()){
				
				flag = true;
				
				String emp_id = rs.getString("emp_id");
				int leave_id = rs.getInt("leave_id");
				Date start = rs.getDate("start_date");
				Date end = rs.getDate("end_date");
				int rows;
				
				java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
				java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());
				
				
				Statement stmt1 = conn.createStatement();
				String update1 = "INSERT INTO empleave_records(emp_id,leave_id,start_date,end_date,status) VALUES('"+emp_id+"','"+leave_id+"','"+sqlStartDate+"','"+sqlEndDate+"',1)";                 
				
				rows = stmt1.executeUpdate(update1);
				
				Statement stmt2 = conn.createStatement();
				String update2 = "UPDATE employee SET leave_applied = 0 WHERE emp_id = '"+id+"'";
						
				rows = stmt2.executeUpdate(update2);
				
				
				Statement stmt3 = conn.createStatement();
				String update3 = "DELETE FROM empleave_pending WHERE emp_id = '"+id+"'";
				
				rows = stmt3.executeUpdate(update3);
				
				
			}
			
			if(!flag){
				
				System.out.println("Invalid entry");
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Leave approved");
		
		
	}
	
	
public static void cancelLeave(String id) {
		
		boolean flag = false;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM empleave_pending WHERE emp_id = '"+id+"'";
			
			ResultSet rs = stmt.executeQuery(query);	
			
			while(rs.next()){
				
				flag = true;
				
				String emp_id = rs.getString("emp_id");
				int leave_id = rs.getInt("leave_id");
				Date start = rs.getDate("start_date");
				Date end = rs.getDate("end_date");
				int rows;
				
				java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
				java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());
				
				
				Statement stmt1 = conn.createStatement();
				String update1 = "INSERT INTO empleave_records(emp_id,leave_id,start_date,end_date,status) VALUES('"+emp_id+"','"+leave_id+"','"+sqlStartDate+"','"+sqlEndDate+"',0)";                 
				
				rows = stmt1.executeUpdate(update1);
				
				Statement stmt2 = conn.createStatement();
				String update2 = "UPDATE employee SET leave_applied = 0 WHERE emp_id = '"+id+"'";
						
				rows = stmt2.executeUpdate(update2);
				
				
				Statement stmt3 = conn.createStatement();
				String update3 = "DELETE FROM empleave_pending WHERE emp_id = '"+id+"'";
				
				rows = stmt3.executeUpdate(update3);
				
				
			}
			
			if(!flag){
				
				System.out.println("Invalid entry");
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Leave cancelled");
		
		
	}
	
	public static void pastLeaveRecords(String id) {
		
		boolean flag = false;
		String query1,query2;
		ResultSet rs;
		
		System.out.println("Employee Leave Record List:");
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			
			query1 = "SELECT * FROM empleave_records";
		
			query2 = "SELECT * FROM empleave_records WHERE emp_id = '"+id+"'";	
			
			if(id.equals("null"))
			rs = stmt.executeQuery(query1);
			
			else 
			rs = stmt.executeQuery(query2);	
			
			while(rs.next()){
				
				flag = true;
				String status;
				int x = rs.getInt("status");
				
				if(x == 0)
				status = "Cancelled";			
				else
					status = "Approved";
				System.out.println("Employee id: '"+rs.getString("emp_id")+"' | Leave id: '"+rs.getInt("leave_id")+"' | Leave starts on: '"+rs.getDate("start_date")+"' | Leave ends on: '"+rs.getDate("end_date")+"' | Leave Status: '"+status+"'");                
			}			
			if(!flag){				
				System.out.println("NULL");			
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static String pastLeaveRecords2(String id, boolean all) {
		
		String res = "";
		boolean flag = false;
		String query1;
		ResultSet rs;
		
	//	System.out.println("Employee Leave Record List:");
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			
			if(all)
				query1 = "SELECT * FROM empleave_records";
			else
				query1 = "SELECT * FROM empleave_records WHERE emp_id = '"+id+"'";	
			
			rs = stmt.executeQuery(query1);
			
			while(rs.next()){
				
				flag = true;
				String status;
				int x = rs.getInt("status");
				
				if(x == 0)
				status = "C";			
				else
					status = "A";
				res += "ID: "+rs.getString("emp_id")+" ("+rs.getInt("leave_id")+") ("+rs.getDate("start_date")+" to "+rs.getDate("end_date")+") "+status+"\n";                
			}			
			if(!flag){				
				res += "NULL";			
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public static void pendingLeaveRecords() {
	
		boolean flag = false;
		
		System.out.println("Employee Leave Pending List:");
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM empleave_pending";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				flag = true;
				
				System.out.println("Employee id: '"+rs.getString("emp_id")+"' | Leave id: '"+rs.getInt("leave_id")+"' | Leave starts on: '"+rs.getDate("start_date")+"' | Leave ends on: '"+rs.getDate("end_date")+"'");
				
				
				
			}
			
			if(!flag){
				
				System.out.println("No pending leaves");
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static String pendingLeaveRecords2() {
		
		boolean flag = false;
		String res = "";
		
		System.out.println("Employee Leave Pending List:");
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM empleave_pending";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				flag = true;
				
				res += " ID: "+rs.getString("emp_id")+" ("+rs.getDate("start_date")+" to "+rs.getDate("end_date")+")\n";
				
				
				
			}
			
			if(!flag){
				
				res += "No pending leaves";
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
		
		
		
	}


	public static void updatePrice(String meal, int price) {
		// TODO Auto-generated method stub
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String update = "UPDATE rates SET price = '"+price+"' WHERE meal = '"+meal+"'";
			int rows = stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	public static void showPriceList() {
		// TODO Auto-generated method stub
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM rates";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				System.out.println("Meal: '"+rs.getString("meal")+"' | Price: '"+rs.getInt("price")+"'");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static int[] showPriceList2() {
		// TODO Auto-generated method stub
		int[] prices = {0, 0, 0};
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM rates";
			
			ResultSet rs = stmt.executeQuery(query);
			int i = 0;
			while(rs.next()){
				System.out.println("Meal: '"+rs.getString("meal")+"' | Price: '"+rs.getInt("price")+"'");	
				prices[i] = rs.getInt("price");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prices;
	}



	public static void showGuestList() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM guest";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){			
				System.out.println("Guest id: "+rs.getInt("guest_id")+" | Date: "+rs.getDate("date")+" | Meal: "+rs.getString("meal")+" | Charge: "+rs.getInt("charge"));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String showGuestList2() {
		String res = "";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM guest";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){			
				res += "  ID: "+rs.getInt("guest_id")+"  |  "+rs.getDate("date")+"  |  "+rs.getString("meal")+"  |  Rs."+rs.getInt("charge") + "\n";
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static void sendsms(String msg) {
		try {

		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");

		String query = "SELECT * FROM test";
		//String query = "SELECT mobileno FROM student";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		String recipient = null;
		while(rs.next()){
		recipient = rs.getString("mobileno");
		
		System.out.println(recipient);
		String message = msg;
		//String message = menu;
		String username = "onquee";
		String password = "123456";
		String originator = "ONQUEE";

		String requestUrl  = "http://login.bulksms360.in:8080/sendsms/bulksms?username=exp1-" + URLEncoder.encode(username, "UTF-8") +
		"&password=" + URLEncoder.encode(password, "UTF-8") +
		"&type=0&dlr=1&destination=" + URLEncoder.encode(recipient, "UTF-8") +
		"&source=" + URLEncoder.encode(originator, "UTF-8")+
		"&message=" + URLEncoder.encode(message, "UTF-8") 
		;


		URL url = new URL(requestUrl);
	    InputStream is = url.openConnection().getInputStream();

	    BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

	    String line = null;
	    while( ( line = reader.readLine() ) != null )  {
	       //System.out.println(line);
	    }
	    reader.close();
		}
		JOptionPane.showMessageDialog(null, "SMS sent!", "", JOptionPane.INFORMATION_MESSAGE);

		} catch(Exception ex) {
			
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

	}
	
	
	
	
}
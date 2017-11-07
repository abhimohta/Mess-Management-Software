package Mess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SWD.SWDAdmin;

public class EmployeeClient {

	
	private static boolean loggedin = false;
	public static String id = null;
	private static String password = null;
	public static String designation = null;
	static Employee e;
	private static boolean exit = false;
	
	public static void Login(String id,String password){
		
		EmployeeClient.loggedin = EmployeeLogin.validateLogin(id, password);
		
	}

	public static void Update() throws NumberFormatException, IOException{
		
		int choice;
		String name,mobileno,address,email;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		do{
			System.out.println("1. Update name");
			System.out.println("2. Update mobile number");
			System.out.println("3. Update address");
			System.out.println("4. Update password");
			System.out.println("5. Update email");
			System.out.println("6. Go back");
			
			choice = Integer.parseInt(br.readLine());
			
		switch(choice){
		
		case 1: System.out.println("Enter new name");
				
				name = br.readLine();
				
				e.updateName(name,EmployeeClient.id);
				
				break;
		case 2: System.out.println("Enter new mobile number");
	
				mobileno = br.readLine();
		
				e.updateMobile(mobileno,EmployeeClient.id);
		
				break;		
				
		case 3: System.out.println("Enter new address");
		
				address = br.readLine();
		
				e.updateAddress(address,EmployeeClient.id);
		
				break;
				
		case 4: String checker;
				int confirm = 0, chk = 0;	
				do{
					System.out.println("Enter new password (minimum 8 characters, both upper and lower case alphabets, "
					+ "atleast 1 numeric digit and special character.");
					
					do{
						password = br.readLine();
					
						Pattern pass = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
						Matcher match = pass.matcher(password);
						
						if(match.find())
							chk = 1;
						else{
							System.out.println("Invalid password, please try again.");
							chk = 0;
						}
						
					}while(chk==0);
						
						System.out.println("Re-enter new password.");
						checker = br.readLine();
						if(checker.equals(password)){
							e.updatePassword(password,EmployeeClient.id);
							confirm = 1;
							System.out.println("Password updated.");
						}
						else
							System.out.println("Not matching, please try again.");
				}while(confirm == 0);
		
				break;
		
		case 5: System.out.println("Enter new email ID");
		
				email = br.readLine();

				e.updateEmail(email,EmployeeClient.id);

		break;		
		
				
		case 6: System.out.println("Returning..");
				
				break;
		
		default: System.out.println("Invalid choice");
		
		}
		
		}while(choice!=6);
			
		
		
		
	}
	
	public static void applyLeave() throws ParseException, IOException{
		
		Date start,end;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date current = new Date();
		System.out.println("Current Time: "+current.toString());
		
		System.out.println("Enter start date(yyyy-MM-dd)");
		start = sdf.parse(br.readLine());
		
		
		if(start.compareTo(current)<0){
			System.out.println("Invalid entry: Start date expected to be greater than or equal to Current date");
			return;
		}
		
		System.out.println("Enter end date(yyyy-MM-dd)");
		end = sdf.parse(br.readLine());
		
		if(end.compareTo(start)<=0){
			System.out.println("Invalid entry: End date expected to be greater than Start date");
			return;
		}
			
		e.applyForLeave(start, end, EmployeeClient.id);
		
	}
	
	public static void access() throws NumberFormatException, IOException, ParseException{
		
		if(EmployeeClient.designation.equals("clerk"))
			 e = new Clerk();
		
		else if(EmployeeClient.designation.equals("cook"))
			e = new Cook();
		

		else if(EmployeeClient.designation.equals("dishwasher"))
			 e = new Dishwasher();
		

		else if(EmployeeClient.designation.equals("janitor"))
			 e = new Janitor();
		
		else if(EmployeeClient.designation.equals("waiter"))
			e = new Waiter();
		
		else {
			System.out.println("Invalid designation");
		
			return;
		}
			
		
			
		int choice = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.println("Enter the choice no:");
    		System.out.println("1. Apply For Leave");
    		System.out.println("2. Update Details");
    		System.out.println("3. Check Leave Status");
    		System.out.println("4. Logout");
    		System.out.println("5. Exit");
			
    		choice = Integer.parseInt(br.readLine());
    		
    		switch(choice){
    		
    		case 1: EmployeeClient.applyLeave();
					break;
    		
    		case 2: EmployeeClient.Update();	
					break;
			
    		case 3: e.leaveStatus(EmployeeClient.id);		
    				break;
    		
    		case 4: System.out.println("Loggedout..");
					EmployeeClient.loggedin = false;
					choice = 5;
					break;	
				
    		case 5: System.out.println("Exiting..");
    				EmployeeClient.exit  = true;
    		
    				break;
    		
    		default: System.out.println("Invalid choice");		
    		
    		}
			
			
			
		}while(choice != 5);
		
		
		
	}
		
		
	
	
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		
		int choice = 0;
		boolean flag = true;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.println("Enter the choice no:");
    		System.out.println("1. Login");
    		System.out.println("2. Exit");
			
    		choice = Integer.parseInt(br.readLine());
    		
    		switch(choice){
    		
    		case 1: if(EmployeeClient.loggedin){
				
					System.out.println("Already logged in");
					break;
					
    				}
					
					System.out.println("\nEnter id: ");
				
					EmployeeClient.id = br.readLine();
				
					System.out.println("\nEnter password: ");
				
					EmployeeClient.password = br.readLine();
					
					EmployeeClient.Login(EmployeeClient.id,EmployeeClient.password);
					
					if(EmployeeClient.loggedin){
						
						System.out.println("logged in");
						
						EmployeeClient.access();
						
						if(EmployeeClient.exit)
							choice = 2;
					}	
					else 
						System.out.println("Wrong entry");
				
				break;
				
		
    		case 2: System.out.println("Exiting..");
    				flag = false;
    				break;
    				
    		default: System.out.println("Invalid choice");		
    		
    		}
			
			
			
		}while(choice != 2);
		
		

	}
	

}

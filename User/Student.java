package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;




//import exceptions.InsertionException;
//import exceptions.SearchException;
import SWD.SWDAdmin;

public class Student {
	
	public boolean logged_in;
	//private boolean found;
	public String id; 
	private String password;
	
	public Student(){
		
		//this.found = false;
		this.logged_in = false;
		this.id = null;
		this.password = null;
	}
	
	
	public void searchStudent (String id, String name){
		
		ResultSet rs = SWDAdmin.find(id,name);
			
		
	}
	
	public void SWDLogin(String id, String password){
		
		this.logged_in = SWDAdmin.validateLogin(id,password);
		
	}
	
	
	public void SWDLogout(){
		
		if(this.logged_in){
			this.logged_in = false;
			System.out.println("Logged out");
		}
		else
			System.out.println("Already logged out");
		
	}
	
	public void Update() throws NumberFormatException, IOException{
		
		int choice;
		String name,mobileno,address,email;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		do{
			System.out.println("1. Update name");
			System.out.println("2. Update mobile number");
			System.out.println("3. Update address");
			System.out.println("4. Update password");
			System.out.println("5. Update email");
			System.out.println("6. View all Details");
			System.out.println("7. Go back");
			
			choice = Integer.parseInt(br.readLine());
			
		switch(choice){
		
		case 1: System.out.println("Enter new name");
				
				name = br.readLine();
				
				SWDAdmin.updateName(name,this.id);
				
				break;
		case 2: System.out.println("Enter new mobile number");
	
				mobileno = br.readLine();
		
				SWDAdmin.updateMobile(mobileno,this.id);
		
				break;		
				
		case 3: System.out.println("Enter new address");
		
				address = br.readLine();
		
				SWDAdmin.updateAddress(address,this.id);
		
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
							SWDAdmin.updatePassword(password,this.id);
							confirm = 1;
							System.out.println("Password updated.");
						}
						else
							System.out.println("Not matching, please try again.");
				}while(confirm == 0);

				break;

		case 5: System.out.println("Enter new email ID");
		
				email = br.readLine();
		
				SWDAdmin.updateEmail(email,this.id);
		
				break;

		case 6: ResultSet rs = SWDAdmin.viewDetails(this.id);
				break;

		case 7: System.out.println("Returning..");
				
				break;
		
		default: System.out.println("Invalid choice");
		
		}
		
		}while(choice!=7);
			
		
		
		
	}
	
	public void applyLeave() throws ParseException, IOException{
		
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
			
		ResultSet rs =SWDAdmin.approveLeave(start, end, this.id);
		
	}
	
	
	
	public static void main(String args[]) throws IOException, ParseException{
		
		Student S = new Student();
		int choice;
		String idnum = null, name =  null;
	
		
		//S.searchStudent("3535");
		//if(S.found)
		//	System.out.println("Student found");
		//else
		//	System.out.println("Student not found");
		//Scanner in = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do
    	{
    		System.out.println("Enter the choice no:");
    		System.out.println("1. Login");
    		System.out.println("2. Update Details");
    		System.out.println("3. Search Student");
    		System.out.println("4. Logout");
    		System.out.println("5. Apply For Leave");
    		System.out.println("6. Get Bill");
    		System.out.println("7. Exit");
    		
    		choice = Integer.parseInt(br.readLine());
    		
    		
    		switch(choice)
    		{
    			case 1: if(S.logged_in){
    				
    					System.out.println("Already logged in");
    					break;
    						}
    						
    					System.out.println("\nEnter id: ");
    					
    					S.id = br.readLine();
    					
    					System.out.println("\nEnter password: ");
    					
    					S.password = br.readLine();
    					S.SWDLogin(S.id,S.password);
    					if(S.logged_in)
    						System.out.println("logged in");
    					else 
    						System.out.println("Wrong entry");
    					
    					break;
    					
    			case 2: if(S.logged_in)
    					S.Update();
    					
    					else
    						System.out.println("Not logged in");
    					
    					break;
    					
    			case 3:	System.out.println("Enter id number");
						idnum = br.readLine();
						
						System.out.println("Enter name");
						name = br.readLine();
						
						
						S.searchStudent(idnum,name);	
				
						
    					break;
    			
    			case 4: S.SWDLogout();
    					break;
    					
    			case 5: if(S.logged_in)
    					S.applyLeave();
    			
    					else
    						System.out.println("Not logged in");
    					
    					break;
    			
    			case 6:  if(S.logged_in)
							SWDAdmin.sendBill(S.id);
    			
						else
							System.out.println("Not logged in");
					
						break;		
    					
    			case 7: System.out.println("Exiting..");
    					break;
    			default : System.out.println("Invalid choice.");
    		}
    		

    	}while(choice != 7);
 
			br.close();
		
        
    }
	
		
		
		
	}
	


	
	//public boolean chooseMess();
	//public boolean checkMenu();
	//public boolean giveFeedback();




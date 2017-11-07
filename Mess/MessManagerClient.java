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


public class MessManagerClient {

	private boolean logged_in;
	private boolean found;
	private String id, password;
	
	public MessManagerClient(){
		
		this.found = false;
		this.logged_in = false;
		this.id = null;
		this.password = null;
	}
	
	
	
	public void ManagerLogin(String id, String password){
		
		this.logged_in = MessManager.validateLogin(id,password);
		
	}
	
	
	public void ManagerLogout(){
		
		if(this.logged_in){
			this.logged_in = false;
			System.out.println("Logged out");
		}
		else
			System.out.println("Already logged out");
		
	}
	
	public void UpdateManager() throws NumberFormatException, IOException{
		
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
				
				MessManager.updateName(name,this.id);
				
				break;
		case 2: System.out.println("Enter new mobile number");
	
				mobileno = br.readLine();
		
				MessManager.updateMobile(mobileno,this.id);
		
				break;		
				
		case 3: System.out.println("Enter new address");
		
				address = br.readLine();
		
				MessManager.updateAddress(address,this.id);
		
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
					MessManager.updatePassword(password,this.id);
					confirm = 1;
					System.out.println("Password updated.");
				}
				else
					System.out.println("Not matching, please try again.");
		}while(confirm == 0);

		break;
		
		case 5: System.out.println("Enter new email ID");
		
				email = br.readLine();

				MessManager.updateEmail(email,this.id);

		break;
				
		case 6: System.out.println("Returning..");
				
				break;
		
		default: System.out.println("Invalid choice");
		
		}
		
		}while(choice!=6);
			
		
		
		
	}
	
	
	public void refreshMessDetails(){
		
		
	try {
		MessManager.updateMess();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
		
	}
	
	
	public void manageLeave() throws ParseException, IOException{
		
		String id;
		int choice,option;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			
			System.out.println("1. Approve Leave");
			System.out.println("2. Cancel Leave");
			System.out.println("3. View Pending Leave Records");
			System.out.println("4. View Past Leave Records");
			System.out.println("5. Go back");
			
			choice = Integer.parseInt(br.readLine());
			
			switch(choice){
			
			
			case 1: System.out.println("Enter id:");
			
					id = br.readLine();
				
					MessManager.approveLeave(id);
					
				 	break;
			
			case 2: System.out.println("Enter id:");
			
					id = br.readLine();
		
					MessManager.cancelLeave(id);
					
					break;
			
			case 3: MessManager.pendingLeaveRecords();
					
					break;
			
			case 4: System.out.println("Press 1 to view all records or Press 2 to view records by id"); 
					
					option = Integer.parseInt(br.readLine());
					
					if(option == 1)
					MessManager.pastLeaveRecords("null");
					
					else if(option == 2){
						
						System.out.println("Enter id:");
						
						id = br.readLine();
						MessManager.pastLeaveRecords(id);
					}
					
					else
						System.out.println("Invalid entry");
					
					break;
			
			case 5: break;		
					
			default : System.out.println("Invalid entry");
			}
			
			
			
			
			
		}while(choice != 5);
		
		
		
	}
	

	private void managePriceList() throws NumberFormatException, IOException {
	
		int choice,price;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.println("Select meal to update price");
			System.out.println("1. Breakfast");
			System.out.println("2. Lunch");
			System.out.println("3. Dinner");
			System.out.println("4. Go back");
			
			choice = Integer.parseInt(br.readLine());
			
			switch(choice){
			
			
			case 1: System.out.println("Enter new price");
				
					price = Integer.parseInt(br.readLine());
				
					MessManager.updatePrice("breakfast",price);
					
				 	break;
			
			case 2: System.out.println("Enter new price");
			
					price = Integer.parseInt(br.readLine());
		
					MessManager.updatePrice("lunch",price);
			
					break;
			
			case 3: System.out.println("Enter new price");
			
					price = Integer.parseInt(br.readLine());
		
					MessManager.updatePrice("dinner",price);
			
					break;
			
			case 4: break;		
					
			default : System.out.println("Invalid entry");
			}
			
			
			
			
			
		}while(choice != 4);
		
	}	
	
	public static void main(String args[]) throws IOException, ParseException{
		
		MessManagerClient M = new MessManagerClient();
		int choice;
		String idnum = null;
	
		
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
    		System.out.println("2. Update Manager Details");
    		System.out.println("3. Refresh Mess Details"); //to be done before every meal//changes onleave table and student table
    		System.out.println("4. Logout");
    		System.out.println("5. Manage Employee Leave");
    		System.out.println("6. Update price of meals");
    		System.out.println("7. View price of meals");
    		System.out.println("8. View guest details");
    		System.out.println("9. Forgot Password");
    		System.out.println("10. Exit");
    		
    		choice = Integer.parseInt(br.readLine());
    		
    		
    		switch(choice)
    		{
    			case 1: if(M.logged_in){
    				
    					System.out.println("Already logged in");
    					break;
    						}
    						
    					System.out.println("\nEnter id: ");
    					
    					M.id = br.readLine();
    					
    					System.out.println("\nEnter password: ");
    					
    					M.password = br.readLine();
    					
    					M.ManagerLogin(M.id,M.password);
    					
    					if(M.logged_in)
    						System.out.println("logged in");
    					else 
    						System.out.println("Wrong entry");
    					
    					break;
    					
    			case 2: if(M.logged_in)
    					M.UpdateManager();
    					
    					else
    						System.out.println("Not logged in");
    					
    					break;
    					
    			case 3: if(M.logged_in){
							
    						MessManager.updateMess();
    						System.out.println("Mess updated");
    					}
    			
						else
						System.out.println("Not logged in");
					
						break;
    			
    			case 4: M.ManagerLogout();
    					System.out.println("Logged out");
    				
    					break;
    					
    			case 5: if(M.logged_in)
    					M.manageLeave();
    			
    					else
    						System.out.println("Not logged in");
    					
    					break;
    			
    			case 6: if(M.logged_in)
							M.managePriceList();
    			
						else
							System.out.println("Not logged in");
					
						break;
    					
    			case 7: if(M.logged_in)
							MessManager.showPriceList();
    			
						else
							System.out.println("Not logged in");
				
						break;	

				 case 8: if(M.logged_in)
							MessManager.showGuestList();
    			
						else
							System.out.println("Not logged in");
		
						break;
						
				 case 9: 	System.out.println("\nEnter id: ");
 				try{
						String idn = br.readLine();
						//MessManager.forgotPassword(idn);
 				}
 				catch(Exception e){
 					System.out.println("ID doesn't exist.");
 				}
						break;
						
    			case 10: System.out.println("Exiting..");
    					break;
    					
    			default : System.out.println("Invalid choice.");
    		}
    		

    	}while(choice != 10);
 
			br.close();
		
        
    }


	
		
		
		
	}


package Mess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;



public class ClerkClient {

	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ClerkClient C = new ClerkClient();
		int choice;
		String id;
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do
    	{
    		System.out.println("Enter the choice no:");
    		System.out.println("1. Scan Card");
    		System.out.println("2. Guest entry");
    		
    		System.out.println("3. Exit");
    		
    		choice = Integer.parseInt(br.readLine());
    		
    		
    		switch(choice)
    		{
    			case 1:
    					System.out.println("Enter id");
    	
    					
    					id = br.readLine();
    					
				try {
					Clerk.scanCard(id);
					} catch (SQLException e) {
					
					e.printStackTrace();
					
					} catch (ParseException e){
				
					e.printStackTrace();
					
					}
    					break;
    					
    		case 2: 
                try {
                    Clerk.takeMoney();
                } catch (ParseException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                        
                        break;
    					
    			case 3: System.out.println("Exiting..");
    					break;
    			default : System.out.println("Invalid choice.");
    		}
    		

    	}while(choice != 3);
 
			br.close();
		
        
    }
		
		
		
	}



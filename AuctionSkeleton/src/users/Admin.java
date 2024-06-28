package users;

import java.util.Scanner;

import com.odu.Accounts;

//IMPORT THE JAR FILE
//import ;

public class Admin extends Users{
	
	public Admin(String username, String password) {
		//Uncomment once constructor for user is created
		//TODO
		super(username, password);
	}
	
	//ASKS FOR A USERNAME AND PASSWORD for a new auctioneer account. Call the addAccount method from the jar file
	public void createAuctioneer(Scanner in, Accounts obj) {
		//TODO
		String aUsername, aPassword;
		System.out.println("Type A USERNAME AND PASSWORD for a new auctioneer account with space: ");
		aUsername = in.next();
		aPassword = in.next();
		obj = new Accounts("A");
		obj.addAccount(aUsername, aPassword);
		
	}
}

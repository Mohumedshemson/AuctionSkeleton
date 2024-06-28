package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.odu.Accounts;

import auctions.AllAuctions;
import auctions.Auctions;
import users.*;

//NEED TO IMPORT THE ACCOUNTS JAR, refer to instructions
//import ;//TODO

public class Utilites {
	
	/*
	 * Accounts is imported from JAR file.
	 */
	private Accounts customers = new Accounts("C");
	private Accounts auctioneers = new Accounts("A");
	private Accounts admin = new Accounts("ADMIN");
	
	private AllAuctions all = new AllAuctions();
	private ArrayList<Customer> customerList = new ArrayList<>();
	private ArrayList<Auctioneer> auctioneerList = new ArrayList<>();
	
	/*
	 * Create all the menu methods, refer to the instructions to see specifics
	 */
	public void viewMainMenu() {
		Scanner in = new Scanner(System.in);
		String acctType;
		
		while(true) {
			System.out.println("1. Create an account \n2. Sign In");
			int key = in.nextInt();
			if(key == 1) {
				System.out.println("What type of account would you like to create: C(customer), ADMIN: ");
	            acctType = in.next();
	            if (acctType.equalsIgnoreCase("C")) {
	                customerAccountCreation(in);
	            }
	            else if (acctType.equalsIgnoreCase("ADMIN")) {
	            	System.out.println("Type A USERNAME AND PASSWORD for a new Admin account separated by a space: ");
	            	String username = in.next();
	        		String password = in.next();
	                admin.addAccount(username,password);
	            } else {
                    System.out.println("Invalid account type.");
                }
			}
			else if(key ==2) {
				login(in);
				break;
			}
		}
		//TODO
		
		writeToAuctions();
		System.out.println("Quiting");
		in.close();
	}
	
	
	private void viewCustomerMenu(String username, String password, Scanner in) {
		//TODO
		//MAKE SURE TO ADD THE CUSTOMER TO THE customerList if the first time being signed in
		Customer newCustomer = new Customer(username, password, all);
		if(!customerList.contains(newCustomer)) {
			customerList.add(newCustomer);
		}
		int choice = 0;
		while(choice != 9) {
			System.out.println("Hello Customer " + username);
			System.out.println("Welcome to the menu. What would you like to do?");
			System.out.printf("%-20s\t %-40s%n", "1. View All Auctions", "2. Filter Auctions");
			System.out.printf("%-20s\t %-40s%n", "3. View Watch List", "4. Add/Remove from watch list");
			System.out.printf("%-20s\t %-40s%n", "5. View my Bids", "6. Make a Bid");
			System.out.printf("%-20s\t %-40s%n", "7. View my Balance", "8. Add to Balance");
			System.out.printf("%-20s\t %-40s%n", "9. Sign Out", "");
			choice = in.nextInt();
			switch(choice) {
			case 1:
				all.printAuctions(all.getAllAuctions());
				break;
			case 2:
				all.printFilteredAuctions(in, all.getAllAuctions());
				break;
			case 3:
				newCustomer.printWatchList(all);
				break;
			case 4:
				System.out.println("Would you like to 1.add or 2.remove:");
				int key = in.nextInt();
				if(key == 1) {
					all.printAuctions(all.getAllAuctions());
					System.out.println("Type the Item name you would like to add");
					String addItem = in.next();
					for(Auctions c : all.getAllAuctions()) {
						if(c.getName().equals(addItem)) {
							newCustomer.addToWatchList(c);
						}	
					}
				}
				else if(key == 2) {
					newCustomer.printWatchList(all);
					System.out.println("Type the Item name you would like to remove");
					String removeItem = in.next();
					for(Auctions y : all.getAllAuctions()) {
						if(y.getName().equals(removeItem)) {
							newCustomer.removeFromWatchList(y);
						}	
					}	
				}
				break;
			case 5:
				newCustomer.printBids(all);
				break;
			case 6:
				newCustomer.makeBid(in, all, customerList);
				break;
			case 7:
				System.out.println(newCustomer.getAccountBalance());
				break;
			case 8:
				newCustomer.addToAccountBalance(in);
				break;
			}		
		}
		
		
		
	}
	
	private void viewAdminMenu(String username, String password, Scanner in) {
		//TODO
		System.out.println("Hello Administrator Admin");
		System.out.println("Would you like to create auctioneer acount?(Y or N)");
		String key = in.next();
		if(key.equals("Y")) {
			new Admin(username, password).createAuctioneer(in, admin);
		}else {
			System.exit(0);
		}
	}
	
	private void viewAuctioneerMenu(String username, String password, Scanner in) {
		//TODO
		//MAKE SURE TO ADD THE CUSTOMER TO THE auctioneerList if the first time being signed in
		Auctioneer newAuctioneer = new Auctioneer(username, password, all);
		if(!auctioneerList.contains(newAuctioneer)) {
			auctioneerList.add(newAuctioneer);
			int choice = 0;
			while(choice != 5) {
				System.out.println("Hello Auctioneer " + username);
				System.out.println("Welcome to the menu. What would you like to do?");
				System.out.printf("%-20s\t %-40s%n", "1. Create an auction", "2. View your Auctions");
				System.out.printf("%-20s\t %-40s%n", "3. Start an Auction", "4. End an Auction");
				System.out.printf("%-20s\t %-40s%n", "5. Sign Out", "");
				choice = in.nextInt();
				switch(choice) {
				case 1:
					newAuctioneer.addAuctions(in, all);
					break;
				case 2:
					newAuctioneer.printAuctions(all);
					break;
				case 3:
					newAuctioneer.startAuction(in, all);
					break;
				case 4:
					newAuctioneer.endAuction(in, all);
					break;
				}
			}
		}
		
		
	}
	
	
	private void login(Scanner in) {
		boolean active = true;
		String userType;
		while(active == true) {
			System.out.println("What type of account are you logging into: C(customer), A(auctioneer), ADMIN");
			in.nextLine();
			userType = in.nextLine();
			if(userType.equalsIgnoreCase("C") || userType.equalsIgnoreCase("A") || userType.equalsIgnoreCase("ADMIN")) {
				signIn(in, userType);
				break;
			} 
			else{
				System.out.println("Incorrect Input");
			}
		}
		
	}
	
	/*
	 * Ask for the username and password. 
	 * Depending on the type of user passed in test it against either customer.signIn(), auctioneers.signIn(), or admin.signIn()
	 * If true call the correct user menu method.
	 */
	private void signIn(Scanner in, String userType) {
		//TODO
		
		if(userType.equals("C")) {
			System.out.println("Enter your username and password to login separated by a space: ");
			String username = in.next();
			String password = in.next();
			if(customers.signIn(username, password)) {
				viewCustomerMenu(username, password, in);
			}
		}
		if(userType.equals("A")) {
			System.out.println("Enter your username and password to login separated by a space: ");
			String username = in.next();
			String password = in.next();
			if(auctioneers.signIn(username, password)) {
				viewAuctioneerMenu(username, password, in);
			}
		}
		if(userType.equals("ADMIN")) {
			System.out.println("Enter your username and password to login separated by a space: ");
			String username = in.next();
			String password = in.next();
			if(admin.signIn(username, password)) {
				viewAdminMenu(username, password, in);
			}
		}
		
	}
	
	/*
	 * Ask for a username and password.
	 * call the addAccount method from the jar file for customers
	 */
	private void customerAccountCreation(Scanner in) {
		//TODO
		System.out.println("Type A USERNAME AND PASSWORD for a new customer account separated by a space: ");
		String username = in.next();
		String password = in.next();
		customers.addAccount(username, password);
	}
	
	/*
	 * Gets called at the end of main menu.
	 * Write over the Auction.txt file with the current state of all auctions list
	 * String per line: x.getItemName()+","+x.getCategory()+","+x.getPostedTime()+","+x.getStartingAmount()+","+x.getCurrentBid()+","+x.getHighestBidder()+","+x.getAuctionStatus()+","+x.getAuctioneer()
	 */
	private void writeToAuctions() {
		//TODO
		try {
			FileWriter fileWriter = new FileWriter("Auctions.txt", true);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	       for(Auctions x : all.getAllAuctions()) {
		        bufferedWriter.write(x.getName()+","+x.getCatagory()+","+x.getCurrentAmount()
		        +","+x.getCurrentBid()+","+x.getHighBidder()+","+x.getAuctionStatus()+","+x.getAuctioneer()+","+x.getTimePosted());
		        bufferedWriter.newLine(); 
	       }
	       bufferedWriter.close();
        } catch( IOException e) {
            e.printStackTrace();
        }
	}
}

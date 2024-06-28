package users;

import java.util.ArrayList;
import java.util.Scanner;

import auctions.AllAuctions;
import auctions.Auctions;

public class Customer extends Users{

	private float accountBalance;
	private ArrayList<Auctions> bids = new ArrayList<>();
	private ArrayList<Auctions> watchList = new ArrayList<>();
	
	public Customer(String username, String password, AllAuctions obj) {
		//Uncomment once constructor for user is created
		super(username, password);
		accountBalance = 0;
		generateBids(obj);
	}
	
	//CREATE SETTERS AND GETTERS
	
	public float getAccountBalance() {
		return accountBalance;
		
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public ArrayList<Auctions> getBids() {
		return bids;
	}

	public void setBids(ArrayList<Auctions> bids) {
		this.bids = bids;
	}

	public ArrayList<Auctions> getWatchList() {
		return watchList;
	}

	public void setWatchList(ArrayList<Auctions> watchList) {
		this.watchList = watchList;
	}

	/*
	 * If AllAuctions arraylist is not null, store all auctions that belong to the user into bids arrayList
	 */
	private void generateBids(AllAuctions obj) {
		//TODO
		if(obj != null) {
			ArrayList<Auctions> auctionObj = obj.getAllAuctions();
			for(Auctions p: auctionObj) {
				if(p.getHighBidder().equals(this.getUsername())) {
					bids.add(p);
				}
			}
		}
	}
	
	/*
	 * Ask how much they want to add to the account. Add that amount to account.
	 */
	public void addToAccountBalance(Scanner in) {
		//TODO
		System.out.println("How much would you like to add to your account:");
		setAccountBalance(this.accountBalance += (float)in.nextDouble());
			
	}
	
	
	private void subtractFromAccountBalance(float subtraction) {
		accountBalance = accountBalance-subtraction;
	}
	
	/*
	 * This method is called when a bid is made. It credits the previous top bidder with the amount that they bidded.
	 */
	private void addAfterFailedBid(float addition, String previousTopBidder, ArrayList<Customer> customers) {
		//TODO		
		for (Customer customer : customers) {
			if (customer.getUsername().equals(previousTopBidder)) {
				customer.setAccountBalance(customer.accountBalance + addition);
				System.out.println("Credited " + previousTopBidder + " with " + addition);
				break;
			}
		}
		
	}
	
	
	public void addToWatchList(Auctions obj) {
		//TODO
		watchList.add(obj);
	}
	public void removeFromWatchList(Auctions obj) {
		//TODO
		for (Auctions x : watchList) {
			if (x.equals(obj)) {
			watchList.remove(obj);
			}
		}
	}
	
	public void printBids(AllAuctions obj) {
		obj.printAuctions(bids);
	}
	public void printWatchList(AllAuctions obj) {
		obj.printAuctions(watchList);
	}
	
	/*
	 * Used as the driver to make bids. Prints all the auctions, asks which auction they want to bid on and how much.
	 * Create a auction object of the selected auction 
	 * Check if the bid is valid. 
	 * If it is call the following methods [addAfterFailedBid() from this class,setCurrentBid() for the Auction Class, and subtractFromAccountBalance() from this class]
	 * Add it to the customers bids list
	 */
	public void makeBid(Scanner in,AllAuctions obj, ArrayList<Customer> customers) {
		//TODO
		
		obj.printAuctions(obj.getAllAuctions());
		System.out.println("Which auction do you want to bid on?");
		int r = in.nextInt();
		try {
			Auctions enteredAuction = obj.getAllAuctions().get(r);
			System.out.println("How much are you trying to bid?");
			float bid = in.nextFloat();
			if (accountBalance < bid) {
			System.out.println("Insufficient funds");
			} else if (bid <= enteredAuction.getCurrentBid()) {
			System.out.println("Cannot Bid with this amount as item's current bid is greater");
			} else {
			float addition = (float) enteredAuction.getCurrentBid();
			String previousBidder = enteredAuction.getHighBidder();
			addAfterFailedBid(addition, previousBidder, customers);
			enteredAuction.setCurrentBid(bid);
			bids.add(enteredAuction);
			subtractFromAccountBalance(bid);
		}

		} catch (Exception e) {
			System.out.println("Out of bounds");
			e.printStackTrace();
		}
	}
	
	/*
	 * Used as the driver to add and delete auctions from watch list. 
	 * Ask if the customer wants to add or remove from watch list.
	 * IF ADDING
	 * 	Print All the Auctions and ask which one the want to add.
	 * 	add that auction to watchlist with addToWatchList()
	 * IF removing
	 * 	Print Watch List and ask which one they want to remove
	 *  Remove it from the watchlist with removeFromWatchList()
	 */
	public void controlWatchList(AllAuctions obj, Scanner in) {
		//TODO
		System.out.println("Do you want to add or delete auctions from watchlist? (Enter add or del)");
		String choice = in.next().toLowerCase();
		switch (choice) {
			case "add":
			obj.printAuctions(obj.getAllAuctions());
			try {
			System.out.println("Which auction do you want to add? (Enter Index of the Auction)");
			int index = in.nextInt();
			Auctions added = obj.getAllAuctions().get(index);
			addToWatchList(added);
	
			} catch (Exception e) {
			e.printStackTrace();
			}
			case "del":
			obj.printAuctions(watchList);
			try {
			System.out.println("Which auction do you want to remove? (Enter Index of the Auction)");
			int index = in.nextInt();
			Auctions added = obj.getAllAuctions().get(index);
			removeFromWatchList(added);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

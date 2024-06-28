package auctions;

import java.time.LocalDateTime;

public class Auctions {
	/*
	 * Initialize variables for the auction class. Refer to instructions. 
	 * Below is the date time object used. 
	 */
	private String name;
	private String catagory;
	private double currentAmount;
	private double currentBid;
	private String highBidder;
	private String auctionStatus;
	private String auctioneer;
	private LocalDateTime timePosted; 
	

	/*
	 * Auctions created in two way:
	 * Previous auctions from a txt file or new auctions being created from an auctioneer.
	 * 
	 * This means that for auctionStatus, highestBidder, and timePosted needs a conditional statement. 
	 * See below for example
	 */
		
		
		//FINISH THE CONSTRUCTOR
		//TODO
	public Auctions(String name, String catagory, double currentAmount, double currentBid, String highBidder,
			String auctionStatus, String auctioneer, LocalDateTime timePosted) {
		super();
		this.name = name;
		this.catagory = catagory;
		this.currentAmount = currentAmount;
		this.currentBid = currentBid;
		if(this.highBidder != null) {
			this.highBidder = highBidder;
		}
		else {
			setHighBidder("");
		}
		if(auctionStatus != null) {
			this.auctionStatus = auctionStatus;
		}
		else {
			setAuctionStatus("Upcomming");
		}
		
		this.auctioneer = auctioneer;
		if(timePosted ==null) {
			setPostedTime(); 
		}
		else {
			this.timePosted = timePosted;
			
		}
		setName(name);
		setCatagory(catagory);
		setCurrentAmount(currentAmount);
		setCurrentBid(currentBid);
		setAuctioneer(auctioneer);
	}
	
	
	//DO NOT EDIT, SETS THE TIME FOR A NEW AUCTION
	
	private void setPostedTime() {
		this.timePosted = LocalDateTime.now();
	}

	public LocalDateTime getTimePosted() {
		return timePosted;
	}


	public void setTimePosted(LocalDateTime timePosted) {
		this.timePosted = timePosted;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public double getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(double currentBid) {
		if(this.currentBid < currentBid) {
			this.currentBid = currentBid;
		}
	}

	public String getHighBidder() {
		return highBidder;
	}

	public void setHighBidder(String highBidder) {
		this.highBidder = highBidder;
	}

	public String getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public String getAuctioneer() {
		return auctioneer;
	}

	public void setAuctioneer(String auctioneer) {
		this.auctioneer = auctioneer;
	}
	
	
	//CREATE SETTERS AND GETTERS
	//TODO
	//In your setter for current bid make sure to check that the current bid is smaller then the new bid being passed in
	
	
}

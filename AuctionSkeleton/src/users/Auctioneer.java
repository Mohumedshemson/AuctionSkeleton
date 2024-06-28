package users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import auctions.AllAuctions;
import auctions.Auctions;

public class Auctioneer extends Users {
	private ArrayList<Auctions> auctions = new ArrayList<>();

	public Auctioneer(String username, String password, AllAuctions obj) {
		super(username, password);
		// TODO
		setAuctions(obj);
	}

	public ArrayList<Auctions> getAuctions() {
		return auctions;
	}

	/*
	 * Loop through all auctions and if any belong to the auctioneer add it to the
	 * auctions list
	 */
	public void setAuctions(AllAuctions obj) {
		// TODO

		if (obj.getAllAuctions() != null) {
			ArrayList<Auctions> auctionObj = obj.getAllAuctions();
			for (Auctions x : auctionObj) {
				if (x.getAuctioneer().equals(this.getUsername())) {
					auctions.add(x);
				}
			}
		}

	}

	public void addAuctions(Scanner in, AllAuctions obj) {
		System.out.println("Do you want to manually upload an auction? (Y or N)");
		String choice = in.next();
		if (choice.equals("Y") || choice.equals("y")) {
			manualAddition(in, obj);
		} else if (choice.equals("n") || choice.equals("N")) {
			System.out.println("Returning");
		} else {
			System.out.println("Incorrect Input");
		}
	}

	/*
	 * Writes to the Auctions.txt file when a new auctions is added STRING TO WRITE:
	 * obj.getItemName()+","+obj.getCategory()+","+obj.getStartingAmount()+","+obj.
	 * getCurrentBid()+","+obj.getHighestBidder()+","+obj.getAuctionStatus()+","+obj
	 * .getAuctioneer()
	 */
	private void writeToAuctions(Auctions obj) {
		// TODO
		/// File file = new File("Auctions.txt");

		try {
			FileWriter fileWriter = new FileWriter("Auctions.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(obj.getName() + "," + obj.getCatagory() + "," + obj.getCurrentAmount() + ","
					+ obj.getCurrentBid() + "," + obj.getHighBidder() + "," + obj.getAuctionStatus() + ","
					+ obj.getAuctioneer() + "," + obj.getTimePosted());
			bufferedWriter.newLine();
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Asks for the Name, category, and starting amount. Create an auction object
	 * Add it to the all auctions list, call the writeToAuctions() method, and add
	 * it to auctioneers auction list
	 */
	private void manualAddition(Scanner in, AllAuctions allAuctionsObj) {
		// TODO
		System.out.println("Enter the item name, category and starting amount: ");
		String name = in.next();
		String category = in.next();
		double startAmount = in.nextDouble();
		Auctions newAuction = new Auctions(name, category, startAmount, 0.0, null, null, this.getUsername(), null);
		allAuctionsObj.addAuction(newAuction);
		writeToAuctions(newAuction);
		auctions.add(newAuction);

	}

	/*
	 * Print the auctioneers auctions and ask which one they would like to start
	 * change the status to "In Progress"
	 */
	public void startAuction(Scanner in, AllAuctions obj) {
		// TODO
		for (Auctions x : auctions) {
			System.out.println(x.getName());
		}
		System.out.println("Which one would you like to start:");
		String itemName = in.next();

		for (Auctions y : auctions) {
			if (itemName.equals(y.getName())) {
				y.setAuctionStatus("In Progress");
			}
		}

	}

	/*
	 * Print the auctioneers auctions and ask which one they would like to end
	 * change the status to "Completed"
	 */
	public void endAuction(Scanner in, AllAuctions obj) {
		// TODO
		for (Auctions x : auctions) {
			System.out.println(x.getName());
		}
		System.out.println("Which one would you like to end:");
		String itemName = in.next();

		for (Auctions y : auctions) {
			if (itemName.equals(y.getName())) {
				y.setAuctionStatus("Completed");
			}
		}
	}

	public void printAuctions(AllAuctions obj) {
		obj.printAuctions(auctions);
	}

}

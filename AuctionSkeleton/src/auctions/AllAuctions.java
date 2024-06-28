package auctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class AllAuctions {
	
	private  ArrayList<Auctions> allAuctions = new ArrayList<>();
	
	public AllAuctions() {
		setAllAuctions();
	}
	
	/*
	 * Create Auctions.txt if it does not already exist. Can use new File("Auctions.txt").createNewFile();
	 * Read auctions line by line from the file and store them into allAuctions ArrayList.
	 * 
	 */
	private void setAllAuctions() {
		//TODO
		File file = new File("Auctions.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        	    String line;
        	    while ((line = reader.readLine()) != null) {
        	        String[] parts = line.split(",");
        	        if (parts.length >= 8) { // Check if there are at least 8 parts
        	            try {
        	                LocalDateTime dateTime = LocalDateTime.parse(parts[7]); // Assuming parts[7] contains the LocalDateTime
        	                allAuctions.add(new Auctions(parts[0], parts[1], Double.parseDouble(parts[2]),
        	                        Double.parseDouble(parts[3]), parts[4], parts[5], parts[6], dateTime));
        	            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
        	                System.err.println("Error parsing line: " + line + ". Skipping...");
        	            }
        	        } else {
        	            System.err.println("Invalid data format in line: " + line + ". Skipping...");
        	        }
        	    }
        	} catch (IOException e) {
        	    System.err.println("Error reading file: " + e.getMessage());
        	}
        }
	}
	
	//Create getter for allAuctions
	//TODO
	
	public void addAuction(Auctions obj) {
		allAuctions.add(obj);
	}
	
	public ArrayList<Auctions> getAllAuctions() {
		return allAuctions;
	}
	
	/*
	 * Takes in an ArrayList of Auctions, prints the auctions in format shown in instructions
	 */
	public void printAuctions(ArrayList<Auctions> obj) {
		
		if(allAuctions == null) {
			System.out.println("No auctions!");
			return;
		}
		// TODO
		else {
			String divider = (String.join("", Collections.nCopies(79, "-")));
			System.out.print(String.format("|%-5s|", " "));
			System.out.print(String.format("%-11s|", "Status"));
			System.out.print(String.format("%-15s|", "Item Name"));
			System.out.print(String.format("%-15s|", "Category"));
			System.out.print(String.format("%-11s|", "Current Bid"));
			System.out.print(String.format("%-15s|", "Top Bidder"));
			System.out.println("\n" + divider);
			
			int i = 0;
			for (Auctions x : obj) {
				System.out.print(String.format("|%-5s|", i));
				System.out.print(String.format("%-11s|", x.getAuctionStatus()));
				System.out.print(String.format("%-15s|", x.getName()));
				System.out.print(String.format("%-15s|", x.getCatagory()));
				System.out.print(String.format("%-11s|", String.format("%.2f",x.getCurrentBid())));
				System.out.println(String.format("%-15s|", x.getHighBidder()));

				i++;
			}

		}
		//TODO
	}
	
	public void printFilteredAuctions(Scanner in, ArrayList<Auctions> obj) {
		ArrayList<Auctions> filteredAuctions = new ArrayList<>();
		if(allAuctions == null) {
			System.out.println("No auctions!");
			return;
		}
		System.out.println("What Category Do you want to filter");
		System.out.print("\nAppliances\tClothing\tElectronics"
				+"\nFurniture\tToys");
		
		String choice = in.next();

		//Uncomments once getCategory Object is made
		for(Auctions x : obj) {
			if (x.getCatagory().equals(choice)) {
				filteredAuctions.add(x);
			}
		}
		
		printAuctions(filteredAuctions);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllAuctions other = (AllAuctions) obj;
		return Objects.equals(allAuctions, other.allAuctions);
	}
	
	
}

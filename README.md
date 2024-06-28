# Auction Program

## Description

This project is an auction program designed to facilitate online auctions. The program allows auctioneers to list items and customers to bid on items. 
The auctioneers decide when an auction is complete. The program is implemented using proper object-oriented programming principles, including objects/classes, encapsulation, and inheritance. 
Usernames, passwords, and auctions are preserved between sessions, while other data is not retained.

## Features

- **Three User Types**: 
  - **Customer**: Can place bids, add money to their account, and add auctions to their watch list.
  - **Auctioneer**: Can create and manage auctions (start/stop).
  - **Admin**: Can create Auctioneer accounts.
- **Auction Management**: 
  - Auctions include item name, category, posted time, starting amount, current bid, highest bidder, status (upcoming, in progress, complete), and managing auctioneer.
  - All auctions are saved to a text file.

## Installation Instructions

1. **Import Skeleton Code to Eclipse**:
   1. Click `File -> Import`.
   2. In the Import Menu, select `General -> Existing Projects into Workspace`.
   3. In "Select root directory", choose the `AuctionSkeleton` folder.
   4. Click `Select All`.
   5. Press the `Finish` button.

2. **Import the Account Jar File**:
   - The provided jar file contains methods to manage user accounts.

## Tasks

### Task 1: Import the Account Jar File
- **Methods**: 
  - `addAccount(String username, String password)`: Writes the username and password to a text file.
  - `signIn(String username, String password)`: Returns true if the username-password pair exists.

### Task 2: Implement Auctions Package

#### Subtask 1: Auctions Class
1. Add 9 attributes: name, category, postedTime, startingAmount, currentBid, highestBidder, auctionStatus, auctioneer.
2. Complete the constructor.
3. Create setters and getters.

#### Subtask 2: AllAuctions Class
1. Complete the `setAllAuctions` method.
2. Create setters and getters.
3. Complete the method to print auctions from a list.

### Task 3: Implement User Package

#### Subtask 1: Users Class
1. Add 2 attributes: Username and Password.
2. Create setters and getters.

#### Subtask 2: Auctioneer Class
1. Complete the `setAuctions` method.
2. Complete the `writeToAuctions` method.
3. Complete the method to add auctions.
4. Complete the `manualAddition` method.
5. Complete the start and end auction methods.

#### Subtask 3: Admin Class
1. Complete the method to create an auctioneer account.

#### Subtask 4: Customer Class
1. Add 3 attributes: account balance, list of bidded auctions, and watch list.
2. Create setters and getters.
3. Complete the `generateBids` method.
4. Complete the `addToAccountBalance` method.
5. Complete the `addAfterFailedBid` method.
6. Complete the `makeBid` method.
7. Complete the methods to manage the watch list.

### Task 4: Implement Utility Package

#### Subtask 1: Import the Account Jar File
- Uncomment the private variables at the top of the Utilities Class.

#### Subtask 2: Complete Menu Methods
1. Implement the main menu and user-specific menus.
2. Each menu should call the relevant methods for that user type.

#### Subtask 3: Complete the Sign-In Method
- Validate the username and password, then direct the user to their respective menu.

#### Subtask 4: Complete the Customer Account Creation Method
- Use the jar file method to create a customer account.

#### Subtask 5: Complete the WriteToAuctions Method
- Delete contents of `Auction.txt` and write the updated `AllAuctions` list to the file.

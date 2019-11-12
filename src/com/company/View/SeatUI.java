package com.company.View;


import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.Controller.MovieGoerController;

import java.lang.reflect.Proxy;
import java.util.*;
import java.time.format.DateTimeFormatter;
public class SeatUI {
	public int getCineplexSelectionView(ArrayList<Cineplex> cineplexes) {
		System.out.println("Please select cineplex: ");
		for(int i=0;i<cineplexes.size();i++) {
			System.out.println((i+1)+")"+cineplexes.get(i).getCineplexName());
		}
		return Utils.getUserChoice(1, cineplexes.size())-1;
	}
	
	public int getMovieSelectionView(ArrayList<String> movies) {
		System.out.println("Please select movie: ");
		for(int i=0;i<movies.size();i++) {
			System.out.println((i+1)+")"+ movies.get(i));
		}
		return Utils.getUserChoice(1, movies.size())-1;
	}

	public int getCinemaTypeSelectionView(ArrayList<String> cinemaType) {
		System.out.println("Please select cinema: ");
		for(int i=0;i<cinemaType.size();i++) {
			System.out.println((i+1)+")"+ cinemaType.get(i));
		}
		return Utils.getUserChoice(1, cinemaType.size())-1;
	}

	public int getShowTimeSelectionView(ArrayList<ShowTime> showTimes) {
		System.out.println("Please select Show Time: ");
		int i;
		Price p = new Price();
		for(i=0;i<showTimes.size();i++) {
			if(p.isHoliday(showTimes.get(i).getDateTime()))
				System.out.println((i+1)+")"+Utils.createDayOfWeekString(showTimes.get(i).getDateTime())+"(Holiday)");
			else
				System.out.println((i+1)+")"+Utils.createDayOfWeekString(showTimes.get(i).getDateTime()));
		}
		System.out.println((i+1)+")"+ "Return to main menu");

		int choice = Utils.getUserChoice(1, showTimes.size()+1)-1;
		if(choice>=showTimes.size()) return -1;
		else return choice;
	}

	public HashMap<String,String> getSeatSelectionMenu(ShowTime st, float basePrice) {
		MovieGoerController mgc = new MovieGoerController();
		Utils.displayHeader("Seat Selection");
		HashMap<String,String> chosenSeat = new HashMap<>();
		getSeatListing(st,chosenSeat);
		System.out.println("---------------------------------------------------------------------");
		int choice;
		float totalPrice = 0;
		while(true) {
			System.out.println(
					"1. Select seat\n" +
					"2. Remove selected seat\n" +
					"3. Make Payment");

			choice = Utils.getUserChoice(1, 3);
			switch(choice) {
				case 1:
					String selectedSeat = getSeatSelectionView(st,chosenSeat);
					if(selectedSeat!=null)
						if(chosenSeat.containsKey(selectedSeat))
							System.out.println("The seat is already chosen!!!");
						else{
							chosenSeat.put(selectedSeat,getAgeSelection());
							totalPrice = mgc.calculateTotalPrice(chosenSeat,basePrice);
						}
					getSeatListing(st,chosenSeat);
					getTicketPriceView(chosenSeat,basePrice);
					System.out.println("Current total price: "+ Float.toString(totalPrice));
					break;
				case 2:
					String removedSeat = getSeatSelectionView(st,chosenSeat);
					if(chosenSeat.size()>0)
						if(removedSeat==null)
							continue;
						else if(chosenSeat.containsKey(removedSeat)){
							chosenSeat.remove(removedSeat);
							totalPrice = mgc.calculateTotalPrice(chosenSeat,basePrice);
						}
						else
							System.out.println("The seat is not chosen!!!");
					else
						System.out.println("You have not chosen any seats!!!");
					getSeatListing(st,chosenSeat);
					getTicketPriceView(chosenSeat,basePrice);
					System.out.println("Current price: "+ totalPrice);
					break;
				case 3:
				    if(getPaymentView(chosenSeat,basePrice,st)) return chosenSeat;
				    else break;
				default:
					System.out.println("Invalid choice");
			}
		}		
	}

	public void getTicketPriceView(HashMap<String,String> chosenSeat, float basePrice){
		MovieGoerController mgc = new MovieGoerController();
		HashMap<String,Integer> ageCount = mgc.getAgeCount(chosenSeat);
		for(Map.Entry<String,Integer> ac : ageCount.entrySet()){
			System.out.println(ac.getKey() + " x" + ac.getValue() + ": " +
					(mgc.calculateTicketPrice(ac.getKey(),basePrice)*ac.getValue()));
		}
	}


	public String getAgeSelection() {
		System.out.println("Select Age: ");
		System.out.println(
	             "1. Adult\n" +
	             "2. Senior Citizen\n" +
	             "3. Child");
		int choice = Utils.getUserChoice(1, 3);
		Price prices = new Price();
		switch(choice) {
		case 1:
			return "Adult";
		case 2:
			return "Senior Citizen";
		case 3:
			return "Child";
		}
		return "Adult";
	}
	
	public String getSeatSelectionView(ShowTime st, HashMap<String,String> chosenSeats) {
		String row = "-1";
		int column = -1;
		boolean complete = false;
		ArrayList<String> chosen = new ArrayList<>();
		for(String seatId: chosenSeats.keySet()){
			chosen.add(seatId);
		}
		while(!complete){
			while(!complete) {
				System.out.println("Please select row: ");
				try {
					Scanner scanner = new Scanner(System.in);
					row = scanner.next();
				} catch (InputMismatchException e) {
					System.out.println("Invalid row");
					continue;
				}

				if(Arrays.asList(st.getRowID()).contains(row)) {
					complete = true;
				}
				else {
					System.out.println("Invalid row");
				}
			}

			complete = false;
			while(!complete) {
				System.out.println("Please select column: ");
				try {
					Scanner scanner = new Scanner(System.in);
					column = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid column");
				}

				if(st.getSeat(row, column)!=null && st.getSeat(row, column).getIsAssigned()) {
					System.out.println("Seat taken!!!");
					return null;
				}
				else if(st.getSeat(row, column)!=null){
					complete = true;
				}
				else {
					System.out.println("Invalid column");
				}
			}
		}
		String rowId = "r"+row+"c"+column;
		return rowId;
	}
	
   public void getSeatListing(ShowTime st, HashMap<String,String> chosenSeats) {
	   ArrayList<String> chosen = new ArrayList<>();
		for(String seatId: chosenSeats.keySet()){
			chosen.add(seatId);
		}
	   System.out.println("################################# Screen #############################");
		for(int r=0;r<st.getNumRows();r++) {
			boolean first = true;
			System.out.print(st.getRowID(r));
			for(int c=0;c<st.getNumColumns();c++) {					
				Seat s = st.getSeat(r, c);
				if(s !=null) {						
					if(first) {
						System.out.print("|");
						first = false;
					}
					if(s.getIsAssigned()) {
						System.out.print(" O |");
					}
					else if(chosen.size()!=0 && chosen.contains(s.getSeatID())) {
						System.out.print(" C |");
					}
					else if(c==8) {
						System.out.print("    |");
					}
					else {
						System.out.print("   |");							
					}
				}
				else {
					System.out.print("    ");
				}
			}
			System.out.println();
		}
		System.out.println("   0   1   2   3   4   5   6   7           10  11  12  13  14  15  16");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("O: Occupied, C: Chosen");
   }

   public boolean getPaymentView(HashMap<String,String> chosenSeats, float basePrice, ShowTime st){
       MovieGoerController mgc = new MovieGoerController();
       System.out.println("---------------------------------------------------------------------");
	    for (Map.Entry<String, String> seat : chosenSeats.entrySet()) {
           String seatID = seat.getKey();
           String age = seat.getValue();
           float price = mgc.calculateTicketPrice(age,basePrice);
            System.out.println(age);
            System.out.println("Seat: " + st.getSeat(seatID).getRow() + st.getSeat(seatID).getColumn());
       }
       System.out.println("Total Price: " + mgc.calculateTotalPrice(chosenSeats,basePrice));
       String choice;
       while(true){
           System.out.println("Confirm payment? (y/n)");
           Scanner scanner = new Scanner(System.in);
           choice = scanner.next();
           switch(choice){
               case "y":
                   return true;
               case "n":
                   return false;
               default:
                   System.out.println("Invalid choice!!!");
           }
       }
   }

   public void getTicketView(Cineplex cineplex, HashMap<String,String> chosenSeats,Cinema cinema, ShowTime st){
       Utils.displayHeader("Tickets");
       for (Map.Entry<String, String> seat : chosenSeats.entrySet()) {
           System.out.println("===========================================");
           String seatID = seat.getKey();
           String age = seat.getValue();
           System.out.println(st.getMovie().getTitle());
		   System.out.println("Cineplex: " + cineplex.getCineplexName());
		   System.out.println("Cinema: " + cinema.getCID());
           System.out.println("Seat: " + st.getSeat(seatID).getRow() + st.getSeat(seatID).getColumn());
           System.out.println(age);
           System.out.println("===========================================");
       }

   }
}

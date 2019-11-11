package com.company.View;


import com.company.Entity.Cineplex;
import com.company.Entity.Price;
import com.company.Entity.Seat;
import com.company.Entity.ShowTime;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		for(int i=0;i<showTimes.size();i++) {
			System.out.println((i+1)+")"+showTimes.get(i).getDateTime().format(formatter));
		}
		return Utils.getUserChoice(1, showTimes.size())-1;
	}

	public ArrayList<String> getSeatSelectionMenu(ShowTime st) {
		Utils.displayHeader("Seat Selection");
		ArrayList<String> chosen = new ArrayList<String>();
		getSeatListing(st,chosen);
		System.out.println("---------------------------------------------------------------------");
		int choice;
		float agePrice;
		while(true) {
			System.out.println(
					"1. Select seat\n" +
					"2. Remove selected seat\n" +
					"3. Make Payment");

			choice = Utils.getUserChoice(1, 3);
			switch(choice) {
				case 1:
					String selectedSeat = getSeatSelectionView(st,chosen);
					if(selectedSeat!=null)
						if(chosen.contains(selectedSeat))
							System.out.println("The seat is chosen");
						else
							agePrice = getAgeSelection();
							chosen.add(selectedSeat);
					getSeatListing(st,chosen);
					break;
				case 2:
					String removedSeat = getSeatSelectionView(st,chosen);
					if(chosen.size()>0)
						if(removedSeat==null)
							continue;
						else if(chosen.contains(removedSeat))
							chosen.remove(removedSeat);
						else
							System.out.println("The seat is not chosen.");
					else
						System.out.println("You have not chosen any seats.");
					getSeatListing(st,chosen);
					break;
				case 3:
					return chosen;
				default:
					System.out.println("Invalid choice");
			}
			for(String c: chosen){
				System.out.println(c);
			}
		}		
	}
	
	public float getAgeSelection() {
		System.out.println("Select Age: ");
		System.out.println(
	             "1. Adult\n" +
	             "2. Senior Citizen\n" +
	             "3. Child\n");
		int choice = Utils.getUserChoice(1, 3);
		Price prices = new Price();
		switch(choice) {
		case 0:
			return prices.getPrice("Adult");			
		case 1:
			return prices.getPrice("Senior Citizen");
		case 2:
			return prices.getPrice("Child");
		}
		return 0;
	}
	
	public String getSeatSelectionView(ShowTime st, ArrayList<String> chosen) {
		String row = "-1";
		int column = -1;
		boolean complete = false;
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
					System.out.println("Seat taken");
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
	
   public void getSeatListing(ShowTime st, ArrayList<String> chosen) {
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
}

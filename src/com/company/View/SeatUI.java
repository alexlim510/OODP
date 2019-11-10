package com.company.View;

import Entity.*;
import java.util.*;
import Utils.Utils;
import Controller.MovieGoerController;

public class SeatUI {
	public int getCineplexSelectionView(ArrayList<Cineplex> cineplexes) {
		System.out.println("Please select cineplex: ");
		for(int i=0;i<cineplexes.size();i++) {
			System.out.println(i+")"+cineplexes.get(i));
		}
		return Utils.getUserChoice(1, cineplexes.size());						
	}
	
	public int getMovieSelectionView(ArrayList<String> movies) {
		System.out.println("Please select movie: ");
		for(int i=0;i<movies.size();i++) {
			System.out.println(i+")"+movies.get(i));
		}
		return Utils.getUserChoice(1, movies.size());						
	}
	
	public void getSeatSelectionMenu(ShowTime st) {
		Utils.displayHeader("Seat Selection");
		System.out.println(
	             "1. Select seat\n" +
	             "2. Remove selected seat\n" +
	             "3. Make Payment\n");
		
		int choice = Utils.getUserChoice(1, 3);
		boolean complete = true;
		ArrayList<String> chosen = new ArrayList<String>();
		
		while(!complete) {
			switch(choice) {
			case 1:
				getSeatListing(st,chosen);
				chosen.add(getSeatSelectionView(st));
				break;
			case 2:
				getSeatListing(st,chosen);
				chosen.remove(getSeatSelectionView(st));
				break;
			case 3:
				
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
	
	public String getSeatSelectionView(ShowTime st) {
		Scanner scanner = new Scanner(System.in);
		String row = "-1";
		int column = -1;
		boolean complete = false;
		while(!complete) {
			System.out.println("Please select row: ");
			try {
				row = scanner.next();
			} catch (InputMismatchException e) {
				System.out.println("Invalid row");
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
				column = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid column");
			}
			
			if(st.getSeat(row, column)!=null) {
				complete = true;
			}
			else {
				System.out.println("Invalid column");
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
					else if(chosen.contains(s.getSeatID())) {
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

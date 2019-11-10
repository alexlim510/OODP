package com.company.Utils;

import com.company.Entity.*;
import com.company.View.SeatUI;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class cineplexConfig {
	public static void main(String[] args) {
		try {
			//new cineplex
			Cineplex cp1 = new Cineplex("Jem");
			Cineplex cp2 = new Cineplex("West Mall");
			Cineplex cp3 = new Cineplex("The Cathay");
			ArrayList<Cineplex> cp = new ArrayList<Cineplex>();
			cp.add(cp1);
			cp.add(cp2);
			cp.add(cp3);
			
			//new cinema
			Cinema c1 = new Cinema("wm1");
			Cinema c2 = new Cinema("wm2");
			Cinema c3 = new Cinema("wm3");
			ArrayList<Cinema> cinemas = new ArrayList<Cinema>(); 
			cinemas.add(c1);
			cinemas.add(c2);
			cinemas.add(c3);					
			

			for(Cineplex c: cp) {
				if(c.getCineplexName().equals("West Mall")) {
					c.setCinemas(cinemas);
				}
				else {
					c.setCinemas(null);
				}
			}
			
			ArrayList<Movie> movie = (ArrayList<Movie>)Utils.readObject("movie.txt");
			ShowTime st1 = new ShowTime(Utils.createLocalDateTime(2019, 12, 10, 12, 10),movie.get(0));
			ShowTime st2 = new ShowTime(Utils.createLocalDateTime(2019, 12, 11, 12, 10),movie.get(0));
			ShowTime st3 = new ShowTime(Utils.createLocalDateTime(2019, 12, 13, 12, 10),movie.get(0));
			try {				
				c1.addShowTime(st1);
				c1.addShowTime(st2);
				c1.addShowTime(st3);
			}
			catch(IllegalArgumentException e) {
				System.out.println(e);
			}
			
			ArrayList<ArrayList<Seat>> seats = c1.getShowTime(Utils.createLocalDateTime(2019, 12, 10, 12, 10)).getSeats();
			seats.get(1).get(1).assignSeat();
			
			// print seats
			
			SeatUI sui = new SeatUI();
			ArrayList<String> chosen = new ArrayList<String>();
			chosen.add("rAc1");
			sui.getSeatListing(st1,chosen);
			Utils.writeObject("cineplex.txt", (Object)cp);
			
			/*
			ArrayList<Cineplex> cpp = (ArrayList<Cineplex>)Utils.readObject("cineplex.txt");
			for(Cineplex c: cpp) {
				ArrayList<Cinema> clist1 = c.getCinemas();
				System.out.println(c.getCineplexName());
				if(c.getCinemas()!=null) {
					for(Cinema ci: clist1) {
						System.out.println(ci.getCID());
					}
				}				
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
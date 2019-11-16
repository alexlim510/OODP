package com.company.Utils;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;


public class config {


public static void main(String[] args) {
		ObjectOutputStream oos1;
	    		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			Customer Customer1 = new Customer("anyone", "0123123", "abc@gmail.com");
			ArrayList<Review> reviewlist= new ArrayList<Review>();
			LocalDateTime now = LocalDateTime.now();

			oos1 = Utils.getObjectOutputStream("movie.txt");
			  
		
			Movie movie1 = new Movie("Superman" ,"Cool and handsome" , "Alex" ,
					new String[]{"Jay" , "Chean" ,"Alfredo","Bharat"},new String[]{ "Sci-fi" , "Cartoon" }
					  , 123 ,null ,1,2 ,1);
			Movie movie2 = new Movie("Spiderman" ,"Swinging around the city with style" , "Alex" ,
					new String[]{"Jay" , "Chean" },new String[]{ "Action"  }  ,
					152 ,null,1,2 ,1);

			Movie movie3 = new Movie("Frozen 2" ,"spend a magical time with the entire family at the Frozen 2 Family Friendly Special Screening" , "Alex" ,
					new String[]{"Josh Gad" , "Kristen Bell" , "Idina Menzel" },new String[]{ "Adventure" , "Animation" }  ,
					109 ,null ,1,2 ,1);


			Movie movie4 = new Movie("Jumanji 2" ,"Bethany, Martha and Fridge re-enter the game \"Jumanji\" after discovering that Spencer has been sucked back into it." , "Jake Kasdan" ,
					new String[]{"Danny" , "Jack Black","Danny Glover","Dwayne Johnson" },new String[]{ "Comedy" , "Adventure" }  ,
					111 ,null ,0,2 ,3);


			Movie movie5 = new Movie("Code 8" ,"Code 8 is set in a world where 4% of the population is born with varying supernatural abilities" , "Alex" ,
					new String[]{"(Greg Bryk" , "Stephen Amell" },new String[]{ "Sci-fi" }  ,
					2 ,null ,0,0 ,0);


			Movie movie6 = new Movie("21 Bridges" ,"A disgraced NYPD detective gets a shot at redemption as he is thrust into a citywide manhunt for a cop killer." , "Brian Kirk" ,
					new String[]{"Channing Tatum" , "Chadwick Boseman" ,"Taylor Kitsch"},new String[]{ "Crime" , "Drama" }  ,
					124 ,null ,2,2 ,2);
			Movie movie7 = new Movie("Ford V Ferrari" ,"To challenge Enzo Ferrari's race cars at the 24 Hours of Le Mans in France in 1966, American car designer Carroll Shelby and race car driver Ken Miles team up to build a revolutionary vehicle for the Ford Motor Co." , "Henry" ,
					new String[]{"Alex" , "Johnny" },new String[]{ "Drama" , "Action" }  ,
					155 ,null ,2,2 ,2);
			
			Review review1 = new Review(  Customer1, "WTF bad movie" , 2f, now);
			Review review2 = new Review(Customer1, "niceeeeee" , 3f, now);
			reviewlist.add(review1);
			reviewlist.add(review2);


			movie1.setMovieReview(reviewlist);
		//	Movie movie2 = new Movie("Spiderman");
			//Movie movie3 = new Movie("boboboy");
			ArrayList<Movie> movie = new ArrayList<Movie>();
			movie.add(movie1);
			movie.add(movie2);
			movie.add(movie3);
			movie.add(movie4);
			movie.add(movie5);
			movie.add(movie6);
			movie.add(movie7);
		oos1.writeObject(movie);
		} catch (IOException e) {
			e.printStackTrace();
		}



		
		//Read
	ArrayList<Movie> moviei = null;
	try {
		moviei = (ArrayList<Movie>) Utils.readObject("movie.txt");
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	for(Movie m: moviei) {
		System.out.println(m.getTitle());
	}
		/*
		try {
			
			oos = Utils.getObjectOutputStream("cinema.txt");
			Cinema cp1 = new Cinema("wm1","West Mall");
			Cinema cp2 = new Cinema("wm2","West Mall");
			Cinema cp3 = new Cinema("wm3","West Mall");
			ArrayList<Cinema> cp = new ArrayList<Cinema>();
			cp.add(cp1);
			cp.add(cp2);
			cp.add(cp3);
			oos.writeObject(cp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<Cinema> cpi = (ArrayList<Cinema>)Utils.getObjectInputStream("cinema.txt").readObject();
			for(Cinema c: cpi) {
				System.out.println(c.getCID());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/

	}


}


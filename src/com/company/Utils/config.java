package com.company.Utils;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;

import java.time.LocalDateTime;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
					  , 2 ,null , 3.6f,1,2 ,1);
			Movie movie2 = new Movie("Superman" ,"Cool and handsome" , "Alex" ,
					new String[]{"Jay" , "Chean" },new String[]{ "Sci-fi" , "Cartoon" }  ,
					2 ,null , 3.6f,1,2 ,1);
			
			Review review1 = new Review(movie1 , Customer1, "WTF bad movie" , 1.52f, now);
			Review review2 = new Review(movie1 , Customer1, "niceeeeee" , 4.1f, now);
			reviewlist.add(review1);
			reviewlist.add(review2);
			
			movie1.setMovieReview(reviewlist);
		//	Movie movie2 = new Movie("Spiderman");
			//Movie movie3 = new Movie("boboboy");
			ArrayList<Movie> movie = new ArrayList<Movie>();
			movie.add(movie1);
		//	movie.add(movie2);
		//	movie.add(movie3);
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

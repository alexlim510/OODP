package com.company.Controller;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

public class HandleReviewMgr {
   /*
   public static void insertReview(String review, float rating, Movie movie){
      ArrayList<Movie> movies = null;
      try{
         movies = (ArrayList<Movie>) Utils.readObject("movie.txt");
      }catch(IOException e){
         System.out.println("File not found");
      }catch(ClassNotFoundException e){
         System.out.println("Class not found");
      }
      for(int i = 0; i< movies.size(); i++){
         for(int j = 0; j<movies.get(i).getReviews().size(); j++){
            System.out.println(movies.get(i).getReviews().get(j).getContent());
         }
      }

   }*/
   public static void insertReview(String review, float rating, Movie movie){
      ArrayList<Movie> movies = null;
      //Retrieving customer
      Customer customer = Utils.getCustomerCookie();
      if(customer == null){
         System.out.println("Please log in");
      }

      //Check if the user has provided a review;
      for(int i = 0; i<movie.getReviews().size(); i++){
         System.out.println(movie.getReviews().get(i).getCustomer().getEmail());
         if(movie.getReviews().get(i).getCustomer().getEmail().equals(customer.getEmail())){
            System.out.println("You have provided a review.");
            return;
         }
      }

      Review userReview = new Review(movie, customer, review, rating, LocalDateTime.now());

      //if not
      movie.insertMovieReview(userReview);
      for(int j = 0; j<movie.getReviews().size(); j++){
         System.out.println(movie.getReviews().get(j).getContent());
      }

      //modifying database
      try{
         movies = (ArrayList<Movie>) Utils.readObject("movie.txt");
      }catch(IOException e){
         System.out.println("File not found");
      }catch(ClassNotFoundException e){
         System.out.println("Class not found");
      }

      for(int i = 0; i<movies.size(); i++){
         if(movies.get(i).getTitle().equals(movie.getTitle())){
            System.out.println(movies.get(i).getTitle());
            movies.set(i, movie);
         }
      }

      try{
         Utils.writeObject("movie.txt", movies);
      }catch(IOException e){
         System.out.println("File not found");
      }
   }


   public static void deleteReview(Movie movie, int index){
      //if the review is his, show the delete | edit button
      ArrayList<Review> reviewsTemp = movie.getMovieReview();
      reviewsTemp.remove(index);
      movie.setMovieReview(reviewsTemp);
   }

   public static void editReview(Movie movie, int index, String review, float rating){
      //if the review is his, show the delete | edit button
      deleteReview(movie, index);
      insertReview(review, rating, movie);
   }

}
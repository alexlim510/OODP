package com.company.Controller;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

public class HandleReviewMgr {
   public static void insertReview(String review, float rating, Movie movie){
      ArrayList<Movie> movies = null;
      //Retrieving customer
      Customer customer = Utils.getCustomerCookie();
      if(customer == null){
         System.out.println("Please log in");
      }

      //Check if the user has provided a review;
      for(int i = 0; i<movie.getReviews().size(); i++){
         if(movie.getReviews().get(i).getCustomer().getEmail().equals(customer.getEmail())){
            System.out.println("You have provided a review.");
            return;
         }
      }

      Review userReview = new Review(movie, customer, review, rating, LocalDateTime.now());

      //if not
      movie.insertMovieReview(userReview);

      //modifying database
      movies = (ArrayList<Movie>) Utils.readObject("movie.txt");

      for(int i = 0; i<movies.size(); i++){
         if(movies.get(i).getTitle().equals(movie.getTitle())){
            movies.set(i, movie);
         }
      }

      Utils.writeObject("movie.txt", movies);
   }


   public static void deleteReview(Movie movie){
      ArrayList<Movie> movies = null;
      //Retrieving customer
      Customer customer = Utils.getCustomerCookie();
      if(customer == null){
         System.out.println("Please log in.");
      }

      //Check if the user has provided any review
      for(int i = 0; i<movie.getReviews().size(); i++){
         if(movie.getReviews().get(i).getCustomer().getEmail().equals(customer.getEmail())){
            ArrayList<Review> reviewsTemp = movie.getReviews();
            reviewsTemp.remove(i);
            movie.setMovieReview(reviewsTemp);
            break;
         }
      }

      movies = (ArrayList<Movie>) Utils.readObject("movie.txt");

      //changing the value of ArrayList
      for(int j = 0; j<movies.size(); j++){
         if(movies.get(j).getTitle().equals(movie.getTitle())){
            movies.set(j, movie);
         }
      }

      //Inserting to database
      Utils.writeObject("movie.txt", movies);

      System.out.println("Review deleted");
   }

   public static void editReview(Movie movie, int index, String review, float rating){
      deleteReview(movie);
      insertReview(review, rating, movie);
   }

}
package com.company.Controller;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

/**
 * This is the controller of the review of a movie. The class is a part of StaffControl.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandleReviewMgr {
   /**
    * This method inserts review to a Movie object. It allows customer, which is logged in, to give a review
    * to a movie. One person can only give one review for one movie.
    * @param review Content of review for the movie
    * @param rating Rating (1-5) for the movie
    * @param movie Movie object to be reviewed
    */
   public static void insertReview(String review, float rating, Movie movie){
      ArrayList<Movie> movies = null;
      //Retrieving customer
      Customer customer = Utils.getCustomerCookie();
      if(customer == null){
         System.out.println("Please log in");
      }

      //Check if the user has provided a review;
      try{
         for(int i = 0; i<movie.getReviews().size(); i++){
            if(movie.getReviews().get(i).getCustomer().getEmail().equals(customer.getEmail())){
               System.out.println("You have already provided a review.");
               return;
            }
         }
      }catch(NullPointerException e){
         System.out.println(e.getMessage());
         return;
      }

      Review userReview = new Review( customer, review, rating, LocalDateTime.now());
      System.out.println("Review submitted.");


      //if not
      movie.insertMovieReview(userReview);

      //modifying database
      try {
         movies = (ArrayList<Movie>) Utils.readObject("movie.txt");
      } catch (IOException e) {
         System.out.println("File is missing. Please try again");
         return;
      } catch (ClassNotFoundException e) {
         System.out.println("File is missing. Please try again");
         return;
      }

      for(int i = 0; i<movies.size(); i++){
         if(movies.get(i).getTitle().equals(movie.getTitle())){
            movies.set(i, movie);
         }
      }

      try {
         Utils.writeObject("movie.txt", movies);
      } catch (IOException e) {
         System.out.println("File is missing. Please try again");
      }
   }

   /**
    * This deletes review from a Movie object. It allows customer, which is logged in, to delete his review of a movie he has reviewed before.
    * @param movie Movie object
    */
   public static void deleteReview(Movie movie){
      ArrayList<Movie> movies;
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

      try {
         movies = (ArrayList<Movie>) Utils.readObject("movie.txt");
      } catch (IOException e) {
         System.out.println("File is missing. Please try again");
         return;
      } catch (ClassNotFoundException e) {
         System.out.println("File is missing. Please try again");
         return;
      }

      //changing the value of ArrayList
      for(int j = 0; j<movies.size(); j++){
         if(movies.get(j).getTitle().equals(movie.getTitle())){
            movies.set(j, movie);
         }
      }

      //Inserting to database
      try {
         Utils.writeObject("movie.txt", movies);
      } catch (IOException e) {
         System.out.println("File is missing. Please try again");
         return;
      }

      System.out.println("Review deleted");
   }
}
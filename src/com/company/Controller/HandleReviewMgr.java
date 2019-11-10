package com.company.Controller;



import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Review;
import com.company.Utils.Utils;

import java.util.*;
import java.time.LocalDateTime;




public class HandleReviewMgr {
   public static void insertReview(String review, float rating, Movie movie){
      //push review to review list
      Customer customer = Utils.getCustomerCookie();
      if(customer != null){
         Review userReview = new Review(movie, customer, review, rating, LocalDateTime.now());
         movie.insertMovieReview(userReview);
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
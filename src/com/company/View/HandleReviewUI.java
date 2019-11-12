
package com.company.View;

import com.company.Controller.HandleReviewMgr;
import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.util.Scanner;

/**
 * This is the class to configure review for a movie.
 * The class is a part of StaffUI.
 * @author GROUP 2
 * @version 1.0
 */
public class HandleReviewUI {
   /**
    * This is the method to add new review to a movie
    * @param  movie movie to contain the new review
    */
   public static void MakeReview(Movie movie){
      float rating = 1.0f;
      String review;
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Insert your review");
      do{
         rating = Utils.getFloatInput("Insert your rating (1-5): ");
      }while(rating > 5 || rating < 0);
      System.out.println("Review: ");
      review = sc.nextLine();
      
      HandleReviewMgr.insertReview(review, rating , movie);
   }

    /**
     * This is the method to add new review to a movie
     * @param movie movie that contains the to-be-deleted review
     */
   public static void deleteReview(Movie movie){
      HandleReviewMgr.deleteReview(movie);
   }
}
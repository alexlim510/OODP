
package com.company.View;

import com.company.Controller.HandleReviewMgr;
import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.util.Scanner;

/**
 * This is the UI to configure review for a movie.
 * The class is a part of StaffUI.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandleReviewUI {
   /**
    * This method displays the UI to add new review to a movie
    * @param movie movie to contain the new review
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
     * This method displays the UI to delete review from a movie.
     * @param movie movie that contains the to-be-deleted review
     */
   public static void deleteReview(Movie movie){
      HandleReviewMgr.deleteReview(movie);
   }
}
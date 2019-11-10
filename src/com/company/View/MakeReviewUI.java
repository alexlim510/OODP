
package com.company.View;

import com.company.Controller.HandleReviewMgr;
import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.util.Scanner;



public class MakeReviewUI {
	
	
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
}
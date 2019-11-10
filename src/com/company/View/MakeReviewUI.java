
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
      Utils.getFloatInput("Rating: ");
      System.out.println("Review: ");
      review = sc.nextLine();
      
      HandleReviewMgr.insertReview(review, rating , movie);
   }
}
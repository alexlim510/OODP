package com.company.View;


import com.company.App.StaffTest;
import com.company.Controller.StaffControl;
import com.company.Entity.Cineplex;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * This is the main UI Class for staff
 * @author Group 2 - SS6
 * @version 1.0
 */
public class StaffUI implements GeneralUI{
   /**
    * Initialize new StaffControl Class
    */
   private StaffControl staffControl = new StaffControl();

   /**
    * Main menu of StaffUI
    * @return boolean will return true if user choose to Exit
    */
   public void displayHomePage() {
       boolean exit = false;
       while(!exit){
           Utils.displayHeader("Staff Portal");
           System.out.println(
                   "1. Create/Update/Remove movie listing\n" +
                           "2. Create/Remove cinema showtimes\n" +
                           "3. Configure system settings\n" +
                           "4. List top 5 movies\n"+
                           "5. Exit\n"
           );
           switch (Utils.getUserChoice(1, 5)) {
               case 1:
                   displayStaffMovieOptions();
                   break;
               case 2:
                   displayStaffShowtimeOptions();
                   break;
               case 3:
                   displayStaffConfigurationOptions();
                   break;
               case 4:
                   listTopMovies();
                   break;
               case 5:
                   exit = true;
           }
       }
   }

   /**
    * Movie menu for staff
    */
   public void displayStaffMovieOptions() {
      Utils.displayHeader("Modify Movie Listing");
      System.out.println(
              "1. Create movie listing\n" +
                      "2. Update movie listing\n" +
                      "3. Remove movie listing");
      switch (Utils.getUserChoice(1, 3)) {
         case 1:
            staffControl.addMovieListing();
            break;
         case 2:
            staffControl.editMovieListing();
            break;
         case 3:
            staffControl.deleteMovieListing();
            break;
      }
   }

   /**
    * ShowTime menu for staff
    */
   public void displayStaffShowtimeOptions() {
      Utils.displayHeader("Modify Showtimes");
      System.out.println(
              "1. Create movie showtime\n" +
                      "2. Delete movie showtime\n");

      switch (Utils.getUserChoice(1, 2)) {
         case 1:
            HandleShowTimeUI.addShowTimeUI();
            break;
         case 2:
            HandleShowTimeUI.deleteShowTimeUI();
            break;
      }
   }
   /**
    * System Configuration for staff
    */
   public void displayStaffConfigurationOptions(){
      Utils.displayHeader("System Configuration");
      System.out.println(
              "1. Configure price\n" +
                      "2. Configure holidays/special dates\n");
      switch (Utils.getUserChoice(1, 2)) {
         case 1:
            displayStaffConfigurationOptionsPricing();
            break;
         case 2:
            displayStaffConfigurationOptionsHoliday();
            break;
      }
   }
   /**
    * Pricing menu for staff (Sub-category of System Configuration)
    */
   public void displayStaffConfigurationOptionsPricing(){
      Utils.displayHeader("Change ticket price");
      System.out.println(
              "1. Add new category\n" +
                      "2. Delete category\n"+
                      "3. Edit category\n");
      switch (Utils.getUserChoice(1, 3)) {
         case 1:
            HandlePricingUI.addPriceCategoryUI();
            break;
         case 2:
            HandlePricingUI.deletePriceCategoryUI();
            break;
         case 3:
            HandlePricingUI.editPriceCategoryUI();
            break;
      }
   }
   /**
    * Holidays/Special Dates menu for staff (Sub-category of System Configuration)
    */
   public void displayStaffConfigurationOptionsHoliday(){
      Utils.displayHeader("Change Holidays/Special Price");
      System.out.println(
              "1. Add new category\n" +
                      "2. Delete category\n");
      switch (Utils.getUserChoice(1, 2)) {
         case 1:
            HandleHolidayUI.addHolidayUI();
            break;
         case 2:
            HandleHolidayUI.deleteHolidayUI();
            break;
      }
   }
   /**
    * Listing Top Movies for staff
    */
   public void listTopMovies() {
      Utils.displayHeader("Top 5 Movies");
      System.out.println(
              "1. List top 5 ranking movies by ticket sales.\n" +
                      "2. List top 5 ranking movies by Overall reviewers' rating.\n");
      switch (Utils.getUserChoice(1, 2)) {
         case 1:
            getTop5byTicketSales();
            break;
         case 2:
            getTop5byReviewRating();
            break;
      }
   }

   /**
    * List top 5 movie based on num of ticket sales
    */
   private void getTop5byTicketSales(){
      MovieGoerUI movieGoer = new MovieGoerUI();
      movieGoer.getTop5MoviesTicketView();
   }

   /**
    * List top 5 movies based on customer review rating
    */
   private void getTop5byReviewRating(){
      MovieGoerUI movieGoer = new MovieGoerUI();
      movieGoer.getTop5MoviesRatingView();
   }
}

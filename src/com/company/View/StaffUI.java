package com.company.View;


import com.company.App.StaffTest;
import com.company.Controller.StaffControl;
import com.company.Entity.Cineplex;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;


public class StaffUI {
   StaffControl staffControl = new StaffControl();

   public boolean displayMenu() {
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
            return false;
         case 2:
            displayStaffShowtimeOptions();
            return false;
         case 3:
            displayStaffConfigurationOptions();
            return false;
         case 4:
            listTopMovies();
            return false;
         case 5:
            return true;
      }
      return true;
   }

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

   public void displayStaffShowtimeOptions() {
      Utils.displayHeader("Modify Showtimes");
      System.out.println(
             "1. Create movie showtime\n" +
                     "2. Delete movie showtime\n");

      switch (Utils.getUserChoice(1, 2)) {
         case 1:
            ShowTimeUI.addShowTimeUI();
            break;
         case 2:
            ShowTimeUI.deleteShowTimeUI();
            break;
      } 
   }

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
            //TO DO configure holiday
            break;
      }
   }

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

   public void listTopMovies() {
      Utils.displayHeader("Top 5 Movies");
      System.out.println(
              "1. List top 5 ranking movies by ticket sales.\n" +
               "2. List top 5 ranking movies by Overall reviewers' rating.\n");
   }
}

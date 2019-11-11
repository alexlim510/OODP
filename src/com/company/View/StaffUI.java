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
                     "2. Create/Update/Remove cinema showtimes\n" +
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
         
            break;
      }
   }

   public void displayStaffShowtimeOptions() {
      Utils.displayHeader("Modify Showtimes");
      System.out.println(
             "1. Create movie showtime\n" +
                     "2. Update movie showtime\n" +
                     "3. Remove movie showtime");
      switch (Utils.getUserChoice(1, 3)) {
         case 1:
            ShowTimeUI.addShowTimeUI();
            break;
         case 2:
            ShowTimeUI.editShowTimeUI();
            break;
         case 3:
            ShowTimeUI.deleteShowTimeUI();
            break;
      } 
   }

   public void displayStaffConfigurationOptions(){
      Utils.displayHeader("Change ticket price");
      System.out.println(
         "1. Add new category\n" +
         "2. Delete category\n"+
         "3. Edit category\n");
      switch (Utils.getUserChoice(1, 3)) {
         case 1:
            addPricingUI();
            break;
         case 2:
            deletePricingUI();
            break;
         case 3:
            editPricingUI();
            break;
      }      
   }

   public void listTopMovies() {
      Utils.displayHeader("Top 5 Movies");
      System.out.println(
              "1. List top 5 ranking movies by ticket sales.\n" +
               "2. List top 5 ranking movies by Overall reviewers' rating.\n");
   }

   //System Configuration Area ==========================

   public static void addPricingUI(){
      Utils.displayHeader("Add Pricing");
      Scanner sc = new Scanner(System.in);

      String type;
      float price;

      System.out.print("Type in the category you wish to add: ");
      type = sc.nextLine();

      price = Utils.getFloatInput("Type in the price you wish to add for "+type+" category: ");

      boolean successful = StaffControl.addSpecialPricingMgr(type, price);

      if(successful){
         System.out.println("Price added");
      }else{
         System.out.println("Price not added. Please try again");
      }
   }

   public static void editPricingUI(){
      Utils.displayHeader("Edit Pricing");
      Scanner sc = new Scanner(System.in);

      String type;
      float price;

      System.out.print("Type in the category you wish to edit: ");
      type = sc.nextLine();

      price = Utils.getFloatInput("Type in the price you wish to edit for "+type+" category: ");

      boolean successful = StaffControl.editSpecialPricingMgr(type, price);

      if(successful){
         System.out.println("Price edited");
      }else{
         System.out.println("Price not edited. Please try again");
      }
   }

   public static void deletePricingUI(){
      Utils.displayHeader("Delete Pricing");
      Scanner sc = new Scanner(System.in);

      String type;

      System.out.print("Type in the category you wish to delete: ");
      type = sc.nextLine();

      boolean successful = StaffControl.deleteSpecialPricingMgr(type);

      if(successful){
         System.out.println("Price deleted");
      }else{
         System.out.println("Price not deleted. Please try again");
      }
   }

   //System Configuration Area ==========================
}

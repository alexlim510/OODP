package com.company.View;


import com.company.Controller.StaffControl;
import com.company.Utils.Utils;

import java.util.Scanner;
import java.time.LocalDateTime;


public class StaffUI {
   StaffControl staffControl = new StaffControl();
   Utils utils = new Utils();

   public void displayMenu() {
      utils.displayHeader("Staff Portal");
      System.out.println(
             "1. Create/Update/Remove movie listing\n" +
                     "2. Create/Update/Remove cinema showtimes\n" +
                     "3. Configure system settings\n" +
                     "4. List top 5 movies");
   
      switch (utils.getUserChoice(1, 4)) {
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
      }
   }

   public void displayStaffMovieOptions() {
      utils.displayHeader("Modify Movie Listing");
      System.out.println(
             "1. Create movie listing\n" +
                     "2. Update movie listing\n" +
                     "3. Remove movie listing");
      switch (utils.getUserChoice(1, 3)) {
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
      utils.displayHeader("Modify Showtimes");
      System.out.println(
             "1. Create movie showtime\n" +
                     "2. Update movie showtime\n" +
                     "3. Remove movie showtime");
      switch (utils.getUserChoice(1, 3)) {
         case 1:
            addShowTimeUI();
            break;
         case 2:
            editShowTimeUI();
            break;
         case 3:
            deleteShowTimeUI();
            break;
      } 
   }

   public void displayStaffConfigurationOptions() {
      utils.displayHeader("Configuration Options");
      System.out.println(
             "1. Change ticket prices\n" +
                     "2. Change holidays\n");
      switch (utils.getUserChoice(1, 2)) {
         case 1:

            break;
         case 2:

            break;
      }
   }

   public void displayStaffConfigurationOptions(){
      utils.displayHeader("Change ticket price");
      System.out.println(
         "1. Add new category\n" +
         "2. Delete category\n"+
         "3. Edit category\n");
      switch (utils.getUserChoice(1, 3)) {
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
      utils.displayHeader("Top 5 Movies");
      System.out.println(
             "1. List top 5 ranking movies by ticket sales.\n" +
                     "2. List top 5 ranking movies by Overall reviewers' rating.\n");
   }

   //SHOW TIME AREA =====================================

   public void addShowTimeUI(){
      Scanner sc = new Scanner(System.in);
      utils.displayHeader("Add Show Time");
      
      String cineplexName;
      String cinemaName;
      String movieTitle;
      int year;
      int month;
      int day;
      int hour;
      int minute;
      boolean successful=false;
   
      while(!successful){
         System.out.print("Insert cineplex name for the movie: ");
         cineplexName = sc.nextLine();
      
         System.out.print("Insert cinema name for the movie: ");
         cinemaName = sc.nextLine();
      
         System.out.print("Insert title of the movie: ");
         movieTitle = sc.nextLine();
         
         System.out.println("Insert date and time of show time of the movie");
      
         day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
         month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
         year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
         hour = Utils.getDateIntInput("Insert the hour (in number)", 0, 24);
         minute = Utils.getDateIntInput("Insert the minute (in number)", 0, 59);
      
         successful = StaffControl.addShowTimeMgr(cineplexName, cinemaName, movieTitle, year, month, day, hour, minute);
         if(successful){
            System.out.println("Showtime was created.");
         }else{
            System.out.println("Showtime was not created. Please try again");
         }
      }
   }

   public void deleteShowTimeUI(){
      Scanner sc = new Scanner(System.in);
      utils.displayHeader("Delete Show Time");
      
      String cineplexName;
      String cinemaName;
      String movieTitle;
      int year;
      int month;
      int day;
      int hour;
      int minute;
      boolean successful=false;
   
      while(!successful){
         System.out.print("Insert cineplex name for the movie: ");
         cineplexName = sc.nextLine();
      
         System.out.print("Insert cinema name for the movie: ");
         cinemaName = sc.nextLine();
      
         System.out.print("Insert title of the movie: ");
         movieTitle = sc.nextLine();
         
         System.out.println("Insert date and time of show time of the movie");
      
         day = Utils.getDateIntInput("Insert the day you want to delete (in number)", 1, 31);
         month = Utils.getDateIntInput("Insert the month you want to delete (in number)", 1, 12);
         year = Utils.getDateIntInput("Insert the year you want to delete (in number)", LocalDateTime.now().getYear(), 9999);
         hour = Utils.getDateIntInput("Insert the hour you want to delete (in number)", 0, 24);
         minute = Utils.getDateIntInput("Insert the minute you want to delete (in number)", 0, 59);
      
         successful = StaffControl.deleteShowTimeMgr(cineplexName, cinemaName, movieTitle, year, month, day, hour, minute);
         if(successful){
            System.out.println("Showtime deleted.");
         }else{
            System.out.println("Showtime not deleted. Please try again");
         }
      }
   }

   public void editShowTimeUI(){
      Scanner sc = new Scanner(System.in);
      utils.displayHeader("Edit Show Time");
      
      String cineplexName;
      String cinemaName;
      String movieTitle;
      int year; int oldyear;
      int month; int oldmonth;
      int day; int oldday;
      int hour; int oldhour;
      int minute; int oldminute;
      boolean successful = false;
   
      while(!successful){
         System.out.print("Insert cineplex name for the movie: ");
         cineplexName = sc.nextLine();
      
         System.out.print("Insert cinema name for the movie: ");
         cinemaName = sc.nextLine();
      
         System.out.print("Insert title of the movie: ");
         movieTitle = sc.nextLine();
         
         System.out.println("Insert date and time of show time of the movie");
      
         oldday = Utils.getDateIntInput("Insert the day you want to edit (in number)", 1, 31);
         oldmonth = Utils.getDateIntInput("Insert the month you want to edit (in number)", 1, 12);
         oldyear = Utils.getDateIntInput("Insert the year you want to edit (in number)", LocalDateTime.now().getYear(), 9999);
         oldhour = Utils.getDateIntInput("Insert the hour you want to edit (in number)", 0, 24);
         oldminute = Utils.getDateIntInput("Insert the minute you want to edit (in number)", 0, 59);
      
         day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
         month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
         year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
         hour = Utils.getDateIntInput("Insert the hour (in number)", 0, 24);
         minute = Utils.getDateIntInput("Insert the minute (in number)", 0, 59);         
      
         successful = StaffControl.editShowTimeMgr(cineplexName, cinemaName, movieTitle, year, month, day, hour, minute, oldyear, oldmonth, oldday, oldhour, oldminute);
         if(successful){
            System.out.println("Showtime edited.");
         }else{
            System.out.println("Showtime not edited. Please try again");
         }
      }
   }

   //SHOW TIME AREA =====================================

   //System Configuration Area ==========================

   public static void addPricingUI(){
      utils.displayHeader("Add Pricing");
      Scanner sc = new Scanner(System.in);

      String type;
      float price;

      System.out.print("Type in the category you wish to add: ");
      type = sc.nextLine();

      price = Utils.getFloatInput("Type in the price you wish to add for "+type+" category: ");

      boolean successful = StaffControl.addSpecialPricingMgr(type, price);

      if(successful){
         system.out.println("Price added");
      }else{
         system.out.println("Price not added. Please try again");
      }
   }

   public static void editPricingUI(){
      utils.displayHeader("Edit Pricing");
      Scanner sc = new Scanner(System.in);

      String type;
      float price;

      System.out.print("Type in the category you wish to edit: ");
      type = sc.nextLine();

      price = Utils.getFloatInput("Type in the price you wish to edit for "+type+" category: ");

      boolean successful = StaffControl.editSpecialPricingMgr(type, price);

      if(successful){
         system.out.println("Price edited");
      }else{
         system.out.println("Price not edited. Please try again");
      }
   }

   public static void deletePricingUI(){
      utils.displayHeader("Delete Pricing");
      Scanner sc = new Scanner(System.in);

      String type;

      System.out.print("Type in the category you wish to delete: ");
      type = sc.nextLine();

      boolean successful = StaffControl.editSpecialPricingMgr(type);

      if(successful){
         system.out.println("Price deleted");
      }else{
         system.out.println("Price not deleted. Please try again");
      }
   }

   //System Configuration Area ==========================
}

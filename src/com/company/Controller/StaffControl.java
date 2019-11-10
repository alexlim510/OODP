package com.company.Controller;


import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;

import java.time.LocalDateTime;


import java.io.IOException;
import java.util.ArrayList;

public class StaffControl {
   Utils utils = new Utils();

   public void addMovieListing() {
      String type;
      String synopsis;
      String director;
      String[] cast;
      String title;
      String ageType;
      String statusType;
   
      title = utils.getStringInput("Enter the movie title: ");
      Movie movie = new Movie(title);
      int i = 1;
      System.out.println("Select movie type from the following options:");
      for (String movietype : movie.getMovieTypes()) {
         System.out.println(i + ". " + movietype);
         i++;
      }
      type = movie.getMovieTypes()[utils.getUserChoice(1, i - 1)];
      movie.setMovieType(type);
   
      synopsis = utils.getStringInput("Enter the movie synopsis: ");
      movie.setSynopsis(synopsis);
   
      director = utils.getStringInput("Enter the movie director: ");
      movie.setDirector(director);
   
      cast = utils.getStringInput("Enter the cast names with a comma separating the cast names. \nCast names: ")
            .split(",");
      movie.setCast(cast);
   
      i = 1;
      System.out.println("Select age type from the following options:");
      for (String agetype : movie.getAgeTypes()) {
         System.out.println(i + ". " + agetype);
         i++;
      }
      ageType = movie.getAgeTypes()[utils.getUserChoice(1, i - 1)];
      movie.setMovieRating(ageType);
   
      i = 1;
      System.out.println("Select movie status type from the following options:");
      for (String statustype : movie.getStatusTypes()) {
         System.out.println(i + ". " + statustype);
         i++;
      }
      statusType = movie.getStatusTypes()[utils.getUserChoice(1, i - 1)];
      movie.setStatusType(statusType);
   
      System.out.println("Movie successfully created!");
      // Todo print out the movie attribues?
   };

   public void editMovieListing() {
      MovieGoerUI UI = new MovieGoerUI();
      Movie[] movieArray = null;
      try {
         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
         movieArray = MovieArray.toArray(new Movie[MovieArray.size()]);
         UI.getMovieListingView();
      
         ArrayList<Cineplex> CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         Cineplex[] array1 = CineplexArray.toArray(new Cineplex[CineplexArray.size()]);
         UI.getHomeView();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      
      }
   
      UI.getMovieListingView();
      System.out.println("Select movie to edit: ");
      int choice = utils.getUserChoice(1, movieArray.length - 1);
   }

   public void deleteMovieListing() {
      MovieGoerUI UI = new MovieGoerUI();
      Movie[] movieArray = null;
      try {
         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
         movieArray = MovieArray.toArray(new Movie[MovieArray.size()]);
         UI.getMovieListingView();
      
         ArrayList<Cineplex> CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         Cineplex[] array1 = CineplexArray.toArray(new Cineplex[CineplexArray.size()]);
         UI.getHomeView();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      
      }
   
      UI.getMovieListingView();
      System.out.println("Select movie to delete: ");
      int choice = utils.getUserChoice(1, movieArray.length - 1);
   }

   // Configure System Setting ==============================

   // Configure System Setting ==============================

   // SHOW TIME AREA =====================================

   public static boolean addShowTimeMgr(String cineplexName, String cinemaName, String movieTitle, int year, int month,
         int day, int hour, int minute) {
      // cinema, film, datetime needed
      Cineplex theCineplex = null;
      Cinema theCinema = null;
      Movie theMovie = null;
      LocalDateTime dateTime;
      ShowTime newShowTime;
   
      // Checking cineplex name
      ArrayList<Cineplex> CineplexArray;
      try {
         CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         for (int i = 0; i < CineplexArray.size(); i++) {
            if (CineplexArray.get(i).getCineplexName() == cineplexName) {
               theCineplex = CineplexArray.get(i);
               break;
            }
         }
      } catch (ClassNotFoundException | IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
      if (theCineplex == null) {
         System.out.println("Cineplex not found");
         return false;
      }
   
      // Checking cinema name
      ArrayList<Cinema> CinemaArray = theCineplex.getCinemas();
   
      for (int i = 0; i < CinemaArray.size(); i++) {
         if (CinemaArray.get(i).getCID() == cinemaName) {
            theCinema = CinemaArray.get(i);
            break;
         }
      }
   
      if (theCineplex == null) {
         System.out.println("Cinema not found in this cineplex");
         return false;
      }
   
      // Checking movie name
      ArrayList<Movie> MovieArray = theCinema.getMovies();
   
      for (int i = 0; i < MovieArray.size(); i++) {
         if (MovieArray.get(i).getTitle() == movieTitle) {
            theMovie = MovieArray.get(i);
            break;
         }
      }
   
      if (theMovie == null) {
         System.out.println("Movie not found in this cinema");
         return false;
      }
   
      newShowTime = new ShowTime(LocalDateTime.of(year, month, day, hour, minute), theMovie);
   
      // checking if the showtime has existed
      for (int i = 0; i < theCinema.getShowTime().size(); i++) {
         if (theCinema.getShowTime().get(i).getDateTime().isEqual(newShowTime.getDateTime())) {
            System.out.println("The show time at the inserted time is unavailable");
            return false;
         }
      }
   
      theCinema.addShowTime(newShowTime);
      return true;
   }

   public static boolean editShowTimeMgr(String cineplexName, String cinemaName, String movieTitle, int year, int month,
         int day, int hour, int minute, int oldyear, int oldmonth, int oldday, int oldhour, int oldminute) {
      // cinema, film, datetime needed
      Cineplex theCineplex = null;
      Cinema theCinema = null;
      Movie theMovie = null;
      LocalDateTime dateTime;
      ShowTime newShowTime;
   
      // Checking cineplex name
      ArrayList<Cineplex> CineplexArray;
      try {
         CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         for (int i = 0; i < CineplexArray.size(); i++) {
            if (CineplexArray.get(i).getCineplexName() == cineplexName) {
               theCineplex = CineplexArray.get(i);
               break;
            }
         }
      
      } catch (ClassNotFoundException | IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
      if (theCineplex == null) {
         System.out.println("Cineplex not found");
         return false;
      }
   
      // Checking cinema name
      ArrayList<Cinema> CinemaArray = theCineplex.getCinemas();
   
      for (int i = 0; i < CinemaArray.size(); i++) {
         if (CinemaArray.get(i).getCID() == cinemaName) {
            theCinema = CinemaArray.get(i);
            break;
         }
      }
   
      if (theCineplex == null) {
         System.out.println("Cinema not found in this cineplex");
         return false;
      }
   
      // Checking movie name
      ArrayList<Movie> MovieArray = theCinema.getMovies();
   
      for (int i = 0; i < MovieArray.size(); i++) {
         if (MovieArray.get(i).getTitle() == movieTitle) {
            theMovie = MovieArray.get(i);
            break;
         }
      }
   
      if (theMovie == null) {
         System.out.println("Movie not found in this cinema");
         return false;
      }
   
      newShowTime = new ShowTime(LocalDateTime.of(year, month, day, hour, minute), theMovie);
      ShowTime oldShowTime = new ShowTime(LocalDateTime.of(oldyear, oldmonth, oldday, oldhour, oldminute), theMovie);
   
      // checking showtime in cinema
      ArrayList<ShowTime> ShowTimeArray = theCinema.getShowTime();
      for (int i = 0; i < ShowTimeArray.size(); i++) {
         if (ShowTimeArray.get(i).getDateTime().isEqual(oldShowTime.getDateTime())
               && ShowTimeArray.get(i).getMovie().getTitle() == (theMovie.getTitle())) {
            ShowTimeArray.get(i).setDateTime(newShowTime.getDateTime());
            return true;
         }
      }
      return false;
   }

   public static boolean deleteShowTimeMgr(String cineplexName, String cinemaName, String movieTitle, int year,
         int month, int day, int hour, int minute) {
      // cinema, film, datetime needed
      Cineplex theCineplex = null;
      Cinema theCinema = null;
      Movie theMovie = null;
      LocalDateTime dateTime;
      ShowTime newShowTime;
   
      // Checking cineplex name
      ArrayList<Cineplex> CineplexArray;
      try {
         CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         for (int i = 0; i < CineplexArray.size(); i++) {
            if (CineplexArray.get(i).getCineplexName() == cineplexName) {
               theCineplex = CineplexArray.get(i);
               break;
            }
         }
      
      } catch (ClassNotFoundException | IOException e) {
         // TODO Auto-generated catch block
         System.out.println("Cineplex not found");
         return false;
      }
   
      // Checking cinema name
      ArrayList<Cinema> CinemaArray = theCineplex.getCinemas();
   
      for (int i = 0; i < CinemaArray.size(); i++) {
         if (CinemaArray.get(i).getCID() == cinemaName) {
            theCinema = CinemaArray.get(i);
            break;
         }
      }
   
      if (theCineplex == null) {
         System.out.println("Cinema not found in this cineplex");
         return false;
      }
   
      // Checking movie name
      ArrayList<Movie> MovieArray = theCinema.getMovies();
   
      for (int i = 0; i < MovieArray.size(); i++) {
         if (MovieArray.get(i).getTitle() == movieTitle) {
            theMovie = MovieArray.get(i);
            break;
         }
      }
   
      if (theMovie == null) {
         System.out.println("Movie not found in this cinema");
         return false;
      }
   
      // checking showtime in cinema
   
      theCinema.deleteShowTime(LocalDateTime.of(year, month, day, hour, minute), theMovie);
      return true;
   }

   public static boolean addSpecialPricingMgr(String type, float price){
      Price thePricing;
      //get the current pricing
   
      try{
         thePricing.addPrice(type, price);
      }
      catch(IllegalArgumentException e){
         System.out.println("The price category exists");
         return false;
      }
   
      return true;
   }

   public static boolean editSpecialPricingMgr(String type, float price){
      Price thePricing;
      //get the current pricing
   
      try{
         thePricing.updatePrice(type, price);
      }
      catch(IllegalArgumentException e){
         System.out.println("The price category does not exist");
         return false;
      }
      return true;
   }

   public static boolean deleteSpecialPricingMgr(String type){
      Price thePricing;
      //get the current pricing
   
      try{
         thePricing.deletePrice(type);
      }
      catch(IllegalArgumentException e){
         System.out.println("The price category does not exist");
         return false;
      }
      return true;
   }

   // SHOW TIME AREA =====================================
}
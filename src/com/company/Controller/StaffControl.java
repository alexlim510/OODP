package com.company.Controller;


import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;

import java.time.LocalDateTime;


import java.io.IOException;
import java.util.ArrayList;

public class StaffControl {
   public void addMovieListing() {
      String type;
      String synopsis;
      String director;
      String[] cast;
      String title;
      String ageType;
      String statusType;
   
      title = Utils.getStringInput("Enter the movie title: ");
      Movie movie = new Movie(title);
      int i = 1;
      System.out.println("Select movie type from the following options:");
      for (String movietype : movie.getMovieClasses()) {
         System.out.println(i + ". " + movietype);
         i++;
      }
      type = movie.getMovieClasses()[Utils.getUserChoice(1, i - 1)];
//      movie.getMovieClasses(type);
   
      synopsis = Utils.getStringInput("Enter the movie synopsis: ");
      movie.setSynopsis(synopsis);
   
      director = Utils.getStringInput("Enter the movie director: ");
      movie.setDirector(director);
   
      cast = Utils.getStringInput("Enter the cast names with a comma separating the cast names. \nCast names: ")
            .split(",");
      movie.setCast(cast);
   
      i = 1;
      System.out.println("Select age type from the following options:");
      for (String agetype : movie.getAgeTypes()) {
         System.out.println(i + ". " + agetype);
         i++;
      }
      ageType = movie.getAgeTypes()[Utils.getUserChoice(1, i - 1)];
      movie.setMovieRating(ageType);
   
      i = 1;
      System.out.println("Select movie status type from the following options:");
      for (String statustype : movie.getStatusTypes()) {
         System.out.println(i + ". " + statustype);
         i++;
      }
      statusType = movie.getStatusTypes()[Utils.getUserChoice(1, i - 1)];
      movie.setStatusType(statusType);
   
      System.out.println("Movie successfully created!");
      //print out movie attributes.

   }

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
      int choice = Utils.getUserChoice(1, movieArray.length - 1);
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
      int choice = Utils.getUserChoice(1, movieArray.length - 1);
   }

   // SHOW TIME AREA =====================================

   public static boolean addShowTimeMgr(ArrayList<Cineplex> CineplexArray, int cineplexChoice, int cinemaChoice, int movieChoice, int year, int month,
         int day, int hour, int minute) {
      // cinema, film, datetime needed
      LocalDateTime dateTime;
      ShowTime newShowTime;

      newShowTime = new ShowTime(LocalDateTime.of(year, month, day, hour, minute), CineplexArray.get(cineplexChoice).getCinemas().get(cinemaChoice).getMovies().get(movieChoice));
   
      // checking if the showtime has existed
      for (ShowTime st: CineplexArray.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowTime()) {
         if (st.getDateTime().isEqual(newShowTime.getDateTime())) {
            System.out.println("The show time at the inserted time is unavailable");
            return false;
         }
      }

      CineplexArray.get(cineplexChoice).getCinemas().get(cinemaChoice).addShowTime(newShowTime);

      //Inserting new showtime to database
      try{
         Utils.writeObject("cineplex.txt", CineplexArray);
      }catch (IOException e){
         System.out.println("File not found. please try again.");
         return false;
      }

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
         for (Cineplex cineplex: CineplexArray) {
            if (cineplex.getCineplexName().equals(cineplexName)) {
               theCineplex = cineplex;
               break;
            }
         }
      
      } catch (ClassNotFoundException | IOException e) {
         System.out.println("Please try again");
         e.printStackTrace();
      }
   
      if (theCineplex == null) {
         System.out.println("Cineplex not found");
         return false;
      }
   
      // Checking cinema name
      ArrayList<Cinema> CinemaArray = theCineplex.getCinemas();
      //null checker
      if (CinemaArray == null) {
         System.out.println("No cinema found in this cineplex");
         return false;
      }
   
      for (Cinema cinema: CinemaArray) {
         if (cinema.getCID().equals(cinemaName)) {
            theCinema = cinema;
            break;
         }
      }
   
      if (theCineplex == null) {
         System.out.println("Cinema not found in this cineplex");
         return false;
      }
   
      // Checking movie name
      ArrayList<Movie> MovieArray = theCinema.getMovies();
      //null checker
      if (MovieArray == null) {
         System.out.println("No movie found in this cinema");
         return false;
      }
   
      for (Movie movie: MovieArray) {
         if (movie.getTitle().equals(movieTitle)) {
            theMovie = movie;
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
      for (ShowTime st: ShowTimeArray) {
         if (st.getDateTime().isEqual(oldShowTime.getDateTime())
               && st.getMovie().getTitle().equals(theMovie.getTitle())) {

            st.setDateTime(newShowTime.getDateTime());

            //Inserting new showtime to database
            try{
               Utils.writeObject("cineplex.txt", theCineplex);
            }catch (IOException e){
               System.out.println("File not found. please try again.");
               return false;
            }

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
      ArrayList<Cineplex> CineplexArray = null;
      try {
         CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
         for (Cineplex cineplex: CineplexArray) {
            if (cineplex.getCineplexName().equals(cineplexName)){
               theCineplex = cineplex;
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
      if(CinemaArray == null) return false;
   
      for (Cinema cinema: CinemaArray) {
         if (cinema.getCID().equals(cinemaName)) {
            theCinema = cinema;
            break;
         }
      }
   
      if (theCinema == null) {
         System.out.println("Cinema not found in this cineplex");
         return false;
      }
   
      // Checking movie name
      ArrayList<Movie> MovieArray = theCinema.getMovies();
      if(MovieArray == null) return false;
   
      for (Movie movie: MovieArray) {
         if (movie.getTitle().equals(movieTitle)){
            theMovie = movie;
            break;
         }
      }
   
      if (theMovie == null) {
         System.out.println("Movie not found in this cinema");
         return false;
      }
   
      // checking showtime in cinema
   
      theCinema.deleteShowTime(LocalDateTime.of(year, month, day, hour, minute), theMovie);

      //Inserting new showtime to database
      try{
         Utils.writeObject("cineplex.txt", theCineplex);
      }catch (IOException e){
         System.out.println("File not found. please try again.");
         return false;
      }

      return true;
   }

   //SHOW TIME AREA ===========================

   public static boolean addSpecialPricingMgr(String type, float price){
      Price thePricing = new Price();
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
      Price thePricing = new Price();
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
      Price thePricing = new Price();
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

}
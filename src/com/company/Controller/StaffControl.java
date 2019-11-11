package com.company.Controller;


import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class StaffControl {
   Utils utils = new Utils();
   MovieGoerController movieController = new MovieGoerController();
   MovieGoerUI movieGoerUI = new MovieGoerUI();
   Movie movieFunctions = new Movie();

   public void addMovieListing() {
      String title;
      String synopsis;
      String director;
      String[] cast;
      String[] genre;
      Date showTill = null;
      int duration;
      int movieClass; // 3D, blockbuster etc..
      int statusType; //now showing, etc..
      int ageType; //age type {"G", "PG", "PG13", "NC16", "M18", "R21"}
      int i;

      title = utils.getStringInput("Enter the movie title: ");
      synopsis = utils.getStringInput("Enter the movie synopsis: ");
      director = utils.getStringInput("Enter the movie director: ");
      cast = utils.getStringInput("Enter the cast names with a comma separating the cast names. \nCast names: ")
              .split(",");
      genre = utils.getStringInput("Enter the movie genre with a comma separating them. \nGenre: ")
              .split(",");
      // DATE Showtill
      boolean loop = true;
      while(loop) {
         String date = utils.getStringInput("Enter the Date in the format dd-MMM-yyyy:");

         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
         try {
            //Parsing the String
            showTill = dateFormat.parse(date);
         } catch (ParseException e) {
            e.printStackTrace();
         }
         loop = false;
      }
      System.out.println("Enter the movie duration in minutes: ");
      duration = utils.getUserChoice(1, 500);
      i = 1;
      System.out.println("Select movie type from the following options:");
      for (String movietype : movieFunctions.getMovieClasses()) {
         System.out.println(i + ". " + movietype);
         i++;
      }
      movieClass = utils.getUserChoice(1, i - 1);
      i = 1;
      System.out.println("Select movie age restriction from the following options:");
      for (String movieagetype : movieFunctions.getAgeTypes()) {
         System.out.println(i + ". " + movieagetype);
         i++;
      }
      ageType = utils.getUserChoice(1, i - 1);

      i = 1;
      System.out.println("Select movie status type from the following options:");
      for (String statustype : movieFunctions.getStatusTypes()) {
         System.out.println(i + ". " + statustype);
         i++;
      }
      statusType = utils.getUserChoice(1, i - 1);

      Movie movie = new Movie( title, synopsis , director , cast , genre, showTill , duration , movieClass, ageType, statusType );

      try {
         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      System.out.println("Movie successfully created!");
      // Todo print out the movie attribues?

   }

   public void editMovieListing() {
      int i = 1;
      int choice;
      ArrayList<Movie> movieList = new ArrayList<Movie>();
      movieList = movieController.getAllMovieList();
      Utils.displayHeader("Movie List");
      for (Movie m: movieList) //MovieLists is initiated in main
      {
         System.out.println(i + ": " + m.getTitle() + ", movie status: " + m.getStatusType());
         i++;
      }
      System.out.println("Select movie to edit: ");
      choice = utils.getUserChoice(1, movieList.size());
      Movie movieSelected = movieList.get(choice);
      movieGoerUI.getMovieDetailsView(movieSelected);
      System.out.println("Select detail to edit: ");
      switch (utils.getUserChoice(1, 10)) {
         case 1:
            break;
         case 2:
            break;

         case 3:
            break;

         case 4:
            break;
         case 5:
            break;
         case 6:
            break;

         case 7:
            break;

         case 8:
            break;
         case 9:
            break;
         case 10:
            break;
         default:
            break;
      }

   }
//   public void editMovieListing() {
//      MovieGoerUI UI = new MovieGoerUI();
//      Movie[] movieArray = null;
//      try {
//         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
//         movieArray = MovieArray.toArray(new Movie[MovieArray.size()]);
//         UI.getMovieListingView();
//
//         ArrayList<Cineplex> CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
//         Cineplex[] array1 = CineplexArray.toArray(new Cineplex[CineplexArray.size()]);
//         UI.getHomeView();
//      } catch (IOException e) {
//         e.printStackTrace();
//      } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//
//      }
//
//      UI.getMovieListingView();
//      System.out.println("Select movie to edit: ");
//      int choice = Utils.getUserChoice(1, movieArray.length - 1);
//   }

   public void deleteMovieListing() {
//      MovieGoerUI UI = new MovieGoerUI();
//      Movie[] movieArray = null;
//      try {
//         ArrayList<Movie> MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
//         movieArray = MovieArray.toArray(new Movie[MovieArray.size()]);
//         UI.getMovieListingView();
//
//         ArrayList<Cineplex> CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
//         Cineplex[] array1 = CineplexArray.toArray(new Cineplex[CineplexArray.size()]);
//         UI.getHomeView();
//      } catch (IOException e) {
//         e.printStackTrace();
//      } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//
//      }
//
//      UI.getMovieListingView();
//      System.out.println("Select movie to delete: ");
//      int choice = Utils.getUserChoice(1, movieArray.length - 1);
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
         for (Cineplex cineplex: CineplexArray) {
            if (cineplex.getCineplexName().equals(cineplexName)) {
               theCineplex = cineplex;
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
   
      // checking if the showtime has existed
      for (int i = 0; i < theCinema.getShowTime().size(); i++) {
         if (theCinema.getShowTime().get(i).getDateTime().isEqual(newShowTime.getDateTime())) {
            System.out.println("The show time at the inserted time is unavailable");
            return false;
         }
      }
   
      theCinema.addShowTime(newShowTime);

      //Inserting new showtime to database
      try{
         Utils.writeObject("cineplex.txt", theCineplex);
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
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
      if (theCineplex == null) {
         System.out.println("Cineplex not found");
         return false;
      }
   
      // Checking cinema name
      ArrayList<Cinema> CinemaArray = theCineplex.getCinemas();
   
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

   // SHOW TIME AREA =====================================
}
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

        title = Utils.getStringInput("Enter the movie title: ");
        synopsis = Utils.getStringInput("Enter the movie synopsis: ");
        director = Utils.getStringInput("Enter the movie director: ");
        cast = Utils.getStringInput("Enter the cast names with a comma separating the cast names. \nCast names: ")
                .split(",");
        genre = Utils.getStringInput("Enter the movie genre with a comma separating them. \nGenre: ")
                .split(",");
        // DATE Showtill
        boolean loop = true;
        while(loop) {
            String date = Utils.getStringInput("Enter the Date in the format dd-MMM-yyyy:");

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
        duration = Utils.getUserChoice(1, 500);
        i = 1;
        System.out.println("Select movie type from the following options:");
        for (String movietype : movieFunctions.getMovieClasses()) {
            System.out.println(i + ". " + movietype);
            i++;
        }
        movieClass = Utils.getUserChoice(1, i - 1);
        i = 1;
        System.out.println("Select movie age restriction from the following options:");
        for (String movieagetype : movieFunctions.getAgeTypes()) {
            System.out.println(i + ". " + movieagetype);
            i++;
        }
        ageType = Utils.getUserChoice(1, i - 1);

        i = 1;
        System.out.println("Select movie status type from the following options:");
        for (String statustype : movieFunctions.getStatusTypes()) {
            System.out.println(i + ". " + statustype);
            i++;
        }
        statusType = Utils.getUserChoice(1, i - 1);

        Movie movie = new Movie( title, synopsis , director , cast , genre, showTill , duration , movieClass, ageType, statusType );

        ArrayList<Movie> movieArray = new ArrayList<Movie>();
        try {
            movieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        movieArray.add(movie);

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
        choice = Utils.getUserChoice(1, movieList.size());
        Movie movieSelected = movieList.get(choice - 1);
        movieGoerUI.getMovieDetailsView(movieSelected);
        System.out.println("Select detail to edit: ");
        switch (Utils.getUserChoice(1, 10)) {
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

    //public void deleteMovieListing() {
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
//   }

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

        //Adding to Cineplex Array
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

    public static boolean deleteShowTimeMgr(ArrayList<Cineplex> CineplexArray, int cineplexChoice, int cinemaChoice, int showTimeChoice) {
        ArrayList<ShowTime> ShowTimeArray = CineplexArray.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowTime();
        ShowTimeArray.remove(ShowTimeArray.get(showTimeChoice));

        //Inserting new showtime to database
        try{
            Utils.writeObject("cineplex.txt", CineplexArray);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return false;
        }
        return true;
    }

    //SHOW TIME AREA ===========================

    //PRICING ================================

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

    //PRICING ================================

}
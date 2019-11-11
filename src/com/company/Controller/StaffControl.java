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

        Movie movie = new Movie( title, synopsis , director , cast , genre , duration , movieClass, ageType, statusType );

//        ArrayList<Movie> movieArray = new ArrayList<Movie>();
//        try {
//            movieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        ArrayList<Movie> movieArray = movieController.getAllMovieList();
        movieArray.add(movie);


        try {
            Utils.writeObject("movie.txt", movieArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Movie successfully created!");
        // Todo print out the movie attribues?

    }

    public void editMovieListing() {
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
        int i = 0;

        int k = 1;
        int choice;

        ArrayList<Movie> movieList = movieController.getAllMovieList();
        Utils.displayHeader("Movie List");
        for (Movie m: movieList) //MovieLists is initiated in main
        {
            System.out.println(k + ": " + m.getTitle() + ", movie status: " + m.getStatusType());
            k++;
        }
        System.out.println("Select movie to edit: ");
        choice = Utils.getUserChoice(1, movieList.size());
        Movie movie = movieList.get(choice - 1);
//        movieGoerUI.getMovieDetailsView(movieSelected);

        Utils.displayHeader("Movie Details");
        System.out.println("The details of "+ movie.getTitle() + " :");
        System.out.println("1) Duration: "+ movie.getDuration());
        System.out.println("2) Synopsis: "+ movie.getSynopsis());
        System.out.println("3) Status: "+ movie.getStatusType());
        System.out.println("4) Movie Type: "+ movie.getMovieClass());
        System.out.println("5) Age Type: "+ movie.getAgeType());
        String[] movieGenre = movie.getGenre();
        System.out.print("6) Genre: ");
        for (int j = 0; j < movieGenre.length; j++) {
            if (j != movieGenre.length - 1)
                System.out.print(movieGenre[j] + ", ");
            else
                System.out.print(movieGenre[j] + ".\n");}
        System.out.println("7) Director: "+ movie.getDirector());
        System.out.print("8) Cast: ");
        String[] movieCast = movie.getCast();
        for (i = 0; i < movieCast.length; i++) {
            if (i != movieCast.length - 1)
                System.out.print(movieCast[i] + ", ");
            else
                System.out.print(movieCast[i] + ".\n");	}

        System.out.println("Select detail to edit: ");
        switch (Utils.getUserChoice(1, 8)) {
            case 1: // duration
                duration = Utils.getUserChoice(1, 500);
                movie.setDuration(duration);
                break;
            case 2: // synopsis
                synopsis = Utils.getStringInput("Enter the movie synopsis: ");
                movie.setSynopsis(synopsis);
                break;
            case 3: // status: now showing
                i = 1;
                System.out.println("Select movie status type from the following options:");
                for (String statustype : movieFunctions.getStatusTypes()) {
                    System.out.println(i + ". " + statustype);
                    i++;
                }
                statusType = Utils.getUserChoice(1, i - 1);
                movie.setStatusType( movie.getStatusTypes()[statusType]);
                break;
            case 4: // movie type : 3D
                i = 1;
                System.out.println("Select movie type from the following options:");
                for (String movietype : movieFunctions.getMovieClasses()) {
                    System.out.println(i + ". " + movietype);
                    i++;
                }
                movieClass = Utils.getUserChoice(1, i - 1);
                movie.setMovieClass(movie.getMovieClasses()[movieClass]);
                break;
            case 5: // age type PG13
                i = 1;
                System.out.println("Select movie age restriction from the following options:");
                for (String movieagetype : movieFunctions.getAgeTypes()) {
                    System.out.println(i + ". " + movieagetype);
                    i++;
                }
                ageType = Utils.getUserChoice(1, i - 1);
                movie.setAgeType(movie.getAgeTypes()[ageType]);
                break;
            case 6: // Genre
                genre = Utils.getStringInput("Enter the movie genre with a comma separating them. \nGenre: ")
                        .split(",");
                movie.setGenre(genre);
                break;
            case 7: // Director
                director = Utils.getStringInput("Enter the movie director: ");
                movie.setDirector(director);
                break;

            case 8: // cast
                cast = Utils.getStringInput("Enter the cast names with a comma separating the cast names. \nCast names: ")
                        .split(",");
                movie.setCast(cast);
                break;
            default:
                break;
        }


    }

    public void deleteMovieListing() {

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
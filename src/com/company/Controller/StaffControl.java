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

import static com.company.Utils.Utils.createLocalDateTime;

public class StaffControl {
    //    MovieGoerController movieController = new MovieGoerController();
//    MovieGoerUI movieGoerUI = new MovieGoerUI();
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
        LocalDateTime showTill = null;
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
        movieClass = Utils.getUserChoice(1, i - 1) - 1;
        i = 1;
        System.out.println("Select movie age restriction from the following options:");
        for (String movieagetype : movieFunctions.getAgeTypes()) {
            System.out.println(i + ". " + movieagetype);
            i++;
        }
        ageType = Utils.getUserChoice(1, i - 1) - 1;

        i = 1;
        System.out.println("Select movie status type from the following options:");
        for (String statustype : movieFunctions.getStatusTypes()) {
            System.out.println(i + ". " + statustype);
            i++;
        }
        statusType = Utils.getUserChoice(1, i - 1) - 1;
        // DATE Showtill
        int year;
        int month;
        int day;
        int hour;
        int minute;

        System.out.println("Insert date and time of show time of the movie");

        day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
        month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
        year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
        hour = Utils.getDateIntInput("Insert the hour (in number)", 0, 24);
        minute = Utils.getDateIntInput("Insert the minute (in number)", 0, 59);

        showTill = Utils.createLocalDateTime(year, month, day, hour, minute);

        // newly created movie object
        Movie movie = new Movie( title, synopsis , director , cast , genre , showTill , duration , movieClass, ageType, statusType );

        // read the movielist from text file
        ArrayList<Movie> movieList;
        try{
            movieList = (ArrayList<Movie>)Utils.readObject("movie.txt");
        }catch (ClassNotFoundException e){
            System.out.println("File not found. please try again.");
            return;
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }
        // add movie to movie list
        movieList.add(movie);
        // write movielist to text file
        try{
            Utils.writeObject("movie.txt", movieList);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }

        System.out.println("Movie successfully created!");
        // Todo print out the movie attribues?
    }

    public void editMovieListing() {
        String synopsis;
        String director;
        String[] cast;
        String[] genre;
        int duration;
        int movieClass; // 3D, blockbuster etc..
        int statusType; //now showing, etc..
        int ageType; //age type {"G", "PG", "PG13", "NC16", "M18", "R21"}
        LocalDateTime showTill = null;
        int i = 0;

        int k = 1;

        ArrayList<Movie> movieList;
        try{
            movieList = (ArrayList<Movie>)Utils.readObject("movie.txt");
        }catch (ClassNotFoundException e){
            System.out.println("File not found. please try again.");
            return;
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }


        Utils.displayHeader("Movie List");
        for (Movie m: movieList)
        {
            System.out.println(k + ": " + m.getTitle() + ", movie status: " + m.getStatusType());
            k++;
        }
        System.out.println("Select movie to edit: ");
        int movieIndex = Utils.getUserChoice(0, movieList.size()) - 1;

        Movie movie = movieList.get(movieIndex);

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
        System.out.print("9) Show Till: " + movie.getShowTill());
        System.out.println("Select detail to edit: ");
        switch (Utils.getUserChoice(1, 9)) {
            case 1: // duration
                System.out.println("Enter the movie duration in minutes: ");
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
                statusType = Utils.getUserChoice(1, i - 1) - 1;
                movie.setStatusType( movie.getStatusTypes()[statusType]);
                break;
            case 4: // movie type : 3D
                i = 1;
                System.out.println("Select movie type from the following options:");
                for (String movietype : movieFunctions.getMovieClasses()) {
                    System.out.println(i + ". " + movietype);
                    i++;
                }
                movieClass = Utils.getUserChoice(1, i - 1) - 1;
                movie.setMovieClass(movie.getMovieClasses()[movieClass]);
                break;
            case 5: // age type PG13
                i = 1;
                System.out.println("Select movie age restriction from the following options:");
                for (String movieagetype : movieFunctions.getAgeTypes()) {
                    System.out.println(i + ". " + movieagetype);
                    i++;
                }
                ageType = Utils.getUserChoice(1, i - 1) - 1;
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
            case 9: // show till
                int year;
                int month;
                int day;
                int hour;
                int minute;

                System.out.println("Insert date and time of show time of the movie");

                day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
                month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
                year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
                hour = Utils.getDateIntInput("Insert the hour (in number)", 0, 24);
                minute = Utils.getDateIntInput("Insert the minute (in number)", 0, 59);

                showTill = Utils.createLocalDateTime(year, month, day, hour, minute);
                movie.setShowTill(showTill);
                break;
            default:
                break;
        }

        movieList.set(movieIndex, movie);

        try{
            Utils.writeObject("movie.txt", movieList);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }
    }

    public void deleteMovieListing() {
        ArrayList<Movie> movieList;
        try{
            movieList = (ArrayList<Movie>)Utils.readObject("movie.txt");
        }catch (ClassNotFoundException e){
            System.out.println("File not found. please try again.");
            return;
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }

        int k = 1;
        Utils.displayHeader("Movie List");
        for (Movie m: movieList)
        {
            System.out.println(k + ": " + m.getTitle() + ", movie status: " + m.getStatusType());
            k++;
        }
        System.out.println("Select movie to delete: ");
        int movieIndex = Utils.getUserChoice(1, movieList.size()) - 1;
        movieList.remove(movieIndex);
        try{
            Utils.writeObject("movie.txt", movieList);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return;
        }
        System.out.println("Movie deleted successfully!");
    }
}
package com.company.View;

import com.company.Controller.HandleShowTimeMgr;
import com.company.Controller.MovieGoerController;
import com.company.Controller.StaffControl;
import com.company.Entity.Cinema;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.UserInputOutput;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class to configure showtime in a cinema in a cineplex.
 * The class is a part of StaffUI.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandleShowTimeUI {
    /**
     * This is the UI to add new showtime to a cinema in a cineplex
     */
    public static void addShowTimeUI(){
        UserInputOutput.displayHeader("Add Show Time");
        Scanner sc = new Scanner(System.in);

        //Selecting the cineplex
        ArrayList<Cineplex> CineplexArray = HandleShowTimeMgr.getCineplexArray();
        int cineplexChoice;
        try{
            if(CineplexArray.size() == 0){
                System.out.println("Cineplex does not exist!");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("Cineplex does not exist!");
            return;
        }
        System.out.println("Select the cineplex: ");
        for(int i = 0; i<CineplexArray.size(); i++){
            System.out.println(i+1+". "+CineplexArray.get(i).getCineplexName());
        }
        cineplexChoice = UserInputOutput.getUserChoice(1, CineplexArray.size()) - 1;
        ArrayList<Cinema> CinemaArray = CineplexArray.get(cineplexChoice).getCinemas();

        //Selecting the cinema
        int cinemaChoice;
        try{
            if(CinemaArray.size() == 0){
                System.out.println("Cinema does not exist!");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("Cinema does not exist!");
            return;
        }

        System.out.println("Select the cinema: ");
        for(int i = 0; i<CinemaArray.size(); i++){
            System.out.println(i+1+". "+ CinemaArray.get(i).getCID() + "(" +CinemaArray.get(i).getCinemaType()+")");
        }
        cinemaChoice = UserInputOutput.getUserChoice(1, CinemaArray.size()) - 1;

        //selecting movie
        int movieChoice;
        ArrayList<Movie> MovieArray = HandleShowTimeMgr.getMovieArray();
        try{
            if(MovieArray.size() == 0){
                System.out.println("Movie does not exist!");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("Movie does not exist!");
            return;
        }
        System.out.println("Select the movie: ");
        MovieGoerController  moviecontrol= new MovieGoerController();
        MovieArray =  moviecontrol.getNowPreviewShowingMovieList();
        //Check if the movie is at the end of showing
//        for(int i = 0; i<MovieArray.size(); i++){
//            System.out.println(MovieArray.get(i).getTitle()+" tes "+MovieArray.get(i).getStatusType());
//            if(MovieArray.get(i).getStatusType().equals("End of showing")){
//                MovieArray.remove(MovieArray.get(i));
//            }
//        }

        for(int i = 0; i<MovieArray.size(); i++){
            System.out.println(i+1+". "+ MovieArray.get(i).getTitle());
        }
        movieChoice = UserInputOutput.getUserChoice(1, MovieArray.size()) - 1;

        int year;
        int month;
        int day;
        int hour;
        int minute;
        boolean successful=false;
        boolean tryAgain = true;

        while(!successful && tryAgain){
            System.out.println("Insert date and time of show time of the movie");

            day = UserInputOutput.getDateIntInput("Insert the day (in number)", 1, 31);
            month = UserInputOutput.getDateIntInput("Insert the month (in number)", 1, 12);
            year = UserInputOutput.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
            hour = UserInputOutput.getDateIntInput("Insert the hour (in number)", 0, 24);
            minute = UserInputOutput.getDateIntInput("Insert the minute (in number)", 0, 59);

            successful = HandleShowTimeMgr.addShowTimeMgr(CineplexArray, MovieArray, cineplexChoice, cinemaChoice, movieChoice, year, month, day, hour, minute);
            if(successful){
                System.out.println("Showtime was created.");
            }else{
                System.out.println("Showtime was not created. Please try again");
                tryAgain = UserInputOutput.retry("Retry");
            }
        }
    }

    /**
     * This is the UI to delete showtime in a cinema in a cineplex
     */
    public static void deleteShowTimeUI(){
        UserInputOutput.displayHeader("Delete Show Time");
        Scanner sc = new Scanner(System.in);
        //Selecting the cineplex
        ArrayList<Cineplex> CineplexArray = HandleShowTimeMgr.getCineplexArray();

        int cineplexChoice;
        try{
            if(CineplexArray.size() == 0){
                System.out.println("Cineplex does not exist!");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("Cineplex does not exist!");
            return;
        }
        System.out.println("Select the cineplex: ");
        for(int i = 0; i<CineplexArray.size(); i++){
            System.out.println(i+1+". "+CineplexArray.get(i).getCineplexName());
        }
        cineplexChoice = UserInputOutput.getUserChoice(1, CineplexArray.size()) - 1;
        ArrayList<Cinema> CinemaArray = CineplexArray.get(cineplexChoice).getCinemas();

        //Selecting the cinema
        int cinemaChoice;
        try{
            if(CinemaArray.size() == 0){
                System.out.println("Cinema does not exist!");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("Cinema does not exist!");
            return;
        }

        System.out.println("Select the cinema: ");
        for(int i = 0; i<CinemaArray.size(); i++){
            System.out.println(i+1+". "+ CinemaArray.get(i).getCID());
        }
        cinemaChoice = UserInputOutput.getUserChoice(1, CinemaArray.size()) - 1;
        ArrayList<ShowTime> ShowTimeArray = CinemaArray.get(cinemaChoice).getShowTime();

        //selecting showtime
        int showTimeChoice;
        try{
            if(ShowTimeArray.size() == 0){
                System.out.println("No Show Time in this cinema");
                return;
            }
        }catch (java.lang.NullPointerException e){
            System.out.println("No Show Time in this cinema");
            return;
        }

        System.out.println("Select the Show Time you want to delete: ");
        for(int i = 0; i<ShowTimeArray.size(); i++){
            System.out.println(i+1+". "+ ShowTimeArray.get(i).getMovie().getTitle()+": "+ ShowTimeArray.get(i).getDateTime().getYear() + "-"+ ShowTimeArray.get(i).getDateTime().getMonth() + "-" + ShowTimeArray.get(i).getDateTime().getDayOfMonth()+ " " + ShowTimeArray.get(i).getDateTime().getHour()+":"+ShowTimeArray.get(i).getDateTime().getMinute());
        }
        showTimeChoice = UserInputOutput.getUserChoice(1, CinemaArray.size()) - 1;

        boolean successful=false;
        boolean tryAgain = true;

        while(!successful && tryAgain){
            successful = HandleShowTimeMgr.deleteShowTimeMgr(CineplexArray, cineplexChoice, cinemaChoice, showTimeChoice);
            if(successful){
                System.out.println("Showtime was deleted.");
            }else{
                System.out.println("Showtime was not deleted. Please try again");
                tryAgain = UserInputOutput.retry("Retry");
            }
        }
    }
}

package com.company.View;

import com.company.Controller.HandleShowTimeMgr;
import com.company.Controller.StaffControl;
import com.company.Entity.Cinema;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimeUI {
    //SHOW TIME AREA =======================================

    public static void addShowTimeUI(){
        Utils.displayHeader("Add Show Time");
        Scanner sc = new Scanner(System.in);
        //Selecting the cineplex
        ArrayList<Cineplex> CineplexArray;
        try {
            CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("File is missing. Please try again");
            return;
        }

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
        cineplexChoice = Utils.getUserChoice(1, CineplexArray.size()) - 1;
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
        cinemaChoice = Utils.getUserChoice(1, CinemaArray.size()) - 1;

        //selecting movie
        int movieChoice;
        ArrayList<Movie> MovieArray;
        try {
            MovieArray = (ArrayList<Movie>) Utils.readObject("movie.txt");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Movie file is missing. Please try again");
            return;
        }
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
        for(int i = 0; i<MovieArray.size(); i++){
            System.out.println(i+1+". "+ MovieArray.get(i).getTitle());
        }
        movieChoice = Utils.getUserChoice(1, CinemaArray.size()) - 1;

        int year;
        int month;
        int day;
        int hour;
        int minute;
        boolean successful=false;
        boolean tryAgain = true;

        while(!successful && tryAgain){
            System.out.println("Insert date and time of show time of the movie");

            day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
            month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
            year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
            hour = Utils.getDateIntInput("Insert the hour (in number)", 0, 24);
            minute = Utils.getDateIntInput("Insert the minute (in number)", 0, 59);

            successful = HandleShowTimeMgr.addShowTimeMgr(CineplexArray, MovieArray, cineplexChoice, cinemaChoice, movieChoice, year, month, day, hour, minute);
            if(successful){
                System.out.println("Showtime was created.");
            }else{
                System.out.println("Showtime was not created. Please try again");
                tryAgain = Utils.retry("Retry");
            }
        }
    }

    public static void deleteShowTimeUI(){
        Utils.displayHeader("Delete Show Time");
        Scanner sc = new Scanner(System.in);
        //Selecting the cineplex
        ArrayList<Cineplex> CineplexArray;
        try {
            CineplexArray = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("File is missing. Please try again");
            return;
        }

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
        cineplexChoice = Utils.getUserChoice(1, CineplexArray.size()) - 1;
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
        cinemaChoice = Utils.getUserChoice(1, CinemaArray.size()) - 1;
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
            System.out.println(i+1+". "+ ShowTimeArray.get(i).getMovie().getTitle()+": "+ShowTimeArray.get(i).getDateTime().getHour()+":"+ShowTimeArray.get(i).getDateTime().getMinute());
        }
        showTimeChoice = Utils.getUserChoice(1, CinemaArray.size()) - 1;

        boolean successful=false;
        boolean tryAgain = true;

        while(!successful && tryAgain){
            successful = HandleShowTimeMgr.deleteShowTimeMgr(CineplexArray, cineplexChoice, cinemaChoice, showTimeChoice);
            if(successful){
                System.out.println("Showtime was deleted.");
            }else{
                System.out.println("Showtime was not deleted. Please try again");
                tryAgain = Utils.retry("Retry");
            }
        }
    }

    //SHOW TIME AREA =====================================
}

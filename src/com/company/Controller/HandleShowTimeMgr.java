package com.company.Controller;

import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HandleShowTimeMgr {
    public static boolean addShowTimeMgr(ArrayList<Cineplex> CineplexArray, ArrayList<Movie> MovieArray, int cineplexChoice, int cinemaChoice, int movieChoice, int year, int month,
                                         int day, int hour, int minute) {
        // cinema, film, datetime needed
        LocalDateTime dateTime;
        ShowTime newShowTime;

        newShowTime = new ShowTime(LocalDateTime.of(year, month, day, hour, minute), MovieArray.get(movieChoice));

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
}

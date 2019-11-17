package com.company.Controller;

import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.FileIO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the controller of the configuration of ShowTime in a cinema.
 * The class is a part of StaffControl.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandleShowTimeMgr {
    /**
     * This method adds a new ShowTime to a cinema, based on the date and time input by staff
     * @param CineplexArray Array of Cineplex. One cineplex is chosen to retrieve the wanted cinema
     * @param MovieArray Array of Movie. One movie is chosen to be used in the new ShowTime
     * @param cineplexChoice The choice of the staff. It is the index of the chosen Cineplex
     * @param cinemaChoice The choice of the staff. It is the index of the chosen Cinema
     * @param movieChoice The choice of the staff. It is the index of the chosen Movie
     * @param year The year of the new ShowTime
     * @param month The month of the new ShowTime
     * @param day The day of the new ShowTime
     * @param hour The hour of the new ShowTime
     * @param minute The minute of the new ShowTime
     * @return true if the addition is successful
     */
    public static boolean addShowTimeMgr(ArrayList<Cineplex> CineplexArray, ArrayList<Movie> MovieArray, int cineplexChoice, int cinemaChoice, int movieChoice, int year, int month,
                                         int day, int hour, int minute) {
        // cinema, film, datetime needed
        LocalDateTime dateTime;
        ShowTime newShowTime;
        LocalDateTime chosenDate = LocalDateTime.of(year, month, day, hour, minute);
        Movie chosenMovie = MovieArray.get(movieChoice);
        newShowTime = new ShowTime(chosenDate, chosenMovie);

        //check if datetime has passed movie showtill date
        if(chosenMovie.getShowTill()!=null && LocalDate.of(year,month,day).isAfter(chosenMovie.getShowTill())){
            System.out.println("The show time at the inserted time has passed the movie's showtill date");
            return false;
        }
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
            FileIO.writeObject("cineplex.txt", CineplexArray);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return false;
        }

        return true;
    }

    /**
     * This method adds a new ShowTime to a cinema, based on the date and time input by staff
     * @param CineplexArray Array of Cineplex. One cineplex is chosen to retrieve the wanted cinema
     * @param cineplexChoice The choice of the staff. It is the index of the chosen Cineplex
     * @param cinemaChoice The choice of the staff. It is the index of the chosen Cinema
     * @param showTimeChoice The choice of the staff. It is the index of the chosen ShowTime
     * @return true if the deletion is successful
     */
    public static boolean deleteShowTimeMgr(ArrayList<Cineplex> CineplexArray, int cineplexChoice, int cinemaChoice, int showTimeChoice) {
        ArrayList<ShowTime> ShowTimeArray = CineplexArray.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowTime();
        ShowTimeArray.remove(ShowTimeArray.get(showTimeChoice));
        //Inserting new showtime to database
        try{
            FileIO.writeObject("cineplex.txt", CineplexArray);
        }catch (IOException e){
            System.out.println("File not found. please try again.");
            return false;
        }
        return true;
    }

    public static ArrayList<Cineplex> getCineplexArray(){
        ArrayList<Cineplex> CineplexArray = null;
        try{
            CineplexArray = (ArrayList<Cineplex>) FileIO.readObject("cineplex.txt");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println("File is missing. Please try again");
        }
        return CineplexArray;
    }

    public static ArrayList<Movie> getMovieArray(){
        ArrayList<Movie> MovieArray = null;
        try{
            MovieArray = (ArrayList<Movie>) FileIO.readObject("movie.txt");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println("File is missing. Please try again");
        }
        return MovieArray;
    }
}

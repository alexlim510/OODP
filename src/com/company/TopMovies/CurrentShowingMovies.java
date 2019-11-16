package com.company.TopMovies;

import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Get Showing Movies from Movie list
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class CurrentShowingMovies {
    /**
     * Array list for current showing movie
     */
    ArrayList<Movie> currentShowingMovieList = new ArrayList<Movie>();

    /**
     * Current Showing Movies constructor
     */
    CurrentShowingMovies(){
        ArrayList<Movie> MovieArray = new ArrayList<Movie>();
        try {
            MovieArray = (ArrayList<Movie>) Utils.getObjectInputStream("movie.txt").readObject();
            for (Movie m: MovieArray) {
                if (m.getStatusType().equals("Now showing")||m.getStatusType().equals("Preview")) {
                    currentShowingMovieList.add(m);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    };

    /**
     * return currentShowingMovieList
     * @return currentShowingMovieList
     */
    public ArrayList<Movie> getCurrentShowingMovieList() {
        return currentShowingMovieList;
    }
}

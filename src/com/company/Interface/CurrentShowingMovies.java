package com.company.Interface;

import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class CurrentShowingMovies {

    ArrayList<Movie> currentShowingMovieList = new ArrayList<Movie>();

    CurrentShowingMovies(){
        ArrayList<Movie> MovieArray = new ArrayList<Movie>();
        try {
            MovieArray = (ArrayList<Movie>) Utils.getObjectInputStream("movie.txt").readObject();
            for (Movie m: MovieArray) {
                if (m.getStatusType().equals("Now showing")) {
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

    public ArrayList<Movie> getCurrentShowingMovieList() {
        return currentShowingMovieList;
    }
}

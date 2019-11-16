package com.company.TopMovies;

import com.company.Entity.Movie;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class TopRatingMovies extends Top5CurrentMovies{
    /**
     *
     * @return
     */
    @Override
    public ArrayList<Movie> getCurrentShowingMovieList() {

        Collections.sort(super.currentShowingMovieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                float change1 = o1.getOverallReviewRating();
                float change2 = o2.getOverallReviewRating();
                if (change1 > change2) return -1;
                if (change1 < change2) return 1;
                return 0;
            }
        });
        return currentShowingMovieList;
    }

    @Override
    public void printTop5Movies() {
        Utils.displayHeader("Top 5 Movie List based on customers rating");
        int i = 1;
        for (Movie m: getCurrentShowingMovieList()){
            System.out.println("Top "+ i + ": " + m.getTitle() + "(" + m.getOverallReviewRating() + ")");
            i++;
            if(i == 6){
                break;
            }
        }
    }
}

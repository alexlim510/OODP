package com.company.TopMovies;


/**
 * To list top5 movies by sales or overall rating
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class TopMovieFactory {
    /**
     * This is the method for the TopMovieFactory which takes in a string and creates a corresponding Top5CurrentMovies subClass.
     * @param newTop5MovieType The string used to determing the subClass to create.
     * @return Top5CurrentMovies's sub class which is either TopTicketMovies or TopRatingMovies.
     */
    public Top5CurrentMovies makeTop5Movie(String newTop5MovieType) {

        if(newTop5MovieType.equals("ticket")){
            return new TopTicketMovies();
        }
        else if(newTop5MovieType.equals("rating")){
            return new TopRatingMovies();
        } else return null;
    }
}

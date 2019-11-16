package com.company.TopMovies;


/**
 * To list top5 movies by sales or overall rating
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class TopMovieFactory {
    public Top5CurrentMovies makeTop5Movie(String newTop5MovieType) {

        if(newTop5MovieType.equals("ticket")){
            return new TopTicketMovies();
        }
        else if(newTop5MovieType.equals("rating")){
            return new TopRatingMovies();
        } else return null;
    }
}

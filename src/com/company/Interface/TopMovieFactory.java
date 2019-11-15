package com.company.Interface;

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

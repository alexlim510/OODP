package com.company.View;
import com.company.Controller.MovieGoerController;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.Review;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;




public class MovieGoerUI {
	
	MovieGoerController movieController = new MovieGoerController();
	
	public void getMovieDetailsView(Movie movie) {
		int n=0;
		
		Utils.displayHeader("Movie Details");
		System.out.println("The details of "+ movie.getTitle() + " :");
		System.out.println("1) Duration: "+ movie.getDuration());
		System.out.println("2) Synopsis: "+ movie.getSynopsis());
		System.out.println("3) Status: "+ movie.getStatusType());
		System.out.println("3) Status Type: "+ movie.getStatusType());
		System.out.println("4) Showing Till: "+ movie.getShowTill());
		System.out.println("5) Type: "+ movie.getMovieClass());
		System.out.println("6) Rating: "+ movie.getMovieRating());
		System.out.println("5) Movie Type: "+ movie.getMovieClass());
		System.out.println("6) Movie Rating: "+ movie.getMovieRating());
		System.out.println("7) Review Rating: "+ movie.getOverallReviewRating());
		String[] movieGenre = movie.getGenre();
		System.out.print("8) Genre: ");
		for (int j = 0; j < movieGenre.length; j++) {
			if (j != movieGenre.length - 1)
				System.out.print(movieGenre[j] + ", ");
			else
				System.out.print(movieGenre[j] + ".\n");}
		System.out.println("9) Director: "+ movie.getDirector());
		System.out.print("10) Cast: ");
		String[] movieCast = movie.getCast();
		for (int i = 0; i < movieCast.length; i++) {
			if (i != movieCast.length - 1)
				System.out.print(movieCast[i] + ", ");
			else
				System.out.print(movieCast[i] + ".\n");	}
		
		System.out.println("11) Reviews: ");
		ArrayList<Review> movieReviews = movie.getMovieReview();
		for (Review r : movieReviews) {
			System.out.println("   (" +(n+1)+ ") " + r.getContent());
					n++;}
		
		}
	public void getMovieListingView(){
		int i=1;
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = movieController.getAllMovieList();
		Utils.displayHeader("Movie List");
		for (Movie m: movieList) //MovieLists is initiated in main
		{
			System.out.println(i + ": " + m.getTitle() + ", movie status: " + m.getStatusType());
			i++;
		}
	getMovieDetailsView(movieList.get(Utils.getUserChoice(1, movieList.size())-1));
	}
	public void getHomeView() {
        System.out.println("Select What to do: ");
        System.out.println("1) Book Ticket");
        System.out.println("2) Movie Listing");
        System.out.println("3) Make a review");



        while(true) {
            Scanner sc = new Scanner(System.in);
            try {
                int choice = sc.nextInt();
                switch(choice) {
                case 1:
                    movieController.seatSelection();
                    break;
                case 2:
                    getMovieListingView();
                    break;
                case 3:
                    getMakeAReviewView();
                    break;
                default:
                    System.out.println("Please input 1,2 or 3.");
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
            }
        }
    }
	
	public void getCineplexView() {
		int i=1;
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		cineplexList = movieController.getCineplexList();
		Utils.displayHeader("Cineplex List");	
		for (Cineplex c: cineplexList)//CineplexList is initiated in main
		{
			System.out.println(i + ") " + c.getCineplexName());
			i++;
		}
	}
	
	public void getMakeAReviewView() {
		System.out.println("Please choose a movie to make review: ");
		int i=1;
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = movieController.getNowShowingMovieList();
		Utils.displayHeader("Movie List");
		for (Movie m: movieList) //MovieLists is initiated in main
		{
			System.out.println(i + ": " + m.getTitle());
			i++;
		}
	
		MakeReviewUI.MakeReview(movieList.get(Utils.getUserChoice(1, movieList.size())-1));
	}
}


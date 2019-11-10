package com.company.Controller;
import com.company.Entity.Cinema;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;
import com.company.View.SeatUI;

import java.io.IOException;
import java.util.ArrayList;



public class MovieGoerController extends Utils {
	 
 
   public void MainMenu(Movie[] MovieArray, Cineplex[] CineplexList)
   {
      MovieGoerUI UI = new MovieGoerUI();
    
      UI.getMovieListingView();
      UI.getHomeView();
   }
   public ArrayList<Movie> getNowShowingMovieList(){
	   ArrayList<Movie> nowShowing = new ArrayList<Movie>();
	   ArrayList<Movie> MovieArray = new ArrayList<Movie>();
	   try {
		   int i = 0;
		   MovieArray = (ArrayList<Movie>)Utils.getObjectInputStream("movie.txt").readObject();
		   for (Movie m: MovieArray) {
			   if (m.getStatusType().equals("Now showing")) {
				   nowShowing.add(m);
			   }
		   }
		  
	   }
       catch (IOException e) {
    	   e.printStackTrace();
       } catch(ClassNotFoundException e) {
    	   e.printStackTrace();
       }
	   return nowShowing;
   }
   public ArrayList<Movie> getAllMovieList(){
	   ArrayList<Movie> allMovie = null;
	   try {
		   int i = 0;
		   allMovie = (ArrayList<Movie>)Utils.getObjectInputStream("movie.txt").readObject();		  
	   }
       catch (IOException e) {
    	   e.printStackTrace();
       } catch(ClassNotFoundException e) {
    	   e.printStackTrace();
       }
	   return allMovie;
   }
   public ArrayList<Cineplex> getCineplexList(){
	   ArrayList<Cineplex> allCineplex = new ArrayList<Cineplex>();
	   try {
		   allCineplex = (ArrayList<Cineplex>)Utils.readObject("cineplex.txt");
	   }
       catch (IOException e) {
    	   e.printStackTrace();
       } catch(ClassNotFoundException e) {
    	   e.printStackTrace();
       }
	   return allCineplex;
   }
   
   public ArrayList<Movie> getMovieList(Cineplex cineplex){	   
	   ArrayList<Movie> movieList = new ArrayList<Movie>();
	   
	   ArrayList<Cinema>  cinemaList = cineplex.getCinemas();		   
	   for (Cinema cinema: cinemaList) {
		   ArrayList<ShowTime> showTimes = cinema.getShowTime();
		   for(ShowTime st: showTimes) {
			   Movie movie = st.getMovie();
			   if(movie.getStatusType().equals("Now Showing")) {		
				   movieList.add(movie);	   
			   }
		   }
	   }
	   return movieList;   		  	   
   }
   
   public ArrayList<String> getMovieTitles(ArrayList<Movie> movies){
	   ArrayList<String> movieList = new ArrayList<String>();
	   
	   for(Movie movie: movies) {
		   if(!movieList.contains(movie.getTitle())) {
			   String title = movie.getTitle();
			   if(movie.getMovieClass()!=null) {
				   movieList.add(movie.getTitle()+"("+movie.getMovieClass()+")");
			   }
			   else {
				   movieList.add(movie.getTitle());					   
			   }				   
		   }
	   }
	   
	   return movieList;
   }
   
   public ArrayList<ShowTime> getShowTimes(Cineplex cineplex, Movie movie){
	   ArrayList<Movie> movieList = new ArrayList<Movie>();
	   ArrayList<ShowTime> showTimes = new ArrayList<>();
	   ArrayList<Cinema>  cinemaList = cineplex.getCinemas();
	   for (Cinema cinema: cinemaList) {
		   showTimes = cinema.getShowTime();
		   for(ShowTime st: showTimes) {
			   Movie m = st.getMovie();
			   if(m.getStatusType().equals("Now Showing")) {		
				   movieList.add(m);	   
			   }
		   }
	   }
//	   return movieList;
	   return showTimes;
   }
   
   public void seatSelection() {
	   SeatUI sui = new SeatUI();
	   int choice;
	   	 
	   ArrayList<Cineplex> cineplexes = getCineplexList();
	   choice = sui.getCineplexSelectionView(cineplexes);
	   Cineplex cineplex = cineplexes.get(choice);
	   
	   ArrayList<Movie> movies = getMovieList(cineplex);
	   ArrayList<String> movieTitles = getMovieTitles(movies);
	   Movie movie = movies.get(sui.getMovieSelectionView(movieTitles));
   }  
}
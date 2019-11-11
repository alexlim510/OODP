package com.company.Controller;
import com.company.Entity.Cinema;
import com.company.Entity.Cineplex;
import com.company.Entity.Movie;
import com.company.Entity.ShowTime;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;
import com.company.View.SeatUI;

import java.time.LocalDateTime;
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
			   if(movie.getStatusType().equals("Now showing")) {
				   movieList.add(movie);	   
			   }
		   }
	   }
	   return movieList;   		  	   
   }
   
   public ArrayList<String> getMovieTitles(ArrayList<Movie> movies){
	   ArrayList<String> movieList = new ArrayList<String>();
	   String temp;
	   for(Movie movie: movies) {
		   if(!movieList.contains(movie.getTitle())) {
			   String title = movie.getTitle();
			   if(movie.getMovieClass()!=null) {
				   temp = movie.getTitle()+"("+movie.getMovieClass()+")";
				   if(!movieList.contains(temp)){
				   	movieList.add(temp);
				   }
			   }
			   else {
			   	temp=movie.getTitle();
			   	if(!movieList.contains(temp)){
			   		movieList.add(temp);
				}
			   }				   
		   }
	   }
	   
	   return movieList;
   }

	public ArrayList<String> getCinemaTypes(Cineplex cineplex, Movie movie){
		ArrayList<ShowTime> showTimes = new ArrayList<>();
		ArrayList<Cinema>  cinemaList = cineplex.getCinemas();
		ArrayList<String> cinemaTypes = new ArrayList<>();
		String cinemaType;
		for (Cinema cinema: cinemaList) {
			showTimes = cinema.getShowTime();
			for(ShowTime st: showTimes) {
				Movie m = st.getMovie();
				if(m.getTitle().equals(movie.getTitle()) && st.getDateTime().compareTo(LocalDateTime.now())>0) {
					cinemaType = cinema.getCinemaType();
					if(cinemaType!=null && !cinemaTypes.contains(cinemaType)){
						cinemaTypes.add(cinemaType);
						break;
					}
				}
			}
		}
		return cinemaTypes;
	}

   public ArrayList<ShowTime> getShowTimes(Cineplex cineplex, Movie movie, String cinemaType){
	   ArrayList<ShowTime> showTimes = new ArrayList<>();
	   ArrayList<ShowTime> selectedShowTimes = new ArrayList<>();
	   ArrayList<Cinema>  cinemaList = cineplex.getCinemas(cinemaType);
	   for (Cinema cinema: cinemaList) {
		   showTimes = cinema.getShowTime();
		   for(ShowTime st: showTimes) {
			   Movie m = st.getMovie();
			   if(m.getTitle().equals(movie.getTitle()) && st.getDateTime().compareTo(LocalDateTime.now())>0) {
				   selectedShowTimes.add(st);
			   }
		   }
	   }
	   return selectedShowTimes;
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

	   ArrayList<String> cinemaTypes = getCinemaTypes(cineplex,movie);
	   String cinemaType = cinemaTypes.get(sui.getCinemaTypeSelectionView(cinemaTypes));

	   ArrayList<ShowTime> showtimes = getShowTimes(cineplex,movie,cinemaType);
	   ShowTime showtime = showtimes.get(sui.getShowTimeSelectionView(showtimes));

	   ArrayList<String> seats = sui.getSeatSelectionMenu(showtime);
   }  
}
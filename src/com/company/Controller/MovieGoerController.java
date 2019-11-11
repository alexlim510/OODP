package com.company.Controller;
import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;
import com.company.View.SeatUI;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
	   int i = 0;
	   allMovie = (ArrayList<Movie>)Utils.readObject("movie.txt");
	   return allMovie;
   }
   public ArrayList<Cineplex> getCineplexList(){
	   ArrayList<Cineplex> allCineplex = new ArrayList<Cineplex>();
	   allCineplex = (ArrayList<Cineplex>)Utils.readObject("cineplex.txt");
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

	public ArrayList<Cinema> getCinemas(Cineplex cineplex, Movie movie){
		ArrayList<ShowTime> showTimes = new ArrayList<>();
		ArrayList<Cinema>  cinemaList = cineplex.getCinemas();
        ArrayList<Cinema>  cinemaResult = new ArrayList<>();
		ArrayList<String> cinemaTypes = new ArrayList<>();
		String cinemaType;
		for (Cinema cinema: cinemaList) {
			showTimes = cinema.getShowTime();
			for(ShowTime st: showTimes) {
				Movie m = st.getMovie();
				if(m.getTitle().equals(movie.getTitle()) && st.getDateTime().compareTo(LocalDateTime.now())>0) {
                    cinemaResult.add(cinema);
				}
			}
		}
		return cinemaResult;
	}

    public ArrayList<String> getCinemaTypes(ArrayList<Cinema> cinemas){
        ArrayList<String> cinemaTypes = new ArrayList<>();
        String cinemaType;
        for (Cinema cinema: cinemas) {
            cinemaType = cinema.getCinemaType();
            if(cinemaType!=null && !cinemaTypes.contains(cinemaType)){
                cinemaTypes.add(cinemaType);
                break;
            }
        }
        return cinemaTypes;
    }

    public Cinema getCinema(ArrayList<Cinema> cinemas, ShowTime st){
        for (Cinema cinema: cinemas) {
            for(ShowTime s: cinema.getShowTime()){
                if(st.isEqual(s)){
                    return cinema;
                }
            }
        }
        return null;
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

   public float calculateTicketBasePrice(Movie movie, String cinemaType, ShowTime showtime){
	   ArrayList<String> categories = new ArrayList<>();
	   String movieClass = movie.getMovieClass();
	   if(movieClass!= null) categories.add(movieClass);
       categories.add(cinemaType);
	   int dayOfWeek = showtime.getDateTime().getDayOfWeek().ordinal();
	   Price p = new Price();
	   if(p.isWeekend(dayOfWeek)) categories.add("Weekend");
	   if(p.isHoliday(showtime.getDateTime())) categories.add("Holiday");
	   if(categories.size()!=0){
	       return p.getPrice(categories);
       }
	   else return 0;
   }

   public float calculateTotalPrice(HashMap<String,String> chosenSeats,float basePrice){
       float totalPrice=0;
       Price p = new Price();
       if(chosenSeats.size()!=0){
           for(String age: chosenSeats.values()){
               totalPrice = basePrice + totalPrice + p.getPrice(age);
           }
       }
       return totalPrice;
   }

    public float calculateTicketPrice(String age, float basePrice){
       Price p = new Price();
       float ticketPrice = basePrice  + p.getPrice(age);
       return ticketPrice;
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

	   ArrayList<Cinema> cinemas = getCinemas(cineplex,movie);
	   ArrayList<String> cinemaTypes = getCinemaTypes(cinemas);
	   String cinemaType = cinemaTypes.get(sui.getCinemaTypeSelectionView(cinemaTypes));

	   ArrayList<ShowTime> showtimes = getShowTimes(cineplex,movie,cinemaType);
	   ShowTime showtime = showtimes.get(sui.getShowTimeSelectionView(showtimes));
       Cinema cinema = getCinema(cinemas,showtime);
       float basePrice = calculateTicketBasePrice(movie,cinemaType,showtime);

       HashMap<String,String> chosenSeats = sui.getSeatSelectionMenu(showtime,basePrice);
       sui.getTicketView(chosenSeats,cinema,showtime);
       occupySeats(cineplex,cinema,showtime,chosenSeats);

   }

   public void occupySeats(Cineplex cineplex, Cinema cinema, ShowTime showTime, HashMap<String,String> chosenSeats){
       ArrayList<Cineplex> cineplexes = (ArrayList<Cineplex>)Utils.readObject("cineplex.txt");
       Cineplex chosenCineplex = null;
       for(int i = 0; i<cineplexes.size();i++){
           if(cineplexes.get(i).getCineplexName().equals(cineplex.getCineplexName())){
               chosenCineplex=cineplexes.get(i);
           }
       }
       for(Cinema ci: chosenCineplex.getCinemas()){
           if(ci.getCID().equals(cinema.getCID())){
               for(ShowTime st : ci.getShowTime()){
                   if(st.getDateTime().isEqual(showTime.getDateTime())){
                       for(String seat : chosenSeats.keySet()){
                           st.occupySeat(seat);
                       }
                   }
               }
           }
       }
       Utils.writeObject("cineplex.txt", cineplexes);
   }
}

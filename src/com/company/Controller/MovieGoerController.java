package com.company.Controller;
import com.company.Entity.*;
import com.company.Utils.Utils;
import com.company.View.MovieGoerUI;
import com.company.View.SeatUI;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Comparator;


public class MovieGoerController extends Utils {
	/**
	 * MainMenu(Movie[] MovieArray, Cineplex[] CineplexList)
	 * User gets a glance of all movies available ----> getMovieListingView()
	 * Then user will be prompted three choices ----> getHomeView()
	 */
   public void MainMenu(Movie[] MovieArray, Cineplex[] CineplexList)
   {
      MovieGoerUI UI = new MovieGoerUI();
    
      UI.getMovieListingView();
      UI.getHomeView();
   }
   /* getNowShowingMovieList()
   Returns and ArrayList of ALL 'Now showing' Movie objects
    */
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
   /* getAllMovieList()
   Returns ArrayList of ALL Movie Objects residing in movie.txt file
    */
   public ArrayList<Movie> getAllMovieList(){
	   ArrayList<Movie> allMovie = null;
	   int i = 0;
	   try {
		   allMovie = (ArrayList<Movie>)Utils.readObject("movie.txt");
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
	   return allMovie;
   }
   /* getCineplexList()
   Returns ArrayList of ALL Cineplex Objects residing in cineplex.txt file
    */
   public ArrayList<Cineplex> getCineplexList(){
	   ArrayList<Cineplex> allCineplex = new ArrayList<Cineplex>();
	   try {
		   allCineplex = (ArrayList<Cineplex>)Utils.readObject("cineplex.txt");
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
	   return allCineplex;
   }
   /* getMovieList(Cineplex cineplex)
   Iterates through the cinemas in Cineplex
   		Iterates through the showtimes in cinemas
   			if the movie residing in the showtime is now-showing movie
   				add to the movieList
   Returns ArrayList of Movie Objects:
	1. Of a particular Cineplex
	2. Of all cinemas
	3. Of all showTimes with now-showing movies
    */
   public ArrayList<Movie> getMovieList(Cineplex cineplex){	   
	   ArrayList<Movie> movieList = new ArrayList<Movie>();
	   
	   ArrayList<Cinema>  cinemaList = cineplex.getCinemas();		   
	   for (Cinema cinema: cinemaList) {
		   ArrayList<ShowTime> showTimes = cinema.getShowTime();
		   for(ShowTime st: showTimes) {
			   Movie movie = st.getMovie();
			   if(movie.getStatusType().equals("Now showing") || movie.getStatusType().equals("Preview")) {
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
	   choice = sui.getShowTimeSelectionView(showtimes);
	   if(choice==-1){
	   	return;
	   }
	   ShowTime showtime = showtimes.get(choice);
       Cinema cinema = getCinema(cinemas,showtime);
       float basePrice = calculateTicketBasePrice(movie,cinemaType,showtime);

       HashMap<String,String> chosenSeats = sui.getSeatSelectionMenu(showtime,basePrice);
       sui.getTicketView(cineplex, chosenSeats,cinema,showtime);
       occupySeats(cineplex,cinema,showtime,chosenSeats);

       //update user transaction history
	   Customer customer = Utils.getCustomerCookie();
	   ArrayList<Customer> customerData = new ArrayList<>();
	   Transaction transaction = new Transaction(customer,cineplex,cinema,showtime,
			   calculateTotalPrice(chosenSeats,basePrice),chosenSeats);
	   customer.addTransactions(transaction);
	   Utils.storeCustomerCookie(customer);

	   try {
		   customerData = (ArrayList<Customer>)Utils.readObject("customer.txt");
		   for(Customer c: customerData){
		   	if(customer.getEmail().equals(c.getEmail())){
		   		c.addTransactions(transaction);
			}
		   }
		   Utils.writeObject("customer.txt",customerData);
	   } catch (IOException e) {
		   System.out.println("File not found!");
		   return;
	   } catch (ClassNotFoundException e) {
		   System.out.println("File not found!");
		   return;
	   }

	   //update movie Total Sales
	   try {
		   ArrayList<Movie> movieData = (ArrayList<Movie>)Utils.readObject("movie.txt");
		   for(Movie m: movieData){
		   	if(m.getTitle().equals(movie.getTitle())){
		   		m.increaseTotalSales(chosenSeats.size());
		   		break;
			}
		   }
		   Utils.writeObject("movie.txt",movieData);
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
   }

   public HashMap<String,Integer> getAgeCount(HashMap<String,String> chosenSeat){
	   HashMap<String,Integer> ageCount = new HashMap<>();
	   for(String age: chosenSeat.values()){
		   if(ageCount.size()==0){
			   ageCount.put(age,1);
		   }
		   else{
			   if(ageCount.containsKey(age)){
				   ageCount.put(age, ageCount.get(age)+1);
			   }
			   else{
				   ageCount.put(age,1);
			   }
		   }
	   }
	   return ageCount;
   }

   public void occupySeats(Cineplex cineplex, Cinema cinema, ShowTime showTime, HashMap<String,String> chosenSeats){
	   ArrayList<Cineplex> cineplexes = null;
	   try {
		   cineplexes = (ArrayList<Cineplex>) Utils.readObject("cineplex.txt");
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
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
	   try {
		   Utils.writeObject("cineplex.txt", cineplexes);
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   public static Comparator<Movie> MovieTicketSalesComparator = new Comparator<Movie>() {

		public int compare(Movie m1, Movie m2) {
			int movieTicketSales1 = m1.getTotalSales();
			int movieTicketSales2 = m2.getTotalSales();

			//ascending order
			//return StudentName1.compareTo(StudentName2);

			//descending order
			return movieTicketSales2-movieTicketSales1;
		}
   };
	public static Comparator<Movie> overallReviewRatingSalesComparator = new Comparator<Movie>() {

		public int compare(Movie m1, Movie m2) {
			int movieTicketSales1 = m1.getTotalSales();
			int movieTicketSales2 = m2.getTotalSales();

			//ascending order
			//return StudentName1.compareTo(StudentName2);

			//descending order
			return movieTicketSales2-movieTicketSales1;
		}
	};
   public ArrayList<Movie> getTop5MoviesListTicket(){
   	ArrayList<Movie> nowShowingMovies = new ArrayList<Movie>();
   	nowShowingMovies = getNowShowingMovieList();
   	ArrayList<Movie> top5Movies = new ArrayList<Movie>();
   	Collections.sort(nowShowingMovies, MovieTicketSalesComparator);
   	int top5;
   	if(nowShowingMovies.size() > 5) {
		top5 = 5;
	}
   	else{
		top5 = nowShowingMovies.size();
	}
   	for (int i = 0; i < top5; i++){
		top5Movies.add(nowShowingMovies.get(i));
	}
   	return top5Movies;
   }
	public ArrayList<Movie> getTop5MoviesListRating(){
		ArrayList<Movie> nowShowingMovies = new ArrayList<Movie>();
		nowShowingMovies = getNowShowingMovieList();
		ArrayList<Movie> top5Movies = new ArrayList<Movie>();
		Collections.sort(nowShowingMovies, overallReviewRatingSalesComparator);
		int top5;
		if(nowShowingMovies.size() > 5) {
			top5 = 5;
		}
		else{
			top5 = nowShowingMovies.size();
		}
		for (int i = 0; i < top5; i++){
			top5Movies.add(nowShowingMovies.get(i));
		}
		return top5Movies;
	}
}

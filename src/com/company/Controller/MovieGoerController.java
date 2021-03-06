package com.company.Controller;

import com.company.Entity.*;
import com.company.Utils.FileIO;
import com.company.View.ListerInterface;
import com.company.View.MovieGoerUI;
import com.company.View.SeatUI;
import com.company.View.UIDisplay;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serve as the controller for Movie Goer's features
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-12-11
 */
public class MovieGoerController extends FileIO {
	/**
	 * User gets a glance of all movies available ----> getMovieListingView()
	 * Then user will be prompted three choices ----> getHomeView()
	 */
   public void MainMenu(Movie[] MovieArray, Cineplex[] CineplexList)
   {
      MovieGoerUI mui = new MovieGoerUI();
      mui.getMovieListingView();
      UIDisplay uid = new UIDisplay(mui);
      uid.displayHomePage();
   }
   /* getNowShowingMovieList()
   Returns and ArrayList of ALL 'Now showing' Movie objects
    */

	/**
	 * Returns and ArrayList of ALL 'Now showing' Movie objects
	 * @return ArrayList of movie
	 */
   public ArrayList<Movie> getNowPreviewShowingMovieList(){
	   ArrayList<Movie> nowShowing = new ArrayList<Movie>();
	   ArrayList<Movie> MovieArray = new ArrayList<Movie>();
	   try {
		   int i = 0;
		   MovieArray = (ArrayList<Movie>) FileIO.getObjectInputStream("movie.txt").readObject();
		   for (Movie m: MovieArray) {
			   if (m.getStatusType().equals("Now showing") || m.getStatusType().equals("Preview")) {
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

	/**
	 * Returns ArrayList of ALL Movie Objects residing in movie.txt file
	 * @return ArrayList of movies
	 */
	public ArrayList<Movie> getAllMovieList(){
	   ArrayList<Movie> allMovie = null;
	   try {
		   allMovie = (ArrayList<Movie>) FileIO.readObject("movie.txt");
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
	   return allMovie;
   }

	/**
	 * Returns ArrayList of ALL Cineplex Objects residing in cineplex.txt file
	 * @return  ArrayList of cineplex
	 */
   public ArrayList<Cineplex> getCineplexList(){
	   ArrayList<Cineplex> allCineplex = new ArrayList<Cineplex>();
	   try {
		   allCineplex = (ArrayList<Cineplex>) FileIO.readObject("cineplex.txt");
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

	/**
	 * Returns movie shown in selected cineplex
	 * @param cineplex selected cineplex
	 * @return Arraylist of movies
	 */
   public ArrayList<Movie> getMovieList(Cineplex cineplex){	   
	   ArrayList<Movie> movieList = new ArrayList<Movie>();
	   
	   ArrayList<Cinema>  cinemaList = cineplex.getCinemas();		   
	   for (Cinema cinema: cinemaList) {
		   ArrayList<ShowTime> showTimes = cinema.getShowTime();
		   for(ShowTime st: showTimes) {
			   Movie movie = st.getMovie();
			   if(movie.getStatusType().equals("Now showing") || movie.getStatusType().equals("Preview")) {
				   if(movieList.size()>0){
					   boolean exists=false;
					   for(Movie m : movieList){
						   if(m.getTitle().equals(movie.getTitle()) && m.getMovieClass().equals(movie.getMovieClass())){
							   exists=true;
							   break;
						   }
					   }
					   if(!exists){
						   movieList.add(movie);
					   }
				   }
				   else{
					   movieList.add(movie);
				   }
			   }
		   }
	   }
	   return movieList;
   }

	/**
	 * Get array list of movie titles
	 * @param movies movies shown in selected cineplex
	 * @return array list of movie titles
	 */
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

	/**
	 * Returns cinemas that plays selected movie from selected cineplex
	 * @param cineplex cineplex selected by customer
	 * @param movie cineplex selected by customer
	 * @return array list of cinemas
	 */
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

	/**
	 * Returns array list of cinema types (Eg. Platinium Suites)
	 * @param cinemas cinemas that plays selected movie from selected cineplex
	 * @return array list of cinema types
	 */
    public ArrayList<String> getCinemaTypes(ArrayList<Cinema> cinemas){
        ArrayList<String> cinemaTypes = new ArrayList<>();
        String cinemaType;
        for (Cinema cinema: cinemas) {
            cinemaType = cinema.getCinemaType();
            if(cinemaType!=null && !cinemaTypes.contains(cinemaType)){
                cinemaTypes.add(cinemaType);

            }
        }
        return cinemaTypes;
    }

	/**
	 * Returns cinema that is playing the selected movie at the selected show time
	 * @param cinemas cinemas that plays selected movie from selected cineplex
	 * @param st selected showtime
	 * @return cinema
	 */
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

	/**
	 * Returns array list of showtime after customer selects cineplex, movie and cinema type
	 * @param cineplex selected cineplex
	 * @param movie selected movie
	 * @param cinemaType selected cinema type
	 * @return arraylist of showtimes
	 */
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

	/**
	 * Calculate base price of tickets after customer selects cineplex, movie, cinema type and showtime
	 * @param movie selected movie
	 * @param cinemaType selected cinema type
	 * @param showtime selected show time
	 * @return base price
	 */
   public float calculateTicketBasePrice(Movie movie, String cinemaType, ShowTime showtime){
	   ArrayList<String> categories = new ArrayList<>();
	   String movieClass = movie.getMovieClass();
	   if(movieClass!= null) categories.add(movieClass);
       categories.add(cinemaType);
	   int dayOfWeek = showtime.getDateTime().getDayOfWeek().ordinal();
	   Price p = null;
	   try {
		   p = (Price) FileIO.readObject("price.txt");
	   } catch (IOException e) {
		   System.out.println("Invalid file");
		   return -1;
	   } catch (ClassNotFoundException e) {
		   System.out.println("Invalid file");
		   return -1;
	   }
	   if(p.isWeekend(dayOfWeek)) categories.add("Weekend");
	   if(p.isHoliday(showtime.getDateTime())) categories.add("Holiday");
	   if(categories.size()!=0){
	       return p.getPrice(categories);
       }
	   else return 0;
   }

	/**
	 * calculates total price of all tickets ordered by the customer
	 * @param chosenSeats hashmap with key seatID and value age category
	 * @param basePrice base price of the tickets
	 * @return total price
	 */
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

	/**
	 * Calculates the ticket price given the age category and base price
	 * @param age selected age category
	 * @param basePrice base price of the tickets
	 * @return price of the ticket
	 */
    public float calculateTicketPrice(String age, float basePrice){
       Price p = new Price();
       float ticketPrice = basePrice  + p.getPrice(age);
       return ticketPrice;
    }

	/**
	 * Serves as a controller between seat UIs and the entities
	 */
	public void seatSelection() {
	   SeatUI sui = new SeatUI();
	   int choice;

	   ArrayList<Cineplex> cineplexes = getCineplexList();
	   choice = sui.getCineplexSelectionView(cineplexes);
	   Cineplex cineplex = cineplexes.get(choice);

	   ArrayList<Movie> movies = getMovieList(cineplex);
	   ArrayList<Movie> allMovies = getNowPreviewShowingMovieList();
	   ArrayList<Movie> filteredMovies = new ArrayList<>();
	   for (int index1 = 0 ; index1<allMovies.size(); index1++){
		   Movie a = allMovies.get(index1);
		   for (int index2 = 0; index2<movies.size(); index2++){
	   		Movie b = movies.get(index2);
	   		if (a.getTitle().equals(b.getTitle())){
	   			if(a.getStatusType().equals(b.getStatusType()))
	   				filteredMovies.add(b);
			}
		}
	   }
	   ArrayList<String> movieTitles = getMovieTitles(filteredMovies);
	   Movie movie = filteredMovies.get(sui.getMovieSelectionView(movieTitles));

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
	   Customer customer = FileIO.getCustomerCookie();
	   ArrayList<Customer> customerData = new ArrayList<>();
	   Transaction transaction = new Transaction(customer,cineplex,cinema,showtime,
			   calculateTotalPrice(chosenSeats,basePrice),chosenSeats);
	   customer.addTransactions(transaction);
	   FileIO.storeCustomerCookie(customer);

	   try {
		   customerData = (ArrayList<Customer>) FileIO.readObject("customer.txt");
		   for(Customer c: customerData){
		   	if(customer.getEmail().equals(c.getEmail())){
		   		c.addTransactions(transaction);
			}
		   }
		   FileIO.writeObject("customer.txt",customerData);
	   } catch (IOException e) {
		   System.out.println("File not found!");
		   return;
	   } catch (ClassNotFoundException e) {
		   System.out.println("File not found!");
		   return;
	   }

	   //update movie Total Sales
	   try {
		   ArrayList<Movie> movieData = (ArrayList<Movie>) FileIO.readObject("movie.txt");
		   for(Movie m: movieData){
		   	if(m.getTitle().equals(movie.getTitle())){
		   		m.increaseTotalSales(chosenSeats.size());
		   		break;
			}
		   }
		   FileIO.writeObject("movie.txt",movieData);
	   } catch (IOException e) {
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
   }

	/**
	 * Returns a hashmapwith key: age category and value: number of tickets in that age category
	 * @param chosenSeat Seats chosen by the customer
	 * @return hashmap with key:age category and value: number of tickets
	 */
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

	/**
	 * Set the selected seats as occupied
	 * @param cineplex selected cineplex
	 * @param cinema selected cinema
	 * @param showTime selected show time
	 * @param chosenSeats selected seats
	 */
   public void occupySeats(Cineplex cineplex, Cinema cinema, ShowTime showTime, HashMap<String,String> chosenSeats){
	   ArrayList<Cineplex> cineplexes = null;
	   try {
		   cineplexes = (ArrayList<Cineplex>) FileIO.readObject("cineplex.txt");
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
		   FileIO.writeObject("cineplex.txt", cineplexes);
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }

	/**
	 * Logic to search for movies that has title containing String inputted.
	 * @param input movie title
	 * @return matched movies
	 */
	public ArrayList<Movie> searchMovieLogic(String input) {

		ArrayList<Movie> movieList = getAllMovieList();
		ArrayList<Movie> matchingMovieList = new ArrayList<Movie>();

		for (Movie m : movieList) {
			String movieTitle = m.getTitle();
			if (movieTitle.contains(input)) {
				matchingMovieList.add(m);
			}
		}
		return matchingMovieList;
	}

	/**
	 * Get customer cookie
	 * @return Customer cookie
	 */
	public Customer getCusCookie() {
		return FileIO.getCustomerCookie();
	}

	/**
	 *
	 * @param array
	 */
	public static void list (ArrayList<? extends ListerInterface> array){
		int i = 1;
		for(ListerInterface li: array){
			System.out.print(i+". ");
			li.listItself();
			i++;
		}
	}
}

package com.company.View;
import com.company.Controller.MovieGoerController;
import com.company.Entity.*;
import com.company.Utils.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


/**
 *  MovieGoerUI to generate User interface for movie goers
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class MovieGoerUI implements GeneralUI{
	
	MovieGoerController movieController = new MovieGoerController();

	/**
	 * 	When a certain movie is passed in, this method will print out
	 * 	All the details of that movie following the sequence
	 * @param movie movie selected by customer
	 */
	public void getMovieDetailsView(Movie movie) {
		int n=0;
		
		Utils.displayHeader("Movie Details");
		System.out.println("The details of "+ movie.getTitle() + " :");
		System.out.println("1) Duration: "+ movie.getDuration());
		System.out.println("2) Synopsis: "+ movie.getSynopsis());
		System.out.println("3) Status: "+ movie.getStatusType());
		System.out.println("5) Movie Type: "+ movie.getMovieClass());
		System.out.println("6) Age Type: "+ movie.getAgeType());
		if (movie.getOverallReviewRating() == 0)
		System.out.println("7) Review Rating: No ratings yet");
		else
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

	/**
	*Prints out all the movies available in the database
	*In the end, asks users which movie they want to view the details
	 */

	public void getMovieListingView(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = movieController.getAllMovieList();
		Utils.list(movieList);
		getMovieDetailsView(movieList.get(Utils.getUserChoice(1, movieList.size())-1));
	}
	/**
	 *Prints the choices available for the users
	 *Asks for users' input
	 *Once they choose, the method will execute to the intended method.
	 */
	public void displayHomePage() {

		boolean loop = true;

		while (loop) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Select What to do: ");
			System.out.println("1) Book Ticket");
			System.out.println("2) Movie Listing");
			System.out.println("3) Search Movie detail");
			System.out.println("4) Make a review");
			System.out.println("5) View booking history");
			System.out.println("6) Top 5 Movies");
			System.out.println("7) Quit");
			try {
				int choice = sc.nextInt();
				switch (choice) {
					case 1:
						movieController.seatSelection();
						break;
					case 2:
						getMovieListingView();
						break;
					case 3:
						searchMovieUI();
						break;

					case 4:
						getMakeAReviewView();
						break;
					case 5:
						getBookingHistoryView();
						break;
					case 6:
						searchMovieUI();
						break;
					case 7:
						loop = false;
						break;
					default:
						System.out.println("Please input 1,2,3,4,5,6 or 7.");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please input an integer.");
			}
		}
	}
	/**
	 *Prints out all the Cineplexes available
	 */
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
	/**
	 *Prints out only the movies with statusType = 'Now showing'
	 *In the end, asks users which movie they want to make reviews
	 */
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
	
		HandleReviewUI.MakeReview(movieList.get(Utils.getUserChoice(1, movieList.size())-1));
	}

	/**
	 * Prints out all transaction history of customer
	 */
	public void getBookingHistoryView(){
		Price p = new Price();
		Customer customer = Utils.getCustomerCookie();
		ArrayList<Transaction> transactions = customer.getTransactions();
		if(transactions.size()==0){
			System.out.println("No bookings have been made.");
			return;
		}
		Transaction curTransaction = transactions.get(0);
		Utils.displayHeader("Booking History");
		System.out.println("Customer name: " + curTransaction.getCustomerName());
		System.out.println("Customer email: " + curTransaction.getCustomerEmail());
		System.out.println("Customer phone: " + curTransaction.getCustomerPhone());
		System.out.println("-----------------------------------------");
		for(Transaction t: transactions){
			System.out.println("TID: "+t.getTID());
			System.out.println("Movie: " + t.getShowTime().getMovie().getTitle());
			System.out.println("Cineplex: " + t.getCineplex().getCineplexName());
			System.out.println("Cinema: " + t.getCinema().getCID());
			if(p.isHoliday(t.getShowTime().getDateTime()))
				System.out.println(Utils.createDayOfWeekString(t.getShowTime().getDateTime())+"(Holiday)");
			else
				System.out.println(Utils.createDayOfWeekString(t.getShowTime().getDateTime()));
			System.out.println("Seats:");
			for(Map.Entry<Seat,String> chosenSeat : t.getSeats().entrySet()){
				Seat seat = chosenSeat.getKey();
				String age = chosenSeat.getValue();
				System.out.println(seat.getRow() + seat.getColumn() + ": " + age);
			}
			System.out.println("Total price: " + t.getTotalPrice());
			System.out.println("-----------------------------------------");
		}
	}

	/**
	 * Get user selection based on how they want to get top 5 movies
	 */
	public void listTopMovies() {
		Utils.displayHeader("Top 5 Movies");
		System.out.println(
				"1. List top 5 ranking movies by ticket sales.\n" +
						"2. List top 5 ranking movies by Overall reviewers' rating.");
		switch (Utils.getUserChoice(1, 2)) {
			case 1:
				getTop5MoviesTicketView();
				break;
			case 2:
				getTop5MoviesRatingView();
				break;
		}
	}

	/**
	 * prints out top 5 movies ordered by total sales
	 */
    public void getTop5MoviesTicketView(){
		Utils.displayHeader("Top 5 Movie List based on tickets sold");
		ArrayList<Movie> top5MovieList = new ArrayList<Movie>();
		top5MovieList = movieController.getTop5MoviesListTicket();
		int i = 1;
		for (Movie m: top5MovieList){
			System.out.println("Top "+ i + ": " + m.getTitle());
			i++;
		}
	}

	/**
	 * prints out top 5 movies ordered by review ratings
	 */
	public void getTop5MoviesRatingView(){
		Utils.displayHeader("Top 5 Movie List based on customers rating");
		ArrayList<Movie> top5MovieList = new ArrayList<Movie>();
		top5MovieList = movieController.getTop5MoviesListRating();
		int i = 1;
		for (Movie m: top5MovieList){
			System.out.println("Top "+ i + ": " + m.getTitle());
			i++;
		}
	}

	public void searchMovieUI() {
		boolean found = false;
		Utils.displayHeader("Search movie");

		ArrayList<Movie> movieList = new ArrayList<Movie>();
		ArrayList<Movie> selected = new ArrayList<Movie>();
		movieList = movieController.getAllMovieList();

		System.out.println("Please type in movie name");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		for (Movie m : movieList) {
			if (m.getTitle().contains(input)) {
				selected.add(m);
				selectedMovieDetailView(selected);
				found = true;
			}
		}
		if (!found)
			System.out.println("Movie not found");

	}

	public void selectedMovieDetailView(ArrayList<Movie> Selectedlist) {
		int i=1;
		int select=0;


			System.out.println("Movies found: ");
			Utils.list(Selectedlist);
			System.out.println("Please select movie: ");

		select = Utils.getUserChoice(1,Selectedlist.size());

	getMovieDetailsView(Selectedlist.get((select-1)));
	}
}
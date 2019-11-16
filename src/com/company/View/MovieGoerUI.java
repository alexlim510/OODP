package com.company.View;
import com.company.Controller.MovieGoerController;
import com.company.Entity.*;
import com.company.TopMovies.Top5CurrentMovies;
import com.company.TopMovies.TopMovieFactory;
import com.company.Utils.UserInputOutput;
import com.company.Utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;


/**
 *  MovieGoerUI to generate User interface for movie goers
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class MovieGoerUI implements GeneralUI{

	MovieGoerController movieController = new MovieGoerController();

	/**
	*Prints out all the movies available in the database
	*In the end, asks users which movie they want to view the details
	 */

	public void getMovieListingView(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = movieController.getAllMovieList();
		Utils.list(movieList);
		movieController.getMovieDetailsView(movieList.get(Utils.getUserChoice(1, movieList.size())-1));
	}
	/**
	 *Prints the choices available for the users
	 *Asks for users' input
	 *Once they choose, the method will execute to the intended method.
	 */
	public void displayHomePage() {

		boolean loop = true;


		while (loop) {
			UserInputOutput.displayHeader("Movie Goer Main Menu");
			System.out.println("1) Book Ticket");
			System.out.println("2) Movie Listing");
			System.out.println("3) Search Movie detail");
			System.out.println("4) Make a review");
			System.out.println("5) View booking history");
			System.out.println("6) Top 5 Movies");
			System.out.println("7) Quit");
			try {
				switch (UserInputOutput.getUserChoice(1, 7)) {
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
						listTopMoviesUI();
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
		movieList = movieController.getNowPreviewShowingMovieList();
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
	public void listTopMoviesUI() {
		Utils.displayHeader("Top 5 Movies");
		System.out.println(
				"1. List top 5 ranking movies by ticket sales.\n" +
						"2. List top 5 ranking movies by Overall reviewers' rating.");
		TopMovieFactory movieFactory = new TopMovieFactory();
		Top5CurrentMovies top5CurrentMovies = null;
		switch (Utils.getUserChoice(1, 2)) {
			case 1:
				top5CurrentMovies = movieFactory.makeTop5Movie("ticket");
				top5CurrentMovies.printTop5Movies();
				break;
			case 2:
				top5CurrentMovies = movieFactory.makeTop5Movie("rating");
				top5CurrentMovies.printTop5Movies();
				break;
		}
	}
	public void searchMovieUI() {
		Utils.displayHeader("Search movie");
		System.out.println("Please type in movie name");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		ArrayList<Movie> movieList = movieController.searchMovieLogic(input);
		if (movieList.size() > 0) {
			System.out.println("Movies found: ");

			Utils.list(movieList);

			System.out.println("Please select movie: ");
			int index = Utils.getUserChoice(1,movieList.size());
			movieController.getMovieDetailsView(movieList.get((index - 1)));
		}
		else {
			System.out.println("Movie not found");
		}
	}
}
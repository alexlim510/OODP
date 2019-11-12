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
 */
public class MovieGoerUI {
	
	MovieGoerController movieController = new MovieGoerController();

	/**
	 *
	 * 	When a certain movie is passed in, this method will print out
	 * 	All the details of that movie following the sequence
	 *
	 * @param movie
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
	*In the end, asks users which movie they wanna view the details
	 */
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
	/** getHomeView()
	Prints the choices available for the users
	Asks for users' input
	Once they choose, the method will execute to the intended method.
	 */
	public void getHomeView() {

		boolean loop = true;

        while(loop) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Select What to do: ");
            System.out.println("1) Book Ticket");
            System.out.println("2) Movie Listing");
            System.out.println("3) Make a review");
			System.out.println("4) View booking history");
            System.out.println("5) Quit");
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
                case 4:
					getBookingHistoryView();
					break;
				case 5:
					loop = false;
                    break;
                default:
                    System.out.println("Please input 1,2,3,4 or 5.");
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
            }
        }
    }
	/** getCineplexView()
	Prints out all the Cineplexes available
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
	/** getMakeAReviewView()
	Prints out only the movies with statusType = 'Now showing'
	In the end, asks users which movie they want to make reviews
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

	public void getBookingHistoryView(){
		Price p = new Price();
		Customer customer = Utils.getCustomerCookie();
		ArrayList<Transaction> transactions = customer.getTransactions();
		if(transactions.size()==0){
			System.out.println("No bookings have been made.");
			return;
		}

		Utils.displayHeader("Booking History");

		for(Transaction t: transactions){
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
    public void getTop5MoviesView(){
        Utils.displayHeader("Top 5 Movie List");
        ArrayList<Movie> top5MovieList = new ArrayList<Movie>();
        top5MovieList = movieController.getTop5MoviesList();
        int i = 1;
        for (Movie m: top5MovieList){
            System.out.println("Top "+ i + ": " + m.getTitle());
        }
    }
}


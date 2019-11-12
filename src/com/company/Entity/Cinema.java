package com.company.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.*;

/**
 * Represents a cinema inside a cineplex.
 * A cinema can have several movies to show.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class Cinema implements Serializable{
   /**
    * The ID of the cinema
    */
   private String CID;

   /**
    * The list of all movies inside this cinema
    */
   private ArrayList<Movie> movies;

   /**
    * The type of the cinema, will be used to calculate ticket price
    */
   private String cinemaType = "Normal";

   /**
    * List of all the show inside this cinema
    */
   private ArrayList<ShowTime> showTime;

   /**
    * list of all the type of a cinema
    */
   String[] cinemaTypes = {"Normal","Platinum Movie Suites"};

   /**
    * Create a new cinema.
    * The constructor will initialize an empty list for
    * movies and showtimes.
    * @param CID CID is the ID of cinema
    */
   public Cinema(String CID) {
      this.CID = CID;
      this.showTime = new ArrayList<ShowTime>();
      this.movies = new ArrayList<Movie>();
   }


   public String[] getCinemaTypes(){
      return this.cinemaTypes;
   }
   public void setCinemaTypes(String[] cinemaTypes){
      this.cinemaTypes = cinemaTypes;
   }

   public void deleteShowTime(LocalDateTime dateTime, Movie movie){
      for(int i = 0; i<this.showTime.size(); i++){
         if(
            this.showTime.get(i).getDateTime().isEqual(dateTime) &&
            this.showTime.get(i).getMovie().getTitle().equals(movie.getTitle())
         ){
            this.showTime.remove(this.showTime.get(i));
         }
      }
   }

   public ArrayList<Movie> getMovies() {
      return movies;
   }

   public void setMovies(ArrayList<Movie> movies) {
      this.movies = movies;
   }


   public String getCID() {
      return CID;
   }

   public String getCinemaType() {
      return cinemaType;
   }


   public void setCinemaType(String cinemaType) {
      this.cinemaType = cinemaType;
   }

   public ArrayList<ShowTime> getShowTime() {
      return showTime;
   }
	
   public ShowTime getShowTime(LocalDateTime datetime) {
      for(ShowTime st: this.showTime) {
         if(st.getDateTime().isEqual(datetime)) {
            return st;
         }
      }
      return null;
   }
	
   public void addShowTime(ShowTime showTime) {
      for(ShowTime st : this.showTime) {
         if(st.getDateTime().isEqual(showTime.getDateTime())) {
            throw new IllegalArgumentException("Conflicting Showtimes");
         }
      }
      ArrayList<ShowTime> newShowTime = this.showTime;
      newShowTime.add(showTime);
      this.showTime = newShowTime;
   }
}

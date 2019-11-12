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
    * @param CID  the ID of cinema
    */
   public Cinema(String CID) {
      this.CID = CID;
      this.showTime = new ArrayList<ShowTime>();
      this.movies = new ArrayList<Movie>();
   }

   /**
    * get array of cinema types
    * @return Types of cinema
    */
   public String[] getCinemaTypes(){
      return this.cinemaTypes;
   }

   /**
    * Set array of cinema types
    * @param cinemaTypes Array of cinema types
    */
   public void setCinemaTypes(String[] cinemaTypes){
      this.cinemaTypes = cinemaTypes;
   }


   /**
    * Delete show time from cinema
    * @param dateTime Date time of movie
    * @param movie Movie object
    */
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

   /**
    * Get Movies from cinema
    * @return ArrayList of movies in cinema
    */
   public ArrayList<Movie> getMovies() {
      return movies;
   }

   /**
    * set Movies into cinema
    * @param movies Arraylist of movies
    */
   public void setMovies(ArrayList<Movie> movies) {
      this.movies = movies;
   }

   /**
    * Get Cinema ID
    * @return Cinema ID
    */
   public String getCID() {
      return CID;
   }

   /**
    * get cinema types
    * @return cinema type
    */
   public String getCinemaType() {
      return cinemaType;
   }

   /**
    * Set cinema Type
    * @param cinemaType Type of Cinema
    */
   public void setCinemaType(String cinemaType) {
      this.cinemaType = cinemaType;
   }


   /**
    * Get Showtime of cinema
    * @return Showtime of cinema
    */
   public ArrayList<ShowTime> getShowTime() {
      return showTime;
   }


   /**
    *
    * Get Showtime Object of specific datetime
    * @param datetime Date and time of Showtime
    * @return Showtime Object
    */
   public ShowTime getShowTime(LocalDateTime datetime) {
      for(ShowTime st: this.showTime) {
         if(st.getDateTime().isEqual(datetime)) {
            return st;
         }
      }
      return null;
   }

   /**
    * Add showtime To cinema
    * @param showTime Showtime SLots
    */
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

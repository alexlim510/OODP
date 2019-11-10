package com.company.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.*;

public class Cinema implements Serializable{
   private String CID;
   private ArrayList<Movie> movies;
   private String cinemaType;
   private ArrayList<ShowTime> showTime;    
   private String cineplexName; 

   String[] cinemaTypes = {"Normal","Platinum Movie Suites"};
   
   public Cinema(String CID) {
      this.CID = CID;
      this.showTime = new ArrayList<ShowTime>();
   }

   public void deleteShowTime(LocalDateTime dateTime, Movie movie){
      for(int i = 0; i<this.showTime.size(); i++){
         if(
            this.showTime.get(i).getDateTime().isEqual(dateTime) &&
            this.showTime.get(i).getMovie().getTitle() == (movie.getTitle())
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

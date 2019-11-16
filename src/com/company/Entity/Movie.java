package com.company.Entity;

import com.company.View.ListerInterface;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the Entity Class of Movie
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class Movie implements Serializable, ListerInterface {
   private String title;
   private String movieClass; // 3D, blockbuster etc..
   private String statusType; //now showing, etc..
   private String ageType; //{"G", "PG", "PG13", "NC16", "M18", "R21"}
   private String synopsis;
   private String director;
   private String[] cast;
   private String[] genre;
   private int duration;
   private LocalDate showTill;
   private ArrayList<Review> reviews;
   private int totalSales = 0;

   String[] movieClasses = {"Normal", "Blockbuster", "3D"};

   String[] ageTypes = {"G", "PG", "PG13", "NC16", "M18", "R21"};

   String[] statusTypes = {"Coming soon", "Now showing", "End of showing", "Preview"};

   // Constructor used for getting movie functions like getmovieClaees / getAgeTypes / getstatusTypes
   public Movie() {}

   /**
    * This method constructs a movie object
    * @param title Title of the movie
    */
   public Movie(String title) {
      this.title = title;
      this.reviews = new ArrayList<Review>();
   }

   /**
    * This method constructs a movie object used by the staff
    * @param title Title of the movie
    * @param synopsis Synopsis of the movie
    * @param director Director of the movie
    * @param cast List of casts of the movie
    * @param genre Duration of the movie
    * @param showTill List of reviews from customers
    * @param duration Date until the movie is available in the cinema
    * @param movieClass Status of the movie (now showing, coming soon, etc)
    * @param ageType Age Type of the movie (G, PG, PG13, etc)
    * @param statusType Status of the movie (now showing, coming soon, etc)
    */
   // This constructor is used for creating new movies from the staffUI.
   public Movie(String title, String synopsis , String director , String[] cast , String[] genre,
                LocalDate showTill , int duration , int movieClass,
                int ageType, int statusType ) {
      this.title= title;
      this.synopsis= synopsis;
      this.director=director;
      this.cast =cast ;
      this.genre = genre;
      this.showTill = showTill;
      this.duration = duration;
      this.movieClass = movieClasses[movieClass];
      this.ageType = ageTypes[ageType];
      this.statusType = statusTypes[statusType];
      this.reviews = new ArrayList<Review>();
   }

   /**
    * This method constructs a movie object
    * @param title Title of the movie
    * @param synopsis Synopsis of the movie
    * @param director Director of the movie
    * @param cast List of casts of the movie
    * @param genre Duration of the movie
    * @param duration Date until the movie is available in the cinema
    * @param movieClass Status of the movie (now showing, coming soon, etc)
    * @param ageType Age Type of the movie (G, PG, PG13, etc)
    * @param statusType Status of the movie (now showing, coming soon, etc)
    */
   // This constructor is used for creating dummy data.
   public Movie(String title, String synopsis , String director , String[] cast , String[] genre,
                int duration , ArrayList<Review> reviews ,int movieClass,
                int ageType, int statusType ) {
      this.title= title;
      this.synopsis= synopsis;
      this.director=director;
      this.cast =cast ;
      this.genre = genre;
      this.duration = duration;
      this.reviews = reviews;
      this.movieClass = movieClasses[movieClass];
      this.ageType = ageTypes[ageType];
      this.statusType = statusTypes[statusType];
      this.reviews = new ArrayList<Review>();
   }

   /**
    * This method gets the title of the movie
    * @return The title of the movie
    */
   public String getTitle() {
      return title;
   }

   /**
    * This method sets the title of the movie
    * @param title Title of the movie
    */
   public void setTitle(String title) {
      this.title = title;
   }

   /**
    * This method gets the synopsis of the movie
    * @return The synopsis of the movie
    */
   public String getSynopsis() {
      return synopsis;
   }

   /**
    * This method gets the Age Type of the movie
    * @return The Age Type of the movie
    */
   public String getAgeType() {
      return ageType;
   }

   /**
    * This method sets the age type of the movie
    * @param  ageType The age type of the movie
    */
   public void setAgeType(String ageType) {
      this.ageType = ageType;
   }

   /**
    * This method sets the synopsis of the movie
    * @param synopsis The age type of the movie
    */
   public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
   }

   /**
    * This method gets the director of the movie
    * @return The director of the movie
    */
   public String getDirector() {
      return director;
   }

   /**
    * This method sets the director of the movie
    * @param director The director of the movie
    */
   public void setDirector(String director) {
      this.director = director;
   }

   /**
    * This method gets List of casts
    * @return The casts of the movie
    */
   public String[] getCast() {
      return cast;
   }

   /**
    * This method sets list of casts
    * @param cast The casts of the movie
    */
   public void setCast(String[] cast) {
      this.cast = cast;
   }

   /**
    * This method gets list of reviews
    * @return The reviews of the movie
    */
   public ArrayList<Review> getMovieReview() {
      return this.reviews;
   }

   /**
    * This method sets list of reviews
    * @param reviews The reviews of the movie
    */
   public void setMovieReview(ArrayList<Review> reviews ) {
      this.reviews.clear();
      this.reviews.addAll(reviews);
   }

   /**
    * This method inserts a review to the list of review
    * @param review The review to be inserted
    */
   public void insertMovieReview(Review review) {
      this.reviews.add(this.reviews.size(), review);
   }

   /**
    * This method gets the overall rating based on the reviews from customer
    * @return Overall review rating of the movie
    */
   public float getOverallReviewRating() {
      float overallReviewRating = 0;
      for(Review review: this.reviews){
         overallReviewRating += review.getRating();
      }
      overallReviewRating = overallReviewRating/this.reviews.size();
      return overallReviewRating;
   }

   /**
    * This method gets the genre of the movie
    * @return Genre of the movie
    */
   public String[] getGenre() {
      return genre;
   }

   /**
    * This method sets the genre of the movie
    * @param genre Genre of the movie
    */
   public void setGenre(String[] genre) {
      this.genre = genre;
   }

   /**
    * This method gets the duration of the movie
    * @return Duration of the movie
    */
   public int getDuration() {
      return duration;
   }

   /**
    * This method sets the duration of the movie
    * @param duration Duration of the movie
    */
   public void setDuration(int duration) {
      this.duration = duration;
   }

   /**
    * This method gets the class of the movie
    * @return Class of the movie
    */
   public String getMovieClass() {
      return movieClass;
   }

   /**
    * This method sets the class of the movie
    * @param movieClass Class of the movie
    */
   public void setMovieClass(String movieClass) {
      this.movieClass = movieClass;
   }

   /**
    * This method gets the status of the movie
    * @return Status of the movie
    */
   public String getStatusType() {
      return statusType;
   }

   /**
    * This method sets the status of the movie
    * @param statusType Status of the movie
    */
   public void setStatusType(String statusType) {
      this.statusType = statusType;
   }

   /**
    * This method gets the list of reviews of the movie
    * @return List of reviews of the movie
    */
   public ArrayList<Review> getReviews() {
      return reviews;
   }

   /**
    * This method gets the list of available classes
    * @return  List of available classes
    */
   public String[] getMovieClasses() {
      return movieClasses;
   }

   /**
    * This method gets the list of available ageTypes
    * @return List of available ageTypes
    */
   public String[] getAgeTypes() {
      return ageTypes;
   }

   /**
    * This method gets the list of available statusTypes
    * @return List of available statusTypes
    */
   public String[] getStatusTypes() {
      return statusTypes;
   }

   /**
    * This method gets the dateTime of the movie's last showing dateTime
    * @return dateTime of the movie's last showing dateTime
    */
   public LocalDate getShowTill() {
      return showTill;
   }

   /**
    * This method sets the dateTime of the movie's last showing dateTime
    * @param showTill dateTime of the movie's last showing dateTime
    */
   public void setShowTill(LocalDate showTill) {
      this.showTill = showTill;
   }

   /**
    * This method gets the total sales of the movie
    * @return Total Sales of the movie
    */
   public int getTotalSales(){return totalSales;}

   /**
    * This method increases the total sale of the movie
    * @param increment The increase of sale of the movie
    * @return Total Sales of the movie (after being incremented)
    */
   public int increaseTotalSales(int increment){
      this.totalSales = this.totalSales + increment;
      return this.totalSales;
   }

   /**
    * This method implements the interface to list itself
    * since this class is often listed in the UI
    */
   public void listItself(){
      System.out.println("Title: "+this.title+" - Status: "+this.statusType);
   }
}
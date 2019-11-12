package com.company.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;

/**
 * This is the Entity Class of Movie
 * @author GROUP 2
 * @version 1.0
 */
public class Movie implements Serializable{
   /**
    * Title of the movie
    */
   private String title;
   /**
    * Class of the movie (3D, Blockbuster, etc)
    */
   private String movieClass; // 3D, blockbuster etc..
   /**
    * Status of the movie (now showing, coming soon, etc)
    */
   private String statusType; //now showing, etc..
   /**
    * Age Type of the movie (G, PG, PG13, etc)
    */
   private String ageType; //{"G", "PG", "PG13", "NC16", "M18", "R21"}
   /**
    * Synopsis of the movie
    */
   private String synopsis;
   /**
    * Director of the movie
    */
   private String director;
   /**
    * List of casts of the movie
    */
   private String[] cast;
   /**
    * List of genres of the movie
    */
   private String[] genre;
   /**
    * Duration of the movie
    */
   private int duration;
   /**
    * Date until the movie is available in the cinema
    */
   private LocalDateTime showTill;
   /**
    * List of reviews from customers
    */
   private ArrayList<Review> reviews;
   /**
    * Overall review rating calculcated based on the list of reviews from customers
    */
   public float overallReviewRating = -99;
   /**
    * Total Sales for the movie
    */
   public int totalSales = 0;

   String[] movieClasses = {"Blockbuster", "3D"};

   String[] ageTypes = {"G", "PG", "PG13", "NC16", "M18", "R21"};

   String[] statusTypes = {"Coming soon", "Now showing", "End of showing", "Preview"};

   // Constructor used for getting movie functions like getmovieClaees / getAgeTypes / getstatusTypes
   public Movie() {}

   /**
    * Constructing a movie object
    * @param title Title of the movie
    */
   public Movie(String title) {
      this.title = title;
      this.reviews = new ArrayList<Review>();
   }

   /**
    * Constructing a movie object used by the staff
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
                LocalDateTime showTill , int duration , int movieClass,
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
    * Constructing a movie object used by the staff
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
                int duration , ArrayList<Review> reviews  , float overallReviewRating,int movieClass,
                int ageType, int statusType ) {
      this.title= title;
      this.synopsis= synopsis;
      this.director=director;
      this.cast =cast ;
      this.genre = genre;
      this.duration = duration;
      this.reviews = reviews;
      this.overallReviewRating = overallReviewRating;
      this.movieClass = movieClasses[movieClass];
      this.ageType = ageTypes[ageType];
      this.statusType = statusTypes[statusType];
      this.reviews = new ArrayList<Review>();
   }

   /**
    * Get the title of the movie
    * @return String The title of the movie
    */
   public String getTitle() {
      return title;
   }

   /**
    * Set the title of the movie
    * @param title Title of the movie
    */
   public void setTitle(String title) {
      this.title = title;
   }

   /**
    * Get the synopsis of the movie
    * @return String The synopsis of the movie
    */
   public String getSynopsis() {
      return synopsis;
   }

   /**
    * Get the Age Type of the movie
    * @return String The Age Type of the movie
    */
   public String getAgeType() {
      return ageType;
   }

   /**
    * Set the age type of the movie
    * @param  ageType The age type of the movie
    */
   public void setAgeType(String ageType) {
      this.ageType = ageType;
   }

   /**
    * Set the synopsis of the movie
    * @param synopsis The age type of the movie
    */
   public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
   }

   /**
    * Get the director of the movie
    * @return String The director of the movie
    */
   public String getDirector() {
      return director;
   }

   /**
    * Set the director of the movie
    * @param String The director of the movie
    */
   public void setDirector(String director) {
      this.director = director;
   }

   public String[] getCast() {
      return cast;
   }

   public void setCast(String[] cast) {
      this.cast = cast;
   }

   public ArrayList<Review> getMovieReview() {
      return this.reviews;
   }

   public void setMovieReview(ArrayList<Review> reviews ) {
      this.reviews.clear();
      this.reviews.addAll(reviews);
   }

   public void insertMovieReview(Review review) {
      this.reviews.add(this.reviews.size(), review);
   }

   public void setOverallReviewRating(float overallReviewRating) {
      this.overallReviewRating = overallReviewRating;
   }

   public float getOverallReviewRating() {
      float overallReviewRating = 0;
      for(Review review: this.reviews){
         overallReviewRating += review.getRating();
      }
      overallReviewRating = overallReviewRating/this.reviews.size();
      return overallReviewRating;
   }

   public String[] getGenre() {
      return genre;
   }

   public void setGenre(String[] genre) {
      this.genre = genre;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }


   public String getMovieClass() {
      return movieClass;
   }

   public void setMovieClass(String movieClass) {
      this.movieClass = movieClass;
   }

   public String getStatusType() {
      return statusType;
   }

   public void setStatusType(String statusType) {
      this.statusType = statusType;
   }

   public ArrayList<Review> getReviews() {
      return reviews;
   }

   public String[] getMovieClasses() {
      return movieClasses;
   }

   public String[] getAgeTypes() {
      return ageTypes;
   }

   public String[] getStatusTypes() {
      return statusTypes;
   }

   public LocalDateTime getShowTill() {
      return showTill;
   }

   public void setShowTill(LocalDateTime showTill) {
      this.showTill = showTill;
   }

   public int getTotalSales(){return totalSales;}

   public int increaseTotalSales(int increment){
      this.totalSales = this.totalSales + increment;
      return this.totalSales;
   }
}
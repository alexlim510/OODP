package com.company.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class Movie implements Serializable{
   private String title;
   private String movieClass; // 3D, blockbuster etc..
   private String statusType; //now showing, etc..
   private String ageType; //{"G", "PG", "PG13", "NC16", "M18", "R21"}
   private String synopsis;
   private String director;
   private String[] cast;
   private String[] genre;
   private int duration;
   private ArrayList<Review> reviews;
   public float overallReviewRating;
   public int totalSales = 0;

   String[] movieClasses = {"Blockbuster", "3D"};

   String[] ageTypes = {"G", "PG", "PG13", "NC16", "M18", "R21"};

   String[] statusTypes = {"Coming soon", "Now showing", "End of showing", "Preview"};


   // Constructor used for getting movie functions like getmovieClaees / getAgeTypes / getstatusTypes
   public Movie() {}

   public Movie(String title) {
      this.title = title;
      this.reviews = new ArrayList<Review>();
   }

   // This constructor is used for creating new movies from the staffUI.
   public Movie(String title, String synopsis , String director , String[] cast , String[] genre,
                int duration ,int movieClass,
                int ageType, int statusType ) {
      this.title= title;
      this.synopsis= synopsis;
      this.director=director;
      this.cast =cast ;
      this.genre = genre;
      this.duration = duration;
      this.movieClass = movieClasses[movieClass];
      this.ageType = ageTypes[ageType];
      this.statusType = statusTypes[statusType];
   }

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

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSynopsis() {
      return synopsis;
   }

   public String getAgeType() {
      return ageType;
   }
   public void setAgeType(String ageType) {
      this.ageType = ageType;
   }
   public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
   }

   public String getDirector() {
      return director;
   }

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
      for(int i = 0; i<this.reviews.size(); i++){
         overallReviewRating += this.reviews.get(i).getRating();
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

   public int getTotalSales(){return totalSales;}

   public int increaseTotalSales(int increment){
      this.totalSales = this.totalSales + increment;
      return this.totalSales;
   }
}
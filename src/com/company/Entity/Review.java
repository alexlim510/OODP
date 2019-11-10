package com.company.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable{
   private LocalDateTime reviewDateTime;
   private String content;
   private Customer customer;
   private Movie movie;
   private float rating;

   public Review(Movie movie, Customer customer, String content, float rating, LocalDateTime reviewDateTime) {
      this.movie = movie;
      this.customer = customer;
      this.content = content;
      this.reviewDateTime = reviewDateTime;
      this.rating = rating;
   }

   public LocalDateTime getReviewDateTime() {
      return this.reviewDateTime;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Customer getCustomer() {
      return this.customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Movie getMovie() {
      return this.movie;
   }

   public void setMovie(Movie movie) {
      this.movie = movie;
   }

   public void setReviewDateTime() {
      this.reviewDateTime = LocalDateTime.now();
   }

   public float getRating(){
      return this.rating;
   }

}
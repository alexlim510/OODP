package com.company.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a review for a movie.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class Review implements Serializable{
   private LocalDateTime reviewDateTime;
   private String content;
   private Customer customer;
   private float rating;

   /**
    * Creates a new Review with the movie, customer, content and reviewDatetime.
    * @param customer This Review's customer
    * @param content This Review's content
    * @param rating This Review's rating
    * @param reviewDateTime This Review's DateTime
    */
   public Review(Customer customer, String content, float rating, LocalDateTime reviewDateTime) {
      this.customer = customer;
      this.content = content;
      this.reviewDateTime = reviewDateTime;
      this.rating = rating;
   }

   /**
    * Gets the getReviewDateTime of this Review.
    * @return This Review's reviewDateTime.
    */
   public LocalDateTime getReviewDateTime() {
      return this.reviewDateTime;
   }

   /**
    * Gets the content of this Review.
    * @return This Review's content.
    */
   public String getContent() {
      return this.content;
   }

   /**
    * Changes the content of this Review.
    * @param content This Review's new content.
    */
   public void setContent(String content) {
      this.content = content;
   }

   /**
    * Gets the customer of this Review.
    * @return This Review's customer.
    */
   public Customer getCustomer() {
      return this.customer;
   }
   /**
    * Changes the customer of this Review.
    * @param customer This Review's new customer.
    */
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   /**
    * Changes the DateTime of this Review to the current DateTime.
    */
   public void setReviewDateTime() {
      this.reviewDateTime = LocalDateTime.now();
   }
   /**
    * Gets the rating of this Review.
    * @return This Review's rating.
    */
   public float getRating(){
      return this.rating;
   }
}
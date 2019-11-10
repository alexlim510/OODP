package com.company.Entity;
import java.time.LocalDateTime;

class Transaction {
   private String TID;
   private String customerPhone;
   private String customerEmail;
   private String customerName;
   private String movieTitle;
   private ShowTime showTime;
   private Seat[] seats;
   private LocalDateTime dateTime;
   private float totalPrice;
   
   public Transaction(String TID, Customer customer, Movie movie, float totalPrice, Seat[] seats, LocalDateTime dateTime){
      this.TID = TID;
      this.customerPhone = customer.getPhone();
      this.customerEmail = customer.getEmail();
      this.customerName = customer.getName();
      this.movieTitle = movie.getTitle();
      this.totalPrice = totalPrice;
      this.seats = seats;
      this.dateTime = dateTime;
   }
   
   public String getCustomerPhone(){
      return this.customerPhone;
   }

   public String getCustomerName(){
      return this.customerName;
   }   
   
   public String getCustomerEmail(){
      return this.customerEmail;
   }   

   public String getMovieTitle(){
      return this.movieTitle;
   }
   
   public ShowTime getShowTime(){
      return this.showTime;
   }   
   
   public Seat[] getSeats(){
      return this.seats;
   }
   
   public LocalDateTime getDateTime(){
      return this.dateTime; 
   }
   
   //modifyCustomer()
   //setMovieTitle()
   
   public void setShowTime(ShowTime showTime){
      this.showTime = showTime;
   }
   
   public void setSeats(Seat[] seats){
      this.seats = seats;
   }
   
   public void setDateTime(){
      this.dateTime = LocalDateTime.now();
   }     
}
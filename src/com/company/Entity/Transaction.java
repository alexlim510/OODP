package com.company.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Transaction implements Serializable{
   private String TID;
   private String customerEmail;
   private String customerName;
   private Cineplex cineplex;
   private Cinema cinema;
   private ShowTime showTime;
   private HashMap<Seat,String> seats;
   private float totalPrice;
   
   public Transaction(Customer customer,Cineplex cineplex, Cinema cinema, ShowTime showTime, float totalPrice,
                       HashMap<String,String> chosenSeats){
      this.TID = customer.getEmail()+showTime.getDateTime();
      this.customerEmail = customer.getEmail();
      this.customerName = customer.getName();
      this.cineplex = cineplex;
      this.cinema = cinema;
      this.showTime = showTime;
      this.totalPrice = totalPrice;
      HashMap<Seat,String> seats = new HashMap<>();
      for (Map.Entry<String, String> seat : chosenSeats.entrySet()) {
         String seatID = seat.getKey();
         String age = seat.getValue();
         seats.put(showTime.getSeat(seatID),age);
      }
      this.seats=seats;
   }

   public String getCustomerName(){
      return this.customerName;
   }   
   
   public String getCustomerEmail(){
      return this.customerEmail;
   }
   
   public ShowTime getShowTime(){
      return this.showTime;
   }   
   
   public HashMap<Seat,String> getSeats(){
      return this.seats;
   }
   
   //modifyCustomer()
   
   public void setShowTime(ShowTime showTime){
      this.showTime = showTime;
   }
   
   public void setSeats(HashMap<Seat,String> seats){
      this.seats = seats;
   }

   public Cineplex getCineplex() {
      return cineplex;
   }

   public void setCineplex(Cineplex cineplex) {
      this.cineplex = cineplex;
   }

   public Cinema getCinema() {
      return cinema;
   }

   public void setCinema(Cinema cinema) {
      this.cinema = cinema;
   }

   public float getTotalPrice(){
      return  this.totalPrice;
   }
}
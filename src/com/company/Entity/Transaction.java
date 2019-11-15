package com.company.Entity;
import com.company.Utils.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Represents the transaction which a customer made
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */

public class Transaction implements Serializable{
   /**
    * Transaction ID in the format XXXYYYYMMDDHHMM where XXX is the cinema ID
    */
   private String TID;
   /**
    * Customer's email
    */
   private String customerEmail;
   /**
    * customer's name
    */
   private String customerName;
   /**
    * Customers phone number
    */
   private String customerPhone;
   /**
    * Selected cineplex
    */
   private Cineplex cineplex;
   /**
    * selected cinema
    */
   private Cinema cinema;
   /**
    * selected show time
    */
   private ShowTime showTime;
   /**
    * hashmap with key:Seat and value: age category
    */
   private HashMap<Seat,String> seats;
   /**
    * total amount paid for this transaction
    */
   private float totalPrice;

   /**
    * Creates transaction after user has made payment
    * @param customer customer of transaction
    * @param cineplex cineplex of transaction
    * @param cinema cinema of transaction
    * @param showTime showtime of transaction
    * @param totalPrice totalprice of transaction
    * @param chosenSeats chosen seats of transaction
    */
   public Transaction(Customer customer,Cineplex cineplex, Cinema cinema, ShowTime showTime, float totalPrice,
                       HashMap<String,String> chosenSeats){
      this.TID = cinema.getCID() + Utils.createTransactionDateString(LocalDateTime.now());
      this.customerEmail = customer.getEmail();
      this.customerName = customer.getName();
      this.customerPhone = customer.getPhone();
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

   /**
    * Gets transaction ID
    * @return transaction ID
    */
   public String getTID() {return this.TID;}

   /**
    * Gets customer name
    * @return customer name
    */
   public String getCustomerName(){
      return this.customerName;
   }

   /**
    * Gets customer email
    * @return customer email
    */
   public String getCustomerEmail(){
      return this.customerEmail;
   }

   /**
    * Gets customer phone
    * @return phone number
    */
   public String getCustomerPhone(){
      return this.customerPhone;
   }

   /**
    * Gets show time
    * @return show time
    */
   public ShowTime getShowTime(){
      return this.showTime;
   }

   /**
    * Gets seats chosen
    * @return seats
    */
   public HashMap<Seat,String> getSeats(){
      return this.seats;
   }

   /**
    * Gets Cineplex
    * @return cineplex
    */
   public Cineplex getCineplex() {
      return cineplex;
   }

   /**
    * Gets cinema
    * @return cinema
    */
   public Cinema getCinema() {
      return cinema;
   }

   /**
    * Gets total price
    * @return total price
    */
   public float getTotalPrice(){
      return  this.totalPrice;
   }
}
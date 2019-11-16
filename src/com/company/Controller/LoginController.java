package com.company.Controller;

import com.company.Entity.Customer;
import com.company.Entity.Movie;
import com.company.Entity.Price;
import com.company.Entity.Staff;
import com.company.Utils.Utils;


import java.time.LocalDate;
import java.util.*;
import java.io.*;

import static com.company.Utils.Utils.storeCustomerCookie;

/**
 * Main controller for login
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-12-11
 */
public class LoginController extends Utils {
    /**
     * checks customer credentials
     * @param email email of customer
     * @return boolean depending on whether the credentials are correct
     */
   public boolean checkCustomer(String email) {
      try {
      	//create empty array of Staff
         ArrayList<Customer> customerArray;
      	//Read objects from staff.txt, cast it to array of staff
         customerArray = (ArrayList<Customer>)getObjectInputStream("customer.txt").readObject();
      	
         for(Customer c : customerArray) {
            if(c.getEmail().equals(email)) {
               storeCustomerCookie(c);
               return true;
            }
         }
      } catch (ClassNotFoundException | IOException e) {}
      return false;
   }

    /**
     * checks staff credentials
     * @param username username of staff
     * @param password password of staff
     * @return boolean depending on whether the credentials are correct
     */
   public boolean checkAdmin(String username, String password) {
      try {
      	//create empty array of Staff
         ArrayList<Staff> staffArray;
      	//Read objects from staff.txt, cast it to array of staff
         staffArray = (ArrayList<Staff>)getObjectInputStream("staff.txt").readObject();
      	
         for(Staff s : staffArray) {
            if(s.getUserName().equals(username)) {
               return s.validateCredentials(password);
            }
         }
      } catch (ClassNotFoundException | IOException e) {
         System.out.println(e);
      }
      return false;
   }

    /**
     * On start of program, checks the end date of all movies and update their status to end of showing
     * if the end date has passed
     */
   public void updateMovieStatus(){
      try {
         ArrayList<Movie> movies = (ArrayList<Movie>)Utils.readObject("movie.txt");
         if(movies.size()!=0){
            for(Movie m: movies){
               if(m.getShowTill()!=null && m.getShowTill().isBefore(LocalDate.now())){
                  m.setStatusType("End of showing");
               }
            }
         }
         Utils.writeObject("movie.txt",(Object)movies);
      } catch (IOException e) {
      } catch (ClassNotFoundException e) {
      }
   }

   public Customer getCusCookie() {
      return Utils.getCustomerCookie();
   }

   public void writeCustomer(ArrayList<Customer> customers) {
      try {
         Utils.writeObject("customer.txt", customers);
      } catch (IOException e) {
         e.printStackTrace();
         return;
      }
      System.out.println("Successfully Registered!");
   }

   public ArrayList<Customer> readCustomer() {
      ArrayList<com.company.Entity.Customer> customers = new ArrayList<>();
      try {
         customers= (ArrayList<com.company.Entity.Customer>) Utils.readObject("customer.txt");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return customers;
   }
}

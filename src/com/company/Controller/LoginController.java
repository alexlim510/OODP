package com.company.Controller;

import com.company.Entity.Customer;
import com.company.Utils.Utils;

import java.util.*;
import java.io.*;

public class LoginController extends Utils {
	

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
      } catch (ClassNotFoundException | IOException e) {
         System.out.println(e);
      }
      return false;
   }
	

   public boolean checkAdmin(String username, String password) {
      try {
      	//create empty array of Staff
         ArrayList<Staff> staffArray; 
      	//Read objects from staff.txt, cast it to array of staff
         staffArray = (ArrayList<Staff>)getObjectInputStream("staff.txt").readObject();
      	
         for(Staff s : staffArray) {
            if(s.getUserName().equals(username) && s.getPassword().equals(password)) {
               return true;
            }
         }
      } catch (ClassNotFoundException | IOException e) {
         System.out.println(e);
      }
      return false;
   }
}

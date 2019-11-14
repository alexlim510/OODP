package com.company.Entity;
import java.io.Serializable;
import java.util.ArrayList;
	
/**
 * This is the Entity Class of Customer
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class Customer extends Person implements Serializable{
   private String email;
   private String name;
   private String phone;
   private int age;
   private boolean isStudent;
   private boolean isElderly;
   private ArrayList<Transaction> transactions = new ArrayList<>();

   /**
    * This method constructs Customer object. It takes 3 arguments.
    * @param name Name of the customer
    * @param phone Phone number of the customer
    * @param email Email of the customer
    */
   public Customer(String name, String phone, String email) {
      super(name);
      this.phone = phone;
      this.email = email;
   }

   /**
    * This method gets the email of the customer
    * @return Email of the customer
    */
   public String getEmail() {
      return this.email;
   }

   /**
    * This method sets the email of the customer
    * @param email Email of the customer
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * This method gets the name of the customer
    * @return Name of the customer
    */
   public String getName() {
      return this.name;
   }

   /**
    * This method sets the name of the customer
    * @param name Name of the customer
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * This method gets the phone number of the customer
    * @return Phone number of the customer
    */
   public String getPhone() {
      return this.phone;
   }

   /**
    * This method sets the phone number of the customer
    * @param phone Phone number of the customer
    */
   public void setPhone(String phone) {
      this.phone = phone;
   }

   /**
    * This method gets the age of the customer
    * @return Age of the customer
    */
   public int getAge() {
      return this.age;
   }

   /**
    * This method sets the age of the customer
    * @param age Age of the customer
    */
   public void setAge(int age) {
      this.age = age;
   }

   /**
    * This method checks whether the customer is a student
    * @return true if customer is a student
    */
   public boolean getIsStudent() {
      return this.isStudent;
   }

   /**
    * This method sets customer as a student
    * @param isStudent true if customer is a student
    */
   public void setIsStudent(boolean isStudent) {
      this.isStudent = isStudent;
   }
   /**
    * This method checks whether the customer is an elderly
    * @return true if customer is an elderly
    */
   public boolean getIsElderly() {
      return this.isElderly;
   }
   /**
    * This method sets customer as an elderly
    * @param isElderly true if customer is an elderly
    */
   public void setIsElderly(boolean isElderly) {
      this.isElderly = isElderly;
   }

   /**
    * This method gets the list of transaction done by a customer
    * @return List of transactions
    */
   public ArrayList<Transaction> getTransactions() {
      return transactions;
   }

   /**
    * This method sets a list of transaction to the customer
    * @param transactions List of transactions
    */
   public void setTransactions(ArrayList<Transaction> transactions) {
      this.transactions.clear();
      this.transactions.addAll(transactions);
   }

   /**
    * This method add a transaction to the list of transaction history
    * @param transaction A new transaction
    */
   public void addTransactions(Transaction transaction){
      this.transactions.add(transaction);
   }

}
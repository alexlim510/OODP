package com.company.Entity;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable{
	
   private String email;
   private String phone;
   private int age;
   private boolean isStudent;
   private boolean isElderly;
   private ArrayList<Transaction> transactions = new ArrayList<>();

   public Customer(String name, String phone, String email) {
      super(name);
      this.phone = phone;
      this.email=email;
   }


public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public int getAge() {
      return this.age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public boolean getIsStudent() {
      return this.isStudent;
   }

   public void setIsStudent(boolean isStudent) {
      this.isStudent = isStudent;
   }

   public boolean getIsElderly() {
      return this.isElderly;
   }

   public void setIsElderly(boolean isElderly) {
      this.isElderly = isElderly;
   }

   public ArrayList<Transaction> getTransactions() { return transactions; }

   public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

   public void addTransactions(Transaction transaction){
      this.transactions.add(transaction);
   }
}
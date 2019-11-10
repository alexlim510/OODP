package com.company.Entity;
import java.io.Serializable;

public class Customer implements Serializable{
	
   private String email;
   private String name;
   private String phone;
   private int age;
   private boolean isStudent;
   private boolean isElderly;

   public Customer(String name, String phone, String email) {
      this.name = name;
      this.phone = phone;
      this.email=email;
   }


public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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
}
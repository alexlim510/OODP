package com.company.Utils;
import com.company.Entity.Customer;
import com.company.Entity.Staff;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Utils {
	
   public static ObjectOutputStream getObjectOutputStream(String fileName) throws IOException {
      return new ObjectOutputStream(new FileOutputStream(fileName));
   }
	
   public static void writeObject(String fileName, Object data) throws IOException {
      getObjectOutputStream(fileName).writeObject(data);
   }
	
   public static ObjectInputStream getObjectInputStream(String fileName) throws IOException {
      return new ObjectInputStream(new FileInputStream(fileName));
   }
	

   public static Object readObject(String fileName) throws IOException,ClassNotFoundException {
      return (Object)getObjectInputStream(fileName).readObject();
   }
	
   public static void storeCustomerCookie(Customer c) {
      try {
         ObjectOutputStream oos = getObjectOutputStream("usercookie.txt");
         oos.writeObject(c);
      } catch (IOException e) {			
         System.out.println(e);
      }
   }
	
   public static void storeAdminCookie(Staff s) {
      try {
         ObjectOutputStream oos = getObjectOutputStream("usercookie.txt");
         oos.writeObject(s);
      } catch (IOException e) {
         System.out.println(e);
      }
   }

   public static Customer getCustomerCookie() {
      try {
         return (Customer)getObjectInputStream("usercookie.txt").readObject();
      } catch (ClassNotFoundException e) {
         System.out.println(e);
      } catch (IOException e) {
         System.out.println(e);
      }
      return null;
   }

   public static Staff getAdminCookie() {
      try {
         return (Staff)getObjectInputStream("usercookie.txt").readObject();
      } catch (ClassNotFoundException e) {
         System.out.println(e);
      } catch (IOException e) {
         System.out.println(e);
      }
      return null;
   }

   public static void clearCookies() {
      try {
         ObjectOutputStream oos = getObjectOutputStream("usercookie.txt");
      
      }
      catch (IOException e) {
         System.out.println(e);
      }
   }

   public static void displayHeader(String headerText) {
      System.out.println("-------------------------------------------");
      System.out.println("   * * * * * * " + headerText + " * * * * * *    ");
      System.out.println("-------------------------------------------");
   }


   public static int getUserChoice(int startingNum, int endingNum) {
      int choice = -1;
      Scanner scanner = new Scanner(System.in);
   
      System.out.print("Choice: ");
      try {
         choice = scanner.nextInt();
      } catch (InputMismatchException e) {
         System.out.println("Invalid value! Please try again!");
         scanner.next(); // this consumes the invalid token
         return getUserChoice(startingNum, endingNum);
      }
   
      if (choice < startingNum || choice > endingNum) {
         System.out.println("Invalid value! Please try again!");
         return getUserChoice(startingNum, endingNum);
      }
   
      return choice;
   }

   public static String getStringInput(String inputMessage) {
      System.out.println(inputMessage);
      Scanner scanner = new Scanner(System.in);
      return scanner.nextLine();
   }
    
   public static LocalDateTime createLocalDateTime(int year, int month, int day, int hour, int minute) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm");
      String date = String.format("%d.%d.%d %d:%d",year,month,day,hour,minute);
      return LocalDateTime.parse(date, formatter);
   }

   public static float getFloatInput(String inputMessage){
      //just parse to get the integer
      float val = 0;
      boolean resolved = false;
      System.out.println(inputMessage);
      Scanner scanner = new Scanner(System.in);
      while(!resolved){
         try{
            resolved = true;
            val = Float.parseFloat(scanner.nextLine());
         }catch(Exception e){
            resolved = false;
            System.out.println("Please insert a number");
         }
      }
      return val;
   }

   public static int getDateIntInput(String message, int lowerLimit, int upperLimit){
      Scanner sc = new Scanner(System.in);
      int input = 0;
      boolean successful = false;
      while(!successful){
         System.out.print(message);
         try{
            successful = true;
            input = Integer.parseInt(sc.nextLine());
            if(input>upperLimit || input<lowerLimit){
               System.out.println("Please enter a valid value (limit = "+lowerLimit+" - "+upperLimit+")");
               successful = false;
            }
         }
         catch(java.lang.NumberFormatException e){
            System.out.println("Please insert a number");
            successful = false;
         } 
      }
      return input;
   }

   public static boolean retry(String message){
      Scanner sc = new Scanner(System.in);
      char ans;
      do{
         System.out.println(message+"? (y/n)");
         ans = (sc.nextLine()).charAt(0);
      }while(ans != 'y' && ans != 'n');

      return ans == 'y' ? true : false;
   }
}


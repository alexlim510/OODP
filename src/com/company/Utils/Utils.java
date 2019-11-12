package com.company.Utils;
import com.company.Entity.Customer;
import com.company.Entity.Staff;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Helper class, contains static methods
 * @author Alexander Lim
 * @version 1.0
 * @since 2019-11-12
 */
public class Utils {

    /**
     * get Object output stream
     * @param fileName file to get Object output stream from
     * @return Object output stream
     */
   public static ObjectOutputStream getObjectOutputStream(String fileName) {
      try {
         return new ObjectOutputStream(new FileOutputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

    /**
     * Write object to file
     * @param fileName file to write object to
     * @param data Data to write to file
     */
   public static void writeObject(String fileName, Object data) throws IOException {
      getObjectOutputStream(fileName).writeObject(data);
   }

    /**
     * get Object input stream
     * @param fileName file to get Object input stream from
     * @return Object input stream
     */
   public static ObjectInputStream getObjectInputStream(String fileName) {
      try {
         return new ObjectInputStream(new FileInputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

    /**
     * Read object from file
     * @param fileName file to read object from
     * @return Data contained in the file
     */
   public static Object readObject(String fileName) throws IOException, ClassNotFoundException {
      return (Object)getObjectInputStream(fileName).readObject();
   }

    /**
     * Writes Customer object to usercookie.txt
     * @param c Object of customer that is currently running the programming
     */
   public static void storeCustomerCookie(Customer c) {
      try {
         ObjectOutputStream oos = getObjectOutputStream("usercookie.txt");
         oos.writeObject(c);
      } catch (IOException e) {			
         System.out.println(e);
      }
   }

    /**
     * Get customer object from usercookie.txt
     * @return Customer object
     */
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

   /**
    * Displays standard header for UI
    * @param headerText text to be displayed in header
    */
   public static void displayHeader(String headerText) {
      System.out.println("-------------------------------------------");
      System.out.println("   * * * * * * " + headerText + " * * * * * *    ");
      System.out.println("-------------------------------------------");
   }

   /**
    * Gets user choice
    * @param startingNum number of first choice
    * @param endingNum number of last choice
    * @return user's choice
    */
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

   /**
    * Gets string inputs
    * @param inputMessage Message to be displayed before getting input
    * @return user input
    */
   public static String getStringInput(String inputMessage) {
      System.out.println(inputMessage);
      Scanner scanner = new Scanner(System.in);
      return scanner.nextLine();
   }

   /**
    * Creates LocalDateTime object
    * @param year year
    * @param month month
    * @param day day
    * @param hour hour
    * @param minute minute
    * @return LocalDateTime
    */
   public static LocalDateTime createLocalDateTime(int year, int month, int day, int hour, int minute) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm");
      String date = String.format("%d.%d.%d %d:%d",year,month,day,hour,minute);
      return LocalDateTime.parse(date, formatter);
   }

   /**
    * Returns date with day of week in the form of a string
    * @param localDateTime inputted LocalDateTime
    * @return String of LocalDateTime object
    */
   public static String createDayOfWeekString(LocalDateTime localDateTime){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm (E)");
      return localDateTime.format(formatter);

   }

   /**
    * Returns date with day of week in the form of a string
    * @param localDateTime inputted LocalDateTime
    * @return String of LocalDateTime object
    */
   public static String createTransactionDateString(LocalDateTime localDateTime){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
      return localDateTime.format(formatter);
   }

   /**
    * Gets float input from user
    * @param inputMessage message to be displayed before getting input
    * @return user's float input
    */
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

   /**
    * Gets date input from user, restricts date to be within specified range
    * @param message message to be displayed before getting user input
    * @param lowerLimit lower limit of date
    * @param upperLimit upper limit of date
    * @return user input
    */
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

   /**
    * Asks user if he/she wants to retry and get's their input
    * @param message message to be displayed before getting user's input
    * @return boolean depending on whether the user wants to retry
    */
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


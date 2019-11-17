package com.company.Utils;
import com.company.Entity.Customer;
import com.company.View.ListerInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Helper class, contains static methods
 * @author Group 2 - SS6
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
         System.out.println(e.getMessage());
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
         System.out.println(e.getMessage());
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }

   /**
    * Displays standard header for UI
    * @param headerText text to be displayed in header
    */
   public static void displayHeader(String headerText) {
      UserInputOutput.displayHeader(headerText);
   }

   /**
    * Gets user choice
    * @param startingNum number of first choice
    * @param endingNum number of last choice
    * @return user's choice
    */
   public static int getUserChoice(int startingNum, int endingNum) {
      return UserInputOutput.getUserChoice(startingNum, endingNum);
   }

   /**
    * Gets string inputs
    * @param inputMessage Message to be displayed before getting input
    * @return user input
    */
   public static String getStringInput(String inputMessage) {
      return UserInputOutput.getStringInput(inputMessage);
   }

   /**
    * Returns date with day of week in the form of a string
    * @param localDateTime inputted LocalDateTime
    * @return String of LocalDateTime object
    */
   public static String createDayOfWeekString(LocalDateTime localDateTime){
      return UserInputOutput.createDayOfWeekString(localDateTime);
   }

   /**
    * Returns date with day of week in the form of a string
    * @param localDateTime inputted LocalDateTime
    * @return String of LocalDateTime object
    */
   public static String createTransactionDateString(LocalDateTime localDateTime){
      return UserInputOutput.createTransactionDateString(localDateTime);
   }

   /**
    * Gets float input from user
    * @param inputMessage message to be displayed before getting input
    * @return user's float input
    */
   public static float getFloatInput(String inputMessage){
      return UserInputOutput.getFloatInput(inputMessage);
   }

   /**
    * Gets date input from user, restricts date to be within specified range
    * @param message message to be displayed before getting user input
    * @param lowerLimit lower limit of date
    * @param upperLimit upper limit of date
    * @return user input
    */
   public static int getDateIntInput(String message, int lowerLimit, int upperLimit){
      return UserInputOutput.getDateIntInput(message, lowerLimit, upperLimit);
   }

   /**
    * Asks user if he/she wants to retry and get's their input
    * @param message message to be displayed before getting user's input
    * @return boolean depending on whether the user wants to retry
    */
   public static boolean retry(String message){
      return UserInputOutput.retry(message);
   }
}


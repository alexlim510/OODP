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
public class FileIO {

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
}


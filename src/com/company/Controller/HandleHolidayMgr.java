package com.company.Controller;

import com.company.Entity.Price;
import com.company.Utils.FileIO;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This is the controller of the configuration of holidays/special dates.
 * The class is a part of StaffControl.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandleHolidayMgr {
    /**
     * This method adds holiday to the database.
     * @param year year of the date
     * @param month month of the date
     * @param day day of the date
     * @return true if addition of date is successful
     */
    public static boolean addHolidayMgr(int year, int month, int day){
        Price pricing = HandlePricingMgr.readPriceFile();
        try{
            if(LocalDateTime.of(year, month, day, 0, 0).isBefore(LocalDateTime.now())){
                System.out.println("The date has passed. Please insert future date.");
                return false;
            }
            pricing.addHoliday(LocalDateTime.of(year, month, day, 0, 0));
        }catch (IllegalArgumentException e){
            System.out.println("Parameter is not date/time. Please try again");
            return false;
        }catch (NullPointerException e){
            System.out.println("Cannot retrieve data from database");
            return false;
        }
        return HandlePricingMgr.modifyPriceDB(pricing);
    }

    /**
     * This method deletes holiday from the database.
     * @param dateTime LocalDateTime object which is retrieved from the UI, to be deleted from the database
     * @return true if deletion of date is successful
     */
    public static boolean deleteHolidayMgr(LocalDateTime dateTime){
        Price pricing = HandlePricingMgr.readPriceFile();
        try{
            pricing.removeHoliday(dateTime);
        }catch (IllegalArgumentException e){
            System.out.println("Parameter is not date/time. Please try again.");
            return false;
        }catch (NullPointerException e){
            System.out.println("Cannot retrieve data from database");
            return false;
        }
        return HandlePricingMgr.modifyPriceDB(pricing);
    }

    public static Price getPricing() {
        Price pricing = null;
        try {
            pricing = (Price) FileIO.readObject("price.txt");
        } catch (IOException e) {
            System.out.println("File is missing. Please try again");
        } catch (ClassNotFoundException e) {
            System.out.println("File is missing. Please try again");
        }
        return pricing;
    }
}

package com.company.Controller;

import com.company.Entity.Price;
import com.company.Utils.Utils;

import java.time.LocalDateTime;

public class HandleHolidayMgr {
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
}

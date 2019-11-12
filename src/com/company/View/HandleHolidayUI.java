package com.company.View;

import com.company.Controller.HandleHolidayMgr;
import com.company.Entity.Price;
import com.company.Utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class to configure holiday dates used for special pricing.
 * The class is a part of StaffUI.
 * @author GROUP 2
 * @version 1.0
 */

public class HandleHolidayUI {
    /**
     * This is the method to add holiday date
     */
    public static void addHolidayUI(){
        Utils.displayHeader("Adding Holiday/Special Date");
        Scanner sc = new Scanner(System.in);
        boolean successful = false;
        boolean tryAgain = true;
        int day;
        int month;
        int year;

        try {
            Price pricing = (Price) Utils.readObject("price.txt");
        } catch (IOException e) {
            System.out.println("File is missing. Please try again");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("File is missing. Please try again");
            return;
        }

        while(!successful && tryAgain){
            System.out.println("Insert the date of holiday/special day");
            day = Utils.getDateIntInput("Insert the day (in number)", 1, 31);
            month = Utils.getDateIntInput("Insert the month (in number)", 1, 12);
            year = Utils.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
            successful = HandleHolidayMgr.addHolidayMgr(year, month, day);
            if(!successful){
                tryAgain = Utils.retry("Retry");
            }else{
                System.out.println("Date added.");
            }
        }
    }

    /**
     * This is the method to delete holiday date
     */
    public static void deleteHolidayUI(){
        Utils.displayHeader("Adding Holiday/Special Date");
        Scanner sc = new Scanner(System.in);
        boolean successful = false;
        boolean tryAgain = true;
        int choice;

        Price pricing;
        try {
            pricing = (Price) Utils.readObject("price.txt");
        } catch (IOException e) {
            System.out.println("File is missing. Please try again");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("File is missing. Please try again");
            return;
        }
        ArrayList<LocalDateTime> holidayArray = pricing.getHolidays();

        while(!successful && tryAgain){
            System.out.println("Select the date you want to delete from holidays/special dates");
            for(int i = 0; i<holidayArray.size(); ++i){
                System.out.println(i+1+". "+holidayArray.get(i).getDayOfMonth()+"-"+
                                holidayArray.get(i).getMonth()+"-"+
                                holidayArray.get(i).getYear()
                        );
            }
            if(holidayArray.size()>0){
                choice = Utils.getUserChoice(1, holidayArray.size()) - 1;
            }else{
                System.out.println("Holiday list is empty!");
                return;
            }
            successful = HandleHolidayMgr.deleteHolidayMgr(holidayArray.get(choice));
            if(!successful){
                tryAgain = Utils.retry("Retry");
            }else{
                System.out.println("Date deleted.");
            }
        }
    }
}

package com.company.View;

import com.company.Controller.HandleHolidayMgr;
import com.company.Entity.Price;
import com.company.Utils.UserInputOutput;
import com.company.Utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the UI to configure holiday dates used for special pricing.
 * The class is a part of StaffUI.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */

public class HandleHolidayUI {
    /**
     * This method displays the UI to add a new Date to holiday database
     */
    public static void addHolidayUI(){
        UserInputOutput.displayHeader("Adding Holiday/Special Date");
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
            day = UserInputOutput.getDateIntInput("Insert the day (in number)", 1, 31);
            month = UserInputOutput.getDateIntInput("Insert the month (in number)", 1, 12);
            year = UserInputOutput.getDateIntInput("Insert the year (in number)", LocalDateTime.now().getYear(), 9999);
            successful = HandleHolidayMgr.addHolidayMgr(year, month, day);
            if(!successful){
                tryAgain = UserInputOutput.retry("Retry");
            }else{
                System.out.println("Date added.");
            }
        }
    }

    /**
     * This method displays the UI to delete a new Date to holiday database
     */
    public static void deleteHolidayUI(){
        UserInputOutput.displayHeader("Adding Holiday/Special Date");
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
                choice = UserInputOutput.getUserChoice(1, holidayArray.size()) - 1;
            }else{
                System.out.println("Holiday list is empty!");
                return;
            }
            successful = HandleHolidayMgr.deleteHolidayMgr(holidayArray.get(choice));
            if(!successful){
                tryAgain = UserInputOutput.retry("Retry");
            }else{
                System.out.println("Date deleted.");
            }
        }
    }
}

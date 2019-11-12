package com.company.View;

import com.company.Controller.HandleHolidayMgr;
import com.company.Entity.Cineplex;
import com.company.Entity.Price;
import com.company.Utils.Utils;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleHolidayUI {
    public static void main(String[] args){
        deleteHolidayUI();
    }

    public static void addHolidayUI(){
        Utils.displayHeader("Adding Holiday/Special Date");
        Scanner sc = new Scanner(System.in);
        boolean successful = false;
        boolean tryAgain = true;
        int day;
        int month;
        int year;

        Price pricing = (Price) Utils.readObject("price.txt");

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

    public static void deleteHolidayUI(){
        Utils.displayHeader("Adding Holiday/Special Date");
        Scanner sc = new Scanner(System.in);
        boolean successful = false;
        boolean tryAgain = true;
        int choice;

        Price pricing = (Price) Utils.readObject("price.txt");
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

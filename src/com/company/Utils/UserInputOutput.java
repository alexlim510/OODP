package com.company.Utils;

import com.company.View.ListerInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Helper class, To handle User input and output
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class UserInputOutput {

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
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input: ");
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

        return ans == 'y';
    }
}

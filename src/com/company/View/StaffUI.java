package com.company.View;

import com.company.Controller.StaffControl;
import com.company.TopMovies.Top5CurrentMovies;
import com.company.TopMovies.TopMovieFactory;
import com.company.Utils.UserInputOutput;


/**
 * This is the main UI Class for staff
 * @author Group 2 - SS6
 * @version 1.0
 */
public class StaffUI implements GeneralUI{
    /**
     * Initialize new StaffControl Class
     */
    private StaffControl staffControl = new StaffControl();

    /**
     * Main menu of StaffUI
     * @return boolean will return true if user choose to Exit
     */
    public void displayHomePage() {
        boolean exit = false;
        while(!exit){
            UserInputOutput.displayHeader("Staff Portal");
            System.out.println(
                    "1. Create/Update/Remove movie listing\n" +
                            "2. Create/Remove cinema showtimes\n" +
                            "3. Configure system settings\n" +
                            "4. List top 5 movies\n"+
                            "5. Exit\n"
            );
            switch (UserInputOutput.getUserChoice(1, 5)) {
                case 1:
                    displayStaffMovieOptions();
                    break;
                case 2:
                    displayStaffShowtimeOptions();
                    break;
                case 3:
                    displayStaffConfigurationOptions();
                    break;
                case 4:
                    staffControl.listTopMovies();
                    break;
                case 5:
                    exit = true;
            }
        }
    }

    /**
     * Movie menu for staff
     */
    public void displayStaffMovieOptions() {
        UserInputOutput.displayHeader("Modify Movie Listing");
        System.out.println(
                "1. Create movie listing\n" +
                        "2. Update movie listing\n" +
                        "3. Remove movie listing\n"+
                "4. Go back");
        switch (UserInputOutput.getUserChoice(1, 4)) {
            case 1:
                staffControl.addMovieListing();
                break;
            case 2:
                staffControl.editMovieListing();
                break;
            case 3:
                staffControl.deleteMovieListing();
                break;
            case 4:
                displayHomePage();
                break;
        }
    }

    /**
     * ShowTime menu for staff
     */
    public void displayStaffShowtimeOptions() {
        UserInputOutput.displayHeader("Modify Showtimes");
        System.out.println(
                "1. Create movie showtime\n" +
                        "2. Delete movie showtime\n"+
                        "3. Go back");

        switch (UserInputOutput.getUserChoice(1, 3)) {
            case 1:
                HandleShowTimeUI.addShowTimeUI();
                break;
            case 2:
                HandleShowTimeUI.deleteShowTimeUI();
                break;
            case 3:
                displayHomePage();
                break;
        }
    }
    /**
     * System Configuration for staff
     */
    public void displayStaffConfigurationOptions(){
        UserInputOutput.displayHeader("System Configuration");
        System.out.println(
                "1. Configure price\n" +
                        "2. Configure holidays/special dates\n"+
                        "3. Go back");
        switch (UserInputOutput.getUserChoice(1, 3)) {
            case 1:
                displayStaffConfigurationOptionsPricing();
                break;
            case 2:
                displayStaffConfigurationOptionsHoliday();
                break;
            case 3:
                displayHomePage();
                break;
        }
    }
    /**
     * Pricing menu for staff (Sub-category of System Configuration)
     */
    public void displayStaffConfigurationOptionsPricing(){
        UserInputOutput.displayHeader("Change ticket price");
        System.out.println(
                "1. Add new category\n" +
                        "2. Delete category\n"+
                        "3. Edit category\n"+
                        "4. Go back");
        switch (UserInputOutput.getUserChoice(1, 4)) {
            case 1:
                HandlePricingUI.addPriceCategoryUI();
                break;
            case 2:
                HandlePricingUI.deletePriceCategoryUI();
                break;
            case 3:
                HandlePricingUI.editPriceCategoryUI();
                break;
            case 4:
                displayStaffConfigurationOptions();
                break;
        }
    }
    /**
     * Holidays/Special Dates menu for staff (Sub-category of System Configuration)
     */
    public void displayStaffConfigurationOptionsHoliday(){
        UserInputOutput.displayHeader("Change Holidays/Special Price");
        System.out.println(
                "1. Add new category\n" +
                        "2. Delete category\n"+
                        "3. Go back");
        switch (UserInputOutput.getUserChoice(1, 3)) {
            case 1:
                HandleHolidayUI.addHolidayUI();
                break;
            case 2:
                HandleHolidayUI.deleteHolidayUI();
                break;
            case 3:
                displayStaffConfigurationOptions();
                break;
        }
    }
}
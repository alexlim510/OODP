package com.company.View;

import com.company.Controller.HandlePricingMgr;
import com.company.Utils.UserInputOutput;


import java.util.ArrayList;

/**
 * This is the class to configure pricing based on categories.
 * The class is a part of StaffUI.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandlePricingUI {
    /**
     * This is the UI to add new price category
     */
    public static void addPriceCategoryUI(){
        UserInputOutput.displayHeader("Adding Price Category");
        boolean successful = false;
        boolean retry = true;
        while(retry && !successful){
            successful = HandlePricingMgr.addPriceCategoryMgr();
            if(!successful){
                retry = UserInputOutput.retry("Retry");
            }
        }
        if(successful){
            System.out.println("Successfully added a new price category.");
        }
    }

    /**
     * This is the UI to delete price category
     */
    public static void deletePriceCategoryUI(){
        UserInputOutput.displayHeader("Delete Price Category");
        int userChoice;
        boolean successful = false;
        boolean retry = true;
        ArrayList<String> priceKeys;
        try{
            priceKeys = new ArrayList<String>(HandlePricingMgr.readPriceFile().getKeys());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return;
        }
        while(retry && !successful){
            System.out.println("Select category you want to delete:  ");
            for(int i = 0; i<priceKeys.size(); i++){
                System.out.println(i+1+". "+priceKeys.get(i));
            }
            userChoice = UserInputOutput.getUserChoice(1, priceKeys.size()) - 1;
            successful = HandlePricingMgr.deletePriceCategoryMgr(priceKeys.get(userChoice));
            if(!successful){
                retry = UserInputOutput.retry("Retry");
            }
        }
        if(successful){
            System.out.println("Successfully deleted the price category.");
        }
    }

    /**
     * This is the UI to to edit price category
     */
    public static void editPriceCategoryUI(){
        UserInputOutput.displayHeader("Edit Price Category");
        int userChoice;
        float newPrice;
        boolean successful = false;
        boolean retry = true;
        ArrayList<String> priceKeys;
        try{
            priceKeys = new ArrayList<String>(HandlePricingMgr.readPriceFile().getKeys());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return;
        }
        while(retry && !successful){
            System.out.println("Select category you want to edit:  ");
            for(int i = 0; i<priceKeys.size(); i++){
                System.out.println(i+1+". "+priceKeys.get(i));
            }
            userChoice = UserInputOutput.getUserChoice(1, priceKeys.size()) - 1;
            newPrice = UserInputOutput.getFloatInput("Insert the new price for the category: ");
            successful = HandlePricingMgr.editPriceCategoryMgr(priceKeys.get(userChoice), newPrice);
            if(!successful){
                retry = UserInputOutput.retry("Retry");
            }
        }
        if(successful){
            System.out.println("Successfully edited the price category.");
        }
    }
}

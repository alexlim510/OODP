package com.company.Controller;

import com.company.Entity.Price;
import com.company.Utils.Utils;

/**
 * This is the controller of the configuration of pricing.
 * The class is a part of StaffControl.
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class HandlePricingMgr {
    /**
     * This method reads the Price database
     * @return Price object, which is read from the database. Return null instead if the read action is unsuccessful.
     */
    public static Price readPriceFile(){
        Price pricing;
        try{
            pricing = (Price) Utils.readObject("price.txt");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Cannot read file");
            return null;
        }
        return pricing;
    }

    /**
     * This method modifies the Price database
     * @param pricing Price object is used to overwrite the current database.
     * @return true if the modification of the database is successful.
     */
    public static boolean modifyPriceDB(Price pricing){
        try{
            Utils.writeObject("price.txt", pricing);
        }catch (Exception e){
            System.out.println("Failed to modify the database. Please try again");
            return false;
        }
        return true;
    }

    /**
     * This method adds price category to the database
     * @return true if the addition is successful.
     */
    public static boolean addPriceCategoryMgr(){
        Price pricing = readPriceFile();
        try{
            pricing.addPrice(Utils.getStringInput("Insert the name of the category: "), Utils.getFloatInput("Insert the price of the category: "));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }catch (NullPointerException e){
            System.out.println("Cannot retrieve data from database");
            return false;
        }
        return modifyPriceDB(pricing);
    }

    /**
     * This method deletes price category to the database
     * @return true if the deletion is successful.
     */
    public static boolean deletePriceCategoryMgr(String catName){
        Price pricing = readPriceFile();
        try{
            pricing.deletePrice(catName);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }catch (NullPointerException e){
            System.out.println("Cannot retrieve data from database");
            return false;
        }
        return modifyPriceDB(pricing);
    }

    /**
     * This method edits price category to the database
     * @return true if the modification is successful.
     */
    public static boolean editPriceCategoryMgr(String catName, float newPrice){
        Price pricing = readPriceFile();
        try{
            pricing.updatePrice(catName, newPrice);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return false;
        }
        return modifyPriceDB(pricing);
    }
}


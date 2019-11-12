package com.company.Controller;

import com.company.Entity.Price;
import com.company.Utils.Utils;

public class HandlePricingMgr {
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

    public static boolean modifyPriceDB(Price pricing){
        try{
            Utils.writeObject("price.txt", pricing);
        }catch (Exception e){
            System.out.println("Failed to modify the database. Please try again");
            return false;
        }
        return true;
    }

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


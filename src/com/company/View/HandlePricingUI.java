package com.company.View;

import com.company.Controller.HandlePricingMgr;
import com.company.Entity.Price;
import com.company.Utils.Utils;
import jdk.jshell.execution.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class HandlePricingUI {
    public static void main(String[] args){
        Price pricing = HandlePricingMgr.readPriceFile();
        ArrayList<String> priceKeys= new ArrayList<String>();
        for(String st : pricing.getKeys()){
            priceKeys.add(st);
        }
        for(String st : priceKeys){
            System.out.println(st);
        }
    }

    public static void addPriceCategoryUI(){
        Price pricing = HandlePricingMgr.readPriceFile();
        Utils.displayHeader("Adding Price Category");
        boolean successful = false;
        boolean retry = true;
        while(retry && !successful){
            try{
                successful = true;
                pricing.addPrice(Utils.getStringInput("Insert the name of the category: "), Utils.getFloatInput("Insert the price of the category: "));
            }catch(IllegalArgumentException e){
                successful = false;
                System.out.println(e.getMessage());
                retry = Utils.retry("Retry");
            }
        }
        if(successful){
            try{
                Utils.writeObject("price.txt", pricing);
            }catch (Exception e){
                System.out.println("Failed to modify the database. Please try again");
                return;
            }
            System.out.println("Successfully added a new price category.");
        }
    }
}

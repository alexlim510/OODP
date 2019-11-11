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
        deletePriceCategoryUI();
    }

    public static void addPriceCategoryUI(){
        Utils.displayHeader("Adding Price Category");
        boolean successful = false;
        boolean retry = true;
        while(retry && !successful){
            successful = HandlePricingMgr.addPriceCategoryMgr();
            if(!successful){
                retry = Utils.retry("Retry");
            }
        }
        if(successful){
            System.out.println("Successfully added a new price category.");
        }
    }

    public static void deletePriceCategoryUI(){
        Utils.displayHeader("Delete Price Category");
        int userChoice;
        boolean successful = false;
        boolean retry = true;
        ArrayList<String> priceKeys= new ArrayList<String>();
        for(String st : HandlePricingMgr.readPriceFile().getKeys()){
            priceKeys.add(st);
        }
        while(retry && !successful){
            System.out.println("Select category you want to delete:  ");
            for(int i = 0; i<priceKeys.size(); i++){
                System.out.println(i+1+". "+priceKeys.get(i));
            }
            userChoice = Utils.getUserChoice(1, priceKeys.size()) - 1;
            successful = HandlePricingMgr.deletePriceCategoryMgr(priceKeys.get(userChoice));
            if(!successful){
                retry = Utils.retry("Retry");
            }
        }
        if(successful){
            System.out.println("Successfully deleted the price category.");
        }
    }


}

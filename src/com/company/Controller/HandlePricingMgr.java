package com.company.Controller;

import com.company.Entity.Price;
import com.company.Utils.Utils;

public class HandlePricingMgr {
    public static Price readPriceFile(){
        Price pricing = new Price();
        try{
            pricing = (Price) Utils.readObject("price.txt");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Cannot read file");
            return null;
        }
        return pricing;
    }
}

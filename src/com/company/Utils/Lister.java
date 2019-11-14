package com.company.Utils;

import com.company.Controller.MovieGoerController;
import com.company.Entity.Movie;

import java.util.ArrayList;

public class Lister {
    public static void list (ArrayList<? extends  ListerInterface> array){
        int i = 1;
        for(ListerInterface li: array){
            System.out.print(i+". ");
            li.listItself();
            i++;
        }
    }
}

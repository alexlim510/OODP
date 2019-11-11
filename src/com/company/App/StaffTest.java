package com.company.App;

import com.company.Utils.Utils;
import com.company.View.StaffUI;

public class StaffTest {

   public static void main(String[] args) {
      StaffUI staffUI = new StaffUI();
      boolean exit = false;
      while(!exit){
         exit = staffUI.displayMenu();
      }
   }
}

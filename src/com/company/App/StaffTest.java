package com.company.App;

import com.company.View.StaffUI;
import com.company.View.UIDisplay;

public class StaffTest {

   public static void main(String[] args) {
      StaffUI staffUI = new StaffUI();
      UIDisplay uid = new UIDisplay(staffUI);
      uid.displayHomePage();
   }
}

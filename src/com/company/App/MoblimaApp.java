package com.company.App;
import com.company.View.LoginUI;

/**
 * MoblimaApp enables customers to purchase movie tickets and also allows staff to configure
 * different features inside the application
 * @author Alexander Lim
 * @version 1.0
 * @since 2019-11-12
 */
public class MoblimaApp{
   public static void main(String[] args) {
      LoginUI log = new LoginUI();
      log.displayLoginPage();
   }
}
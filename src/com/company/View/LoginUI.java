package com.company.View;
import com.company.Controller.LoginController;
import com.company.Entity.Customer;
import com.company.Utils.Utils;

import java.util.Scanner;

import java.util.*;


public class LoginUI {
	
   public void displayLoginPage() {
      System.out.println("Select User Type: ");
      System.out.println("1) Admin");
      System.out.println("2) Movie Goer");
   	
      boolean exit = false;
      while(!exit) {
         Scanner sc = new Scanner(System.in);
         try {
            int choice = sc.nextInt();
            switch(choice) {
               case 1:
                  displayAdminLoginPage();
                  exit=true;
                  break;
               case 2:
                  displayPublicLoginPage();
                  exit=true;
                  break;
               default:
                  System.out.println("Please input 1 or 2.");
            }
         }
         catch (InputMismatchException e) {
            System.out.println("Please input an integer.");
         }			
      }
   }
	 
   public void displayPublicLoginPage() {
      LoginController logCtrl = new LoginController();
      Utils.displayHeader("Customer Login");
      boolean  credentialCheck = false;
      while(!credentialCheck) {
         System.out.println("Please input Email:");
         Scanner sc = new Scanner(System.in);
         String email = sc.nextLine();
         credentialCheck = logCtrl.checkCustomer(email);
         if(!credentialCheck) {
            System.out.println("Invalid account.");
         }
      }
      Customer c = Utils.getCustomerCookie();
      System.out.println("Your email: " + c.getEmail());
      MovieGoerUI mui = new MovieGoerUI();
   	//mui.getHomeView(Movies, Cineplex);
   }
	
   public void displayAdminLoginPage() {
      LoginController logCtrl = new LoginController(); 
      Utils.displayHeader("Admin Login");				
   	
   	//Check password
      boolean credentialCheck = false;
      while(!credentialCheck) {
         Scanner sc = new Scanner(System.in);
         System.out.println("Please input Username:");
         String username = sc.nextLine();
         System.out.println("Please input Password:");
         String password = sc.nextLine();
         credentialCheck = logCtrl.checkAdmin(username,password);
         if(!credentialCheck) {
            System.out.println("Invalid account.");
         }
      }
      Staff s = Utils.getAdminCookie();
      StaffUI sui = new StaffUI();
      sui.displayMenu();
   }
}

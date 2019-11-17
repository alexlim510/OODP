package com.company.Utils;

import com.company.Entity.Customer;

import java.io.IOException;
import java.util.ArrayList;

public class customerConfig {
    public static void main(String[] args){
        try {
            Customer cp1 = new Customer("john","98765432","john@gmail.com");
            Customer cp2 = new Customer("alex","87654321","alex@gmail.com");
            Customer cp3 = new Customer("drew","87654322","drew@gmail.com");
            ArrayList<Customer> cp = new ArrayList<Customer>();
            cp.add(cp1);
            cp.add(cp2);
            cp.add(cp3);
            FileIO.writeObject("customer.txt", (Object)cp);
            //oos.writeObject(cp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Customer> cpi = (ArrayList<Customer>) FileIO.readObject("customer.txt");
            for(Customer c: cpi) {
                System.out.println(c.getEmail());
                System.out.println(c.getName());
                System.out.println(c.getPhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package com.company.Utils;

import com.company.Entity.Staff;

import java.util.ArrayList;

public class staffConfig {
    public static void main(String[] args){
        Staff theStaff = new Staff("s1", "Staff1", "Staff1", "Staff Name");
        ArrayList<Staff> theStaffs= new ArrayList<Staff>();
        theStaffs.add(theStaff);

        try{
            Utils.writeObject("staff.txt", theStaffs);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

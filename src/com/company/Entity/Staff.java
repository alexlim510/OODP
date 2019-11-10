package com.company.Entity;
import java.io.Serializable;

public class Staff implements Serializable {
    private String staffID;
    private String userName;
    private String password;
    private String staffName;

    public Staff(String staffID, String userName, String password, String staffName) {
        this.staffID = staffID;
        this.userName = userName;
        this.password = password;
        this.staffName = staffName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


}

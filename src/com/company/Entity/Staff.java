package com.company.Entity;
import java.io.Serializable;

public class Staff extends Person implements Serializable {
    private String staffID;
    private String userName;
    private String password;

    public Staff(String staffID, String userName, String password, String staffName) {
        super(staffName);
        this.staffID = staffID;
        this.userName = userName;
        this.password = password;
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
}

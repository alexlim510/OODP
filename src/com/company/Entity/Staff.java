package com.company.Entity;
import com.company.Utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a Staff Class which inherits from the Person Class
 * @author Akarapu Bharadwaj
 * @version 1.0
 * @since 2019-11-12
 */
public class Staff extends Person implements Serializable {
    private String staffID;
    private String userName;
    private String password;

    /**
     * Creates a new Staff object with the given staffID, userName, password and staffName.
     * @param staffID This Staff's staffID
     * @param userName This Staff's userName
     * @param password This Staff's password
     * @param staffName This Staff's staffName
     */
    public Staff(String staffID, String userName, String password, String staffName) {
        super(staffName);
        this.staffID = staffID;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets staff id
     * @return staff id
     */
    public String getStaffID(){ return this.staffID;}

    /**
     * Gets the name of this Staff.
     * @return This staff's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Changes the userName of this Staff.
     * @param userName This Staff new userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Changes the password of this Staff.
     * @param password This Staff new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Validates Staff password
     * @param password password
     * @return boolean
     */
    public boolean validateCredentials(String password) {
        if(this.password.equals(password)) return true;
        else return false;
    }
}

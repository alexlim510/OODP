package com.company.Entity;
import java.io.Serializable;
public class Seat implements Serializable{
    private String seatID;
    private String row;
    private int column;
    private boolean isAssigned;

    public Seat(String seatID, String row, int column) {
        this.seatID = seatID;
        this.row = row;
        this.column = column;
        this.isAssigned=false;
    }

    public String getSeatID() {
        return seatID;
    }

    public void assignSeat() {
        this.isAssigned = true;
    }

    public void unAssignSeat() {
        this.isAssigned = false;
    }

    public String getRow() {
        return row;
    }
    
    public boolean getIsAssigned() {
    	return this.isAssigned;
    }
    
    public int getColumn() {
        return column;
    }
}

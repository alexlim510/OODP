package com.company.Entity;
import java.io.Serializable;

/**
 * Represents a seat in a movie shaw time
 * @author Alexander Lim
 * @version 1.0
 * @since 2019-11-12
 */
public class Seat implements Serializable{
    /**
     * ID of seat
     */
    private String seatID;
    /**
     * row of seat
     */
    private String row;
    /**
     * column of seat
     */
    private int column;
    /**
     * true if seats is assigned
     */
    private boolean isAssigned;

    /**
     * Creates seat given seatid, row and column
     * @param seatID SeatID of seat
     * @param row Row of seat
     * @param column Column of seat
     */
    public Seat(String seatID, String row, int column) {
        this.seatID = seatID;
        this.row = row;
        this.column = column;
        this.isAssigned=false;
    }

    /**
     * Gets id of seat
     * @return seatID
     */
    public String getSeatID() {
        return seatID;
    }

    /**
     * Assigns seat
     */
    public void assignSeat() {
        this.isAssigned = true;
    }

    /**
     * Gets row of seat
     * @return row
     */
    public String getRow() {
        return row;
    }

    /**
     * Check if seat is assigned
     * @return boolean
     */
    public boolean getIsAssigned() {
    	return this.isAssigned;
    }

    /**
     * gets column of seat
     * @return column
     */
    public int getColumn() {
        return column;
    }
}

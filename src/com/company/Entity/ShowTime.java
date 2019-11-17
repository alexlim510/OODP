package com.company.Entity;

import java.util.*;
import java.time.*;
import java.io.Serializable;

/**
 * Represents the show time of a movie in a cineplex
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-12
 */
public class ShowTime implements Serializable {
	/**
	 * Date and time of show time
	 */
    private LocalDateTime dateTime;
	/**
	 * Movie assigned to show time
	 */
	private Movie movie;
	/**
	 * Seats allocated to show time
	 */
    private ArrayList<ArrayList<Seat>> seats;
	/**
	 * Number of rows of seats in show time
	 */
	private int numRows = 9;
	/**
	 * Number of columns of seats in show time
	 */
    private int numColumns = 17;
	/**
	 * Array of rows in show time
	 */
	private String[] rowID = {"A","B","C","D","E","F","G","H","I"};

	/**
	 * Creates showtime given a LocalDateTime and movie
	 * @param time
	 * @param movie
	 */

    public ShowTime(LocalDateTime time, Movie movie) {
        this.dateTime = time;
        this.movie = movie;
        
        //initialise seats
		ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();
		for(int i =0; i<9;i++) {
			ArrayList<Seat> row = new ArrayList<Seat>(); 
			if(i<5) {
				for(int j=2; j<17; j++) {
					if(j==8) continue;
					row.add(new Seat("r"+rowID[i]+"c"+j,rowID[i],j));
				}
			}
			else{
				for(int j=0; j<17; j++) {
					if(j==8) continue;
					if(i==8 && (j==9 || j==10)) continue;
					row.add(new Seat("r"+rowID[i]+"c"+j,rowID[i],j));
				}
			}
			seats.add(row);
		}
		
		this.seats = seats;
    }

	/**
	 * Gets date and time of showtime
	 * @return date and time of showtime
	 */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

	/**
	 * Get movie being played at this show time
	 * @return movie
	 */
	public Movie getMovie() {
        return movie;
    }

	/**
	 * Set movie played at this show time
	 * @param movie movie
	 */
	public void setMovie(Movie movie) {
        this.movie = movie;
    }

	/**
	 * Get seat given the row and column
	 * @param row row of seat
	 * @param column column of seat
	 * @return Seat
	 */
    public Seat getSeat(String row, int column) {
    	int r = Arrays.asList(this.rowID).indexOf(row);
    	ArrayList<Seat> rowSeats = seats.get(r);
    	for(Seat s: rowSeats) {    		
    		if(s.getRow().equals(row)&&s.getColumn()==column) {
    			return s;
    		}
    	}
    	return null;
    }

	/**
	 * Get seat given the row and column
	 * @param row row of seat
	 * @param column column of seat
	 * @return Seat
	 */
	public Seat getSeat(int row, int column) {
    	ArrayList<Seat> rowSeats = seats.get(row);
    	for(Seat s: rowSeats) {
    		if(s.getRow().equals(rowID[row])&&s.getColumn()==column) {
    			return s;
    		}
    	}
    	return null;
    }

	/**
	 * Get seat given the id of the seat
	 * @param seatID ID of seat
	 * @return Seat
	 */
	public Seat getSeat(String seatID) {
		for(ArrayList<Seat> row : this.seats){
			for(Seat seat : row){
				if(seat.getSeatID().equals(seatID)){
					return seat;
				}
			}
		}
		return null;
	}

	/**
	 * Occupies seat given the seat id
	 * @param seatID seatID
	 */
	public void occupySeat(String seatID){
		for(ArrayList<Seat> row : this.seats){
			for(Seat seat : row){
				if(seat.getSeatID().equals(seatID)){
					seat.assignSeat();
				}
			}
		}
	}

	/**
	 * compares the date of this show time with given show time
	 * @param st show time
	 * @return boolean
	 */
	public boolean isEqual(ShowTime st){
    	if(st.getDateTime().isEqual(this.dateTime) && st.movie.getTitle().equals(this.movie.getTitle())){
    		return true;
		}
    	return false;
	}

	/**
	 * gets rowID of this seat
	 * @return rowID
	 */
    public String[] getRowID() {
    	return rowID;
    }

	/**
	 * get rowID given an index
	 * @param i index
	 * @return rowID
	 */
	public String getRowID(int i) {
    	return rowID[i];
    }

	/**
	 * get number of rows of seats in this show time
	 * @return number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * set number of rows of seats in this show time
	 * @param numRows number of rows
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * get number of columns of seats in this show time
	 * @return number of columns
	 */
	public int getNumColumns() {
		return numColumns;
	}

	/**
	 * sets number of columns of seats in this show time
	 * @param numColumns number of columns
	 */
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
}

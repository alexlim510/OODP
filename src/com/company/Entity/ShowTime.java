package com.company.Entity;

import java.util.*;
import java.time.*;
import java.io.Serializable;
public class ShowTime implements Serializable {
    private LocalDateTime dateTime;
    private Movie movie;
    private ArrayList<ArrayList<Seat>> seats;
    private int numRows = 9;
    private int numColumns = 17;
	private String[] rowID = {"A","B","C","D","E","F","G","H","I"};
	
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


    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<ArrayList<Seat>> seats) {
        this.seats = seats;
    }

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
    
    public Seat getSeat(int row, int column) {
    	ArrayList<Seat> rowSeats = seats.get(row);
    	for(Seat s: rowSeats) {
    		if(s.getRow().equals(rowID[row])&&s.getColumn()==column) {
    			return s;
    		}
    	}
    	return null;
    }
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

	public void occupySeat(String seatID){
		for(ArrayList<Seat> row : this.seats){
			for(Seat seat : row){
				if(seat.getSeatID().equals(seatID)){
					seat.assignSeat();
				}
			}
		}
	}

	public boolean isEqual(ShowTime st){
    	if(st.getDateTime().isEqual(this.dateTime) && st.movie.getTitle().equals(this.movie.getTitle())){
    		return true;
		}
    	return false;
	}
    public String[] getRowID() {
    	return rowID;
    }
    
    public String getRowID(int i) {
    	return rowID[i];
    }
    
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
}

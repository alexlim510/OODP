package com.company.Entity;

import java.util.Date;

public class Ticket {

    private float ticketPrice;
    private Date date;
    private boolean isHoliday;
    private String ageType;
    private String movieTitle;
    private String cinemaCID;
    private String seatID;
    private String showtime;

    public Ticket(float ticketPrice, Date date, boolean isHoliday, String ageType, String movieTitle, String cinemaCID, String seatID, String showtime) {
        this.ticketPrice = ticketPrice;
        this.date = date;
        this.isHoliday = isHoliday;
        this.ageType = ageType;
        this.movieTitle = movieTitle;
        this.cinemaCID = cinemaCID;
        this.seatID = seatID;
        this.showtime = showtime;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public String getAgeType() {
        return ageType;
    }

    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCinemaCID() {
        return cinemaCID;
    }

    public void setCinemaCID(String cinemaCID) {
        this.cinemaCID = cinemaCID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }
}

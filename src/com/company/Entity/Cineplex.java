package com.company.Entity;
import java.util.*;
import java.io.Serializable;

public class Cineplex implements Serializable{
	private String cineplexName;
	private ArrayList<Cinema> cinemas;
	
	public Cineplex(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	
	public String getCineplexName() {
		return cineplexName;
	}
	
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	
}

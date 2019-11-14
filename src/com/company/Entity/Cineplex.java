package com.company.Entity;
import java.util.*;
import java.io.Serializable;

/**
 * This is the Entity Class of Cineplex
 * @author Group 2 - SS6
 * @version 1.0
 */
public class Cineplex implements Serializable{
	/**
	 * Name of cinplex
	 */
	private String cineplexName;
	/**
	 * array of cinema in cineplex
	 */
	private ArrayList<Cinema> cinemas;

	/**
	 *Constructor of Cineplex
	 * @param cineplexName Name of Cineplex
	 */
	public Cineplex(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * Get name of Cineplex
	 * @return name of Cineplex
	 */
	public String getCineplexName() {
		return cineplexName;
	}

	/**
	 * Set name of cineplex
	 * @param cineplexName
	 */
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}


	/**
	 * get array of Cinema in Cineplex
	 * @return Array of cinema in Cineplex
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	/**
	 * Get Cinema of a specific type (Platinum, normal etc)
	 * @param cinemaType Type of cinema
	 * @return return cinema of specified type
	 */
	public ArrayList<Cinema> getCinemas(String cinemaType) {
		ArrayList<Cinema> cinemas = new ArrayList<>();
		for(Cinema c:this.cinemas){
			if(c.getCinemaType().equals(cinemaType)){
				cinemas.add(c);
			}
		}
		return cinemas;
	}


	/**
	 *Set cinemas in the Cineplex
	 * @param cinemas array of cinema
	 */
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	
}

package com.company.Entity;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.io.Serializable;

/**
 * Represents the price to be charged on the tickets
 */
public class Price implements Serializable{
	/**
	 * constant for Friday
	 */
	private static final int FRIDAY = 4;
	/**
	 * constant for Saturday
	 */
	private static final int SATURDAY= 5;
	/**
	 * constant for Sunday
	 */
	private static final int SUNDAY = 6;
	/**
	 * Hashmap with key: Price category and value: price
	 */
	private HashMap<String,Float> prices;
	/**
	 * Array list of dates of holidays
	 */
	private ArrayList<LocalDateTime> holidays;
	/**
	 * List of weekend constants
	 */
	private List<Integer> weekends;

	/**
	 * Creates Price
	 */
	public Price() {
		this.prices = new HashMap<String,Float>();
		this.prices.put("Adult", 7f);
		this.prices.put("Senior Citizen", 5f);
		this.prices.put("Child", 5f);
		this.prices.put("Holiday", 3f);
		this.prices.put("Weekend", 2f);
		this.prices.put("Blockbuster", 2f);
		this.prices.put("3D", 4f);
		this.prices.put("Platinum Movie Suites", 10f);
		this.prices.put("Normal",2f);
		this.holidays = new ArrayList<LocalDateTime>();
		this.weekends = Arrays.asList(FRIDAY, SATURDAY, SUNDAY);
	}

	/**
	 * get all of the price categories
	 * @return price categories
	 */
	public Set<String> getKeys(){
		return this.prices.keySet();
	}

	/**
	 * gets all of the holidays
	 * @return holidays
	 */
	public ArrayList<LocalDateTime> getHolidays() { return this.holidays; }

	/**
	 * Add price category given a category and a price
	 * @param category category
	 * @param price price
	 */
	public void addPrice(String category, float price) {
		if(prices.containsKey(category)) {
			throw new IllegalArgumentException("Price category is already present.");
		}
		prices.put(category, price);
	}

	/**
	 * remove price category
	 * @param category price category
	 */
	public void deletePrice(String category) {
		if(prices.containsKey(category)) {
			prices.remove(category);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}

	/**
	 * Update price of price category
	 * @param category price category
	 * @param price price
	 */
	public void updatePrice(String category, float price) {
		if(prices.containsKey(category)) {
			prices.replace(category, price);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}

	/**
	 * Get price given a price category
	 * @param category price category
	 * @return price
	 */
	public float getPrice(String category) {
		if(prices.containsKey(category)) {
			return prices.get(category);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}

	/**
	 * calculates total price given an array list of price categories
	 * @param categories array list of price categories
	 * @return total price
	 */
	public float getPrice(ArrayList<String> categories) {
		float price = 0;
		for(String category: categories){
			price = price + getPrice(category);
		}
		return price;
	}

	/**
	 * adds date of holiday
	 * @param holiday date of holiday
	 */
	public void addHoliday(LocalDateTime holiday) {
		if(holidays.contains(holiday)) {
			throw new IllegalArgumentException("Holiday is already present.");
		}
		else {
			holidays.add(holiday);
		}
	}

	/**
	 * removes date of holiday
	 * @param holiday date of holiday
	 */
	public void removeHoliday(LocalDateTime holiday) {
		if(holidays.contains(holiday)) {
			holidays.remove(holiday);
		}
		else {
			throw new IllegalArgumentException("Holiday does not exist.");
		}
	}

	/**
	 * Add weekend
	 * @param weekend weekend constant
	 */
	public void addWeekend(int weekend) {
		if(weekends.contains(weekend)) {
			throw new IllegalArgumentException("Weekend is already present.");
		}
		else {
			weekends.add(weekend);			
		}
	}

	/**
	 * remove weekend
	 * @param weekend weekend constant
	 */
	public void removeWeekend(int weekend) {
		if(weekends.contains(weekend)) {
			weekends.remove(weekend);
		}
		else {
			throw new IllegalArgumentException("Weekend does not exist.");
		}
	}

	/**
	 * check if day is weekend
	 * @param day day constant
	 * @return true or false depending if given day is a weekend
	 */
	public boolean isWeekend(int day){
		if(weekends.contains(day)){
			return true;
		}
		return false;
	}

	/**
	 * checks if date is a holiday
	 * @param date date
	 * @return true or false depending if given day is a holiday
	 */
	public boolean isHoliday(LocalDateTime date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String chosenDate = date.format(formatter);
		for(LocalDateTime holiday: holidays){
			if(holiday.format(formatter).equals(chosenDate)) return true;
		}
		return false;
	}
}

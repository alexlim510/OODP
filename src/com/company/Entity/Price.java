package com.company.Entity;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.io.Serializable;

public class Price implements Serializable{
	private static final int FRIDAY = 4;
	private static final int SATURDAY= 5;
	private static final int SUNDAY = 6;
	private HashMap<String,Float> prices;
	private ArrayList<LocalDateTime> holidays;
	private List<Integer> weekends;
	
	public Price() {
		this.prices = new HashMap<String,Float>();
		this.prices.put("Adult", 7f);
		this.prices.put("Senior Citizen", 5f);
		this.prices.put("Child", 5f);
		this.prices.put("Holiday", 3f);
		this.prices.put("Weekend", 2f);
		this.prices.put("Blockbuster", 2f);
		this.prices.put("3D", 2f);
		this.prices.put("Platinum Movie Suites", 10f);
		this.prices.put("Normal",2f);
		this.holidays = new ArrayList<LocalDateTime>();
		this.weekends = Arrays.asList(FRIDAY, SATURDAY, SUNDAY);
	}
	
	public void addPrice(String category, float price) {
		if(prices.containsKey(category)) {
			throw new IllegalArgumentException("Price category is already present.");
		}
		prices.put(category, price);
	}
	
	public void deletePrice(String category) {
		if(prices.containsKey(category)) {
			prices.remove(category);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}
	
	public void updatePrice(String category, float price) {
		if(prices.containsKey(category)) {
			prices.replace(category, price);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}
	
	public float getPrice(String category) {
		if(prices.containsKey(category)) {
			return prices.get(category);
		}
		else {
			throw new IllegalArgumentException("Price category does not exist.");
		}
	}

	public float getPrice(ArrayList<String> categories) {
		float price = 0;
		for(String category: categories){
			price = price + getPrice(category);
		}
		return price;
	}

	public void addHoliday(LocalDateTime holiday) {
		if(holidays.contains(holiday)) {
			throw new IllegalArgumentException("Holiday is already present.");
		}
		else {
			holidays.add(holiday);
		}
	}
	
	public void removeHoliday(LocalDateTime holiday) {
		if(holidays.contains(holiday)) {
			holidays.remove(holiday);
		}
		else {
			throw new IllegalArgumentException("Holiday does not exist.");
		}
	}
	
	public void addWeekend(int weekend) {
		if(weekends.contains(weekend)) {
			throw new IllegalArgumentException("Weekend is already present.");
		}
		else {
			weekends.add(weekend);			
		}
	}
	
	public void removeWeekend(int weekend) {
		if(weekends.contains(weekend)) {
			weekends.remove(weekend);
		}
		else {
			throw new IllegalArgumentException("Weekend does not exist.");
		}
	}

	public boolean isWeekend(int day){
		if(weekends.contains(day)){
			return true;
		}
		return false;
	}

	public boolean isHoliday(LocalDateTime date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String chosenDate = date.format(formatter);
		for(LocalDateTime holiday: holidays){
			if(holiday.format(formatter).equals(chosenDate)) return true;
		}
		return false;
	}
}

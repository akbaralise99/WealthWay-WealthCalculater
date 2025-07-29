package com.WealthWay.WealthCalulater.model;

import java.math.BigDecimal;

public class wealthDto {
	private int currentAge;  // need age 
	private int numberOfYears; // number of years 
	private double annualRetrnRate;  // return rate 
	public int getCurrentAge() {
		return currentAge;
	}
	public int getNumberOfYears() {
		return numberOfYears;
	}
	public double getAnnualRetrnRate() {
		return annualRetrnRate;
	}
	public void setCurrentAge(int age) {
		this.currentAge = age;
	}
	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}
	public void setAnnualRetrnRate(double annualRetrnRate) {
		this.annualRetrnRate = annualRetrnRate;
	}
	

}

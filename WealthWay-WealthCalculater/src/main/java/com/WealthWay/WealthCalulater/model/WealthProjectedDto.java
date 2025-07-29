package com.WealthWay.WealthCalulater.model;

import java.math.BigDecimal;


public class WealthProjectedDto  extends wealthDto{
	
	private String totalWealthCreated;
	private String onYourFutureAge;
	private String investedAmount;
	private String profitAmount;  
	private String interestEarned;
	private String rate;
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	private String message;

	public String getTotalWealthCreated() {
		return totalWealthCreated;
	}

	public void setTotalWealthCreated(String wealthCretaed) {
		this.totalWealthCreated = wealthCretaed;
	}

	public String getOnYourFutureAge() {
		return onYourFutureAge;
	}

	public String getInvestedAmount() {
		return investedAmount;
	}

	public String getProfitAmount() {
		return profitAmount;
	}

	public void setOnYourFutureAge(String onYourAge) {
		this.onYourFutureAge = onYourAge;
	}

	public void setInvestedAmount(String investedAmount) {
		this.investedAmount = investedAmount;
	}

	public void setProfitAmount(String profitAmount) {
		this.profitAmount = profitAmount;
	}
	
	public String getInterestEarned() {
		return interestEarned;
	}

	public String getMessage() {
		return "your total wealth will be "+this.totalWealthCreated +"on your age "+this.onYourFutureAge +" you earned " + this.profitAmount + "on yout invested amount "+ this.investedAmount + "with the Interest " + this.interestEarned + "with the Rate "+this.rate;
	}

	public void setInterestEarned(String interestEarned) {
		this.interestEarned = interestEarned;
	}

	


}

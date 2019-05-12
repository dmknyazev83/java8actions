package com.dto;

public class TranspirationIndex {
	
	private int days;
	private double transpirationIntensity;
	
	public TranspirationIndex(int days, double transpirationIntensity) {
		super();
		this.days = days;
		this.transpirationIntensity = transpirationIntensity;
	}

	public int getDays() {
		return days;
	}

	public double getTranspirationIntensity() {
		return transpirationIntensity;
	}	

}

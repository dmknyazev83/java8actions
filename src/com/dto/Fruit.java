package com.dto;

public class Fruit {
	
	private int weight;
	private Color color;
	private String country;
	
	public Fruit(int weight, Color color, String country) {
		super();
		this.weight = weight;
		this.color = color;
		this.country = country;
	}

	public int getWeight() {
		return weight;
	}

	public Color getColor() {
		return color;
	}

	public String getCountry() {
		return country;
	}	
	
	@Override
	public String toString(){
		return this.color + " " + this.weight + "  " + this.country;
	}

}

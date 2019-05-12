package com.dto;

import java.util.HashMap;
import java.util.Map;

public class Fruit {
	
	private int weight;
	private Color color;
	private String country;
	private Map<Integer, TranspirationIndex> fruitTranspirationStat;
	
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
	
	public Map<Integer, TranspirationIndex> getFruitTranspirationStat() {
		if(fruitTranspirationStat == null){
			fruitTranspirationStat = new HashMap<Integer, TranspirationIndex>();
		}
		return fruitTranspirationStat;
	}

	public void setFruitTranspirationStat(Map<Integer, TranspirationIndex> fruitTranspirationStat) {
		this.fruitTranspirationStat = fruitTranspirationStat;
	}

	@Override
	public String toString(){
		return this.color + " " + this.weight + "  " + this.country;
	}

}

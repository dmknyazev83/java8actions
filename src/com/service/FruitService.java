package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dto.Color;
import com.dto.Fruit;

public class FruitService implements IFruitService {
	
	private final String[] COUNTRIES = {"Italy", "Germany", "Russia", "Spain", "France"};
	
	

	@Override
	public List<Fruit> generateFruitCollection() {
		return createFruitList(Constants.DEFAULT_SIZE);
	}

	@Override
	public List<Fruit> generateFruitCollection(int size) {
		return createFruitList(size);
	}
	
	private List<Fruit> createFruitList(int size){
		final List<Fruit> fruitList = new ArrayList<>(Constants.DEFAULT_SIZE);
		final Random rWeigth = new Random();
		final Random rColor = new Random();
		final Random rCountry = new Random();
		IFruitCreator<Integer, Color, String, Fruit> creator = Fruit :: new;

		for(int i = 1; i <= size; i++){
			final Fruit fruit = creator.create(rWeigth.ints(2, 50).findFirst().getAsInt(), 
					Color.getByIntValue(rColor.ints(1, 5).findFirst().getAsInt()),
					COUNTRIES[rCountry.ints(0, COUNTRIES.length).findFirst().getAsInt()]);
			fruitList.add(fruit);
		}
		return fruitList;
	}

}

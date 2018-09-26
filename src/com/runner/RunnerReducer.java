package com.runner;

import java.util.List;

import com.dto.Fruit;
import com.service.FruitService;
import com.service.IFruitService;

public class RunnerReducer {

	public static void main(String[] args) {
		IFruitService fruitService = new FruitService();
		final List<Fruit> fruits = fruitService.generateFruitCollection(4);
		printList(fruits);
		System.out.println("-------------------------------------------");
		int totalWeight = fruits.stream().map((Fruit fruit) -> fruit.getWeight()).reduce(0, (fruit1, fruit2) -> fruit1 + fruit2);
		System.out.println("totalWeight = " + totalWeight);

	}
	
	private static void printList(List<Fruit> fruits){
		fruits.stream().forEach(item -> System.out.println(item));
	}

}

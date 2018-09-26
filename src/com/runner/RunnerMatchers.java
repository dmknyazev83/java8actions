package com.runner;

import java.util.List;

import com.dto.Fruit;
import com.service.FruitService;
import com.service.IFruitService;

public class RunnerMatchers {

	public static void main(String[] args) {
		IFruitService fruitService = new FruitService();
		final List<Fruit> fruits = fruitService.generateFruitCollection(40);
		printList(fruits);
		System.out.println("-------------------------------------------");
		boolean anyFranceFruit = fruits.stream().anyMatch(fruit -> fruit.getCountry().equals("France"));
		System.out.println("anyFranceFruit = " + anyFranceFruit);
		System.out.println("-------------------------------------------");
		boolean noneHeavierThan50 = fruits.stream().noneMatch(fruit -> fruit.getWeight() > 50);
		System.out.println("noneHeavierThan50 = " + noneHeavierThan50);
		System.out.println("-------------------------------------------");
		fruits.stream().filter(fruit -> fruit.getCountry().equals("Italy")).findAny().ifPresent(item -> System.out.println(item));

	}
	
	private static void printList(List<Fruit> fruits){
		fruits.stream().forEach(item -> System.out.println(item));
	}

}

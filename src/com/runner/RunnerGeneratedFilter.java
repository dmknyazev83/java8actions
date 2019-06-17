package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.dto.Fruit;
import com.service.FruitService;
import com.service.IFruitService;

public class RunnerGeneratedFilter {

	public static void main(String[] args) {
		IFruitService fruitService = new FruitService();
		final List<Fruit> fruits = fruitService.generateFruitCollection(40);
		printList(fruits);
		
		List<Long> numbers = Arrays.asList(2L, 5L, 3L, 12L, 6L, 3L, 4L, 2L);
		List<Long> evenNmbers = numbers.stream().filter(item -> item%2 == 0).distinct().collect(Collectors.toList());
		System.out.println("even numbers");
		evenNmbers.stream().forEach(item -> System.out.print( "  " +item));
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("First 5 Friuts from Italy");
		final List<Fruit> someItalyFruits = fruits.stream()
				.filter(fr -> fr.getCountry().equalsIgnoreCase("Italy")).limit(5)
				.collect(Collectors.toList());
		printList(someItalyFruits);
		System.out.println("-------------------------------------------");
		System.out.println("Skip first 7 Friuts from Italy");
		final List<Fruit> someItalyFruits2 = fruits.stream()
				.filter(fr -> fr.getCountry().equalsIgnoreCase("Italy")).skip(7)
				.collect(Collectors.toList());
		printList(someItalyFruits2);

	}
	
	private static void printList(List<Fruit> fruits){
		fruits.stream().forEach(item -> System.out.println(item));
	}

}

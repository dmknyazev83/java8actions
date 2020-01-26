package com.runner;

import com.dto.Color;
import com.dto.Fruit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class StreamCollectorsTest {
	
	@Test
	public void streamCollectorsMaxMin(){
		Fruit f1 = new Fruit(12, Color.ORANGE, "Spain");
		Fruit f2 = new Fruit(10, Color.ORANGE, "Spain");
		Fruit f3 = new Fruit(15, Color.ORANGE, "Spain");
		Fruit f4 = new Fruit(12, Color.ORANGE, "Spain");
		Fruit f5 = new Fruit(11, Color.ORANGE, "Spain");
		Fruit f6 = new Fruit(13, Color.ORANGE, "Spain");
		Fruit f7 = new Fruit(11, Color.ORANGE, "Spain");
		Fruit f8 = new Fruit(14, Color.ORANGE, "Spain");
		
		Stream<Fruit> streamFruit = Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8).stream();
		Optional<Fruit> maxWeightFruit = streamFruit.collect(Collectors.maxBy(Comparator.comparingInt(Fruit::getWeight)));
		
		Assert.assertTrue(maxWeightFruit.isPresent());
		Assert.assertEquals(15, maxWeightFruit.get().getWeight());
		
		streamFruit = Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8).stream();
		Optional<Fruit> minWeightFruit = streamFruit.collect(Collectors.minBy(Comparator.comparingInt(Fruit::getWeight)));
		
		Assert.assertTrue(minWeightFruit.isPresent());
		Assert.assertEquals(10, minWeightFruit.get().getWeight());
	}
	
	@Test
	public void streamCollectorsSummarizingAvaraging(){
		Fruit f1 = new Fruit(3, Color.GREEN, "Greece");
		Fruit f2 = new Fruit(8, Color.GREEN, "Greece");
		Fruit f3 = new Fruit(5, Color.GREEN, "Greece");
		Fruit f4 = new Fruit(4, Color.GREEN, "Greece");
		Fruit f5 = new Fruit(6, Color.GREEN, "Greece");
		Fruit f6 = new Fruit(2, Color.GREEN, "Greece");
		Fruit f7 = new Fruit(7, Color.GREEN, "Greece");
		Fruit f8 = new Fruit(9, Color.GREEN, "Greece");
		
		Stream<Fruit> streamFruit = Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8).stream();
		IntSummaryStatistics fruitWeightSummary = streamFruit.collect(Collectors.summarizingInt(Fruit :: getWeight));
		Assert.assertEquals(44, fruitWeightSummary.getSum());
		Assert.assertEquals(2, fruitWeightSummary.getMin());
		Assert.assertEquals(9, fruitWeightSummary.getMax());
	}
	
	@Test
	public void streamCollectorsJoining(){
		Fruit f1 = new Fruit(3, Color.GREEN, "Greece");
		Fruit f2 = new Fruit(8, Color.ORANGE, "Spain");
		Fruit f3 = new Fruit(5, Color.YELLOW, "Italy");
		Fruit f4 = new Fruit(4, Color.RED, "Cyprus");
		
		Stream<Fruit> streamFruit = Arrays.asList(f1, f2, f3, f4).stream();
		String allFruits = streamFruit.map(Fruit :: toString).collect(Collectors.joining("; "));
		Assert.assertTrue(allFruits != null);
		System.out.println(allFruits);

	}
	
	@Test
	public void streamCollectorsReducing(){
		Fruit f1 = new Fruit(20, Color.GREEN, "Poland");
		Fruit f2 = new Fruit(23, Color.GREEN, "Poland");
		Fruit f3 = new Fruit(44, Color.GREEN, "Poland");
		Fruit f4 = new Fruit(38, Color.GREEN, "Poland");
		
		Stream<Fruit> streamFruit = Arrays.asList(f1, f2, f3, f4).stream();
		Stream<Fruit> streamFruitEmpty = Stream.empty();
		int totalFruitWeight = streamFruit.collect(Collectors.reducing(0, Fruit :: getWeight, (item1, item2) -> item1+item2));
		int totalFruitWeightEmpty = streamFruitEmpty.collect(Collectors.reducing(0, Fruit :: getWeight, (item1, item2) -> item1+item2));
		
		Assert.assertEquals(0, totalFruitWeightEmpty);
		Assert.assertEquals(125, totalFruitWeight);
	}

}

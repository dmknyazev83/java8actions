package com.runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.dto.Fruit;
import com.dto.Trader;
import com.dto.transaction.Transaction;
import com.service.FruitService;
import com.service.IFruitService;

import org.junit.Assert;

public class ParallelStreamTest {

	private static final int FRUITS_COLLECTION_SIZE = 2000;

	private List<Fruit> fruits;

	@Before
	public void init() {
		IFruitService fruitService = new FruitService();
		fruits = fruitService.generateFruitCollection(FRUITS_COLLECTION_SIZE);
	}

	@Test
	public void parallelStreamMap() {
		Map<Long, List<Fruit>> mapWithParallel = fruits.stream().map(this::generateFruitThreadHolder).parallel()
				.collect(Collectors.groupingBy(FruitThreadHolder::getThreadId,
						Collectors.mapping(FruitThreadHolder::getFruit, Collectors.toList())));

		Map<Long, List<Fruit>> mapNoParallel = fruits.stream().map(this::generateFruitThreadHolder)
				.collect(Collectors.groupingBy(FruitThreadHolder::getThreadId,
						Collectors.mapping(FruitThreadHolder::getFruit, Collectors.toList())));

		Assert.assertFalse(mapWithParallel.keySet().size() == mapNoParallel.keySet().size());
	}

	@Test
	public void parallelStreamSideEffect() {
		Set<Long> calculatedWeightValues = new HashSet<>();
		for (int i = 1; i <= 8; i++) {
			long value = getTotalWeighOfAllFruits(this.fruits);
			System.out.println(value);
			calculatedWeightValues.add(value);
		}

		Assert.assertFalse(calculatedWeightValues.size() == 1);
	}

	@Test
	public void parallelStreamTransactionTest() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		Trader trader = new Trader("Vladimir", "Paris");
		int year2011 = 2011;
		int year2020 = 2020;
		int year2018 = 2018;
		int year2015 = 2015;
		List<Transaction> year2011List = createTransactionList(20, trader, year2011);
		List<Transaction> year2020List = createTransactionList(7, trader, year2020);
		List<Transaction> year2018List = createTransactionList(33, trader, year2018);
		List<Transaction> year2015List = createTransactionList(20, trader, year2015);
		transactionList.addAll(year2011List);
		transactionList.addAll(year2020List);
		transactionList.addAll(year2018List);
		transactionList.addAll(year2015List);
		
		long startOrdinary = System.currentTimeMillis();
		Map<Integer, IntSummaryStatistics> txYearToSummaryOrdinary = transactionList.stream()
				.collect(Collectors.groupingBy(Transaction::getYear, Collectors.summarizingInt(Transaction::getValue)));
		long endOrdinary = System.currentTimeMillis();
		
		long startParallel = System.currentTimeMillis();
		Map<Integer, IntSummaryStatistics> txYearToSummaryParallel = transactionList.stream().parallel()
				.collect(Collectors.groupingBy(Transaction::getYear, Collectors.summarizingInt(Transaction::getValue)));
		long endParallel = System.currentTimeMillis();
		
		Assert.assertTrue(String.format("Summary values for %d year are different", year2011), 
				txYearToSummaryOrdinary.get(year2011).getSum() == txYearToSummaryParallel.get(year2011).getSum());
		Assert.assertTrue(String.format("Summary values for %d year are different", year2020), 
				txYearToSummaryOrdinary.get(year2020).getSum() == txYearToSummaryParallel.get(year2020).getSum());
		Assert.assertTrue(String.format("Summary values for %d year are different", year2018), 
				txYearToSummaryOrdinary.get(year2018).getSum() == txYearToSummaryParallel.get(year2018).getSum());
		Assert.assertTrue(String.format("Summary values for %d year are different", year2015), 
				txYearToSummaryOrdinary.get(year2015).getSum() == txYearToSummaryParallel.get(year2015).getSum());
		
		long durationOrdinary = endOrdinary - startOrdinary;
		long durationParallel = endParallel - startParallel;
		Assert.assertTrue("Parallel invokation not faster", durationOrdinary > durationParallel);
		
	}

	private FruitThreadHolder generateFruitThreadHolder(Fruit fruit) {
		FruitThreadHolder holder = new FruitThreadHolder();
		Long threadID = Thread.currentThread().getId();
		holder.fruit = fruit;
		holder.threadId = threadID;
		return holder;
	}

	private long getTotalWeighOfAllFruits(List<Fruit> fruitsCollection) {
		Accumulator accumulator = new Accumulator();
		fruits.stream().parallel().forEach(fruit -> {
			accumulator.add(fruit.getWeight());
		});
		return accumulator.getTotal();
	}
	
	private List<Transaction> createTransactionList(int size, Trader trader, int year) {
		Random randomTransactionValue = new Random();
		List<Transaction> result = new ArrayList();
		for(int i = 1; i <= size; i++) {
			Transaction tr = new Transaction(trader, 
					year, 
					randomTransactionValue.nextInt(100) + 1,
					true);
			result.add(tr);
		}
		return result;
	}

	public class FruitThreadHolder {
		Fruit fruit;

		Long threadId;

		public Fruit getFruit() {
			return fruit;
		}

		public Long getThreadId() {
			return threadId;
		}
	}

	public class Accumulator {
		private long total;

		public void add(long value) {
			total += value;
		}

		public long getTotal() {
			return total;
		}
	}

}

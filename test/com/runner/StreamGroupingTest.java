package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dto.Country;
import com.dto.Trader;
import com.dto.Transaction;

public class StreamGroupingTest {
	
	private List<Transaction> transactionList;
	
	private final static String OLEG = "Oleg";
	private final static String MARIO = "Mario";
	private final static String VICTOR = "Victor";
	private final static String ALEX = "alex";

	
	@Before
	public void init(){
		final Trader oleg = new Trader(OLEG, "Minsk");
		final Trader mario = new Trader(MARIO, "Genoa");
		final Trader victor = new Trader(VICTOR, "Minsk");
		final Trader alex = new Trader(ALEX, "Berlin");

		transactionList = Arrays.asList(
				new Transaction(oleg, 2019, 4000),
				new Transaction(mario, 2019, 150),
				new Transaction(victor, 2016, 3850),
				new Transaction(alex, 2019, 5100),
				new Transaction(alex, 2017, 4200),
				new Transaction(victor, 2019, 4400));
	}
	
	@Test
	public void streamGroupingToMap(){
		Map<Trader, List<Transaction>> transactToTraiders = 
		transactionList.stream().collect(Collectors.groupingBy(Transaction :: getTrader));
		Assert.assertEquals(4, transactToTraiders.size());
		
		transactToTraiders.entrySet().forEach(entry -> {
			if(VICTOR.equals(entry.getKey().getName())){
				Assert.assertEquals(2, entry.getValue().size());
			}else if(ALEX.equals(entry.getKey().getName())){
				Assert.assertEquals(2, entry.getValue().size());
			}else{
				Assert.assertEquals(1, entry.getValue().size());
			}
		});
	}
	
	@Test
	public void streamGroupingToMapComplexFunction(){
		Map<Country, List<Transaction>> transactionToContry = 
		transactionList.stream().collect(Collectors.groupingBy(transaction -> {
			String city = transaction.getTrader().getCity();
			if("Minsk".equals(city)){
				return Country.BELARUS;
			}else if("Berlin".equals(city)){
				return Country.GERMANY;
			}else if("Genoa".equals(city)){
				return Country.ITALY;
			}
			return Country.NO_COUNTRY;
			}));
		Assert.assertEquals(3, transactionToContry.size());
		
		transactionToContry.entrySet().forEach(entry -> {
			if(Country.BELARUS == entry.getKey()){
				Assert.assertEquals(3, entry.getValue().size());
			}else if(Country.GERMANY == entry.getKey()){
				Assert.assertEquals(2, entry.getValue().size());
			}else if(Country.ITALY == entry.getKey()){
				Assert.assertEquals(1, entry.getValue().size());
			}
		});
		
		Assert.assertFalse(transactionToContry.containsKey(Country.FRANCE));
		Assert.assertFalse(transactionToContry.containsKey(Country.NO_COUNTRY));		
	}
	
	@Test
	public void streamGroupingToMapMultiLevel(){
		Map<String, Map<Long, List<Transaction>>> transactionsToTraiderYearSeparation = 
		transactionList.stream().collect(Collectors.groupingBy(transaction -> transaction.getTrader().getName(), 
				Collectors.groupingBy(transaction -> Long.valueOf(transaction.getYear()))));
		
		Assert.assertEquals(4, transactionsToTraiderYearSeparation.size());
		Map<Long, List<Transaction>> alexTransactions = transactionsToTraiderYearSeparation.get(ALEX);
		Assert.assertEquals(2, alexTransactions.size());	
		
	}
	
	@Test
	public void streamGroupingToMapWithCounting(){
		Map<String, Long> traiderNameToNumberTransactions =
		transactionList.stream().collect(Collectors.groupingBy(transaction -> transaction.getTrader().getName(), Collectors.counting()));
		
		Assert.assertEquals(4, traiderNameToNumberTransactions.size());
		Assert.assertEquals(2, traiderNameToNumberTransactions.get(ALEX).longValue());
		Assert.assertEquals(2, traiderNameToNumberTransactions.get(VICTOR).longValue());
		Assert.assertEquals(1, traiderNameToNumberTransactions.get(MARIO).longValue());
		Assert.assertEquals(1, traiderNameToNumberTransactions.get(OLEG).longValue());
	}

}

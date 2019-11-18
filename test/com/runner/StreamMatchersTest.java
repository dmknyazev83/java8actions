package com.runner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.dto.Trader;
import com.dto.Transaction;

public class StreamMatchersTest {
	
	@Test
	public void matchingTradersOperationsTest(){
		
		final Trader oleg = new Trader("Oleg", "Minsk");
		final Trader mario = new Trader("Mario", "Genoa");
		final Trader victor = new Trader("Victor", "Minsk");
		final Trader alex = new Trader("Alex", "Berlin");
		final Trader denis = new Trader("Denis", "Minsk");
		final Trader vladimir = new Trader("Vladimir", "Berlin");
		
		List<Transaction> transList = Arrays.asList(
				new Transaction(oleg, 2019, 4000),
				new Transaction(mario, 2019, 150),
				new Transaction(victor, 2016, 3850),
				new Transaction(alex, 2019, 5100),
				new Transaction(denis, 2017, 4200),
				new Transaction(vladimir, 2019, 4400));
		
		//find all transactions of year 2019 and sort them by value
		List<Transaction> trans2019Sorted = transList.stream().filter(item -> item.getYear() == 2019)
		.sorted(Comparator.comparing(Transaction :: getValue)).collect(Collectors.toList());
		trans2019Sorted.stream().forEach(tr -> {
			Assert.assertEquals(2019, tr.getYear());
		});
		
		//find unique cities where traders work
		List<String> uniqueCities = transList.stream().map(Transaction :: getTrader)
		.map(Trader :: getCity).distinct().collect(Collectors.toList());
		Assert.assertEquals(3, uniqueCities.size());
		Assert.assertTrue(uniqueCities.contains("Minsk"));
		Assert.assertTrue(uniqueCities.contains("Berlin"));
		Assert.assertTrue(uniqueCities.contains("Genoa"));
		
		//find all traders from Minsk and sort them by name
		List<Trader> minskTraders = transList.stream().map(Transaction :: getTrader)
		.filter(trader ->  trader.getCity().equals("Minsk"))
		.sorted(Comparator.comparing(Trader :: getName)).collect(Collectors.toList());
		Assert.assertEquals(3, minskTraders.size());
		Assert.assertEquals("Denis", minskTraders.get(0).getName());
		Assert.assertEquals("Oleg", minskTraders.get(1).getName());
		Assert.assertEquals("Victor", minskTraders.get(2).getName());
		
		//Return string of all traders name sorted alphabetically
		final StringBuilder aggregatedNamesBuf = new StringBuilder();
		transList.stream().map(Transaction :: getTrader)
		.map(Trader :: getName).sorted(Comparator.naturalOrder()).forEach(name-> aggregatedNamesBuf.append(name + ","));
		aggregatedNamesBuf.deleteCharAt(aggregatedNamesBuf.length()-1);
		final String aggregatedNames = aggregatedNamesBuf.toString();
		String[] namesArr = aggregatedNames.split(",");
		Assert.assertEquals(6, namesArr.length);
		Assert.assertEquals("Alex", namesArr[0]);
		Assert.assertEquals("Denis", namesArr[1]);
		Assert.assertEquals("Mario", namesArr[2]);
		Assert.assertEquals("Oleg", namesArr[3]);
		Assert.assertEquals("Victor", namesArr[4]);
		Assert.assertEquals("Vladimir", namesArr[5]);
		
		//Are there any traders based in Genoa
		Optional<Trader> genoaTrader = transList.stream().map(Transaction :: getTrader)
		.filter(trader -> trader.getCity().equals("Genoa")).findAny();
		Assert.assertTrue(genoaTrader.isPresent());
		Assert.assertEquals("Genoa", genoaTrader.get().getCity());
		
		//print all transaction values from traders living in Berlin
		transList.stream().filter(trans -> trans.getTrader().getCity().equals("Berlin"))
		.forEach(tr -> System.out.println(tr.getValue()));
		
		//find transaction highest value
		Optional<Transaction> tranMaxValue = transList.stream().max(Comparator.comparingInt(Transaction :: getValue));
		Assert.assertTrue(tranMaxValue.isPresent());
		Assert.assertEquals(5100, tranMaxValue.get().getValue());
		
		//find transaction smallest value
		Optional<Transaction> tranMixValue = transList.stream().min(Comparator.comparingInt(Transaction :: getValue));
		Assert.assertTrue(tranMixValue.isPresent());
		Assert.assertEquals(150, tranMixValue.get().getValue());
		
		//find all cities.
		final Optional<String> optAllCities = transList.stream()
		.map(Transaction :: getTrader).map(Trader :: getCity).distinct()
		.reduce((city1, city2) -> city1 + " " + city2);
		Assert.assertTrue(optAllCities.isPresent());
		System.out.println(optAllCities.get());
		Assert.assertTrue(optAllCities.get().contains("Minsk"));
		Assert.assertTrue(optAllCities.get().contains("Genoa"));
		Assert.assertTrue(optAllCities.get().contains("Berlin"));
	}

}

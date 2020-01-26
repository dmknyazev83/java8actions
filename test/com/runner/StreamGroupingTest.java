package com.runner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dto.Trader;
import com.dto.Transaction;

public class StreamGroupingTest {
	
	private List<Transaction> transactionList;
	
	@Before
	public void init(){
		final Trader oleg = new Trader("Oleg", "Minsk");
		final Trader mario = new Trader("Mario", "Genoa");
		final Trader victor = new Trader("Victor", "Minsk");
		final Trader alex = new Trader("Alex", "Berlin");

		transactionList = Arrays.asList(
				new Transaction(oleg, 2019, 4000),
				new Transaction(mario, 2019, 150),
				new Transaction(victor, 2016, 3850),
				new Transaction(alex, 2019, 5100),
				new Transaction(alex, 2017, 4200),
				new Transaction(victor, 2019, 4400));
	}

}

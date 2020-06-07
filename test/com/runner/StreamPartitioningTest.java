package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dto.Trader;
import com.dto.transaction.Transaction;

public class StreamPartitioningTest {
	
	private List<Transaction> transactionList;
	
	@Before
	public void init(){
		final Trader oleg = new Trader("OLEG", "Minsk");
		final Trader victor = new Trader("Victor", "Gomel");

		transactionList = Arrays.asList(
				new Transaction(oleg, 2019, 4000, true),
				new Transaction(oleg, 2019, 150, false),
				new Transaction(victor, 2016, 3850, true),
				new Transaction(victor, 2019, 5100, true),
				new Transaction(victor, 2017, 4200, false),
				new Transaction(victor, 2019, 4400, true));
	}
	
	@Test
	public void streamPartitioningToMap(){
		
		Map<Boolean, List<Transaction>> transactionsByPayed = transactionList.stream().
				collect(Collectors.partitioningBy(Transaction :: isPayed));
		Assert.assertEquals(2, transactionsByPayed.size());		
		List<Transaction> payedTransactions = transactionsByPayed.get(Boolean.TRUE);
		List<Transaction> unPayedTransactions = transactionsByPayed.get(Boolean.FALSE);
		Assert.assertEquals(4, payedTransactions.size());
		Assert.assertEquals(2, unPayedTransactions.size());
		
		Map<Boolean, List<Transaction>> transactionsByYear = transactionList.stream().
				collect(Collectors.partitioningBy(trans -> trans.getYear() == 2019));
		Assert.assertEquals(2, transactionsByYear.size());		
		List<Transaction> transactions2019 = transactionsByPayed.get(Boolean.TRUE);
		List<Transaction> transactionsOthers = transactionsByPayed.get(Boolean.FALSE);
		Assert.assertEquals(4, transactions2019.size());
		Assert.assertEquals(2, transactionsOthers.size());
		
		Map<Boolean, List<Transaction>> transactionsByValue = transactionList.stream().
				collect(Collectors.partitioningBy(trans -> trans.getValue() > 4000));
		Assert.assertEquals(2, transactionsByValue.size());
		List<Transaction> transactionsMore4000 = transactionsByValue.get(Boolean.TRUE);
		List<Transaction> transactionsLess400 = transactionsByValue.get(Boolean.FALSE);
		Assert.assertEquals(3, transactionsMore4000.size());
		Assert.assertEquals(3, transactionsLess400.size());		
	}
	
	@Test
	public void streamPartitioningToComplexMap(){
		Map<Boolean, Map<String, List<Transaction>>> transactionsByPayed = transactionList.stream().
				collect(Collectors.partitioningBy(Transaction :: isPayed,
						Collectors.groupingBy(trans -> trans.getTrader().getName())));
		Assert.assertEquals(2, transactionsByPayed.size());		
		
		Map<String, List<Transaction>> payedTransactions = transactionsByPayed.get(Boolean.TRUE);
		Map<String, List<Transaction>> unPayedTransactions = transactionsByPayed.get(Boolean.FALSE);
		
		Assert.assertEquals(3, payedTransactions.get("Victor").size());	
		Assert.assertEquals(1, unPayedTransactions.get("Victor").size());	
	}

}

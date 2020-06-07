package com.service.transaction;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dto.transaction.TransactionItem;
import com.dto.transaction.TransactionMinMax;

public class TransactionMinMaxCollectorTest {
	
	private TransactionMinMaxCollector trCollector;
	
	@Before
	public void init(){
		trCollector = new TransactionMinMaxCollector();
	}
	
	@Test
	public void useTransactionMinMaxCollector(){
		Set<TransactionItem> source = getTransactionItemSource();
		TransactionMinMax result = source.stream().collect(this.trCollector);
		
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getMaxTransaction());
		Assert.assertNotNull(result.getMinTransaction());
		
		Assert.assertEquals(250L, result.getMaxTransaction().getAmount());
		Assert.assertEquals(185L, result.getMinTransaction().getAmount());
	}
	
	private Set<TransactionItem> getTransactionItemSource(){
		Set<TransactionItem> res = new HashSet<TransactionItem>();
		
		TransactionItem trItem1 = new TransactionItem(250L, Calendar.getInstance().getTime(), "Adidas");
		TransactionItem trItem2 = new TransactionItem(185L, Calendar.getInstance().getTime(), "Manchester");
		TransactionItem trItem3 = new TransactionItem(220L, Calendar.getInstance().getTime(), "Barman");
		
		res.add(trItem1);
		res.add(trItem2);
		res.add(trItem3);
		
		return res;
		
	}

}

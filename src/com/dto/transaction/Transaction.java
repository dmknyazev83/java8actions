package com.dto.transaction;

import com.dto.Trader;

public class Transaction {
	
	private final Trader trader;
	private final int year;
	private final int value;
	private final boolean payed;
	
	public Transaction(Trader trader, int year, int value, boolean payed) {
		super();
		this.trader = trader;
		this.year = year;
		this.value = value;
		this.payed = payed;
	}

	public Trader getTrader() {
		return trader;
	}

	public int getYear() {
		return year;
	}

	public int getValue() {
		return value;
	}

	public boolean isPayed() {
		return payed;
	}	

}

package com.dto.transaction;

public class TransactionMinMax {
	
	private TransactionItem minTransaction;
	private TransactionItem maxTransaction;
	
	public TransactionMinMax(TransactionItem maxTransaction, TransactionItem minTransaction) {
		super();
		this.minTransaction = minTransaction;
		this.maxTransaction = maxTransaction;
	}	
	
	public TransactionItem getMinTransaction() {
		return minTransaction;
	}


	public TransactionItem getMaxTransaction() {
		return maxTransaction;
	}
}

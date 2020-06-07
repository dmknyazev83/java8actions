package com.dto.transaction;

import java.util.Date;

public class TransactionItem {
	
	private long amount;
	private Date completeTime;
	private String supplier;
	
	
	public TransactionItem(long amount, Date completeTime, String supplier) {
		super();
		this.amount = amount;
		this.completeTime = completeTime;
		this.supplier = supplier;
	}


	public long getAmount() {
		return amount;
	}


	public Date getCompleteTime() {
		return completeTime;
	}


	public String getSupplier() {
		return supplier;
	}
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Amount = ");
		sb.append(this.amount);
		sb.append(", ");
		sb.append("Complete Time = ");
		sb.append(this.completeTime);
		sb.append(", ");
		sb.append("Supplier = ");
		sb.append(this.supplier);
		sb.append(".");
		return sb.toString();
	}
}

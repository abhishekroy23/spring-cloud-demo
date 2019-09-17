package com.demo.useraccountservice.enums;

public enum TransactionType {

	CREDIT("Credit"),DEBIT("Debit");
	
	private final String transactionValue;
	private TransactionType(String transactionValue) {
		this.transactionValue = transactionValue;		
	}
	public String getValue() {
		return this.transactionValue;
	}
}

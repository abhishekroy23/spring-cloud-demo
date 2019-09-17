package com.demo.useraccountservice.enums;

public enum TransferStatus {

	FAILED_INSUFF("Insufficient balance"),SUCCESS("Funds transfer successfull"),ACCOUNT_NOT_FOUND("Account not found"),;
	
	
	private final String transactionValue;
	private TransferStatus(String transactionValue) {
		this.transactionValue = transactionValue;		
	}
	public String getValue() {
		return this.transactionValue;
	}
}

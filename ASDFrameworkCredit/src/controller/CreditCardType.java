package controller;

import framework.component.AccountType;

public class CreditCardType extends AccountType {

	protected double minimumPayment;
	
	public static String CREDIT_CARD_GOLD="gold";
	
	public static String CREDIT_CARD_SILVER="silver";
	
	public static String CREDIT_CARD_BRONZE="bronze";
	
	
	
	public CreditCardType(String typeName, double interestRate,
			double maxTransactionLimit, double lb,double minPay) {
		super(typeName, interestRate, maxTransactionLimit, lb);
		minimumPayment=minPay;
	}

	public double getMinimumPayment() {
		return minimumPayment;
	}
	
	

	
}

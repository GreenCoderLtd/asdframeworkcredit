package controller;

import framework.component.TransactionEntry;

public class CreditCardDepositEntry extends TransactionEntry {

	protected CreditCardDepositEntry(double theBalance,
			double theAmount, String theIssuerName) {
		super("Credit", theBalance, theAmount, theIssuerName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateNewBalance() {
		
		newBalance = previousBalance - amount;

	}

}

package controller;

import framework.component.TransactionEntry;

public class CreditCardChargeEntry extends TransactionEntry {

	CreditCardChargeEntry( double theBalance,
			double theAmount, String theIssuerName) {
		super("Debit",theBalance, theAmount, theIssuerName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateNewBalance() {
		newBalance = previousBalance + amount;

	}

}

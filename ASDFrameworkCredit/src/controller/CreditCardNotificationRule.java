package controller;

import framework.component.ITransactionEntry;
import framework.component.NotificationRule;

public class CreditCardNotificationRule implements NotificationRule {

	private double notifiAmount;

	public CreditCardNotificationRule(double notifiAmount) {
		this.notifiAmount = notifiAmount;
	}

	public boolean ruleMatch(ITransactionEntry transactionEntry) {

		if(transactionEntry.getEntryAmount()>=notifiAmount)

			return true;
		else
			return false;
	}

}

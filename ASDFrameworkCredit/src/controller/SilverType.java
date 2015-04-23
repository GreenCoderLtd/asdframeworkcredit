package controller;
import framework.component.AccountType;


public class SilverType extends CreditCardType {

	public SilverType(String typeName, double interestRate,
			double maxTransactionLimit, double lb,double minPay) {
		super(typeName, interestRate, maxTransactionLimit, lb,minPay);
	}

}

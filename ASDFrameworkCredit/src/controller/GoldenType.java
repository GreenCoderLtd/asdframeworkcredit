package controller;
import framework.component.AccountType;


public class GoldenType extends CreditCardType{

	public GoldenType(String typeName, double interestRate,
			double maxTransactionLimit, double lb,double minPay) {
		super(typeName, interestRate, maxTransactionLimit, lb,minPay);

	}

}

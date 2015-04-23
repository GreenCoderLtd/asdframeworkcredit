package controller;

import framework.component.AccountTypeFactory;


public class SimpleCreditTypeFactory {

	public static AccountTypeFactory getCreditTypeFactory()
	{
		return new CreditCardTypeFactory();
	}
}

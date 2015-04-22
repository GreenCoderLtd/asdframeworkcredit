package controller;

import framework.component.DefaultTypeFactory;
import framework.component.SimpleTypeFactory;
import framework.component.TypeFactory;

public class SimpleBankTypeFactory {

	public static TypeFactory getBankTypeFactory()
	{
		return new CreditCardTypeFactory();
	}
}

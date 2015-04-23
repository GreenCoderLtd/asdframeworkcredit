package controller;

import framework.component.DefaultTypeFactory;
import framework.component.SimpleTypeFactory;
import framework.component.TypeFactory;

public class SimpleCreditTypeFactory {

	public static TypeFactory getCreditTypeFactory()
	{
		return new CreditCardTypeFactory();
	}
}

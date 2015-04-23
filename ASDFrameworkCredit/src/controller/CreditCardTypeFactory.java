package controller;

import framework.component.AccountType;
import framework.component.TypeFactory;

public class CreditCardTypeFactory extends TypeFactory {

	@Override
	public String[] getTypes() {
		// TODO Auto-generated method stub
		return new String[]{"golden","silver","bronze"};
		
	}

	@Override
	public AccountType getType(String typeName) {
		
		if(typeName.equals(CreditCardType.CREDIT_CARD_GOLD))
		{
			return new GoldenType("golden",6,1000,-99999,10);
		}
		else if(typeName.equals(CreditCardType.CREDIT_CARD_SILVER))
		{
			return new SilverType("silver",8,700,-99999,12);
		}
		else
		{
			return new BronzeType("bronze",10,500,-99999,14);
		}
	}

}

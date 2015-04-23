package controller;

import java.util.HashMap;

import javax.swing.JOptionPane;

import framework.component.Account;
import framework.component.AccountType;
import framework.component.CompanyCustomer;
import framework.component.Customer;
import framework.component.FinanceHandler;
import framework.component.IAccount;
import framework.component.ICommand;
import framework.component.ICustomer;
import framework.component.PersonalCustomer;

public class CreditCardOpenCommand implements ICommand {

	FinanceHandler controller;
	HashMap<String, String> guiData;
	AccountType accountType;
	double notifiAmount = 400;
	
	public CreditCardOpenCommand(FinanceHandler controller,
			HashMap<String, String> guiData, AccountType accountType) {
		this.controller = controller;
		this.guiData = guiData;
		this.accountType=accountType;
	}
	
	@Override
	public void exceute() {
		
		if(controller.getAccount(guiData.get(Account.ACC_NUM_FIELD))==null)
		{
			ICustomer customer=new Customer(guiData);
			IAccount creditCard=new CreditCard(guiData,accountType);
			customer.addAccount(creditCard);
			customer.setNotificationRule(new CreditCardNotificationRule(notifiAmount));
			controller.addCustomer(customer);
			
			//JOptionPane.showMessageDialog(null, "Account "+guiData.get(Account.ACC_NUM_FIELD)+" created");
		}
		else {
			
			JOptionPane.showMessageDialog(null, "Credit Card "+guiData.get(Account.ACC_NUM_FIELD)+" already Exits");
		}

	}

}

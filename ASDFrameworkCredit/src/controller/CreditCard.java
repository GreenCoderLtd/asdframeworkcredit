package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import framework.component.Account;
import framework.component.AccountType;
import framework.component.DebitEntry;
import framework.component.IReport;
import framework.component.ITransactionEntry;
import framework.component.NotificationRule;
import framework.component.PersonalCustomer;
import framework.component.PersonalNotificationRule;

public class CreditCard extends Account {

	Date expiryDate;
	public static String EXIPIRY_DATE_FIELD = "expiry";
	
	public CreditCard(HashMap<String, String> data, AccountType accountType) {
		super(data, accountType);
		expiryDate=new Date(data.get(CreditCard.EXIPIRY_DATE_FIELD));
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public String getExpiryDateString()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(expiryDate);
	}

	@Override
	public void doDebit(double amount) {
		
		ITransactionEntry newEntry = new CreditCardChargeEntry(accountBalance, amount,
				getAccountNumber());

		if(accountType.isTransactionValidate(newEntry))
		{
			accountBalance = newEntry.getNewBalance();

			transactionEntry.add(newEntry);

			NotificationRule notificationRule = owner.getNotificationRule();

			if (notificationRule.ruleMatch(newEntry)) {
				owner.update(newEntry);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null,newEntry.getEntryType()+"  is not allwed as max transaction limit "+accountType.getMaxTransactionLimit());

		}
	}

	@Override
	public void doCredit(Double amount) {
		ITransactionEntry newEntry = new CreditCardDepositEntry(accountBalance, amount,
				getAccountNumber());

			accountBalance = newEntry.getNewBalance();

			transactionEntry.add(newEntry);

			NotificationRule notificationRule = owner.getNotificationRule();

			if (notificationRule.ruleMatch(newEntry)) {
				owner.update(newEntry);
			}
		
	}

	@Override
	public void generateReport(Date date1, Date date2, IReport reportType) {
		List<ITransactionEntry> reportEntryList = new LinkedList<ITransactionEntry>();
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(23);
		date2.setMinutes(59);
		date2.setSeconds(59);
		for (ITransactionEntry e : transactionEntry) {
			Date ev = e.getDate();
			if ((date1.equals(e.getDate()) || date2.equals(e.getDate()))
					|| (date1.before(e.getDate()) && date2.after(e.getDate()))) {
				reportEntryList.add(e);
			}
		}// for

		reportType.setAccount(this);
		reportType.Generate(getAccountNumber(), reportEntryList);
		
	}

	
}

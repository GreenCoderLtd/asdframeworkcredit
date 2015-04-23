package controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import framework.component.IAccount;
import framework.component.IReport;
import framework.component.ITransactionEntry;

public class CreditCardReport implements IReport {
	File file;
	String dirPath = "CreditCardReport";
	BufferedWriter output;
	CreditCard card;


	@Override
	public void Generate(String accountNumber, List<ITransactionEntry> inputListOfEntry) {
		
		createFile(accountNumber);
		
		PrintWriter out = new PrintWriter(output);
		
		double previousBalance=card.getAccountType().getMaxTransactionLimit();
		
		
		
		out.println("Name "+card.getOwner().getName()+" Card No "+card.getAccountNumber());
		
		out.println("Previous Balance : "+ previousBalance);
		
		Date startDate=new Date("04/20/2015");
		
		double totalCredit=card.getTotalCreditAmount(startDate, new Date());
		
		double totalCharges=card.getTotalDebitAmount(startDate, new Date());
		
		out.println("Total Charges :"+totalCharges);
		
		out.println("Total Deposits :"+totalCredit);
		
		double newBalance=previousBalance - totalCredit + totalCharges + card.getAccountType().getInterestRate()/100 * (previousBalance - totalCredit);
		
		out.println("New Balance :"+newBalance);
		
		double minimumDue=((CreditCardType)card.getAccountType()).getMinimumPayment()/100*newBalance;
		
		out.println("Minimum Due :"+minimumDue);

		out.close();
	}
	
	void createFile(String accountNumber)
	{
		File file = new File(dirPath);
		if (file.exists()) {
			// System.out.println("File Exists");
			file = new File(dirPath + "/" + accountNumber + ".txt");
			System.out.println("Report Created @ " + file.getAbsolutePath());
			try {
				output = new BufferedWriter(new FileWriter(file, true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean wasDirecotyMade = file.mkdirs();
			if (wasDirecotyMade) {
				System.out.println("Direcoty Created @ "
						+ file.getAbsolutePath());
				try {
					file = new File(dirPath + "/" + accountNumber + ".txt");
					output = new BufferedWriter(new FileWriter(file, true));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out
						.println("Sorry could not create \"Reports\" directory");
			}
		}

		
	}

	@Override
	public void setAccount(IAccount account) {
		card=(CreditCard)account;
		
	}

}


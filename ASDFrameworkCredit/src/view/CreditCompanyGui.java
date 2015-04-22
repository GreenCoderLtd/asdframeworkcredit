package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import framework.component.Account;
import framework.component.FinanceHandler;
import framework.component.IAccount;
import framework.component.TxtReport;
import framework.view.AGui;
import framework.view.DefaultGui;
import framework.view.JDialog_Deposit;
import framework.view.JDialog_Withdraw;

public class CreditCompanyGui extends DefaultGui {

	SymAction bankGuiActionListener=new SymAction();


	public CreditCompanyGui(String title, FinanceHandler controller) {
		super(title, controller);
		
		JButton_PerAC.removeActionListener(defaultGuiActionListener);
		JButton_PerAC.addActionListener(bankGuiActionListener);
		
		JButton_CompAC.removeActionListener(defaultGuiActionListener);
		JButton_CompAC.addActionListener(bankGuiActionListener);

	}

	public DefaultTableModel getTableModel() {
		model = new DefaultTableModel();

		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("P/C");
		model.addColumn("S/Ch");
		model.addColumn("Amount");

		return model;
	}



	class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_CompAC)
				JButtonCompAC_actionPerformed(event);
		
			
		}

		
	}

	
	public void modelUpdated()
	{
		
		model.setRowCount(0);
		
		List<IAccount> allAccounts=controller.getAllAccount();
		
		for(IAccount account:allAccounts)
		{
			Object[] rawData=new Object[6];
			rawData[0] = account.getAccountNumber();
			rawData[1] = account.getOwner().getName();
			rawData[2] = account.getOwner().getCity();
			rawData[3] = account.getOwner().getCustomerType();
			rawData[4] = account.getAccountType().getTypeName();
			rawData[5] = account.getAccountBalance();
	        model.addRow(rawData);
		}
	}
	

	void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {

		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		AddPAccWindow pac = new AddPAccWindow(myframe);
		pac.setBounds(450, 20, 300, 400);
		pac.show();



	}

	void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and
		 * show it
		 */

		AddCompAccWindow pac = new AddCompAccWindow(myframe);
		pac.setBounds(450, 20, 300, 400);
		pac.show();

	}
	




}


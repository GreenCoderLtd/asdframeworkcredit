package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controller.CreditCard;
import controller.CreditCardReport;
import framework.component.Account;
import framework.component.CreditCommand;
import framework.component.DebitCommand;
import framework.component.FinanceSystem;
import framework.component.IAccount;
import framework.component.ITransactionCommand;
import framework.component.TxtReport;
import framework.view.AGui;
import framework.view.DefaultGui;
import framework.view.TransactionDialog;

public class CreditCompanyGui extends DefaultGui {

	SymAction bankGuiActionListener=new SymAction();


	public CreditCompanyGui(String title, FinanceSystem controller) {
		super(title, controller);
		
		JButton_PerAC.removeActionListener(defaultGuiActionListener);
		JButton_Deposit.removeActionListener(defaultGuiActionListener);
		JButton_Withdraw.removeActionListener(defaultGuiActionListener);
		JButton_GenerateReport.removeActionListener(defaultGuiActionListener);
		
		JButton_PerAC.setText("Add Credit-Card");
		JButton_PerAC.addActionListener(bankGuiActionListener);
		JButton_Deposit.addActionListener(bankGuiActionListener);
		JButton_Withdraw.addActionListener(bankGuiActionListener);
		JButton_GenerateReport.addActionListener(bankGuiActionListener);
		
		JButton_CompAC.setVisible(false);
		
		JButton_GenerateReport.setText("Generate Monthly Bill");
		JButton_Withdraw.setText("Charge");

		

	}

	public DefaultTableModel getTableModel() {
		model = new DefaultTableModel();

		model.addColumn("CC Number");
		model.addColumn("Name");
		model.addColumn("Expiry Date");
		model.addColumn("Type");
		model.addColumn("Balance");

		return model;
	}



	class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);
			else if (object == JButton_GenerateReport)
				JButtonGenerateReport_actionPerformed(event);
		
			
		}

		
	}

	
	public void modelUpdated()
	{
		
		model.setRowCount(0);
		
		List<IAccount> allAccounts=controller.getAllAccount();
		
		for(IAccount account:allAccounts)
		{
			Object[] rawData=new Object[5];
			rawData[0] = account.getAccountNumber();
			rawData[1] = account.getOwner().getName();
			rawData[2] = ((CreditCard) account).getExpiryDateString();
			rawData[3] = account.getAccountType().getTypeName();
			rawData[4] = account.getAccountBalance();
	        model.addRow(rawData);
		}
	}
	

	void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {

		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		AddCreditCardWindow pac = new AddCreditCardWindow(myframe);
		pac.setBounds(450, 20, 300, 400);
		pac.show();



	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event)
	{
		

		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		
        if (selection >=0){
        	
            String accountNumber = (String)model.getValueAt(selection, 0);
            
		    ITransactionCommand command=new CreditCommand(getController(), accountNumber);
		    
		    TransactionDialog dep = new TransactionDialog(myframe,"Deposit",accountNumber,command);
		    dep.setBounds(430, 15, 275, 180);
		    dep.show();
        }
    		

		
		
	}
	
	void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event)
	{
	    // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        
        if (selection >=0){
        	
        	String accountNumber = (String)model.getValueAt(selection, 0);
            
		    ITransactionCommand command=new DebitCommand(getController(), accountNumber);
		    
		    TransactionDialog dep = new TransactionDialog(myframe,"Charge",accountNumber,command);
		    dep.setBounds(430, 15, 275, 180);
		    dep.show();
        }
		   

	}
	
	void JButtonGenerateReport_actionPerformed(java.awt.event.ActionEvent event)
	{
	    Date startDate=new Date("04/20/2015");
	    
		try {
			getController().generateReport(startDate, new Date(), new CreditCardReport());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
		   

	}




}


package view;


/*
		A basic implementation of the JDialog class.
*/

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import controller.CreditCard;
import controller.CreditCardOpenCommand;
import controller.SimpleCreditTypeFactory;
import framework.component.*;
import framework.view.AGui;
import framework.view.JDialogAddAccount;
import framework.view.JDialogAddPerAcc;


public class AddCreditCardWindow extends JDialogAddAccount
{

	JRadioButton[] typeRadioButton;
	
	SymAction creditActionListener = new SymAction();
	
	JLabel expiryLabel;
	protected JTextField expiryTextField;
	
	public AddCreditCardWindow(AGui myframe)
	{
		super(myframe);
		
		setTitle("Add Credit Card");

		generateTypeRadioButtons();
		
		JLabel8.setText("CC");
		
		expiryTextField = new JTextField();
		expiryTextField.setText("10/11/2017");
		getContentPane().add(expiryTextField);
		expiryTextField.setBounds(84, 204, 156, 20);

		expiryLabel = new JLabel();
		expiryLabel.setText("expiryDate");
		getContentPane().add(expiryLabel);
		expiryLabel.setForeground(java.awt.Color.black);
		expiryLabel.setBounds(12, 204, 96, 24);

		
		JButton_OK.addActionListener(creditActionListener);
		JButton_Cancel.addActionListener(creditActionListener);
		
	}

	public void generateTypeRadioButtons()
	{
		int yPosition=1;
		String[] types=SimpleCreditTypeFactory.getCreditTypeFactory().getAccountTypes();
		
		ButtonGroup bG = new ButtonGroup();
	   
		typeRadioButton=new JRadioButton[types.length];
		
		for(int i=0;i<types.length;i++)
		{
			typeRadioButton[i]=new JRadioButton(types[i]);
			typeRadioButton[i].setBounds(36,yPosition,100,19);
			getContentPane().add(typeRadioButton[i]);
			bG.add(typeRadioButton[i]);
			yPosition=yPosition+16;
		}
	}


	public String getAccountTypeSelected()
	{
		String type="saving";
		
		for(JRadioButton radio:typeRadioButton)
		{
			if(radio.isSelected())
			{
				type=radio.getText();
				return type;
			}
		}
		
		return type;
	}

	public void getCommonInputData() {
		
		super.getCommonInputData();
		
		guiData.put(CreditCard.EXIPIRY_DATE_FIELD, expiryTextField.getText());
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
			else if (object == JButton_Cancel)
				JButtonCalcel_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
		getCommonInputData();
		
		boolean allInputOK=inputValidate(new ArrayList<String>(guiData.values()));

		
		if(allInputOK)
		{
			if(isDateValid(guiData.get(CreditCard.EXIPIRY_DATE_FIELD))==false)
			{
			
			    JOptionPane.showMessageDialog(null, "Insert expiry date as mm/dd/yyyy");
			  
			    return;
			}
			
			String type=getAccountTypeSelected();
			
			command=new CreditCardOpenCommand(parentframe.getController(), guiData,SimpleCreditTypeFactory.getCreditTypeFactory().getAccountType(type));
			command.exceute();
			
			parentframe.modelUpdated();

	       dispose();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please Input All Data");
		}

		
	}
	
	void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event) {
		// make this frame invisible if Cancel button is clicked
		dispose();
	}

	
}
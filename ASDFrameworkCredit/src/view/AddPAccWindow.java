package view;


/*
		A basic implementation of the JDialog class.
*/

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import controller.SimpleBankTypeFactory;
import framework.component.*;
import framework.view.AGui;
import framework.view.JDialogAddPerAcc;


public class AddPAccWindow extends JDialogAddPerAcc
{

	JRadioButton[] typeRadioButton;
	
	SymAction lSymAction = new SymAction();
	
	public AddPAccWindow(AGui myframe)
	{
		super(myframe);

		generateTypeRadioButtons();
		
		JButton_OK.removeActionListener(mySymAction);
		
		JButton_OK.addActionListener(lSymAction);
		
	}

	public void generateTypeRadioButtons()
	{
		int yPosition=12;
		String[] types=SimpleBankTypeFactory.getBankTypeFactory().getTypes();
		
		ButtonGroup bG = new ButtonGroup();
	   
		typeRadioButton=new JRadioButton[types.length];
		
		for(int i=0;i<types.length;i++)
		{
			typeRadioButton[i]=new JRadioButton(types[i]);
			typeRadioButton[i].setBounds(36,yPosition,100,24);
			getContentPane().add(typeRadioButton[i]);
			bG.add(typeRadioButton[i]);
			yPosition=yPosition+24;
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


	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
	{
		getCommonInputData();
		
		boolean allInputOK=inputValidate(new ArrayList<String>(guiData.values()));

		
		if(allInputOK)
		{
			String type=getAccountTypeSelected();
			
			command=new PersonalAccountOpenCommand(parentframe.getController(), guiData,SimpleBankTypeFactory.getBankTypeFactory().getType(type));
			command.exceute();
			
			parentframe.modelUpdated();

	       dispose();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please Input All Data");
		}

		
	}

	
}
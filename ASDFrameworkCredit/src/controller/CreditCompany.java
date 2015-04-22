package controller;

import view.CreditCompanyGui;
import framework.component.FinanceHandler;
import framework.model.DefaultModel;
import framework.model.IModel;
import framework.view.AGui;
import framework.view.DefaultGui;

public class CreditCompany {
	
	public static void main(String[] Args)
	{
		FinanceHandler finance=new FinanceHandler();
		
		IModel model=new DefaultModel();
		
		AGui gui=new CreditCompanyGui("Crdit Card App", finance);
		
		finance.setModel(model);
		
		finance.setGui(gui);
		
		


		
		
		
	}

}

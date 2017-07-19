package fl.domo.main;

import fl.domo.base.DomoObjectFactory;

public class DomoBrain {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		DomoObjectFactory factory = new DomoObjectFactory();
		
		factory.BuildFromXML("configtest.xml");
	}

}

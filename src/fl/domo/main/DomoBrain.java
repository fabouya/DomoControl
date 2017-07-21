package fl.domo.main;

import java.util.ArrayList;

import fl.domo.base.DomoObject;
import fl.domo.base.DomoObjectFactory;

public class DomoBrain {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		DomoObjectFactory factory = new DomoObjectFactory();
		
		factory.BuildFromXML("configtest.xml");
		
		ArrayList<DomoObject> liste = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");
		
		for (DomoObject o : liste) 
		{
			System.out.println("Switch : " + o.GetName());
		}
	}

}

package fl.domo.base;

import org.w3c.dom.Element;

public abstract class DomoSensor extends DomoObject 
{

	public DomoSensor(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public DomoSensor() 
	{
	}

	public abstract String ReadSensor();
}

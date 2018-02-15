package fl.domo.base;

import org.w3c.dom.Element;

public abstract class DomoCalendar extends DomoObject
{
	public DomoCalendar(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public DomoCalendar() 
	{
	}

	abstract public void SetPeriod(String period);

	abstract public boolean IsActive();
	abstract public void Reload();
	
	public void Create(String name)
	{
		super.Create(name);
	}
	
	void FromXML(Element item)
	{
	    _logger.debug("Create DomoCalendar");		
	}
	

}

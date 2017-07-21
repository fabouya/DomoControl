package fl.domo.base;

public abstract class DomoCalendar extends DomoObject
{
	public DomoCalendar(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	abstract public void SetPeriod(String period);

	abstract public boolean IsActive();
}

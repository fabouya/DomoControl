package fl.domo.base;

public abstract class DomoSensor extends DomoObject 
{

	public DomoSensor(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public abstract String ReadSensor();
}

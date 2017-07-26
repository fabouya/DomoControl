package fl.domo.base;

public class DomoInversedSwitch extends DomoSwitch {

	// -------------- members ----------------
	
	// ---------- static members -------------
				
	// -------------- function ----------------

	public DomoInversedSwitch(String name, String nameGPIO) 
	{
		super(name, nameGPIO);
		// TODO Auto-generated constructor stub
	}

	public void SwitchON()
	{
		super.SwitchOFF();
	}

	public void SwitchOFF()
	{
		super.SwitchON();
	}
	
	public int GetState()
	{
		// read gpio
		return ( GetState() == 0) ? 1 : 0;
	}
	
}

package fl.domo.base;

public class DomoSwitch extends DomoObject
{
	// -------------- members ----------------
	
	protected		int		_state=-1;	// -1 = undef, 0 = off, 1 = on
	protected		String	_gpioName = "undef";
		
	// ---------- static members -------------
				
	// -------------- function ----------------

	public DomoSwitch(String name, String nameGPIO) 
	{
		// TODO Auto-generated constructor stub
		super(name);
		_gpioName = nameGPIO;
	}
	
	public void SwitchON()
	{
		_state = 1;
	}

	public void SwitchOFF()
	{
		_state = 0;
	}
	
	public boolean IsON()
	{
		return (1 == _state) ? true : false;
	}
	
	
	public int GetState()
	{
		// read gpio
		return _state;
	}
	
	// ---------- static function -------------


}

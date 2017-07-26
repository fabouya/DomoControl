package fl.domo.base;

public class DomoSwitch extends DomoObject
{
	// -------------- members ----------------
	
	protected		int		_state=-1;	// -1 = undef, 0 = off, 1 = on
	protected		String	_gpioName = "undef";
	
	protected		int		_mode = 0;

	// 0 = normal/auto
	// 1 = forced on
	// 2 = forced off
		
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
		return (1 == GetState()) ? true : false;
	}
	
	
	public int GetState()
	{
		// read gpio
		return _state;
	}

	public String SetMode(String s)
	{
		_logger.debug("Set Mode <" + s + "> pour " + _name);
		
		int mode = StringToMode(s);
		
		if(-1 == mode)
		{		
				_logger.error("Mode inconnu <" + s + "> pour " + _name);
				return "ERROR";
		}
		
		_mode = mode;
		
		return "OK";
	}
	
	public void SetModeAuto() { _mode = 0; }
	public void SetModeForcedON() { _mode = 1; }
	public void SetModeForcedOFF() { _mode = 2; }
	
	public int GetMode()
	{
		return _mode;
	}
	
	public String ModeToString(int mode)
	{
		switch(mode)
		{
			case 0: return "auto";
			case 1: return "forcedon";
			case 2: return "forcedoff";
			default : return "undef";
		}
	}

	public int StringToMode(String s)
	{
		switch(s.toLowerCase())
		{
			case "auto": { return 0;}
			case "forcedon": { return 1; }			
			case "forcedoff": { return 2; }
			default:
			{
				_logger.error("Mode inconnu <" + s + "> pour " + _name);
				return -1;
			}
		}
		
	}
	// ---------- static function -------------


}

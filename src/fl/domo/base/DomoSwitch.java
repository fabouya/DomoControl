package fl.domo.base;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.PinState;

public class DomoSwitch extends DomoObject
{
	// -------------- members ----------------
	
	protected		String			_gpioName = "undef";
	protected		DomoOutputGPIO	_gpio = null;
	
	protected		int		_mode = 0;

	// 0 = normal/auto
	// 1 = forced on
	// 2 = forced off
		
	// ---------- static members -------------
	protected final static Logger _logger = Logger.getLogger(DomoSwitch.class);	
				
	// -------------- function ----------------

	public DomoSwitch(String name, String nameGPIO) 
	{
		super(name);
		_logger.debug("Create DomoSwitch " + name + " -> " + nameGPIO);
		_gpioName = nameGPIO;
		_gpio = (DomoOutputGPIO) DomoObject.GetObjectByName(nameGPIO.toLowerCase());
	}
	
	public void InitSwitch()
	{
		_gpio.SetLow();		
	}
	
	public void SwitchON()
	{
		_gpio.SetHigh();
	}

	public void SwitchOFF()
	{
		_gpio.SetLow();
	}
	
	
	public boolean IsON()
	{
		return _gpio.IsHigh();
	}
	
	public boolean IsOFF()
	{
		return _gpio.IsLow();
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
	
	public static String ModeToString(int mode)
	{
		switch(mode)
		{
			case 0: return "auto";
			case 1: return "forcedon";
			case 2: return "forcedoff";
			default : return "undef";
		}
	}

	public static int StringToMode(String s)
	{
		switch(s.toLowerCase())
		{
			case "auto": { return 0;}
			case "forcedon": { return 1; }			
			case "forcedoff": { return 2; }
			default:
			{
				_logger.error("Mode inconnu <" + s + ">");
				return -1;
			}
		}
		
	}
	// ---------- static function -------------


}

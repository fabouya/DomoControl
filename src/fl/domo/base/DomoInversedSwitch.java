package fl.domo.base;

import org.apache.log4j.Logger;

public class DomoInversedSwitch extends DomoSwitch {

	// -------------- members ----------------
	
	// ---------- static members -------------
	protected final static Logger _logger = Logger.getLogger(DomoInversedSwitch.class);	

	// -------------- function ----------------

	public DomoInversedSwitch(String name, String nameGPIO) 
	{
		super(name, nameGPIO);
		_logger.debug("Create DomoInversedSwitch " + name + " -> " + nameGPIO);		
	}

	@Override
	public void InitSwitch()
	{
		_gpio.SetInversedLogic(true);
		_gpio.SetHigh();		
	}

	@Override
	public void SwitchON()
	{
		super.SwitchOFF();
	}

	@Override
	public void SwitchOFF()
	{
		super.SwitchON();
	}
	
	@Override
	public boolean IsON()
	{
		return ! super.IsON();
	}
	
	@Override
	public boolean IsOFF()
	{
		return ! super.IsOFF();
	}
	
		
}

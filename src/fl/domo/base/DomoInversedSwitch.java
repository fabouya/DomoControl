package fl.domo.base;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class DomoInversedSwitch extends DomoSwitch {

	// -------------- members ----------------
	
	// ---------- static members -------------
	protected final static Logger _logger = Logger.getLogger(DomoInversedSwitch.class);	

	// -------------- function ----------------

	public DomoInversedSwitch(String name, String nameGPIO) 
	{
		_logger.debug("Create DomoInversedSwitch " + name + " -> " + nameGPIO);		
		super.Create(name, nameGPIO);
	}
	
	public DomoInversedSwitch() 
	{
	}	
	
	@Override
	void FromXML(Element item)
	{
		String name = item.getAttribute("name");
		String gpio = item.getAttribute("gpioname");
		
		_logger.debug("Create DomoInversedSwitch " + name + " -> " + gpio);		
	    Create(name, gpio);
	}
	
	@Override
	void ReloadFromXML(Element item) 
	{
		FromXML(item);
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
		_logger.debug("InversedSwitch SwitchOn : " + _name);
		super.SwitchOFF();
	}

	@Override
	public void SwitchOFF()
	{
		_logger.debug("InversedSwitch SwitchOff : " + _name);
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

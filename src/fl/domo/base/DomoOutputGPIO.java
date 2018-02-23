package fl.domo.base;
/* SANS PI4J

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
*/

import org.w3c.dom.Element;

public class DomoOutputGPIO extends DomoGPIO 
{
	/* SANS PI4J
	
	protected GpioPinDigitalOutput 	_pin = null;
	*/
	
	protected	boolean				_inversedLogic = false;


	public DomoOutputGPIO(String name, int pinNum) 
	{
		super(name, pinNum);
		/* SANS PI4J
		_pin = _gpioControler.provisionDigitalOutputPin(_gpioPin, _name + "_pin_" + pinNum );
		*/		
	}
	
	public DomoOutputGPIO()
	{		
	}
		
	//--------------------------------------------------------------------------
	
	public void Create(String name, int pinNum)
	{
		super.Create(name, pinNum);
	}
	
	@Override
	void FromXML(Element item)
	{
		Create(item.getAttribute("name"), Integer.parseInt(item.getAttribute("id")));		
	}

	@Override
	void ReloadFromXML(Element item) {
		// TODO Auto-generated method stub
		
	}


	//--------------------------------------------------------------------------

	public void SetInversedLogic(boolean logic)
	{
		/* SANS PI4J
		_inversedLogic = logic;
		
		if (_inversedLogic) 
		{
			_pin.setShutdownOptions(true, PinState.HIGH);
		} 
		else 
		{
			_pin.setShutdownOptions(true, PinState.LOW);
		}
		*/		
	}
	
	//--------------------------------------------------------------------------

	public boolean IsHigh() 
	{
		/* SANS PI4J

		PinState etat = _pin.getState();

		_logger.debug("Etat de " + _name + " sur  : " + _pinNumber + " = " + etat);

		return (PinState.HIGH == etat) ? true : false;
		*/
		return false;
	}
	
	public boolean IsLow() 
	{
		/* SANS PI4J
		PinState etat = _pin.getState();

		_logger.debug("Etat de " + _name + " sur  : " + _pinNumber + " = " + etat);

		return (PinState.LOW == etat) ? true : false;
		*/
		return false;
	}

	
	public void SetHigh()
	{
		/* SANS PI4J
		_pin.high();
		*/			
	}
	
	public void SetLow()
	{
		/* SANS PI4J
		_pin.low();
		*/
	}
	
	public void Toggle()
	{
		/* SANS PI4J
		
		PinState etat = _pin.getState();

		if(PinState.LOW == etat)
		{
			SetHigh();
		}
		else
		{
			SetLow();
		}
		*/
	}


}

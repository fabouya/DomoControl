package fl.domo.base;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class DomoOutputGPIO extends DomoGPIO 
{
	
	protected GpioPinDigitalOutput 	_pin = null;
	protected	boolean				_inversedLogic = false;


	public DomoOutputGPIO(String name, int pinNum) 
	{
		super(name, pinNum);

		_pin = _gpioControler.provisionDigitalOutputPin(_gpioPin, _name + "_pin_" + pinNum );		
	}
	
	public void SetInversedLogic(boolean logic)
	{
		_inversedLogic = logic;
		
		if (_inversedLogic) 
		{
			_pin.setShutdownOptions(true, PinState.HIGH);
		} 
		else 
		{
			_pin.setShutdownOptions(true, PinState.LOW);
		}		
	}

	public boolean IsHigh() 
	{
		PinState etat = _pin.getState();

		_logger.debug("Etat de " + _name + " sur  : " + _pinNumber + " = " + etat);

		return (PinState.HIGH == etat) ? true : false;
	}
	
	public boolean IsLow() 
	{
		PinState etat = _pin.getState();

		_logger.debug("Etat de " + _name + " sur  : " + _pinNumber + " = " + etat);

		return (PinState.LOW == etat) ? true : false;
	}

	
	public void SetHigh()
	{
		_pin.high();			
	}
	
	public void SetLow()
	{
		_pin.low();
	}
	
	public void Toggle()
	{
		PinState etat = _pin.getState();

		if(PinState.LOW == etat)
		{
			SetHigh();
		}
		else
		{
			SetLow();
		}
	}


}

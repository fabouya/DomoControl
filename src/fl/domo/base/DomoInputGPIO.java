package fl.domo.base;

import com.pi4j.io.gpio.GpioPinDigitalInput;

public class DomoInputGPIO extends DomoGPIO 
{
	protected GpioPinDigitalInput 	_pin = null;

	public DomoInputGPIO(String name, int pinNum) 
	{
		super(name, pinNum);

		_pin = _gpioControler.provisionDigitalInputPin(_gpioPin, _name + "_pin_" + pinNum );			

	}

}

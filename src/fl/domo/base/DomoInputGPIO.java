package fl.domo.base;
/* SANS PI4J

import com.pi4j.io.gpio.GpioPinDigitalInput;
*/

public class DomoInputGPIO extends DomoGPIO 
{
	/* SANS PI4J
	
	protected GpioPinDigitalInput 	_pin = null;
	*/

	public DomoInputGPIO(String name, int pinNum) 
	{
		super(name, pinNum);
		/* SANS PI4J

		_pin = _gpioControler.provisionDigitalInputPin(_gpioPin, _name + "_pin_" + pinNum );
		*/			

	}

}

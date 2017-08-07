package fl.domo.base;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public abstract class DomoGPIO extends DomoObject
{
	// -------------- members ----------------
	
	protected 	int					_pinNumber;	//wiredPi numerotation
	// pi4j gpio controler

	protected 	Pin 				_gpioPin;
	
	// ---------- static members -------------

	final static protected GpioController _gpioControler = GpioFactory.getInstance();
	
	// -------------- function ----------------

		public DomoGPIO(String name, int pinNum)
		{
			super(name);
			_pinNumber = pinNum;
			_gpioPin = RaspiPin.getPinByAddress(pinNum);

		}
	// ---------- static function -------------
		
}

package fl.domo.base;

public class DomoGPIO extends DomoObject
{
	// -------------- members ----------------
	
	protected 	int		_pinNumber;	//wiredPi numerotation
		
	// ---------- static members -------------
				
	// -------------- function ----------------

		public DomoGPIO(String name, int pinNum)
		{
			super(name);
			_pinNumber = pinNum;
		}
		
	// ---------- static function -------------
		
}

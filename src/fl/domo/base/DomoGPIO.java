package fl.domo.base;

public class DomoGPIO extends DomoObject
{
	// -------------- members ----------------
	
	protected 	int		_pinNumber;	//wiredPi numerotation
	// pi4j gpio controler
		
	// ---------- static members -------------
				
	// -------------- function ----------------

		public DomoGPIO(String name, int pinNum)
		{
			super(name);
			_pinNumber = pinNum;
		}
		
		public void SetHigh()
		{
			
		}
		
		public void SetLow()
		{
			
		}
		
		public void Toggle()
		{
			
		}
		
		public void SetOutputMode()
		{
			
		}
		
		public void SetInputMode()
		{
				
		}
	// ---------- static function -------------
		
}

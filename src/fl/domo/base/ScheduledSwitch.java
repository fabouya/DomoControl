package fl.domo.base;

import org.apache.log4j.Logger;

public class ScheduledSwitch extends DomoScheduledObject 
{
	// -------------- members ----------------
	
	protected String		_switchName = "undef";
	protected DomoSwitch	_switchObject = null;
	
	// ---------- static members -------------

	protected final static Logger _logger = Logger.getLogger(ScheduledSwitch.class);	
			
	// -------------- function ----------------

	public ScheduledSwitch(String name, String calendarName, String switchname) 
	{
		super(name, calendarName);
		_switchName = switchname;
		_switchObject = (DomoSwitch) DomoObject.GetObjectByName(_switchName);
	}

	@Override
	public void Step() 
	{
		// TODO Auto-generated method stub

		if(GetCalendarObject().IsActive())
		{
			if(! _switchObject.IsON())
			{
				_logger.debug("Activation " + _name);
				_switchObject.SwitchON();
			}
		}
		else
		{
			if(_switchObject.IsON())
			{
				_logger.debug("Désactivation " + _name);
				_switchObject.SwitchOFF();
			}
		}
	}

}

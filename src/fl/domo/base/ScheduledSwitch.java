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
		try
		{
			_switchObject.SwitchOFF();
		}
		catch(Exception e)
		{
			_logger.error("Erreur de creation de <" + name +"> <" + calendarName + "> <" + switchname + ">");
		}
	}

	@Override
	public void Step() 
	{
		// TODO Auto-generated method stub

		_logger.debug("Object : " + _name + " starting step");
		
		if(GetCalendarObject().IsActive())
		{
			if(! _switchObject.IsON())
			{
				_logger.debug("Activation switch " + _name);
				_switchObject.SwitchON();
			}
		}
		else
		{
			if(_switchObject.IsON())
			{
				_logger.debug("Désactivation switch " + _name);
				_switchObject.SwitchOFF();
			}
		}
		
		_logger.debug("Object : " + _name + " ending step");
	}

}

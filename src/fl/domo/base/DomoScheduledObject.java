package fl.domo.base;

import org.w3c.dom.Element;

public abstract class DomoScheduledObject extends DomoObject 
{
	// -------------- members ----------------
	
	protected String		_calendarName = "undef";
	protected DomoCalendar	_calendarObject = null;
	
	// ---------- static members -------------
				
	// -------------- function ----------------

	public DomoScheduledObject(String name, String calendarname) 
	{
		super(name);
		Create(name, calendarname);

	}

	public DomoScheduledObject()
	{
		
	}
	// -------------- function ----------------------------
	
	
	protected void Create(String name, String calendarName)
	{	
		super.Create(name);
		_calendarName = calendarName;
		_calendarObject = (DomoCalendar) DomoObject.GetObjectByName(_calendarName);
	}
	
	void FromXML(Element item)
	{
	}
	
	
	public String GetCalendarName() { return _calendarName; }
	public DomoCalendar GetCalendarObject() { return _calendarObject; }
	public void SetCalendarObject(DomoCalendar c) { _calendarObject = c; }

	abstract public void Step();
	
}

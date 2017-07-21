package fl.domo.base;

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
		_calendarName = calendarname;
		_calendarObject = (DomoCalendar) DomoObject.GetObjectByName(_calendarName);

	}
	
	public String GetCalendarName() { return _calendarName; }
	public DomoCalendar GetCalendarObject() { return _calendarObject; }
	public void SetCalendarObject(DomoCalendar c) { _calendarObject = c; }

	abstract public void Step();
	
}

package fl.domo.base;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class DomoCombinedCalendar extends DomoCalendar 
{

	// -------------- members ----------------
	
	protected	ArrayList<String> _calendars = new ArrayList<String>();
	
	// ---------- static members -------------
	private static Logger _logger = Logger.getLogger(DomoCombinedCalendar.class);

	// -------------- function ----------------

	public DomoCombinedCalendar(String name) 
	{
		// TODO Auto-generated constructor stub
		super(name);
	}

	public void SetPeriod(String period)
	{
		_logger.debug("Set period " + period);
		String[] parts = period.split(",");
		
		for(int i=0; i < parts.length; i++)
		{
			String c = parts[i];
			
			DomoObject o = DomoObject.GetObjectByName(c);
			
			if(null != o)
			{
				if(DomoCalendar.class.isInstance(o))
				{
					_logger.debug("Ajout calendrier " + c + " dans " + _name);
					_calendars.add(c.toLowerCase());
				}
				else
				{
					_logger.error("L'objet " + c + " n'est pas un calendrier");
				}
			}
			else
			{
				_logger.error("Le calendrier " + c + " n'existe pas");
			}
			
		}
		
	}
	
	public boolean IsActive() 
	{
		boolean active = true;
		// TODO Auto-generated method stub
		
		for(int i=0; i < _calendars.size(); i++)
		{
			String cal = _calendars.get(i);
			
			DomoCalendar o = (DomoCalendar) DomoObject.GetObjectByName(cal);
			
			active &= o.IsActive();
		}
		
		return active;
	}

	@Override
	public void Reload() {
		// TODO Auto-generated method stub
		
	}

}

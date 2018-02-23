package fl.domo.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class DomoHourlyCalendar extends DomoCalendar 
{
	// -------------- members ----------------

	protected int _tabHeures[] = new int[24];

	// ---------- static members -------------

	private static Logger _logger = Logger.getLogger(DomoHourlyCalendar.class);
	
	// -------------- function ----------------

	public DomoHourlyCalendar(String name) 
	{
		super(name);
	}
	
	public DomoHourlyCalendar() 
	{
	}
	
	public void Create(String name, String hours)
	{
	    _logger.debug("Create DomoHourlyCalendar");		
		super.Create(name);
		SetPeriod(hours);
	}
	
	@Override
	void FromXML(Element item)
	{
		String name = item.getAttribute("name");
		String hours = item.getAttribute("hours");
		
	    _logger.debug("CAL : " + name + " : " + hours);
	    
	    Create(name, hours);		
	}
	
	@Override
	public void Reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void ReloadFromXML(Element item) {
		// TODO Auto-generated method stub
		
	}
	
	
	//---------------------------------------------

	@Override
	public void SetPeriod(String period)
	{
		_logger.debug("Set period " + period);
		// periode de la forme "h1, h2, h3, ..."
		String[] parts = period.split(",");
		
		for(int h=0; h < 24; h++)
			_tabHeures[h] = 0;
		
		for(int i=0; i < parts.length; i++)
		{
			int h = Integer.parseInt(parts[i]);
			if((h >-1) && (h<24))
			{
				_tabHeures[h] = 1;
			}
			else
			{
				_logger.error("Heure invalide " + h);				
			}
		}
	}

	@Override
	public boolean IsActive() 
	{
		String h = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());

		return (_tabHeures[Integer.parseInt(h)] == 1 ? true : false);
	}


	// ---------- static function -------------

}

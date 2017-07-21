package fl.domo.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

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

	public void SetPeriod(String period)
	{
		_logger.debug("Addperiod " + period);
		// periode de la forme "h1, h2, h3, ..."
		String[] parts = period.split(",");
		
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
	
	public boolean IsActive() 
	{
		String h = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());

		return (_tabHeures[Integer.parseInt(h)] == 1 ? true : false);
	}

	// ---------- static function -------------

}

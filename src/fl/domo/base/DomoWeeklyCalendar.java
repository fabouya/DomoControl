package fl.domo.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DomoWeeklyCalendar extends DomoCalendar 
{

	// -------------- members ----------------

	protected int _tabJourSemaine[] = new int[8];
	
	// ---------- static members -------------
	
	private static Logger _logger = Logger.getLogger(DomoWeeklyCalendar.class);
				
	// -------------- function ----------------


	public DomoWeeklyCalendar(String name) 
	{
		// TODO Auto-generated constructor stub
		super(name);
	}

	@Override
	public void SetPeriod(String period)
	{
		_logger.debug("Set period " + period);
		// periode de la forme "h1, h2, h3, ..."
		// 1 = lundi, 2 = mardi, ..., 7 = dimanche
		String[] parts = period.split(",");
		
		for(int i=0; i < parts.length; i++)
		{
			int d = Integer.parseInt(parts[i]);
			if((d >0) && (d<8))
			{
				_tabJourSemaine[d] = 1;
			}
			else
			{
				_logger.error("jour invalide " + d);				
			}
		}
	}

	@Override
	public boolean IsActive() 
	{
		// TODO Auto-generated method stub
		String h = new SimpleDateFormat("u", Locale.FRANCE).format(new Date());

		return (_tabJourSemaine[Integer.parseInt(h)] == 1 ? true : false);
	}

	@Override
	public void Reload() {
		// TODO Auto-generated method stub
		
	}

}

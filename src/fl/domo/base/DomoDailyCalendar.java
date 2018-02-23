package fl.domo.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class DomoDailyCalendar extends DomoCalendar 
{

	// -------------- members ----------------
	protected int _tabJourMois[] = new int[32];
	
	// ---------- static members -------------
	private static Logger _logger = Logger.getLogger(DomoDailyCalendar.class);
				
	// -------------- function ----------------

	public DomoDailyCalendar(String name) 
	{
		// TODO Auto-generated constructor stub
		super(name);
	}

	@Override
	public void SetPeriod(String period)
	{
		_logger.debug("Set period " + period);
		String[] parts = period.split(",");
		
		for(int j=1; j < 32; j++)
			_tabJourMois[j] = 0;
		
		for(int i=0; i < parts.length; i++)
		{
			int d = Integer.parseInt(parts[i]);
			if((d >0) && (d<32))
			{
				_tabJourMois[d] = 1;
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
		String h = new SimpleDateFormat("d", Locale.FRANCE).format(new Date());

		return (1 == _tabJourMois[Integer.parseInt(h)] ? true : false);
	}
	// ---------- static function -------------

	@Override
	public void Reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void ReloadFromXML(Element item) {
		// TODO Auto-generated method stub
		
	}
}

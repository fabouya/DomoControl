package fl.domo.threads;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import fl.domo.base.DomoObject;
import fl.domo.base.DomoScheduledObject;
import fl.domo.base.DomoThread;
import fl.domo.tools.Global;

public class SchedulerThread extends DomoThread 
{

	private static Logger _logger = Logger.getLogger(SchedulerThread.class);
	
	public SchedulerThread(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void run() 
	{
		_logger.info("Starting thread " + _name);
		
		while (false == Global._quitFlag) 
		{
			try 
			{
				_logger.info("Starting SchedulerThread " + _name);

				ArrayList<DomoObject> scheduledItems = DomoObject.GetObjectsByClass("fl.domo.base.DomoScheduledObject");
				
				for(DomoObject item : scheduledItems)
				{
					((DomoScheduledObject) item).Step();
				}

				sleep(Global._scheduleSleep);				
			}
			catch (InterruptedException ex) 
			{
	            Thread.currentThread().interrupt(); // Très important de réinterrompre
	            break; // Sortie de la boucle infinie
	        }
			catch (Exception e) 
			{
				e.printStackTrace();
				break;
			}
			
		}
	}

}

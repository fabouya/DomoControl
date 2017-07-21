package fl.domo.threads;

import org.apache.log4j.Logger;

import fl.domo.base.DomoThread;

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

		try {
			_logger.info("Starting SchedulerThread " + _name);
			
	        
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

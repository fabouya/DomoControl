package fl.domo.threads;

import org.apache.log4j.Logger;

import fl.domo.base.DomoThread;
import fl.domo.tools.Global;

public class TcpServerThread extends DomoThread 
{

	private static Logger _logger = Logger.getLogger(TcpServerThread.class);

	public TcpServerThread(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void run() 
	{

		try {
			
			_logger.info("Starting TcpServerThread " + _name);
			
	        
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	
}

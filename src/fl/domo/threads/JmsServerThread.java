package fl.domo.threads;

import org.apache.log4j.Logger;

import fl.domo.base.DomoThread;

public class JmsServerThread extends DomoThread 
{

	private static Logger _logger = Logger.getLogger(JmsServerThread.class);
			
	public JmsServerThread(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void run() 
	{

		try {
			_logger.info("Starting SchedulerThread " + _name);

			/*
			_jmsConsumer = new PoolJMSConsumer();
			
			_jmsConsumer.Server();
			
			while (false == Global._quitFlag) 
			{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
	        */
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

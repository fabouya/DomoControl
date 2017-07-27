package fl.domo.threads;

import org.apache.log4j.Logger;

import fl.domo.base.DomoThread;
import fl.domo.coms.JMSConsumer;
import fl.domo.tools.Global;

public class JmsServerThread extends DomoThread 
{

	private static Logger _logger = Logger.getLogger(JmsServerThread.class);
	
	private JMSConsumer		_jmsConsumer=null;
			
	public JmsServerThread(String name) 
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void EndJMSConsumer()
	{
		_jmsConsumer.EndJMSConsumer();
	}
	
	public void run() 
	{

		try {
			_logger.info("Starting SchedulerThread " + _name);

			
			_jmsConsumer = new JMSConsumer();
			
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
	        
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

package fl.domo.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import fl.domo.base.DomoObjectFactory;
import fl.domo.threads.JmsServerThread;
import fl.domo.threads.SchedulerThread;
import fl.domo.threads.TcpServerThread;
import fl.domo.tools.Global;

public class DomoBrain 
{

	protected final static Logger _logger = Logger.getLogger(DomoBrain.class);			
	
	private static void ReadProperties(String filename)
	{
		_logger.debug("Read prop file : " + filename);

		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filename);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			Global._xmlFile = prop.getProperty("config");
			Global._tcpPort = Integer.parseInt(prop.getProperty("tcpserverport"));
			Global._jmsProvider = prop.getProperty("_jmsProvider");
			Global._commandQueueName = prop.getProperty("commandQueueName");
			Global._scheduleSleep = Integer.parseInt(prop.getProperty("schedulerSleep"));
			Global._schedulerEnabled = Boolean.parseBoolean(prop.getProperty("enableScheduler"));
			Global._jmsEnabled = Boolean.parseBoolean(prop.getProperty("enableJMS"));
			Global._tcpEnabled = Boolean.parseBoolean(prop.getProperty("enableTcpServer"));

		} 
		catch (final IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

//1
		// Lire les properties
		ReadProperties("domo.properties");
		
//2
		// Lire de fichier xml de declaration des objets
		DomoObjectFactory factory = new DomoObjectFactory();
		
		factory.BuildFromXML("configtest.xml");
		
//3
		SchedulerThread scheduler = null;
		TcpServerThread tcpserver = null;
		JmsServerThread jms = null;
		
		// lancer les threads de serveurs
		// Scheduling thread
		if(Global._schedulerEnabled)
			scheduler = new SchedulerThread("Scheduler"); 
		
		// TCPServer thread
		if(Global._tcpEnabled)
			tcpserver = new TcpServerThread("TcpServerThread");
		
		// JMS Server thread
		if(Global._jmsEnabled)
			jms = new JmsServerThread("JMSServer");
				
		if(Global._schedulerEnabled)
		{
			_logger.debug("Start thread scheduler");
			scheduler.start();
		}
		
		if(Global._tcpEnabled)
		{
			_logger.debug("Start thread tcp server");
			tcpserver.start();
		}
		
		if(Global._jmsEnabled)
		{
			_logger.debug("Start thread jms");
			
			jms.start();
		}
		
//4
		// entrer dans la boucle principale
		while (false == Global._quitFlag) 
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(Global._jmsEnabled)
			jms.EndJMSConsumer();
		
	}

}

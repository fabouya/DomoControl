package fl.domo.threads;

import org.apache.log4j.Logger;

import fl.domo.base.DomoThread;
import fl.domo.coms.TCPServer;
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
			
			TCPServer server = new TCPServer(Global._tcpPort);
			
			server.RunServer();			
	        
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	
}

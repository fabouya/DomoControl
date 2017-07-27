package fl.domo.coms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import fl.domo.main.SimpleCommandEngine;
import fl.domo.tools.Global;

public class TCPServer 
{
	// -------------- members ----------------

	protected int _portNumber = 5832;
	protected SimpleCommandEngine _engine = null;
	
	// ---------- static members -------------

	private static Logger _logger = Logger.getLogger(TCPServer.class);
			
	// -------------- function ----------------


	public TCPServer(int port) 
	{
		_portNumber = port;
	}

	public void RunServer() throws Exception 
	{
		String clientSentence;
		String capitalizedSentence;
		_engine = new SimpleCommandEngine();
		
		ServerSocket welcomeSocket = new ServerSocket(_portNumber);
		

		_logger.info("Running TCP server on " + _portNumber);
		
		
		while (false == Global._quitFlag) 
		{
			Socket connectionSocket = welcomeSocket.accept();
			connectionSocket.setTcpNoDelay(true);
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			_logger.debug("Reading from client");
					
			clientSentence = inFromClient.readLine();

			_logger.debug("Received: " + clientSentence);
			
			capitalizedSentence = clientSentence.toLowerCase();
			
			if(! clientSentence.endsWith("\n"))
			{
				capitalizedSentence += '\n';
			}

			try
			{
				if (null != _engine) 
				{
					String result = _engine.RunCommand(capitalizedSentence);
					outToClient.writeBytes(result + '\n');
				}				

				inFromClient.close();
				outToClient.close();
				connectionSocket.close();			

			}
			catch(Exception e)
			{
				inFromClient.close();
				outToClient.close();
				connectionSocket.close();							
			}
			
			
		}
		
		_logger.info("Exit TCP Server");
		welcomeSocket.close();

	}

	
}

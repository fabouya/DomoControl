package fl.domo.coms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import fl.domo.tools.Global;

public class TCPServer 
{
	// -------------- members ----------------

	protected int _portNumber = 5832;
	
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
		
		@SuppressWarnings("resource")
		ServerSocket welcomeSocket = new ServerSocket(_portNumber);

		_logger.info("Running server on " + _portNumber);
		
		while (false == Global._quitFlag) 
		{
			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			clientSentence = inFromClient.readLine();

			// System.out.println("Received: " + clientSentence);
			_logger.debug("Received: " + clientSentence);
			
			capitalizedSentence = clientSentence.toUpperCase() + '\n';

			// outToClient.writeBytes(capitalizedSentence);

/*
			if (null != _engine) {
				String result = _engine.RunCommand(capitalizedSentence);
				outToClient.writeBytes(result + '\n');
			}
*/
		}
	}

	
}

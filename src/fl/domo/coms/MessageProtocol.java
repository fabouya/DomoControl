package fl.domo.coms;

import org.apache.log4j.Logger;
import fl.domo.main.CommandEngine;

public class MessageProtocol 
{
	private CommandEngine _commandEngine = new CommandEngine();
	
	protected final static Logger _logger = Logger.getLogger(MessageProtocol.class);		
	
    public String handleProtocolMessage(String messageText) 
    {
    	_logger.info("Decodage message : " + messageText);
    	
        String responseText = _commandEngine.RunCommand(messageText);

        _logger.info("returning message " + responseText);
        return responseText;
    }
}
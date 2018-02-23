package fl.domo.commands;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fl.domo.base.BasicCommands;
import fl.domo.base.DomoObject;
import fl.domo.base.DomoObjectFactory;
import fl.domo.tools.Global;

public class DomoCommande 
{

	protected final static Logger _logger = Logger.getLogger(DomoCommande.class);	

	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageProducer messageProducer = null;

	private int ackMode;
	private boolean transacted = false;
	
	private String _url;
	private String  _commandQueueName;	 
    
	public DomoCommande(String mqURL, String commandQueueName ) 
	{
		// TODO Auto-generated constructor stub		
		_url = mqURL;
		_commandQueueName = commandQueueName;
        ackMode = Session.AUTO_ACKNOWLEDGE;
	}
	
	//----------------------------------------------------------
	
	public String Status()
	{
		
		JSONObject obj = BuilJSONCommand("status");
		
		try
		{
			return send2Queue(obj.toJSONString());
		}
		catch(Exception e)
		{
			_logger.error(e.getMessage());
			return "ERROR";
		}
	}
	
	public String Mode()
	{
		
		JSONObject obj = BuilJSONCommand("mode");
		
		try
		{
			return send2Queue(obj.toJSONString());
		}
		catch(Exception e)
		{
			_logger.error(e.getMessage());
			return "ERROR";
		}
	}

	
	//----------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private JSONObject BuilJSONCommand(String commande)
	{
		JSONObject obj = new JSONObject();
        obj.put("tag", "command");
        obj.put("command", commande);
        
        return obj;
		
	}
	
	private String send2Queue(String message) throws Exception
	{

		  ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(_url);
		  
		  connection = connectionFactory.createConnection();

          session = connection.createSession(transacted, ackMode);
          destination = session.createQueue(_commandQueueName);		  
		  
		  messageProducer = session.createProducer(destination);
		  connection.start();
		  
		  String s = this.sendMessage(message);

		  messageProducer.close();
		  session.close();
		  connection.close();
		  
		  return s;
	 }	
	
	//-----------------------------------------------------------------------
	
	private String createRandomString() 
    {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        
        _logger.info("Id : " + Long.toHexString(randomLong));
        
        return "ID_" + Long.toHexString(randomLong);
    }


	private String sendMessage( String requestText) throws RuntimeException 
	{
		  try 
		  {
		
			   TextMessage message = session.createTextMessage(requestText);
			   String correlationId = createRandomString();
			   //Create Reply To Queue
		
			   Destination replyQueue = session.createTemporaryQueue();
			   
			   message.setJMSReplyTo(replyQueue);
			   message.setJMSCorrelationID(correlationId);
			   String messgeSelector = "JMSCorrelationID = '" + correlationId + "'";
			
			   MessageConsumer replyConsumer = session.createConsumer(replyQueue, messgeSelector);
			
			   messageProducer.send(message, javax.jms.DeliveryMode.PERSISTENT,   javax.jms.Message.DEFAULT_PRIORITY, 1800000);
			   System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			   System.out.println("Message sent "+ requestText);
			   System.out.println("messageSelector name is : "+messgeSelector.toString());
			   System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			
			   Message replayMessage = replyConsumer.receive();
			
			   TextMessage textMessage = (TextMessage) replayMessage;
			   String replayText = textMessage.getText();
			
			   System.out.println("Request Message -->"+ requestText);
			   System.out.println("Response Message -->"+ replayText);
			   
			   return replayText;
		  } 
		  catch (Exception e) 
		  {
			  throw new RuntimeException(e);
		  } 
	}
	
	
	//------------------------

}

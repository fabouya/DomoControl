package fl.domo.coms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import fl.domo.coms.MessageProtocol;
import fl.domo.tools.Global;

import javax.jms.*;

public class JMSConsumer implements MessageListener 
{
	protected final static Logger _logger = Logger.getLogger(JMSConsumer.class);

    private  int 				_ackMode;
    private  String 			_messageQueueName;
    private  String 			_messageBrokerUrl;
 
    private Session 			_session;
    private boolean 			_transacted = false;
    private MessageProducer 	_replyProducer;
    private MessageProtocol 	_messageProtocol;
     
    {
        _messageBrokerUrl = Global._jmsProvider;
        _messageQueueName = Global._commandQueueName;
        _ackMode = Session.AUTO_ACKNOWLEDGE;
    }

	public JMSConsumer() 
	{
		// TODO Auto-generated constructor stub
	}
	

	
    public void Server() 
    {
 
        //Delegating the handling of messages to another class, instantiate it before setting up JMS so it
        //is ready to handle messages
        _messageProtocol = new MessageProtocol();
        setupMessageQueueConsumer();
    }
 
    private void setupMessageQueueConsumer() 
    {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(_messageBrokerUrl);
        Connection connection;
        
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            _session = connection.createSession(_transacted, _ackMode);
            Destination adminQueue = _session.createQueue(_messageQueueName);
 
            //Setup a message producer to respond to messages from clients, we will get the destination
            //to send to from the JMSReplyTo header field from a Message
            _replyProducer = _session.createProducer(null);
            _replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
 
            //Set up a consumer to consume messages off of the admin queue
            MessageConsumer _consumer = _session.createConsumer(adminQueue);
            _consumer.setMessageListener(this);
        } 
        catch (JMSException e) 
        {
            //Handle the exception appropriately
        	_logger.error("Erreur connexion activemq", e);
        	System.exit(1);        	
        }
    }
 
	@Override
    public void onMessage(Message message) 
    {
        try 
        {
        	_logger.debug("Reception message");
            TextMessage response = _session.createTextMessage();
            if (message instanceof TextMessage) 
            {
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                response.setText(_messageProtocol.handleProtocolMessage(messageText));
            }
 
            //Set the correlation ID from the received message to be the correlation id of the response message
            //this lets the client identify which message this is a response to if it has more than
            //one outstanding message to the server
            response.setJMSCorrelationID(message.getJMSCorrelationID());
 
            //Send the response to the Destination specified by the JMSReplyTo field of the received message,
            //this is presumably a temporary queue created by the client
            _replyProducer.send(message.getJMSReplyTo(), response);
        } 
        catch (JMSException e) 
        {
            //Handle the exception appropriately
        	_logger.error("Erreur onMessage", e);        	        	
        }
    }

	
	
}

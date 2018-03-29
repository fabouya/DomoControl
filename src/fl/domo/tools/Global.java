package fl.domo.tools;


public class Global 
{
	public static volatile boolean 	_quitFlag = false;
	public static volatile boolean  _delayedQuitFlag = false;
	public static String			_xmlFile = "";
	public static int				_tcpPort = 5832;
	public static int				_scheduleSleep = 5000;
	public static String 			_jmsProvider = "";
	public static String 			_commandQueueName = "";	
	public static boolean			_schedulerEnabled = true;
	public static boolean			_jmsEnabled = true;
	public static boolean			_tcpEnabled = true;
	public static String			_propFile = "";
	public static String			_configFile = "";
	
}

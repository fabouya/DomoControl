package fl.domo.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import fl.domo.base.DomoObject;
import fl.domo.tools.Global;
import fl.domo.tools.Tools;

public class SimpleCommandEngine 
{
	private static Logger _logger = Logger.getLogger(SimpleCommandEngine.class);

	public SimpleCommandEngine() 
	{
		// TODO Auto-generated constructor stub
		
		_logger.debug("Create SimpleCommandEngine");
		
	}
	
	public String RunCommand(String command)
	{
		// commandes :
		// QUIT
		// SET [ROBOT|POMPE] [AUTO|FORCEDON|FORCEDOFF]
		// RELOAD
		// GET [ROBOT|POMPE|LUMIERE|STATUS|ALLMODE]

		command = Tools.chop(command);

		_logger.info("Command to parse : <" + command + ">");

		if (command.equals("QUIT")) 
		{
			_logger.info("Quit command");
			Global._quitFlag = true;
			return "QUIT";
		}

		if (command.equals("RELOAD")) 
		{
			_logger.info("RELOAD command");
			return "RELOAD OK";
		}

		Pattern pat = Pattern.compile("^SET +(.+) +(.+) *");
		Matcher matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("SET command");
			
			// SET [ROBOT|POMPE|LUMIERE] [AUTO|FORCEDON|FORCEDOFF]
			String[] items = command.split(" +");

/*			
			if (items[1].equals("ROBOT")) {
				target = Global._RobotGPIO;
			}

			if (items[1].equals("POMPE")) {
				target = Global._PumpGPIO;
			}

			if (items[1].equals("LUMIERE")) {
				target = Global._LumiereGPIO;
			}

			if (null == target) 
			{
				_logger.info("SET to unknown target : <" + items[1] + ">");
				return "ERROR";
			}
*/

/*			
			if (items[2].matches("AUTO")) {
				_logger.info("SET " + target._name + " to auto mode");
				target.SetModeAuto();
				return target._name + " SET to auto mode";
			}

			if (items[2].matches("FORCEDON")) {
				_logger.info("SET " + target._name + " to ON mode");
				target.SetModeForcedON();
				return target._name + " SET to ON mode";
			}

			if (items[2].matches("FORCEDOFF")) {
				_logger.info("SET " + target._name + " to OFF mode");
				target.SetModeForcedOFF();
				return target._name + " SET to OFF mode";
			}

			_logger.info("unknown mode");
*/			
			return "ERROR";
		}

		// pat = Pattern.compile("^GET +(.+) +(.+) *");
		pat = Pattern.compile("^GET +(.+) *");
		matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("GET command");
			String[] items = command.split(" +");

			_logger.debug("items 0 : " + items[0]);
			_logger.debug("items 1 : " + items[1]);
			
			DomoObject o = DomoObject.GetObjectByName(items[1]);

/*			
			if (items[1].equals("STATUS")) 
			{
				_logger.info("GET STATUS");           
				
		        JSONObject obj = new JSONObject();
		        obj.put("TAG", "STATUS");
		        obj.put("STATUS", "OK");
		        
		        obj.put("POMPE", String.valueOf(Global._PumpGPIO.IsActivated()));
		        obj.put("ROBOT", String.valueOf(Global._RobotGPIO.IsActivated()));
		        obj.put("LUMIERE", String.valueOf(Global._LumiereGPIO.IsActivated()));
		        
		        
		        String s = obj.toJSONString();
		        
		        _logger.info("Return " + s);

				return s;
			}
			
			if (items[1].equals("ALLMODE")) 
			{
				_logger.info("GET ALLMODE");
				
		        JSONObject obj = new JSONObject();
		        obj.put("TAG", "STATUS");
		        obj.put("STATUS", "OK");

		        obj.put("POMPE", String.valueOf(Global._PumpGPIO.GetMode()));
		        obj.put("ROBOT", String.valueOf(Global._RobotGPIO.GetMode()));
		        obj.put("LUMIERE", String.valueOf(Global._LumiereGPIO.GetMode()));
		        
		        String s = obj.toJSONString(); 
		        _logger.info("Return " + s);

				return s;
			}
			
	
			if (null == target) {
				_logger.info("GET unknown target : <" + items[1] + ">");
				return "ERROR";
			}

			return String.valueOf(target.GetMode());
*/			
		}

		return "ERROR";
	}
	

}

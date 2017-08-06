package fl.domo.main;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import fl.domo.base.DomoObject;
import fl.domo.base.DomoSwitch;
import fl.domo.tools.Global;
import fl.domo.tools.Tools;
import fl.domo.base.BasicCommands;

public class SimpleCommandEngine 
{
	private static Logger _logger = Logger.getLogger(SimpleCommandEngine.class);
	
	private static final String _error = "ERROR";
	private static final String _ok = "OK";

	public SimpleCommandEngine() 
	{
		// TODO Auto-generated constructor stub
		
		_logger.debug("Create SimpleCommandEngine");
		
	}
	
	public synchronized String RunCommand(String command)
	{

		command = Tools.chop(command);
		
		_logger.info("Command to parse : <" + command + ">");

		if (command.equals("quit")) 
		{
			_logger.info("Quit command");
			Global._quitFlag = true;
			return _ok;
		}

//------------------------ COMMANDE RELOAD -------------------------------------
		
		if (command.equals("reload")) 
		{
			_logger.info("RELOAD command");
			return _ok;
		}

//------------------------ COMMANDE SET -------------------------------------
		
		Pattern pat = Pattern.compile("^set +(.+) +(.+) *");
		Matcher matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("set command");
			
			// SET [ROBOT|POMPE|LUMIERE] [AUTO|FORCEDON|FORCEDOFF]
			String[] items = command.split(" +");
			
			DomoObject o = DomoObject.GetObjectByName(items[1]);

			if (null == o) 
			{
				_logger.error("set to unknown target : <" + items[1] + ">");
				return _error;
			}
			
			if(o instanceof fl.domo.base.DomoSwitch)
			{
				return ((fl.domo.base.DomoSwitch) o).SetMode(items[2]);
			}
			else
			{
				_logger.error("set to bad class target : <" + items[1] + ">");
				return _error;				
			}

		}

//------------------------ COMMANDE GET -------------------------------------
		
		// pat = Pattern.compile("^GET +(.+) +(.+) *");
		pat = Pattern.compile("^get +(.+) +(.+) *");
		matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("get command");
			String[] items = command.split(" +");

			_logger.debug("items 0 : <" + items[0] + ">");
			_logger.debug("items 1 : <" + items[1] + ">");
			_logger.debug("items 2 : <" + items[2] + ">");
			
			if(items[1].toLowerCase().equals("all"))
			{
				ArrayList<DomoObject> objs = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");
				
				if(null == objs)
				{
					_logger.error("aucun switch referencé");
					return _error;					
				}
				
				if(items[2].toLowerCase().equals("mode"))
				{
					return BasicCommands.JsonModeAll();
				}
				else
					if(items[2].toLowerCase().equals("status"))
					{
						return BasicCommands.JsonStatusAll();
					}
					else
					{
						_logger.error("commande GET inconnue : " + items[0] + " " + items[1]);
						return _error;
					}
					
			}
			
			DomoObject o = DomoObject.GetObjectByName(items[1]);
			
			if(null == o)
			{
				_logger.error("objet inconnu: " + items[0]);
				return _error;
				
			}
			
			if(! (o instanceof fl.domo.base.DomoSwitch))
			{
				_logger.error("objet " + items[1] + "n'est pas un switch");
				return _error;				
			}
			
			if(items[2].toLowerCase().equals("mode"))
			{
				return BasicCommands.JsonGetMode(items[1]);
			}
			else
				if(items[2].toLowerCase().equals("status"))
				{				
					return BasicCommands.JsonGetStatus(items[1]);					
				}
				else
				{
					_logger.error("commande GET inconnue : " + items[0] + " " + items[1]);
					return _error;
				}
			
		}

		_logger.error("commande inconnue : " + command);
		return _error;
	}
	

}

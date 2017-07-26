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
	
	public String RunCommand(String command)
	{
		// commandes :
		// QUIT
		// SET <objectname> [AUTO|FORCEDON|FORCEDOFF]
		// RELOAD
		// GET [ROBOT|POMPE|LUMIERE|STATUS|ALLMODE]

		command = Tools.chop(command);

		_logger.info("Command to parse : <" + command + ">");

		if (command.equals("QUIT")) 
		{
			_logger.info("Quit command");
			Global._quitFlag = true;
			return _ok;
		}

		if (command.equals("RELOAD")) 
		{
			_logger.info("RELOAD command");
			return _ok;
		}

//------------------------ COMMANDE SET -------------------------------------
		
		Pattern pat = Pattern.compile("^SET +(.+) +(.+) *");
		Matcher matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("SET command");
			
			// SET [ROBOT|POMPE|LUMIERE] [AUTO|FORCEDON|FORCEDOFF]
			String[] items = command.split(" +");
			
			DomoObject o = DomoObject.GetObjectByName(items[1]);

			if (null == o) 
			{
				_logger.error("SET to unknown target : <" + items[1] + ">");
				return _error;
			}
			
			if(o instanceof fl.domo.base.DomoSwitch)
			{
				return ((fl.domo.base.DomoSwitch) o).SetMode(items[2]);
			}
			else
			{
				_logger.error("SET to bad class target : <" + items[1] + ">");
				return _error;				
			}

		}

//------------------------ COMMANDE GET -------------------------------------
		
		// pat = Pattern.compile("^GET +(.+) +(.+) *");
		pat = Pattern.compile("^GET +(.+) *");
		matcher = pat.matcher(command);

		if (matcher.matches()) 
		{
			_logger.info("GET command");
			String[] items = command.split(" +");

			_logger.debug("items 0 : " + items[0]);
			_logger.debug("items 1 : " + items[1]);
			
			if(items[0].toLowerCase().equals("ALL"))
			{
				ArrayList<DomoObject> objs = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");
				
				if(null == objs)
				{
					_logger.error("aucun switch referencé");
					return _error;					
				}
				
				if(items[1].toLowerCase().equals("mode"))
				{
			        JSONObject json = new JSONObject();
			        json.put("TAG", "MODE");
			        json.put("STATUS", "OK");

			        for(DomoObject o : objs)
					{
						String mode = String.valueOf(((DomoSwitch) o).GetMode());
						json.put(o.GetName(), mode);
					}
					return json.toJSONString();
				}
				else
					if(items[1].toLowerCase().equals("status"))
					{
				        JSONObject json = new JSONObject();
				        json.put("TAG", "STATUS");
				        json.put("STATUS", "OK");
				        
						for(DomoObject o : objs)
						{
							String state = String.valueOf(((DomoSwitch) o).GetState());
							json.put(o.GetName(), state);
						}
						
						return json.toJSONString();
					}
					else
					{
						_logger.error("commande GET inconnue : " + items[0] + " " + items[1]);
						return _error;
					}
					
			}
			
			DomoObject o = DomoObject.GetObjectByName(items[0]);
			
			if(null == o)
			{
				_logger.error("objet inconnu: " + items[0]);
				return _error;
				
			}
			
			if(items[1].toLowerCase().equals("mode"))
			{
		        JSONObject json = new JSONObject();
		        json.put("TAG", "MODE");
		        json.put("STATUS", "OK");
		        
				String mode = String.valueOf(((DomoSwitch) o).GetMode());
				json.put(o.GetName(), mode);
		     
				return json.toJSONString();
			}
			else
				if(items[1].toLowerCase().equals("status"))
				{
			        JSONObject json = new JSONObject();
			        json.put("TAG", "STATUS");
			        json.put("STATUS", "OK");
			        
					String state = String.valueOf(((DomoSwitch) o).GetState());
					json.put(o.GetName(), state);
					
					return json.toJSONString();
					
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

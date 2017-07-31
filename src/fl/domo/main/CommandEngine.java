package fl.domo.main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fl.domo.base.BasicCommands;
import fl.domo.base.DomoObject;
import fl.domo.tools.Global;

public class CommandEngine
{
	// -------------- members ----------------
		
	// ---------- static members -------------

	protected final static Logger _logger = Logger.getLogger(CommandEngine.class);	
			
	// -------------- function ----------------

	public CommandEngine()
	{
		_logger.info("Create CommandEngine");
	}

	// -------------- function utilitaires ---
	
	@SuppressWarnings("unchecked")
	protected JSONObject BuilJSONError(String errorMsg)
	{
		JSONObject obj = new JSONObject();
        obj.put("tag", "status");
        obj.put("status", "nok");
        obj.put("error_msg", errorMsg);
        
        return obj;
		
	}
	
	@SuppressWarnings("unchecked")
	protected JSONObject BuilJSONSuccess()
	{
		JSONObject obj = new JSONObject();
        obj.put("tag", "status");
        obj.put("status", "ok");
        
        return obj;		
	}
	
	// ------------- Decodeur -----------------
	
	protected synchronized String HandleCommand(JSONObject jsonObject)
	{
		JSONObject jsonobj = null;

		String command = (String) jsonObject.get("command");
		
		if(null == command)
		{
			jsonobj = BuilJSONError("Missing command tag");
		}
		else
		{
			switch(command.toUpperCase())
			{
				case "STATUS":
					return Status(jsonObject);
				
				
				case "MODE":
					return Mode(jsonObject);
				
				
				case "SET":
					return Set(jsonObject);
				
				case "GET":
					return Get(jsonObject);

				case "RELOAD":
					return Reload(jsonObject);
				
					
				case "STOP":
					jsonobj = Stop(jsonObject);
				break;
				
				default:
					jsonobj = BuilJSONError("unknown command request : " + command );						
			}
		}
		
		String jsonString = jsonobj.toJSONString();
		
		return jsonString;								
		
	}

	
	public synchronized String RunCommand(String jsonCommand)
	{
		JSONObject jsonobj = null;
		
		//1 decode json
		
		try
		{
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(jsonCommand);
	
			//1 check tag is command
			String tag = (String) jsonObject.get("tag");
			
			if(null == tag)
			{
				_logger.error("l'entree <tag> est absente du json");
				return BuilJSONError("entree <tag> absente").toJSONString();
			}
			
			switch(tag.toLowerCase())
			{
				case "command":
				{
					return HandleCommand(jsonObject);
				}
				
				default:
				{
					jsonobj = BuilJSONError("not a command request : " + tag );

					String jsonString = jsonobj.toJSONString();
					
					return jsonString;								
				}
			} // switch
			
		}
		catch(Exception e)
		{	
			_logger.error(e);
			
			return BuilJSONError("Error decoding : " + jsonCommand ).toJSONString();
		}
	}

	//------------------------- Effective commands -------------------------------

	
	//+++++++++++++++++++++++++ STATUS +++++++++++++++++++++++++++++++
		
		protected String Status(JSONObject command)
		{
			_logger.info("command : STATUS");           
				        
	        return BasicCommands.JsonStatusAll();
	        
		}

	//+++++++++++++++++++++++++ MODE +++++++++++++++++++++++++++++++
		
		protected String Mode(JSONObject command)
		{
			_logger.info("command : MODE");           
								
			return BasicCommands.JsonModeAll();			
		}

	//+++++++++++++++++++++++++ SET +++++++++++++++++++++++++++++++
		
		protected String Set(JSONObject command)
		{
			String device = (String) command.get("device");		
			String mode = (String) command.get("mode");
			
			JSONObject jsonErr = BuilJSONError("Error in SET command");
			
			if((null == device) || (null == mode))
			{
				return jsonErr.toJSONString();
			}

			DomoObject o = DomoObject.GetObjectByName(device);

			if (null == o) 
			{
				_logger.error("set to unknown target : <" + device + ">");
				return jsonErr.toJSONString();
			}
			
			if(o instanceof fl.domo.base.DomoSwitch)
			{
				if("OK".equals(((fl.domo.base.DomoSwitch) o).SetMode(mode)))
				{
					return BuilJSONSuccess().toJSONString();
				}
				else
				{
					return jsonErr.toJSONString();									
				}
			}
			else
			{
				_logger.error("set to bad class target : <" + device + ">");
				return jsonErr.toJSONString();				
			}
		}
		
	//+++++++++++++++++++++++++ RELOAD +++++++++++++++++++++++++++++++
		
		protected String Reload(JSONObject command)
		{
			_logger.debug("command : RELOAD");
			JSONObject jsonErr = BuilJSONError("Commande reload non implementee");
			
//			JSONObject obj = BuilJSONSuccess();
			
			return jsonErr.toJSONString();
		}

	//+++++++++++++++++++++++++ STOP +++++++++++++++++++++++++++++++
		
		protected JSONObject Stop(JSONObject command)
		{
			_logger.debug("command : STOP");

			JSONObject obj = BuilJSONSuccess();
			
			Global._quitFlag = true;
			
			return obj;
		}

	//+++++++++++++++++++++++++ Get +++++++++++++++++++++++++++++++
		
		protected String Get(JSONObject command)
		{
			String device = (String) command.get("device");		
			String mode = (String) command.get("mode");
			
			JSONObject jsonErr = BuilJSONError("Error in SET command");
			
			if((null == device) || (null == mode))
			{
				return jsonErr.toJSONString();
			}

			DomoObject o = DomoObject.GetObjectByName(device);

			if (null == o) 
			{
				_logger.error("set to unknown target : <" + device + ">");
				return jsonErr.toJSONString();
			}
			
			if(o instanceof fl.domo.base.DomoSwitch)
			{
				if("OK".equals(((fl.domo.base.DomoSwitch) o).SetMode(mode)))
				{
					return BuilJSONSuccess().toJSONString();
				}
				else
				{
					return jsonErr.toJSONString();									
				}
			}
			else
			{
				_logger.error("set to bad class target : <" + device + ">");
				return jsonErr.toJSONString();				
			}
		}
	
	
	
}

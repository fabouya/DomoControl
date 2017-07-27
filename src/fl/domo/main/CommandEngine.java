package fl.domo.main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
			
			if("COMMAND".equals(tag.toUpperCase()))
			{
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
							jsonobj = Status(jsonObject);
						break;
						
						case "MODE":
							jsonobj = Mode(jsonObject);
						break;
						
						case "SET":
							jsonobj = Set(jsonObject);
						break;
	
						case "RELOAD":
							jsonobj = Reload(jsonObject);
						break;
							
						case "STOP":
							jsonobj = Stop(jsonObject);
						break;
						
						default:
							jsonobj = BuilJSONError("unknown command request : " + command );						
					}
				}
			}
			else
			{
				jsonobj = BuilJSONError("not a command request : " + tag );
			}
			
			String jsonString = jsonobj.toJSONString();
			
			return jsonString;			
		}
		catch(Exception e)
		{	
			_logger.error(e);
			
			return BuilJSONError("Error decoding : " + jsonCommand ).toJSONString();
		}
	}

	//------------------------- Effective commands -------------------------------

	
	//+++++++++++++++++++++++++ STATUS +++++++++++++++++++++++++++++++
		
		protected JSONObject Status(JSONObject command)
		{
			_logger.info("command : STATUS");           
			
	        JSONObject obj = BuilJSONSuccess();
/*	        
	        obj.put("pompe", String.valueOf(Global._PumpGPIO.IsActivated()));
	        obj.put("robot", String.valueOf(Global._RobotGPIO.IsActivated()));
	        obj.put("lumiere", String.valueOf(Global._LumiereGPIO.IsActivated()));      
*/
	        if(Level.DEBUG == _logger.getLevel())
	        {
	        	String s = obj.toJSONString();
	        	_logger.debug("Return : " + s);
	        }
					
			return obj;
		}

	//+++++++++++++++++++++++++ MODE +++++++++++++++++++++++++++++++
		
		protected JSONObject Mode(JSONObject command)
		{
			_logger.info("command : MODE");           
			
	        JSONObject obj = BuilJSONSuccess();
/*        
	        obj.put("pompe", String.valueOf(Global._PumpGPIO.GetMode()));
	        obj.put("robot", String.valueOf(Global._RobotGPIO.GetMode()));
	        obj.put("lumiere", String.valueOf(Global._LumiereGPIO.GetMode()));      
*/
	        if(Level.DEBUG == _logger.getLevel())
	        {
	        	String s = obj.toJSONString();
	        	_logger.debug("Return : " + s);
	        }
					
			return obj;	}

	//+++++++++++++++++++++++++ SET +++++++++++++++++++++++++++++++
		
		protected JSONObject Set(JSONObject command)
		{
			DomoObject target = null;
			
			// parametres:
			// device = <pompe|robot|lumiere>
			// mode = <0|1|2>
			
			String device = (String) command.get("device");		
			String mode = (String) command.get("mode");
			
			JSONObject jsonErr = BuilJSONError("Error in SET command");
			
			if((null == device) || (null == mode))
			{
				return jsonErr;
			}

/*			
			switch(device.toUpperCase())
			{
				case "POMPE":
					target = Global._PumpGPIO;
				break;

				case "ROBOT":
					target = Global._RobotGPIO;
				break;

				case "LUMIERE":
					target = Global._LumiereGPIO;
				break;
				
				default:
					return jsonErr;				
			}
			
			switch(mode.toUpperCase())
			{
				case "AUTO":
					target.SetModeAuto();
				break;

				case "FORCEDON":
					target.SetModeForcedON();
				break;

				case "FORCEDOFF":
					target.SetModeForcedOFF();
				break;
				
				default:
					return jsonErr;				
			}
*/					
			return BuilJSONSuccess();
		}
		
	//+++++++++++++++++++++++++ RELOAD +++++++++++++++++++++++++++++++
		
		protected JSONObject Reload(JSONObject command)
		{
			_logger.debug("command : RELOAD");
/*			
			Global._calendrierPompe.Reload();
			Global._calendrierRobot.Reload();
			Global._calendrierLumiere.Reload();
*/
			JSONObject obj = BuilJSONSuccess();
			
			return obj;
		}

	//+++++++++++++++++++++++++ STOP +++++++++++++++++++++++++++++++
		
		protected JSONObject Stop(JSONObject command)
		{
			_logger.debug("command : STOP");

			JSONObject obj = BuilJSONSuccess();
			
			Global._quitFlag = true;
			
			return obj;
		}

	
	
	
}

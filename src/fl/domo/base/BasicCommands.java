package fl.domo.base;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class BasicCommands {


	@SuppressWarnings("unchecked")
	public static String JsonModeAll()
	{
		ArrayList<DomoObject> objs = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");

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
	
	@SuppressWarnings("unchecked")
	public static String JsonStatusAll()
	{
		ArrayList<DomoObject> objs = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");
		
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
	
	@SuppressWarnings("unchecked")
	public static String JsonGetMode(String objectName)
	{
		DomoObject o = DomoObject.GetObjectByName(objectName);

	    JSONObject json = new JSONObject();
	    json.put("TAG", "MODE");
	    json.put("STATUS", "OK");
	        
		String mode = String.valueOf(((DomoSwitch) o).GetMode());
		json.put(o.GetName(), mode);
     
		return json.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public static String JsonGetStatus(String objectName)
	{
		DomoObject o = DomoObject.GetObjectByName(objectName);

	    JSONObject json = new JSONObject();
	    json.put("TAG", "STATUS");
	    json.put("STATUS", "OK");
	        
		String state = String.valueOf(((DomoSwitch) o).GetState());
		json.put(o.GetName(), state);
     
		return json.toJSONString();
	}
	
}

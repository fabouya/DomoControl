package fl.domo.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;

import org.w3c.dom.Element;

public abstract class DomoObject 
{
// -------------- members ----------------
	
	protected	String		_name = "undef DomoObject";	
	
// ---------- static members -------------
	
	protected static	int		_counter = 0;
	private static	Hashtable<String, DomoObject>	_ht = new Hashtable<String, DomoObject>();
	protected final static Logger _logger = Logger.getLogger(DomoObject.class);
	
// -------------- function ----------------

	public DomoObject(String name)
	{
		_logger.debug("Creation : " + name);
		_name = name.toLowerCase();
		
		Register(name);
	}
	
	public DomoObject()
	{
		_logger.debug("Creation generique DomoObject");
	}
	
	public void Create(String name)
	{
		_logger.debug("Create de DomoObject : " + name);
		_name = name.toLowerCase();
		
		Register(name);
	}
	
	abstract void FromXML(Element item);
	abstract void ReloadFromXML(Element item);
	
	
	public String GetName() { return _name; }
	
// --------------------------------------- Private -----------------------------------------
	
	private void Register(String name)
	{		
		if(null == _ht.get(_name))
		{
			_ht.put(_name,  this);
			_counter++;
		}
		else
		{
			_logger.debug("Erreur : un objet nommé " + name + " existe déjà.");			
		}		
	}
	
// ---------- static function -------------
	
	public static Hashtable<String, DomoObject> GetAllObjects()
	{
		return _ht;
	}
	
	public static DomoObject GetObjectByName(String name)
	{
		DomoObject o = _ht.get(name.toLowerCase());
		
		_logger.debug("Recherche objet : " + name + ((null != o) ? " : trouvé" : " : pas trouvé !"));		
		
		return o;
	}
	
	public static ArrayList<DomoObject> GetObjectsByClass(String classname)
	{
		ArrayList<DomoObject> l = new ArrayList<DomoObject>();
		
		Iterator<DomoObject> it = _ht.values().iterator();
				
		while ( it.hasNext()) 
		{
			Object o = (Object) it.next();

			try 
			{
				if(Class.forName(classname).isInstance(o))
				{
					l.add((DomoObject) o);
				}
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		
		return l;
	}
	
	public String RunCommand(String s)
	{
		return "RunCommand undef for " + _name;
	}

}

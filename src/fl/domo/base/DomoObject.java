package fl.domo.base;

import java.util.Hashtable;

import org.apache.log4j.Logger;

public class DomoObject 
{
// -------------- members ----------------
	
	protected	String		_name = "undef";	
	
// ---------- static members -------------
	
	protected static	int		_counter = 0;
	private	static	Hashtable<String, DomoObject>	_ht = new Hashtable<String, DomoObject>();
	protected final static Logger _logger = Logger.getLogger(DomoObject.class);
	
// -------------- function ----------------

	public DomoObject(String name)
	{
		_logger.debug("Creation : " + name);
		_name = name;
		if(null == _ht.get(name))
		{
			_ht.put(name,  this);
			_counter++;
		}
		else
		{
			_logger.debug("Erreur : un objet nommé " + name + " existe déjà.");			
		}
	}
	
	public String GetName() { return _name; }
	
// ---------- static function -------------
	
	public static DomoObject GetObjectByName(String name)
	{
		return _ht.get(name);
	}

}

package fl.domo.base;

import java.util.Hashtable;

public class DomoObject 
{
// -------------- members ----------------
	
	protected	String		_name = "undef";	
	
// ---------- static members -------------
	
	protected static	int		_counter = 0;
	private	static	Hashtable<String, DomoObject>	_ht = new Hashtable<String, DomoObject>();
	
// -------------- function ----------------

	public DomoObject(String name)
	{
		_name = name;
		_ht.put(name,  this);
		_counter++;
	}
	
	public String GetName() { return _name; }
	
// ---------- static function -------------
	
	public static DomoObject GetObjectByName(String name)
	{
		return _ht.get(name);
	}

}

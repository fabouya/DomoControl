package fl.domo.tools;

public class Tools 
{

	public static String chop(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.substring(0, s.length() - 1);
	}
	
}

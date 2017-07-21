package fl.domo.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import fl.domo.base.DomoObject;
import fl.domo.base.DomoObjectFactory;
import fl.domo.tools.Global;

public class DomoBrain 
{

	protected final static Logger _logger = Logger.getLogger(DomoBrain.class);			
	
	private static void ReadProperties(String filename)
	{
		_logger.debug("Read prop file : " + filename);

		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filename);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			Global._xmlFile = prop.getProperty("config");
			Global._tcpPort = Integer.parseInt(prop.getProperty("tcpserverport"));
			

		} 
		catch (final IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

//1
		// Lire les properties
		
//2
		// Lire de fichier xml de declaration des objets
		DomoObjectFactory factory = new DomoObjectFactory();
		
		factory.BuildFromXML("configtest.xml");
		
//3
		// lancer les threads de serveurs
		// Scheduling thread
		// TCPServer thread
		// JMS Server thread
		
//4
		// entrer dans la boucle principale
		
		
		ArrayList<DomoObject> liste = DomoObject.GetObjectsByClass("fl.domo.base.DomoSwitch");
		
		for (DomoObject o : liste) 
		{
			System.out.println("Switch : " + o.GetName());
		}
	}

}

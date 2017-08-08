package fl.domo.base;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomoObjectFactory 
{
	// -------------- members ----------------

	private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	// ---------- static members -------------
				
	protected final static Logger _logger = Logger.getLogger(DomoObjectFactory.class);
	
	// -------------- function ----------------

	public DomoObjectFactory() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void BuildFromXML(String filename)
	{
		_logger.debug("config " + filename);
		
		try 
		{
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    
		    Document document= builder.parse(new File(filename));
		    
		    _logger.debug(document.getXmlVersion() + " " + document.getXmlEncoding() + " " + document.getXmlStandalone());

		    final Element racine = document.getDocumentElement();
		    
		    _logger.debug("Racine : " + racine.getNodeName());
		    
		    if(! racine.getNodeName().toLowerCase().equals("config"))
		    {
		    	_logger.error("Document xml invalide, la racine doit etre 'config'");
		    	return;
		    }
		    
		    NodeList racineNoeuds = racine.getChildNodes();

		    int nbRacineNoeuds = racineNoeuds.getLength();            

		    for (int i = 0; i<nbRacineNoeuds; i++) 
		    {
		    	if(Node.ELEMENT_NODE == racineNoeuds.item(i).getNodeType()) 
		    	{
		    		String nodeType = racineNoeuds.item(i).getNodeName(); 
		    		
		    		_logger.debug(nodeType);
		    		
		    		switch(nodeType.toLowerCase())
		    		{
		    			case "gpios":
		    			{
		    				ParseGPIOS((Element) racineNoeuds.item(i));
		    			}
		    			break;
		    			
		    			case "switchs":
		    			{
		    				ParseSWITCHS((Element) racineNoeuds.item(i));
		    			}
		    			break;
		    			
		    			case "calendars":
		    			{
		    				ParseCalendars((Element) racineNoeuds.item(i));
		    			}
		    			break;
		    						    			
		    			case "scheduledswitchs":
		    			{
		    				ParseScheduledSwitch((Element) racineNoeuds.item(i));
		    			}
		    			break;
		    			
		    			default:
		    			{
		    				_logger.error("Noeud de type inconnu : " + nodeType);
		    			}
		    		}
		    	}
		    }		    
		    
		}

		catch (ParserConfigurationException | SAXException | IOException e) 
		{
		    e.printStackTrace();
		}		
	}

	protected void ParseGPIOS(Element item)
	{

		NodeList gpios = item.getElementsByTagName("gpio");

		int nbgpios = gpios.getLength();		                    

		for(int j = 0; j<nbgpios; j++) 
		{
		    Element gpio = (Element) gpios.item(j);
		    String typeGpio = gpio.getAttribute("type");

		    _logger.debug("GPIO : " + gpio.getAttribute("name") + " : " + gpio.getAttribute("id") + ":"  + typeGpio);
		    
		    
		    switch(typeGpio.toLowerCase())
		    {
		    	case "output":
		    	{
				    new DomoOutputGPIO(gpio.getAttribute("name"), Integer.parseInt(gpio.getAttribute("id")));		    		
		    	}
		    	break;
		    	
		    	case "input":
		    	{
				    new DomoInputGPIO(gpio.getAttribute("name"), Integer.parseInt(gpio.getAttribute("id")));		    		
		    	}
		    	break;
		    	
		    	default:
		    	{
		    		_logger.debug("GPIO : " + gpio.getAttribute("name") + " : " + typeGpio + " : type inconnu");		    		
		    	}
		    }
		    
		}
		
	}
	
	protected void ParseSWITCHS(Element item)
	{
		int j;
		
		NodeList switchs = item.getElementsByTagName("switch");
		NodeList iswitchs = item.getElementsByTagName("inverseswitch");

		int nbswitch = switchs.getLength();		                    
		int nbiswitch = iswitchs.getLength();		                    

		for(j = 0; j<nbswitch; j++) 
		{
		    Element sw = (Element) switchs.item(j);

		    _logger.debug("switch : " + sw.getAttribute("name") + " : " + sw.getAttribute("gpioname"));
		    
		    DomoSwitch s = new DomoSwitch(sw.getAttribute("name"), sw.getAttribute("gpioname"));
		    s.InitSwitch();

		}
		
		for(j = 0; j<nbiswitch; j++) 
		{
		    Element isw = (Element) iswitchs.item(j);

		    _logger.debug("inverse switch : " + isw.getAttribute("name") + " : " + isw.getAttribute("gpioname"));

		    DomoInversedSwitch s = new DomoInversedSwitch(isw.getAttribute("name"), isw.getAttribute("gpioname"));
		    s.InitSwitch();
		}
		
	}

	protected void ParseCalendars(Element item)
	{

		NodeList cals = item.getElementsByTagName("hourlycalendar");

		int nbcal = cals.getLength();		                    

		for(int j = 0; j<nbcal; j++) 
		{
		    Element cal = (Element) cals.item(j);

		    _logger.debug("CAL : " + cal.getAttribute("name") + " : " + cal.getAttribute("hours"));
		    
		    DomoHourlyCalendar c = new DomoHourlyCalendar(cal.getAttribute("name"));
		    c.SetPeriod(cal.getAttribute("hours"));
		}
		
	}
	
	protected void ParseScheduledSwitch(Element item)
	{

		NodeList switchs = item.getElementsByTagName("scheduledswitch");

		int nbsw = switchs.getLength();		                    

		for(int j = 0; j<nbsw; j++) 
		{
		    Element sw = (Element) switchs.item(j);

		    _logger.debug("Scheduled Switch : " + sw.getAttribute("name") + " : " + sw.getAttribute("calendar") + ":" + sw.getAttribute("switch"));
		    
		    new ScheduledSwitch(sw.getAttribute("name"), sw.getAttribute("calendar"), sw.getAttribute("switch"));
		}
		
	}
	
	
	protected void ReloadCalendars(Element item)
	{
	    _logger.debug("Reload calendars");

		NodeList cals = item.getElementsByTagName("hourlycalendar");

		int nbcal = cals.getLength();		                    

		for(int j = 0; j<nbcal; j++) 
		{
		    Element cal = (Element) cals.item(j);
		    
		    String name = cal.getAttribute("name");
		    
		    DomoObject o = DomoObject.GetObjectByName(name);
		    
		    if(null != o)
		    {
		    	((DomoHourlyCalendar) o).SetPeriod(cal.getAttribute("hours"));
		    }
		    else
		    {
			    DomoHourlyCalendar c = new DomoHourlyCalendar(cal.getAttribute("name"));
			    c.SetPeriod(cal.getAttribute("hours"));		    	
		    }
		}
		
	}

	
	public void ReloadXML(String filename)
	{
		_logger.debug("Reload config " + filename);
		
		try 
		{
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    
		    Document document= builder.parse(new File(filename));
		    
		    _logger.debug(document.getXmlVersion() + " " + document.getXmlEncoding() + " " + document.getXmlStandalone());

		    final Element racine = document.getDocumentElement();
		    
		    _logger.debug("Racine : " + racine.getNodeName());
		    
		    if(! racine.getNodeName().toLowerCase().equals("config"))
		    {
		    	_logger.error("Document xml invalide, la racine doit etre 'config'");
		    	return;
		    }
		    
		    NodeList racineNoeuds = racine.getChildNodes();

		    int nbRacineNoeuds = racineNoeuds.getLength();            

		    for (int i = 0; i<nbRacineNoeuds; i++) 
		    {
		    	if(Node.ELEMENT_NODE == racineNoeuds.item(i).getNodeType()) 
		    	{
		    		String nodeType = racineNoeuds.item(i).getNodeName(); 
		    		
		    		_logger.debug(nodeType);
		    		
		    		switch(nodeType.toLowerCase())
		    		{
		    			case "gpios":
		    			case "switchs":
		    			case "scheduledswitchs":
		    			{
		    				_logger.info("Type ignoré pendant reload : " + nodeType.toLowerCase());
		    			}
		    			break;
		    			
		    			case "calendars":
		    			{
		    				ReloadCalendars((Element) racineNoeuds.item(i));
		    			}
		    			break;
		    			
		    			default:
		    			{
		    				_logger.error("Noeud de type inconnu : " + nodeType);
		    			}
		    		}
		    	}
		    }		    
		    
		}

		catch (ParserConfigurationException | SAXException | IOException e) 
		{
		    e.printStackTrace();
		}		
	}
	
	// ---------- static function -------------
}

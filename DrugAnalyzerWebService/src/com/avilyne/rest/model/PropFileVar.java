package com.avilyne.rest.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropFileVar {
	
	public static void PropertyFileReading() throws IOException
	{
			//prop.load(new FileInputStream("resources/myConfig.properties"));	// load a properties file for reading
			//prop.load(new FileInputStream("resources/MyMessages.xml"));
			System.out.println("inside prop");
			//File file = new File("com.avilyne.rest.model/Properties/config.properties");
			//File file = new File("resources/MyMessages.xml");
			//FileInputStream fileInput = new FileInputStream(file);
			//InputStream fileInput = PropFileVar.getClass().getClassLoader().getResourceAsStream("com.avilyne.rest.model/Properties/config.properties");
			Properties properties = new Properties();
			//properties.load(fileInput);
			//properties.loadFromXML(fileInput);
			
			System.out.println("loaded prop - " + properties.getProperty("propDatabase"));
			//StatVariables.stUrl = properties.getProperty("propUrl");
			//StatVariables.stDatabase = properties.getProperty("propDatabase");
			//StatVariables.stUsername = properties.getProperty("propUsername");
			//StatVariables.stPassword = properties.getProperty("propPassword");
			//StatVariables.stClassName = properties.getProperty("propClassName");
			//System.out.println(StaticVar.destFilePath);
			//fileInput.close();
	}
	
	
}

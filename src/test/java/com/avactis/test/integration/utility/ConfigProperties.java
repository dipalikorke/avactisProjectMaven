package com.avactis.test.integration.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	private static Properties prop = new Properties();  //create object of Properties class
	
	
	//different methods
	public static void loadProperties()
	{
		try
		{
			prop.load(new FileInputStream("config/avactis.properties"));
			
		}catch (FileNotFoundException e)
		{
			System.out.println("Config.properties file not found");
		} catch (IOException e)
		{
			System.out.println("IO exception while accessing confif.properties file");
		}
	}
	
	public static String getProperty(String keyValue)
	{
		return prop.getProperty(keyValue);
	}
	//driver.get(ConfigProperties.getProperty("STROE_URL"));
}

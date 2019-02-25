package com.avactis.test.integration.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AvactisProperties {

	public final static String BASEURL = "BASEURL";

	public final static String PROPERTY_FILENAME = "config/avactis.properties";

	private Properties avactis_properties = new Properties();

	public final static String XLS_DATA = "XLS_DATA";

	public final static String BROWSER = "BROWSER";

	/**
	 * Loads the properties file
	 */
	public AvactisProperties() {
		try {
			avactis_properties.load(new FileInputStream(PROPERTY_FILENAME));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		assert !avactis_properties.isEmpty();
	}

	/**
	 * returns the value of the property denoted by key
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		String property = avactis_properties.getProperty(key);
		return property != null ? property.trim() : property;
	}

	
	
}

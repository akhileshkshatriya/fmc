package com.acompany.fmc.framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FMCProperties {
	
	private final static String FILE_NAME = "config.properties";
	private static Properties prop;
	static{
		loadProperties();
	}
	
	private static void loadProperties() {
		InputStream inputStream = null;
		try {
			prop = new Properties();
			inputStream = FMCProperties.class.getClassLoader().getResourceAsStream(FILE_NAME);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + FILE_NAME + "' not found in the classpath");
			}
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getPropValue(String key) {
		return prop.getProperty(key);
	}

}

package com.acompany.fmc;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerConfiguration {
	
	private static final Logger LOGGER = Logger.getLogger("fmcLogger");
	static{
		try {
			LogManager.getLogManager().readConfiguration(LoggerConfiguration.class.getClassLoader().getResourceAsStream("logger.properties"));
		} catch (IOException exception) {
			LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
		}
	}
	public static void main(String[] args) {
		LOGGER.fine("Fine message logged");
	}
}

package com.acompany.fmc;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.framework.ApplicationContext;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.frontcontroller.FrontController;
import com.acompany.fmc.framework.model.Model;

public class MainClass {

	private static Logger LOGGER = LoggerFactory.getLogger(MainClass.class);
	
	public static void main(String[] args) throws URISyntaxException {
		LOGGER.info("Put something in log");
		ApplicationContext context  = ApplicationContext.getApplicationContext();
		FrontController frontController = (FrontController)context.getBean(FrontController.class);
		new RequestSubmitter(frontController);
		
		try {
			RequestSubmitter.submitRequest("home", new Model());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}

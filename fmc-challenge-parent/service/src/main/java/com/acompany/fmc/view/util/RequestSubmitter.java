package com.acompany.fmc.view.util;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.framework.frontcontroller.FrontController;
import com.acompany.fmc.framework.model.Model;

public class RequestSubmitter {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RequestSubmitter.class);
	
	private static FrontController frontController;
	public static void submitRequest(String request, Model model) {
		try {
			frontController.dispatchRequest(request, model);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public RequestSubmitter (FrontController frontController){
		this.frontController = frontController;
	}

}

package com.acompany.fmc.framework.frontcontroller;

import java.lang.reflect.InvocationTargetException;

import com.acompany.fmc.framework.model.Model;

public class RequestSubmitter {
	
	private static FrontController frontController;
	public static void submitRequest(String request, Model model) {
		try {
			frontController.dispatchRequest(request, model);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public RequestSubmitter (FrontController frontController){
		this.frontController = frontController;
	}

}

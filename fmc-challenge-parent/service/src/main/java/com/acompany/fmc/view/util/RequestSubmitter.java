package com.acompany.fmc.view.util;

import com.acompany.fmc.framework.frontcontroller.FrontController;
import com.acompany.fmc.framework.model.Model;

public class RequestSubmitter {
	
	private static FrontController frontController;
	public static void submitRequest(String request, Model model) {
			frontController.dispatchRequest(request, model);
	}
	
	@SuppressWarnings("static-access")
	public RequestSubmitter (FrontController frontController){
		this.frontController = frontController;
	}

}

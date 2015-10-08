package com.acompany.fmc;

import java.net.URISyntaxException;

import com.acompany.fmc.framework.ApplicationContext;
import com.acompany.fmc.framework.frontcontroller.FrontController;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.view.util.RequestSubmitter;

public class MainClass {

	public static void main(String[] args) throws URISyntaxException {
		ApplicationContext context = ApplicationContext.getApplicationContext();
		FrontController frontController = (FrontController) context.getBean(FrontController.class);
		new RequestSubmitter(frontController);
		RequestSubmitter.submitRequest("home", new Model());
	}

}

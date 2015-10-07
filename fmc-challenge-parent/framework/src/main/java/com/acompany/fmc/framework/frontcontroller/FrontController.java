package com.acompany.fmc.framework.frontcontroller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.model.Model;


public class FrontController {

	@Autowired
	private Dispatcher dispatcher;

	public void dispatchRequest(String request, Model model){
		dispatcher.dispatch(request, model);
	}

}

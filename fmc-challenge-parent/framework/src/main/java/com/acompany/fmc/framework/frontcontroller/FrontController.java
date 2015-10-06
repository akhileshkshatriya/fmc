package com.acompany.fmc.framework.frontcontroller;

import java.lang.reflect.InvocationTargetException;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.model.Model;


public class FrontController {

	@Autowired
	private Dispatcher dispatcher;

	public void dispatchRequest(String request, Model model) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		dispatcher.dispatch(request, model);
	}

}

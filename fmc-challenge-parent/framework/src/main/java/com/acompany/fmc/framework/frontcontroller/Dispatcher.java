package com.acompany.fmc.framework.frontcontroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.acompany.fmc.framework.ApplicationContext;
import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Bean;
import com.acompany.fmc.framework.mapping.HandlerMapping;
import com.acompany.fmc.framework.mapping.ViewResolver;
import com.acompany.fmc.framework.model.Model;

@Bean
public class Dispatcher {

	@Autowired
	private HandlerMapping handler;
	
	@Autowired
	private ViewResolver resolver;

	public void dispatch(String request, Model model) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		ApplicationContext context = ApplicationContext.getApplicationContext();

		Method controllerMethod = handler.get(request);
		Object targetController = context.getBean(controllerMethod.getDeclaringClass());
		Model retModel = (Model) controllerMethod.invoke(targetController, model);

		Method viewMethod = resolver.get((String) retModel.getRedirectTo());
		Object targetView = context.getBean(viewMethod.getDeclaringClass());
		viewMethod.invoke(targetView, retModel);

	}
}

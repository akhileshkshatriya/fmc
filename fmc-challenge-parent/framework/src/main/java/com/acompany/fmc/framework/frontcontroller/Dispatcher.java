package com.acompany.fmc.framework.frontcontroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.framework.ApplicationContext;
import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.exception.FMCFrameworkException;
import com.acompany.fmc.framework.mapping.HandlerMapping;
import com.acompany.fmc.framework.mapping.ViewResolver;
import com.acompany.fmc.framework.model.Model;

public class Dispatcher {

	@Autowired
	private HandlerMapping handler;

	@Autowired
	private ViewResolver resolver;

	static Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);

	public void dispatch(String request, Model model) {

		try {

			ApplicationContext context = ApplicationContext.getApplicationContext();

			Method controllerMethod = handler.get(request);
			Class<?> claz = controllerMethod.getDeclaringClass();
			Object targetController = context.getBean(claz);
			Model retModel = (Model) controllerMethod.invoke(targetController, model);

			Method viewMethod = resolver.get((String) retModel.getRedirectTo());
			Object targetView = context.getBean(viewMethod.getDeclaringClass());
			viewMethod.invoke(targetView, retModel);

		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
			FMCFrameworkException fmcException = new FMCFrameworkException("Invalid configuration", e);
			throw fmcException;
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			FMCFrameworkException fmcException = new FMCFrameworkException("Invalid Input", e);
			throw fmcException;
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
			FMCFrameworkException fmcException = new FMCFrameworkException("Improper Request and View Mapping", e);
			throw fmcException;
		}

	}
}

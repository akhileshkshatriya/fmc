package com.acompany.fmc.framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Bean;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.Service;
import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.mapping.HandlerMapping;
import com.acompany.fmc.framework.mapping.ViewResolver;

public class ApplicationContext {

	static Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);
	private static ApplicationContext context;
	private static final String COMPONENT_SCAN = FMCProperties.getPropValue("component.scan");
	private Reflections reflections;
	

	private Map<Class<?>, Object> instanceMapWithDependencies;

	private ApplicationContext() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		this.reflections = new Reflections(COMPONENT_SCAN);
		init();
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			try {
				context = new ApplicationContext();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException
					| SecurityException | IllegalArgumentException | IOException e) {
				e.printStackTrace();
			}
		}
		return context;
	}
	
	private void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, FileNotFoundException, IOException {
		Map<Class<? extends Annotation>, Set<Class<?>>> allAnnotatedTypesMap = getAllAnnotatedTypesMap();
		Map<String, Method> requestMethodMap = getRequestMethodMapFrom(allAnnotatedTypesMap);
		Map<String, Method> viewMethodMap = getViewMethodMapFrom(allAnnotatedTypesMap);
		Map<Class<?>, Object> instanceMapWithoutDependencies = instantiateAllAnnotatedTypes(allAnnotatedTypesMap);
		instanceMapWithDependencies = injectDependencies(instanceMapWithoutDependencies);
		injectRequestMethodMapping(requestMethodMap);
		injectViewMethodMapping(viewMethodMap);
	}
	
	private Map<Class<?>, Object> injectDependencies(Map<Class<?>, Object> instanceMap) throws IllegalArgumentException, IllegalAccessException {
		for (Map.Entry<Class<?>, Object> entry : instanceMap.entrySet()) {
			Class<?> clas = entry.getKey();
			Field[] fields = clas.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Autowired.class)) {
					field.setAccessible(true);
					field.set(entry.getValue(), instanceMap.get(field.getType()));
				}
			}
		}
		return instanceMap;
	}

	private Map<Class<?>, Object> instantiateAllAnnotatedTypes(Map<Class<? extends Annotation>, Set<Class<?>>> allAnnotatedTypesMap) throws InstantiationException, IllegalAccessException {
		Map<Class<?>, Object> instanceMap = new HashMap<>();
		for (Map.Entry<Class<? extends Annotation>, Set<Class<?>>> entry : allAnnotatedTypesMap.entrySet()) {
			Set<Class<?>> value = entry.getValue();
			for (Class<?> claz : value) {
				Object newInstance = claz.newInstance();
				instanceMap.put(claz, newInstance);
				Class<?>[] interfaces = claz.getInterfaces();
				if(interfaces!= null && interfaces.length > 0){
					for (Class<?> ifClaz : interfaces) {
						instanceMap.put(ifClaz, newInstance);
					}
				}
			}
		}
		return instanceMap;
	}

	private Map<Class<? extends Annotation>, Set<Class<?>>> getAllAnnotatedTypesMap() throws ClassNotFoundException, FileNotFoundException, IOException {
		Map<Class<? extends Annotation>, Set<Class<?>>> allAnnotatedTypesMap = new HashMap<>();
		allAnnotatedTypesMap.put(Bean.class, reflections.getTypesAnnotatedWith(Bean.class));
		allAnnotatedTypesMap.put(Controller.class, reflections.getTypesAnnotatedWith(Controller.class));
		allAnnotatedTypesMap.put(Service.class, reflections.getTypesAnnotatedWith(Service.class));
		allAnnotatedTypesMap.put(View.class, reflections.getTypesAnnotatedWith(View.class));
		return allAnnotatedTypesMap;
	}

	private Map<String, Method> getRequestMethodMapFrom(Map<Class<? extends Annotation>, Set<Class<?>>> allAnnotatedTypesMap) {
		Map<String, Method> requestMethodMap = new HashMap<>();
		Set<Class<?>> annotatedTypes = allAnnotatedTypesMap.get(Controller.class);
		for (Class<?> claz : annotatedTypes) {
			requestMethodMap.putAll(reflections.getMethodsAnnotatedWithRequestMapping(claz));
		}
		return requestMethodMap;
	}

	private Map<String, Method> getViewMethodMapFrom(Map<Class<? extends Annotation>, Set<Class<?>>> allAnnotatedTypesMap) {
		Map<String, Method> viewMethodMap = new HashMap<>();
		Set<Class<?>> annotatedTypes = allAnnotatedTypesMap.get(View.class);
		for (Class<?> claz : annotatedTypes) {
			viewMethodMap.putAll(reflections.getMethodsAnnotatedWithViewMapping(claz));
		}
		return viewMethodMap;
	}

	private void injectRequestMethodMapping(Map<String, Method> requestMethodMap) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> handlerMappingClass = HandlerMapping.class;
		Field field = handlerMappingClass.getDeclaredField("requestMapping");
		field.setAccessible(true);
		field.set(instanceMapWithDependencies.get(HandlerMapping.class), requestMethodMap);
	}
	
	private void injectViewMethodMapping(Map<String, Method> viewMethodMap) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> viewResolverClass = ViewResolver.class;
		Field field = viewResolverClass.getDeclaredField("viewMapping");
		field.setAccessible(true);
		field.set(instanceMapWithDependencies.get(ViewResolver.class), viewMethodMap);
	}
	public Object getBean(Class<?> claz) {
		return instanceMapWithDependencies.get(claz);
	}
}

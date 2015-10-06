package com.acompany.fmc.framework.mapping;

import java.lang.reflect.Method;
import java.util.Map;


public class ViewResolver {
	
private  Map<String, Method> viewMapping;
	
	public Method get(String viewName){
		return viewMapping.get(viewName);
	}
}

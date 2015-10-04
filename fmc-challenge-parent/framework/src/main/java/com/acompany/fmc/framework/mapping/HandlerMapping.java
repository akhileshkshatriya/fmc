package com.acompany.fmc.framework.mapping;

import java.lang.reflect.Method;
import java.util.Map;

import com.acompany.fmc.framework.annotation.Bean;

@Bean
public class HandlerMapping {
	
	private Map<String, Method> requestMapping;
	
	public Method get(String methodName){
		return requestMapping.get(methodName);
	}
}

package com.acompany.fmc.framework.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
	
	private Map<String, Object> model = new HashMap<>();
	
	public void addAttribute(String attributeName, Object attributeValue){
		model.put(attributeName, attributeValue);
	}
	
	public Object getAttribute(String attributeName){
		return model.get(attributeName);
	}
}

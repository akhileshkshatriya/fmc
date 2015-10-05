package com.acompany.fmc.framework.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
	
	private Map<String, Object> model = new HashMap<>();
	private String redirectTo;
	
	public void addAttribute(String attributeName, Object attributeValue){
		model.put(attributeName, attributeValue);
	}
	
	public Object getAttribute(String attributeName){
		return model.get(attributeName);
	}

	public String getRedirectTo() {
		return redirectTo;
	}

	public void redirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}
}

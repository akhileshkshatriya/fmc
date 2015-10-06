package com.acompany.fmc.framework.test.dummy;

import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;

@Controller
public class TestController {

	@RequetMapping(name="test.controller.mapping")
	public Model test(Model model){
		model = new  Model();
		model.redirectTo("test.view.mapping");
		return model;
	}
	
}

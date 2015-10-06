package com.acompany.fmc.framework.test.dummy;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;

@View
public class TestView {

	@ViewMapping(name="test.view.mapping")
	public void displayTest(Model model){
		
	}
}

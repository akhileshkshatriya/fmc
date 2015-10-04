package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;

@Controller
public class StartGameController {

	@RequetMapping(name="startGame")
	public Model startGame(Model model) {
		Model retModel = new Model();
		retModel.addAttribute("view", "start.options");
		
		return retModel;
	}
}

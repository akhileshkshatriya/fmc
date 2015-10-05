package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;

@Controller
public class HomeController {

	@Autowired
	private CharacterService characterService;

	@RequetMapping(name = "home")
	public Model execute(Model model) {
		Model returnModel = new Model();
		
		if(characterService.getVillain()!=null){
			returnModel.addAttribute("resumeoption", "true");
		}
		returnModel.addAttribute("view", "welcome");
		return returnModel;
	}
}

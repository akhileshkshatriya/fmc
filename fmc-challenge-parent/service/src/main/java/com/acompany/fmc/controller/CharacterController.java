package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.dto.Character;

@Controller
public class CharacterController {
	
	@Autowired
	private CharacterService characterService;
	
	@RequetMapping(name="createCharacter")
	public Model createCharacter(Model model){
		Character character = (Character) model.getAttribute("hero");
		characterService.createHero(character);
		model.addAttribute("view", "start.options");
		return model;
	}
}

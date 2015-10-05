package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.GameService;
import com.acompany.fmc.service.dto.Character;

@Controller
public class GameController {

	@Autowired
	private CharacterService characterService;

	@Autowired
	private GameService gameService;

	@RequetMapping(name = "home")
	public Model welcome(Model model) {
		Model returnModel = new Model();

		if (gameService.isThereAnySavedGame()) {
			returnModel.addAttribute("resumeoption", "true");
		}

		returnModel.addAttribute("view", "welcome");
		return returnModel;
	}

	@RequetMapping(name = "initializeNewGame")
	public Model initializeNewGame(Model model) {
		Model retModel = new Model();
		Character hero = characterService.getHero();
		if (hero == null) {
			retModel.addAttribute("view", "create.character");
		} else {
			retModel.addAttribute("view", "start.options");
			return retModel;
		}

		return retModel;
	}
}

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
		model = new Model();

		if (gameService.isThereAnySavedGame()) {
			model.addAttribute("resumeoption", "true");
		}

		model.redirectTo("welcome");
		return model;
	}

	@RequetMapping(name = "initializeNewGame")
	public Model initializeNewGame(Model model) {
		model = new Model();
		Character hero = characterService.getHero();
		if (hero == null) {
			model.redirectTo("create.character");
		} else {
			model.redirectTo("start.options");
			return model;
		}

		return model;
	}
}

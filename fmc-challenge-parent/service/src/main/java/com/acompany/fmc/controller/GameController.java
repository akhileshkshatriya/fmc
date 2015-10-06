package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.GameService;

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
		model.redirectTo("welcome.home");
		return model;
	}

	@RequetMapping(name = "goToPolice")
	public Model goToPolice(Model model) {
		model = new Model();
		model.redirectTo("goto.police.options");

		if (!characterService.isHeroCreated()) {
			model.redirectTo("create.character");
		}
		return model;
	}
}

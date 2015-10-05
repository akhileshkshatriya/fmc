package com.acompany.fmc.controller;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.GameService;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@Controller
public class SaveResumeGameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private CharacterService characterService;

	@RequetMapping(name = "saveGame")
	public Model saveGame(Model model) {
		model = new Model();
		gameService.saveGame();
		Character hero = characterService.getHero();
		Character villain = characterService.getVillain();
		Statistics statistics = new Statistics();
		statistics.setHero(hero);
		statistics.setVillain(villain);
		model.addAttribute("statistics", statistics);
		model.addAttribute("view", "level1.start");
		return model;
	}

	@RequetMapping(name = "resumeGame")
	public Model resumeGame(Model model) {
		Statistics statistics = gameService.resumeGame();
		model.addAttribute("statistics", statistics);
		model.addAttribute("view", "level1.start");
		return model;
	}

}

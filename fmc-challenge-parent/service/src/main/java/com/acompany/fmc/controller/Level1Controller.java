package com.acompany.fmc.controller;

import java.util.Random;

import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Controller;
import com.acompany.fmc.framework.annotation.RequetMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@Controller
public class Level1Controller {

	@Autowired
	private CharacterService characterService;

	@RequetMapping(name = "initializeLevel1")
	public Model initializeLevel1(Model model) {
		model = new Model();

		characterService.createVillain();
		Character villain = characterService.getVillain();
		Character hero = characterService.reInitializeHero();

		Statistics statistics = new Statistics();
		statistics.setHero(hero);
		statistics.setVillain(villain);
		model.addAttribute("statistics", statistics);

		model.redirectTo("start.level1");
		return model;
	}

	@RequetMapping(name = "fightLevel1")
	public Model fight(Model model) {

		model = new Model();
		Character hero = characterService.getHero();
		Character villain = characterService.getVillain();

		int heroDamage = (hero.getArmour() * random(hero.getRound())) - villain.getDefence();
		int villainDamage = (villain.getArmour() * random(villain.getRound())) - hero.getDefence();

		int heroHealth = hero.getHealth() - heroDamage;
		int villainHealth = villain.getHealth() - villainDamage;

		if (heroHealth > 0 && villainHealth > 0) {
			model.redirectTo("fight.level1");
		} else {
			characterService.increaseHeroExperience();
			model.redirectTo(getWhoWon(heroHealth, villainHealth));
		}

		hero = characterService.updateHeroHealth(heroHealth);
		villain = characterService.updateVillainHealth(villainHealth);
		Statistics statistics = new Statistics();
		statistics.setHero(hero);
		statistics.setVillain(villain);
		model.addAttribute("statistics", statistics);

		return model;
	}
	
	protected String getWhoWon(int heroHealth, int villainHealth){
		if (heroHealth <= 0) {
			if (villainHealth <= 0) {
				if (Math.signum((heroHealth) - (villainHealth)) > 0) {
					return "hero.won";
				} else {
					return "hero.lose";
				}
			} else {
				return "hero.lose";
			}
		} else {
			return "hero.won";
		}
	}

	private int random(int intMax) {
		Random rnd = new Random();
		return rnd.nextInt(intMax);
	}

}

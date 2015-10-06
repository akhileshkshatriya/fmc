package com.acompany.fmc.service;

import com.acompany.fmc.service.dto.Character;

public interface CharacterService {
	
	Character getHero();
	String createHero(Character hero);
	Character reInitializeHero();
	Character updateHeroHealth(int health);
	
	
	String createVillain();
	Character getVillain();
	Character updateVillainHealth(int health);
	int increaseHeroExperience();
	boolean isHeroCreated();
	
	
}

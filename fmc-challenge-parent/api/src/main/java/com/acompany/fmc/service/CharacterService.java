package com.acompany.fmc.service;

import com.acompany.fmc.service.dto.Character;

public interface CharacterService {
	
	Character getHero();
	boolean createHero(Character hero);
	Character reInitializeHero();
	Character updateHeroHealth(int health);
	
	
	boolean createVillain();
	Character getVillain();
	Character updateVillainHealth(int health);
	
	
}

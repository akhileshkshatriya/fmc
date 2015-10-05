package com.acompany.fmc.service;

import com.acompany.fmc.data.Data;
import com.acompany.fmc.framework.FMCProperties;
import com.acompany.fmc.framework.annotation.Service;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Character.GENDER;

@Service
public class CharacterServiceImpl implements CharacterService {

	public static final String HERO_KEY = FMCProperties.getPropValue("hero.primary.key");
	public static final String VILLAIN_KEY = FMCProperties.getPropValue("villain.primary.key");
	
	private static final String VILLAIN_NAME = "TheVillain";

	private static final int EXPERIENCE_INCREMENT_BY = 1;
	private static final int INITIAL_HEALTH = 500;
	private static final int INITIAL_DEFENCE = 20;
	private static final int INITIAL_ROUND = 5;
	private static final int INITIAL_ARMOUR = 50;

	@Override
	public Character getHero() {
		return Data.selectCharacter(HERO_KEY);
	}

	@Override
	public Character reInitializeHero() {
		Character hero = Data.selectCharacter(HERO_KEY);
		hero.setHealth(INITIAL_HEALTH);
		hero.setArmour(INITIAL_ARMOUR);
		hero.setRound(INITIAL_ROUND);
		hero.setDefence(INITIAL_DEFENCE);
		boolean retVal = Data.saveCharacter(hero, HERO_KEY);
		if (retVal) {
			return hero;
		}
		return null;

	}

	@Override
	public Character updateHeroHealth(int health) {
		Data.updateCharacterHealth(HERO_KEY, health);
		return Data.selectCharacter(HERO_KEY);
	}

	@Override
	public Character updateVillainHealth(int health) {
		Data.updateCharacterHealth(VILLAIN_KEY, health);
		return Data.selectCharacter(VILLAIN_KEY);
	}

	@Override
	public boolean createHero(Character hero) {
		if (Data.saveCharacter(hero, HERO_KEY)) {
			Data.persistAllData();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean increaseHeroExperience() {
		Character hero = Data.selectCharacter(HERO_KEY);
		hero.setExperience(hero.getExperience()+EXPERIENCE_INCREMENT_BY);
		Data.saveCharacter(hero, HERO_KEY);
		return Data.persistAllData();
	}

	@Override
	public Character getVillain() {
		return Data.selectCharacter(VILLAIN_KEY);
	}

	@Override
	public boolean createVillain() {

		Character villain = new Character();
		villain.setName(VILLAIN_NAME);
		villain.setGender(GENDER.MALE);
		villain.setHealth(INITIAL_HEALTH);
		villain.setArmour(INITIAL_ARMOUR);
		villain.setRound(INITIAL_ROUND);
		villain.setDefence(INITIAL_DEFENCE);
		return Data.saveCharacter(villain, VILLAIN_KEY);
	}

}

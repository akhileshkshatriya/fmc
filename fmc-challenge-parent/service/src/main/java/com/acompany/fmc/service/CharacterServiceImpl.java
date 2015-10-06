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

	@Override
	public Character getHero() {
		return Data.selectCharacter(HERO_KEY);
	}

	@Override
	public boolean isHeroCreated() {
		if (Data.selectCharacter(HERO_KEY) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Character reInitializeHero() {
		Character hero = Data.selectCharacter(HERO_KEY);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setRound(Character.INITIAL_ROUND);
		hero.setDefence(Character.INITIAL_DEFENCE);
		String id = Data.saveCharacter(hero, HERO_KEY);
		if (id != null) {
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
	public String createHero(Character hero) {
		String id = Data.saveCharacter(hero, HERO_KEY);
		if (id != null) {
			Data.persistAllData();
		}
		return id;
	}

	@Override
	public boolean increaseHeroExperience() {
		Character hero = Data.selectCharacter(HERO_KEY);
		hero.setExperience(hero.getExperience() + Character.EXPERIENCE_INCREMENT_BY);
		Data.saveCharacter(hero, HERO_KEY);
		return Data.persistAllData();
	}

	@Override
	public Character getVillain() {
		return Data.selectCharacter(VILLAIN_KEY);
	}

	@Override
	public String createVillain() {
		Character villain = new Character();
		villain.setName(VILLAIN_NAME);
		villain.setGender(GENDER.MALE);
		villain.setHealth(Character.INITIAL_HEALTH);
		villain.setArmour(Character.INITIAL_ARMOUR);
		villain.setRound(Character.INITIAL_ROUND);
		villain.setDefence(Character.INITIAL_DEFENCE);
		Data.saveCharacter(villain, VILLAIN_KEY);
		return VILLAIN_NAME;
	}

}

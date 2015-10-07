package com.acompany.fmc.service.impl;

import com.acompany.fmc.data.Data;
import com.acompany.fmc.framework.FMCProperties;
import com.acompany.fmc.framework.annotation.Autowired;
import com.acompany.fmc.framework.annotation.Service;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Character.GENDER;

@Service
public class CharacterServiceImpl implements CharacterService {

	public static final String HERO_KEY = FMCProperties.getPropValue("hero.primary.key");
	public static final String VILLAIN_KEY = FMCProperties.getPropValue("villain.primary.key");

	public static final String VILLAIN_NAME = "TheVillain";
	
	@Autowired
	private Data data;

	@Override
	public Character getHero() {
		return data.selectCharacter(HERO_KEY);
	}

	@Override
	public Character getVillain() {
		return data.selectCharacter(VILLAIN_KEY);
	}

	@Override
	public boolean isHeroCreated() {
		if (data.selectCharacter(HERO_KEY) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Character reInitializeHero() {
		Character hero = data.selectCharacter(HERO_KEY);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setRound(Character.INITIAL_ROUND);
		hero.setDefence(Character.INITIAL_DEFENCE);
		String id = data.saveCharacter(hero, HERO_KEY);
		if (id != null) {
			return hero;
		}
		return null;

	}

	@Override
	public Character updateHeroHealth(int health) {
		data.updateCharacterHealth(HERO_KEY, health);
		return data.selectCharacter(HERO_KEY);
	}

	@Override
	public Character updateVillainHealth(int health) {
		data.updateCharacterHealth(VILLAIN_KEY, health);
		return data.selectCharacter(VILLAIN_KEY);
	}

	@Override
	public String createHero(Character hero) {
		String id = data.saveCharacter(hero, HERO_KEY);
		if (id != null) {
			data.persistAllData();
		}
		return id;
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
		data.saveCharacter(villain, VILLAIN_KEY);
		return villain.getName();
	}
	
	@Override
	public int increaseHeroExperience() {
		Character hero = data.selectCharacter(HERO_KEY);
		hero.setExperience(hero.getExperience() + Character.EXPERIENCE_INCREMENT_BY);
		data.saveCharacter(hero, HERO_KEY);
		data.persistAllData();
		return hero.getExperience();
	}

}

package com.acompany.fmc.service;

import com.acompany.fmc.data.Data;
import com.acompany.fmc.framework.FMCProperties;
import com.acompany.fmc.framework.annotation.Service;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@Service
public class GameServiceImpl implements GameService {

	public static final String HERO_KEY = FMCProperties.getPropValue("hero.primary.key");
	public static final String VILLAIN_KEY = FMCProperties.getPropValue("villain.primary.key");

	@Override
	public boolean saveGame() {
		return Data.persistAllData();
	}

	@Override
	public Statistics resumeGame() {
		Statistics statistics = new Statistics();
		Character hero = Data.selectCharacter(HERO_KEY);
		Character villain = Data.selectCharacter(VILLAIN_KEY);
		statistics.setHero(hero);
		statistics.setVillain(villain);
		return statistics;
	}

	@Override
	public boolean isThereAnySavedGame() {
		if (Data.selectCharacter(VILLAIN_KEY) != null) {
			return true;
		}
		return false;
	}
}

package com.acompany.fmc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.CharacterService;
import com.acompany.fmc.service.GameService;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@RunWith(MockitoJUnitRunner.class)
public class SaveResumeGameControllerTest {

	@InjectMocks
	private SaveResumeGameController controller = new SaveResumeGameController();
	
	@Mock
	private GameService gameService;
	
	@Mock
	private CharacterService characterService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveGameShouldSuccessFullySaveGameAndRedirectToSuccess() {
		when(characterService.getHero()).thenReturn(getDummyHero());
		when(characterService.getVillain()).thenReturn(getDummyVillain());
		Model model = controller.saveGame(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		Statistics statistics = (Statistics)model.getAttribute("statistics");
		assertNotNull(statistics);
		assertEquals("save.success", model.getRedirectTo());
	}
	
	@Test
	public void resumeGameShouldSuccessFullyResumeGameAndRedirectToFight() {
		when(gameService.resumeGame()).thenReturn(getDummyStatistics());
		
		Model model = controller.resumeGame(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		Statistics statistics = (Statistics)model.getAttribute("statistics");
		assertNotNull(statistics);
		assertEquals("fight.level1", model.getRedirectTo());
	}
	
	private Statistics getDummyStatistics(){
		Statistics statistics = new Statistics();
		statistics.setHero(getDummyHero());
		statistics.setVillain(getDummyVillain());
		return statistics;
	}
	
	private Character getDummyHero() {
		Character hero = new Character();
		hero.setName("test_hero");
		hero.setGender(Character.GENDER.MALE);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setDefence(Character.INITIAL_DEFENCE);
		hero.setExperience(0);
		hero.setRound(Character.INITIAL_ROUND);
		return hero;
	}
	private Character getDummyVillain() {
		Character villain = new Character();
		villain.setName("test_villain");
		villain.setGender(Character.GENDER.MALE);
		villain.setHealth(Character.INITIAL_HEALTH);
		villain.setArmour(Character.INITIAL_ARMOUR);
		villain.setDefence(Character.INITIAL_DEFENCE);
		villain.setExperience(0);
		villain.setRound(Character.INITIAL_ROUND);
		return villain;
	}
	
}

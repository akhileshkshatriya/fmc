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
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@RunWith(MockitoJUnitRunner.class)
public class Level1ControllerTest {

	
	private static int GOOD_HEALTH = 500;
	private static int BAD_HEALTH = -500;
	
	@InjectMocks
	private Level1Controller controller = new Level1Controller();
	
	
	@Mock
	private CharacterService characterService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitializeLevel1(){
		when(characterService.reInitializeHero()).thenReturn(getDummyHero(GOOD_HEALTH));
		when(characterService.getVillain()).thenReturn(getDummyVillain(GOOD_HEALTH));
		
		Model model = controller.initializeLevel1(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		Statistics statistics = (Statistics)model.getAttribute("statistics");
		assertNotNull(statistics);
		assertEquals("test_hero", statistics.getHero().getName());
		assertEquals("test_villain", statistics.getVillain().getName());
		assertEquals("start.level1", model.getRedirectTo());
	}
	
	@Test
	public void givenBothHaveFullHealthShouldFightMore(){
		
		when(characterService.getHero()).thenReturn(getDummyHero(GOOD_HEALTH));
		when(characterService.getVillain()).thenReturn(getDummyVillain(GOOD_HEALTH));
		
		Model model = controller.fight(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("fight.level1", model.getRedirectTo());
		
	}
	
	@Test
	public void givenHeroGoodHealthAndVillainBadHealthShouldRedirectToHeroWin(){
		
		when(characterService.getHero()).thenReturn(getDummyHero(GOOD_HEALTH));
		when(characterService.getVillain()).thenReturn(getDummyVillain(BAD_HEALTH));
		
		Model model = controller.fight(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("hero.won", model.getRedirectTo());
	}
	
	@Test
	public void givenHeroBadHealthAndVillainGoodHealthShouldRedirectToVillainWin(){
		
		when(characterService.getHero()).thenReturn(getDummyHero(BAD_HEALTH));
		when(characterService.getVillain()).thenReturn(getDummyVillain(GOOD_HEALTH));
		
		Model model = controller.fight(new Model());
		assertNotNull(model);
		assertNotNull(model.getRedirectTo());
		assertEquals("hero.lose", model.getRedirectTo());
	}
	
	private Character getDummyHero(int health) {
		Character hero = new Character();
		hero.setName("test_hero");
		hero.setGender(Character.GENDER.MALE);
		hero.setHealth(health);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setDefence(Character.INITIAL_DEFENCE);
		hero.setExperience(0);
		hero.setRound(Character.INITIAL_ROUND);
		return hero;
	}
	private Character getDummyVillain(int health) {
		Character villain = new Character();
		villain.setName("test_villain");
		villain.setGender(Character.GENDER.MALE);
		villain.setHealth(health);
		villain.setArmour(Character.INITIAL_ARMOUR);
		villain.setDefence(Character.INITIAL_DEFENCE);
		villain.setExperience(0);
		villain.setRound(Character.INITIAL_ROUND);
		return villain;
	}
}

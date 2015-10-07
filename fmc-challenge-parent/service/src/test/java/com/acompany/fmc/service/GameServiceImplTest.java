package com.acompany.fmc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.data.Data;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;
import com.acompany.fmc.service.impl.GameServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {
	
	@InjectMocks
	private GameServiceImpl service = new GameServiceImpl();
	
	@Mock
	private Data data;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveGame(){
		when(data.persistAllData()).thenReturn(true);
		boolean isOperationSuccessfull = service.saveGame();
		assertTrue(isOperationSuccessfull);
	}
	
	@Test
	public void testResumeGame(){
		when(data.selectCharacter(GameServiceImpl.HERO_KEY)).thenReturn(getDummyHero());
		when(data.selectCharacter(GameServiceImpl.VILLAIN_KEY)).thenReturn(getDummyVillain());
		
		Statistics statistics = service.resumeGame();
		
		assertNotNull(statistics);
		assertEquals("test_hero", statistics.getHero().getName());
		assertEquals("test_villain", statistics.getVillain().getName());
		
	}
	
	@Test
	public void givenSavedGamePresentShouldReturnTrue(){
		when(data.selectCharacter(GameServiceImpl.VILLAIN_KEY)).thenReturn(getDummyVillain());
		
		boolean isAnySavedGame = service.isThereAnySavedGame();
		assertTrue(isAnySavedGame);
	}
	
	@Test
	public void givenNoSavedGamePresentShouldReturnFalse(){
		when(data.selectCharacter(GameServiceImpl.VILLAIN_KEY)).thenReturn(null);
		
		boolean isAnySavedGame = service.isThereAnySavedGame();
		assertFalse(isAnySavedGame);
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

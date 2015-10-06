package com.acompany.fmc.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {

	private static final int HERO_EXPERIENCE = 10;
	private static final int HEALTH = 250;

	@InjectMocks
	private CharacterServiceImpl service = new CharacterServiceImpl();

	@Mock
	private Data data;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetHero() {
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(getDummyHero());

		Character hero = service.getHero();

		assertNotNull(hero);
	}

	@Test
	public void testVillainHero() {
		when(data.selectCharacter(CharacterServiceImpl.VILLAIN_KEY)).thenReturn(getDummyVillain());

		Character villain = service.getVillain();

		assertNotNull(villain);
	}

	
	@Test
	public void givenHeroCreatedShouldReturnTrue() {
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(getDummyHero());

		boolean isHeroCreated = service.isHeroCreated();

		assertTrue(isHeroCreated);
	}

	@Test
	public void givenHeroNotCreatedShouldReturnFalse() {
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(null);

		boolean isHeroCreated = service.isHeroCreated();

		assertFalse(isHeroCreated);
	}

	@Test
	public void testReInitializeHero() {

		Character dummyHero = getDummyHero();
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero);
		when(data.saveCharacter(dummyHero, CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero.getName());

		Character hero = service.reInitializeHero();

		assertNotNull(hero);
		assertEquals(hero.getHealth(), Character.INITIAL_HEALTH);
		assertEquals(hero.getRound(), Character.INITIAL_ROUND);
		assertEquals(hero.getDefence(), Character.INITIAL_DEFENCE);
		assertEquals(hero.getArmour(), Character.INITIAL_ARMOUR);
	}

	@Test
	public void testReInitializeHeroShouldNotUpdateExperience() {

		Character dummyHero = getDummyHero();
		dummyHero.setExperience(HERO_EXPERIENCE);
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero);
		when(data.saveCharacter(dummyHero, CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero.getName());

		Character hero = service.reInitializeHero();

		assertNotNull(hero);
		assertEquals(hero.getExperience(), HERO_EXPERIENCE);
		assertEquals(hero.getHealth(), Character.INITIAL_HEALTH);
		assertEquals(hero.getRound(), Character.INITIAL_ROUND);
		assertEquals(hero.getDefence(), Character.INITIAL_DEFENCE);
		assertEquals(hero.getArmour(), Character.INITIAL_ARMOUR);

	}

	@Test
	public void givenCantSaveDataReInitializeHeroShouldReturnNull() {

		Character dummyHero = getDummyHero();
		dummyHero.setExperience(HERO_EXPERIENCE);
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero);
		when(data.saveCharacter(dummyHero, CharacterServiceImpl.HERO_KEY)).thenReturn(null);

		Character hero = service.reInitializeHero();

		assertNull(hero);
	}
	
	@Test
	public void testUpdateHeroHealth() {
		
		Character dummyHero = getDummyHero();
		when(data.updateCharacterHealth(CharacterServiceImpl.HERO_KEY,HEALTH)).thenReturn(dummyHero);
		dummyHero.setHealth(HEALTH);
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero);
		
		Character hero = service.updateHeroHealth(HEALTH);

		assertNotNull(hero);
		assertEquals(HEALTH, hero.getHealth());

	}
	@Test
	public void testUpdateVillainHealth() {
		
		Character dummyVillain = getDummyVillain();
		when(data.updateCharacterHealth(CharacterServiceImpl.VILLAIN_KEY,HEALTH)).thenReturn(dummyVillain);
		dummyVillain.setHealth(HEALTH);
		when(data.selectCharacter(CharacterServiceImpl.VILLAIN_KEY)).thenReturn(dummyVillain);
		
		Character villain = service.updateVillainHealth(HEALTH);

		assertNotNull(villain);
		assertEquals(HEALTH, villain.getHealth());

	}
	
	@Test
	public void testCreateHero() {
		
		Character dummyHero = getDummyHero();
		when(data.saveCharacter(dummyHero,CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero.getName());
		
		String id = service.createHero(dummyHero);

		assertNotNull(dummyHero);
		assertEquals(id, dummyHero.getName());

	}
	
	@Test
	public void testCreateVillain() {
		
		Character dummyVillain = getDummyVillain();
		when(data.saveCharacter(dummyVillain,CharacterServiceImpl.VILLAIN_KEY)).thenReturn(dummyVillain.getName());
		
		String id = service.createVillain();

		assertNotNull(dummyVillain);
		assertEquals(id, dummyVillain.getName());

	}
	
	@Test
	public void testIncreaseHeroExperience() {
		
		Character dummyHero = getDummyHero();
		dummyHero.setExperience(HERO_EXPERIENCE);
		when(data.selectCharacter(CharacterServiceImpl.HERO_KEY)).thenReturn(dummyHero);
		
		int incrementedExperience = service.increaseHeroExperience();

		assertEquals(HERO_EXPERIENCE+1, incrementedExperience);

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
		villain.setName(CharacterServiceImpl.VILLAIN_NAME);
		villain.setGender(Character.GENDER.MALE);
		villain.setHealth(Character.INITIAL_HEALTH);
		villain.setArmour(Character.INITIAL_ARMOUR);
		villain.setDefence(Character.INITIAL_DEFENCE);
		villain.setExperience(0);
		villain.setRound(Character.INITIAL_ROUND);
		return villain;
	}

}

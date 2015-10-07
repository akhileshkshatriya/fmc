package com.acompany.fmc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.service.dto.Character;

@RunWith(MockitoJUnitRunner.class)
public class DataTest {
	
	private static final String  PRIMARY_KEY = "primaryKey";
	private static final int HEALTH = 100;
	
	
	@InjectMocks
	private Data database;
	
	@Mock
	private Map<String, Character> data;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenCharacterExistShouldReturnValidCharacter(){
		when(data.get(PRIMARY_KEY)).thenReturn(getDummyCharacter());
		
		Character character = database.selectCharacter(PRIMARY_KEY);
		
		assertNotNull(character);
		assertEquals("test-charcter-name", character.getName());
	}
	
	@Test
	public void givenNoCharacterExistShouldReturnNull(){
		when(data.get(null)).thenReturn(null);
		
		Character character = database.selectCharacter(null);
		
		assertNull(character);
		
	}
	
	@Test
	public void givenValidCharacterShouldSaveCharacterAndReturnNameAsID(){
		Character character = getDummyCharacter();
		when(data.put(PRIMARY_KEY, character)).thenReturn(character);
		
		String nameAsID =  database.saveCharacter(character, PRIMARY_KEY);
		
		assertNotNull(character);
		assertEquals(character.getName(), nameAsID);
	}

	@Test
	public void givenNULLCharacterShouldNotSaveAndReturnNULLAsID(){
		Character character = null;
		String primaryKey = null;
		
		String nameAsID =  database.saveCharacter(character, primaryKey);
		
		assertNull(nameAsID);
		
	}
	
	@Test
	public void givenCharacterExistShouldUpdateCharacterHealth(){
		when(data.get(PRIMARY_KEY)).thenReturn(getDummyCharacter());
		
		Character character = database.updateCharacterHealth(PRIMARY_KEY, HEALTH);
		
		assertNotNull(character);
		assertEquals(HEALTH, character.getHealth());
	}
	
	@Test
	public void givenCharacterDoesNotExistShouldReturnNULL(){
		when(data.get(PRIMARY_KEY)).thenReturn(null);
		
		Character character = database.updateCharacterHealth(PRIMARY_KEY, HEALTH);
		
		assertNull(character);
	}
	
	@Test
	public void testPersistAllData() {
			boolean aa = database.persistAllData();
			System.out.println(aa);
	}
	
	
	
	private Character getDummyCharacter() {
		Character hero = new Character();
		hero.setName("test-charcter-name");
		hero.setGender(Character.GENDER.MALE);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setDefence(Character.INITIAL_DEFENCE);
		hero.setExperience(0);
		hero.setRound(Character.INITIAL_ROUND);
		return hero;
	}
}

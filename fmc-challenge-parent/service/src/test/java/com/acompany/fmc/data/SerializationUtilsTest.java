package com.acompany.fmc.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.service.dto.Character;

@RunWith(MockitoJUnitRunner.class)
public class SerializationUtilsTest {

	@InjectMocks
	private SerializationUtils utils = new SerializationUtils();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private String path;

	private static final String FILE_NAME = "fmcdatatest.ser";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		path = temporaryFolder.getRoot().getPath() + FILE_NAME;
	}

	@Test
	@Ignore
	public void testSerializationAndDeserialization() {
		Map<String, Character> data = new HashMap<>();
		Character hero = getDummyHero();
		data.put("hero", hero);
		
		boolean retVal = utils.serialize(data, path);
		File file = new File(path);
		assertTrue(file.exists());
		assertTrue(retVal);
		
		Map<String, Character> deserializedData = utils.deserialize(path);
		assertNotNull(deserializedData);
		Character deserializedCharacter = deserializedData.get("hero");
		assertNotNull(deserializedCharacter);
		assertEquals("test-hero", deserializedCharacter.getName());
	}
	
	private Character getDummyHero() {
		Character hero = new Character();
		hero.setName("test-hero");
		hero.setGender(Character.GENDER.MALE);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setDefence(Character.INITIAL_DEFENCE);
		hero.setExperience(0);
		hero.setRound(Character.INITIAL_ROUND);
		return hero;
	}
}

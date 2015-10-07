package com.acompany.fmc.data;

import java.util.HashMap;
import java.util.Map;

import com.acompany.fmc.framework.FMCProperties;
import com.acompany.fmc.framework.annotation.Bean;
import com.acompany.fmc.service.dto.Character;

@Bean
public class Data {

	private static final String DATA_FILE_NAME = FMCProperties.getPropValue("data.file.name");
	private Map<String, Character> data = new HashMap<>();
	private SerializationUtils serializationUtils = new SerializationUtils();
	
	public Data() {
		data = serializationUtils.deserialize(DATA_FILE_NAME);
	}

	public Character selectCharacter(String primaryKey) {
		if (primaryKey != null) {
			return (Character) data.get(primaryKey);
		}
		return null;
	}

	public String saveCharacter(Character character, String primaryKey) {
		if (character != null) {
			data.put(primaryKey, character);
			return character.getName();
		}
		return null;
	}

	public Character updateCharacterHealth(String primaryKey, int health) {
		Character character = data.get(primaryKey);
		if (character != null) {
			character.setHealth(health);
		}
		return character;
	}

	public boolean persistAllData() {
		return serializationUtils.serialize(data, DATA_FILE_NAME);
	}

}

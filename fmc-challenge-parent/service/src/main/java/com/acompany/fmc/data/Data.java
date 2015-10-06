package com.acompany.fmc.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.service.dto.Character;

public class Data {

	private static Logger LOGGER = LoggerFactory.getLogger(Data.class);
	private static final String DATA_FILE_NAME = "fmcdata.ser";

	private static Map<String, Character> data = new HashMap<>();

	static {
		loadData();
	}

	public static Character selectCharacter(String primaryKey) {
		if (primaryKey != null) {
			return (Character) data.get(primaryKey);
		}
		return null;
	}

	public static String saveCharacter(Character character, String primaryKey) {
		if (character != null) {
			data.put(primaryKey, character);
			return character.getName();
		}
		return null;
	}

	public static Character updateCharacterHealth(String primaryKey, int health) {
		Character character = data.get(primaryKey);
		if (character != null) {
			character.setHealth(health);
		}
		return character;
	}

	private static void loadData() {

		try {
			FileInputStream fis = new FileInputStream(DATA_FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (HashMap<String, Character>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOGGER.info(e.getMessage());
			LOGGER.info("No user exist !!, Player Will be prompted to create one");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

	}

	public static boolean persistAllData() {
		try {
			FileOutputStream fos = new FileOutputStream(DATA_FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();

		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage());
		}
		return false;
	}

}

package com.acompany.fmc.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acompany.fmc.service.dto.Character;

public class SerializationUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(SerializationUtils.class);

	public boolean serialize(Map<String, Character> data, String absolutePath) {

		try {
			FileOutputStream fos = new FileOutputStream(absolutePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
			return true;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Character> deserialize(String absolutePath) {
		Map<String, Character> data = new HashMap<>();

		try {
			FileInputStream fis = new FileInputStream(absolutePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (HashMap<String, Character>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LOGGER.info("No user exist !!, Player Will be prompted to create one");
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return data;
	}

}

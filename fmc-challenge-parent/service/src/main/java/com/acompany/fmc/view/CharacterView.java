package com.acompany.fmc.view;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.view.util.RequestSubmitter;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class CharacterView {

	private static final String CHARACTERNAME_PATTERN = "[0-9A-Za-z.\\-_@]{2,10}+";

	@ViewMapping(name = "create.character")
	public void createCharacter(Model model) {

		Character character = new Character();
		dialog();
		character = askName(character);
		character = askGender(character);
		model.addAttribute("hero", character);
		RequestSubmitter.submitRequest("createCharacter", model);

	}

	protected void dialog() {
		System.out.println();
		System.out.println("Me:             Officer someone has stollen my Car... please help ");
		System.out.println("Police Officer: Ohh is it...Please give me your ID first...");
		System.out.println("Me:             What ID ? ");
		System.out.println("Police Officer: Ohh it seems you have not registered, Please Do it first...");
		System.out.println();
		System.out.println("Create Character......");
	}

	private Character askName(Character character) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println();
			System.out.println("Character Name ?");
			String name = scanner.nextLine();
			if(!validateUserName(name)){
				askName(character);
			}else{
				character.setName(name);
			}
			
		} catch (Exception e) {
			ViewUtil.displayErrorMessage("Not a valid Character name, Please enter valid name");
			askName(character);
		}
		return character;
	}

	protected Character askGender(Character character) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println();
			System.out.print("Choose Gender [M] Male  [F] Female");
			System.out.println();
			String gender = scanner.next();

			switch (gender) {
			case "M":
				character.setGender(Character.GENDER.MALE);
				break;
			case "F":
				character.setGender(Character.GENDER.FEMALE);
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");
				askGender(character);
			}

		} catch (Exception e) {
			ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided, eeeee");
			askGender(character);
		}
		return character;
	}

	protected static boolean validateUserName(String heroName) {

		if (heroName == null) {
			return false;
		}
		final Pattern pattern = Pattern.compile(CHARACTERNAME_PATTERN);
		if (!pattern.matcher(heroName).matches()) {
			ViewUtil.displayErrorMessage("Not a Valid characterName");
			System.out.println("Please Note: ");
			System.out.println("You can only use Characters, Numbers _ and @");
			System.out.println("Your charactername must be 2 to 10 char long");
			return false;
		}

		return true;
	}
}

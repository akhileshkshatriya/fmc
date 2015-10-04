package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Character;

@View
public class CreateCharacterView {

	@ViewMapping(name = "create.character")
	public void createCharacter(Model model) {

		Character character = new Character();
		character = askName(character);
		character = askGender(character);
		model.addAttribute("hero", character);
		RequestSubmitter.submitRequest("createCharacter", model);
		
	}

	private Character askName(Character character) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println();
			System.out.println("Please Create Character...");
			System.out.println();
			System.out.println("Character Name ?");
			String name = scanner.next();
			if (name == null || name.isEmpty()) {
				ViewUtil.displayErrorMessage("Not a valid Character name, Please enter valid name");
				askName(character);
			}else{
				character.setName(name);
			}
		}catch(Exception e){
			ViewUtil.displayErrorMessage("Not a valid Character name, Please enter valid name");
			askName(character);
		}
		return character;
	}
	
	protected  Character askGender(Character character){
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
	
}

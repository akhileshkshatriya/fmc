package com.acompany.fmc.view;

import java.io.IOException;
import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.view.util.RequestSubmitter;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class WelcomeView {

	private static final int THERAD_SLEEP_MS = 1500;
	private static final String WELCOME_MESSAGE = "F M C";

	@ViewMapping(name = "welcome.home")
	public void displayHomeMessageWithOptions(Model model) {
		System.out.println();
		System.out.println();

		try {
			ViewUtil.displayASCII(WELCOME_MESSAGE, 15);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Welcome To Find My Car Game");
		System.out.println();

		if(model.getAttribute("resumeoption")!=null){
			displayStoryLine(true);
		}else{
			displayStoryLine(false);	
		}
		
		System.out.println();
		displayHomeOptions();
	}

	private void displayHomeOptions() {
		System.out.print("[1] Go to Police \n");
		System.out.print("[2] Game Story Line \n");
		System.out.print("[3] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		try {

			int userInput = scanner.nextInt();

			switch (userInput) {

			case 1:
				System.out.println();
				RequestSubmitter.submitRequest("goToPolice", new Model());
				break;
			case 2:
				displayStoryLine(false);
				break;
			case 3:
				ViewUtil.exit();
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");
				displayHomeOptions();
			}
		} catch (Exception e) {
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
			displayHomeOptions();
		} 
	}
	private void displayHomeOptionsWithResume() {

		System.out.print("[1] Go to Police \n");
		System.out.print("[2] Game Story Line \n");
		System.out.print("[3] Resume Game\n");
		System.out.print("[4] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		try {

			int userInput = scanner.nextInt();

			switch (userInput) {

			case 1:
				System.out.println();
				RequestSubmitter.submitRequest("goToPolice", new Model());
				break;
			case 2:
				displayStoryLine(true);
				break;
			case 3:
				RequestSubmitter.submitRequest("resumeGame", new Model());
				break;
			case 4:
				ViewUtil.exit();
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");
				displayHomeOptionsWithResume();
			}
		} catch (Exception e) {
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
			displayHomeOptionsWithResume();
		} 
	}

	private void displayStoryLine(boolean resume) {
		System.out.println("Me: I am getting late for an important presentation.I have to rush to office.");
		try {
			System.out.println("I picked the car keys and ran towards the parking...");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Ohh !! I cant believe it, My car is not there");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Seems someone has stolen my red beuty");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Find Out what happened");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println();
			Thread.sleep(THERAD_SLEEP_MS);
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Press Enter to Continue ........");
			scanner.nextLine();
			if(resume){
				displayHomeOptionsWithResume();
			}else{
				displayHomeOptions();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

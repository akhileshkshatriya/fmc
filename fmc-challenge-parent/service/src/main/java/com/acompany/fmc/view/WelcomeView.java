package com.acompany.fmc.view;

import java.io.IOException;
import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.model.Model;

@View
public class WelcomeView {

	private static final int THERAD_SLEEP_MS = 1500;
	private static final String WELCOME_MESSAGE = "F M C";

	@ViewMapping(name = "welcome")
	public void displayHomeMessageWithOptions(Model model) {
		System.out.println();
		System.out.println();

		try {
			ViewUtil.displayASCII(WELCOME_MESSAGE, 15);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Welcome To Find My Car Gamesaasas");
		System.out.println();

		displayStoryLineWithEnterOption();

		System.out.println();
		displayHomeOptions();
	}

	private void displayHomeOptions() {

		System.out.print("[1] New Game \n");
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
				RequestSubmitter.submitRequest("initializeNewGame", new Model());
				break;
			case 2:
				// Runtime.getRuntime().exec("clear");
				displayStoryLine();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");
				displayHomeOptions();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
			displayHomeOptions();
		} finally {
			scanner.reset();
		}
	}

	public void displayStoryLineWithEnterOption() {
		Scanner scanner = new Scanner(System.in);

		displayStoryLine();
		System.out.println("Press Enter to Continue ........");
		scanner.nextLine();
		displayHomeOptions();

	}

	private void displayStoryLine() {
		System.out.println("Me: I am getting late for an important presentation.I have to rush to office.");
		try {
			System.out.println("I picked the car keys and ran towards it");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Ohh !! I cant believe it, My car is not there");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Seems someone has stolen my car");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Find Out what happened");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println();
			Thread.sleep(THERAD_SLEEP_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

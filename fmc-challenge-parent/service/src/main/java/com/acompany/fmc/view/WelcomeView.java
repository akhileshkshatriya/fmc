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
		System.out.println("Welcome To Find My Car Game");
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
				//Runtime.getRuntime().exec("clear");
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
		}finally {
			scanner.reset();
		}
	}

	public void displayStoryLine() {
		Scanner scanner = new Scanner(System.in);
		try {
			
			System.out.println("You are a hardworking person with ........");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("You bought your favorite car with your hard earned money");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Now, Someone Stole your car");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Police is not able to help you out because of crime rate in city");
			Thread.sleep(THERAD_SLEEP_MS);
			System.out.println("Now You need to enter in the world of FMC(Find My Car...and get your car back");
			System.out.println();
			System.out.println("Press Enter to Continue ........");
			scanner.nextLine();
			
			displayHomeOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.reset();
		}

	}

}

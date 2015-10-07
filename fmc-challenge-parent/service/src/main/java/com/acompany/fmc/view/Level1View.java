package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Statistics;
import com.acompany.fmc.view.util.RequestSubmitter;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class Level1View {

	@ViewMapping(name = "start.level1")
	public void displayDialogWithFightOptions(Model model) {
		dialog();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press Enter to Continue ........");
		scanner.nextLine();
		displayStatisticsWithFightOption(model);
	}
	
	@ViewMapping(name = "save.success")
	public void displaySuccessSaveMessage(Model model) {
		System.out.println("Your Game has been saved successfully, Bye.... See you soon !!");
		System.exit(0);
	}

	@ViewMapping(name = "fight.level1")
	public void displayStatisticsWithFightOption(Model model) {
		Statistics statistics = (Statistics)model.getAttribute("statistics");
		
		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		
		System.out.print("[1] Fight \n");
		System.out.print("[2] Exit \n");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {

			case 1:
				System.out.println();
				RequestSubmitter.submitRequest("fightLevel1", new Model());
				break;
			case 2:
				displaySaveAndExitOption(model);
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");

			}
		} catch (Exception e) {
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
		}
	}
	
	protected void displaySaveAndExitOption(Model model){
		System.out.println("Do you want to Save Game ?");
		System.out.print("[1] Yes \n");
		System.out.print("[2] No \n");
		Scanner scanner = new Scanner(System.in);
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {

			case 1:
				System.out.println();
				RequestSubmitter.submitRequest("saveGame", new Model());
				break;
			case 2:
				System.out.println("Bye.... See you soon");
				System.exit(0);
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");

			}
		} catch (Exception e) {
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
		}
	}

	protected void dialog() {
		System.out.println();
		System.out.println("I have found out TheVillains den and my car too");
		System.out.println("But here what I see, He has already put in my Car in the Car crusher...OH MY GOD!! ");
		System.out.println("What is he up too....OH MY GOD!! He is about to crush MY CAR.");
		System.out.println("Now the only option remains with me is to FIGHT...");
		System.out.println();
	}
	
	
}

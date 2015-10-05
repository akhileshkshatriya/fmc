package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Statistics;

@View
public class Level1View {

	@ViewMapping(name = "level1.start")
	public void displayLevel1Options(Model model) {
		
		System.out.print("I am ready to fight now...");
		System.out.print("My and TheVillains comparison.");
		System.out.println();
		
		Statistics statistics = (Statistics)model.getAttribute("statistics");
		
		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		
		System.out.print("[1] Fight \n");
		System.out.print("[2] Save Game \n");
		System.out.print("[3] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {

			case 1:
				System.out.println();
				RequestSubmitter.submitRequest("level1.fight", new Model());
				break;
			case 2:
				RequestSubmitter.submitRequest("saveGame", new Model());
				break;
			case 3:
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
	
	
}

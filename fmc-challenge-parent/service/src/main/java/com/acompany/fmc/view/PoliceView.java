package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.view.util.RequestSubmitter;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class PoliceView {
	
	@ViewMapping(name="goto.police.options")
	public void displayStartOptions(Model model){
		
		System.out.println("I informed Police about it, They said my Car has been Stolen by TheVillain");
		System.out.println("Police is also looking for TheVillain for a long time");
		System.out.println("But Police can't help me much, because they doon't know where TheVillain is.");
		System.out.println("It seems like it is going to take a little long");
		System.out.println("Now Either I can sit back and wait for Police to find TheVillain, and hopefully my Car");
		System.out.println("OR");
		System.out.println("I can take the control of situation and start a mission to FIND MY CAR own my own.");
		//System.out.println("I also have option to exit from here and use Public Transport from now on which is also environment friendly");
		System.out.println("Ohh yes, Meanwhile ...I need a commute, Temporarily...I can simply buy a second hand car too.");
		System.out.println();
		
		System.out.println();
		System.out.println("Please Select...");
		System.out.print("[1] Start Mission\n");
		System.out.print("[2] Buy Second Hand Car \n");
		System.out.print("[3] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			int userInput = scanner.nextInt(); 

			switch (userInput) {

			case 1:
				RequestSubmitter.submitRequest("initializeLevel1", new Model());
				break;
			case 2:
				displaySecondHandCarSite(model);
				break;
			case 3:
				System.exit(0);
				break;
			default:
				ViewUtil.displayErrorMessage("You Naughty Player, Please choose from the option provided");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
			ViewUtil.displayErrorMessage("Sorry Worng Input, Please try again...");
			System.out.println();
		}
		
	}
	public void displaySecondHandCarSite(Model model){
		System.out.println();
		System.out.println("Hmm....So I have bought a really nice car at effordable price here...");
		System.out.println("Thank god, at least for that matter there is a wondeful site where I can buy nice cars at effordable prices.");
		System.out.println("www.acompany.com");
		System.out.println("Here... buying and selling is really easy.");
		System.out.println();
		
		System.out.print("[1] Back \n");
		System.out.print("[2] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {
			case 1:
				displayStartOptions(model);
				break;
			case 2:
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

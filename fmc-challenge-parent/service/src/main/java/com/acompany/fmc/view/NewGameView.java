package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.model.Model;

@View
public class NewGameView {
	
	@ViewMapping(name="start.options")
	public void displayStartOptions(Model model){
		
		System.out.println("I have found out that my Car has been stolen by TheVillain");
		System.out.println("and he is very powerfull....");
		System.out.println("I can either chose to start the mission or forget about my car");
		
		System.out.println();
		System.out.println("Please Select...");
		System.out.print("[1] Start \n");
		System.out.print("[2] Buy Second Hand Car \n");
		System.out.print("[3] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			int userInput = scanner.nextInt(); 

			switch (userInput) {

			case 1:
				RequestSubmitter.submitRequest("initializeLavel1", new Model());
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
		System.out.println("You can buy second hand cars in very good conditions from");
		System.out.println("www.acompany.com");
		System.out.println("Here the prices are really low");
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

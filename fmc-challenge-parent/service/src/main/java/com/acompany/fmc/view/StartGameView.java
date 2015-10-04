package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.frontcontroller.RequestSubmitter;
import com.acompany.fmc.framework.model.Model;

@View
public class StartGameView {
	
	@ViewMapping(name="start.options")
	public void displayStartOptions(Model model){
		
		System.out.println("So you have decided to bring your car back, Awesome");
		System.out.println("Good Luck !!");
		System.out.println();
		System.out.println("Please Select...");
		System.out.print("[1] Start \n");
		System.out.print("[2] Exit");
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			int userInput = scanner.nextInt(); 

			switch (userInput) {

			case 1:
				System.out.println("Inside");
				RequestSubmitter.submitRequest("initializeLavel1", new Model());
				break;
			case 2:
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
	
	public static void main(String[] args) {
		new StartGameView().displayStartOptions(new Model());
	}
	
}

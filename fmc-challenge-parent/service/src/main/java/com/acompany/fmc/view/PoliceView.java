package com.acompany.fmc.view;

import java.util.Scanner;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.view.lambda.PoliceViewLambda;
import com.acompany.fmc.view.util.RequestSubmitter;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class PoliceView {

	@ViewMapping(name = "goto.police.options")
	public void displayDialogWithStartOptions(Model model) {
		displayOptions(model, true);
	}

	protected void displayOptions(Model model, boolean displayDialog) {
		if(displayDialog){
			PoliceViewLambda.display(PoliceViewLambda.dialog);
		}
		PoliceViewLambda.display(PoliceViewLambda.policeViewOptions);
		Scanner scanner = new Scanner(System.in);
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {
			case 1:
				RequestSubmitter.submitRequest("initializeLevel1", new Model());
				break;
			case 2:
				displaySecondHandCarSite(model, true);
				break;
			case 3:
				ViewUtil.exit();
				break;
			default:
				PoliceViewLambda.display(PoliceViewLambda.error);
				displayOptions(model, false);
			}
		} catch (Exception e) {
			PoliceViewLambda.display(PoliceViewLambda.error);
			displayOptions(model, false);
		}
	}

	public void displaySecondHandCarSite(Model model, boolean displayDialog) {
		
		if(displayDialog){
			PoliceViewLambda.display(PoliceViewLambda.secondHandCarDialog);
		}
		PoliceViewLambda.display(PoliceViewLambda.secondHandCarOptions);
		
		Scanner scanner = new Scanner(System.in);
		try {
			int userInput = scanner.nextInt();
			switch (userInput) {
			case 1:
				displayOptions(model, true);
				break;
			case 2:
				ViewUtil.exit();
				break;
			default:
				PoliceViewLambda.display(PoliceViewLambda.error);
				displaySecondHandCarSite(model, false);
			}
		} catch (Exception e) {
			PoliceViewLambda.display(PoliceViewLambda.error);
			displaySecondHandCarSite(model, false);
		}
	}
}

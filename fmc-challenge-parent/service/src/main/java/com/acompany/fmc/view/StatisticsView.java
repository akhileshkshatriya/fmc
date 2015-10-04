package com.acompany.fmc.view;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;

@View
public class StatisticsView {
	
	@ViewMapping(name="display.stats")
	public void displayStatistics(Model model){
		
		Character character = (Character)model.getAttribute("character");
		
		String name = "akhil";
		int health = 100;
		int experience = 0;
		
		System.out.println("______________________");
		System.out.println("|   Name      :"+name+" |");
		System.out.println("|   Health    :"+health+"   |");
		System.out.println("|   Experirnce:"+experience+"     |");
		System.out.println("______________________");
	}
}

package com.acompany.fmc.view;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Statistics;

@View
public class FinishView {

	@ViewMapping(name = "hero.won")
	public void displayHeroWinningMessage(Model model) {

		Statistics statistics = (Statistics) model.getAttribute("statistics");

		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		System.out.println("Congratulations.....You Win");
	}

	@ViewMapping(name = "hero.lose")
	public void displayHeroLoseMessage(Model model) {

		Statistics statistics = (Statistics) model.getAttribute("statistics");

		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		
		System.out.println("You Lose, Try Again");
	}
}

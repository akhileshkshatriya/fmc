package com.acompany.fmc.view;

import com.acompany.fmc.framework.annotation.View;
import com.acompany.fmc.framework.annotation.ViewMapping;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Statistics;
import com.acompany.fmc.view.util.ViewUtil;

@View
public class FinishView {

	@ViewMapping(name = "hero.won")
	public void displayHeroWinningMessage(Model model) {

		Statistics statistics = (Statistics) model.getAttribute("statistics");

		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		System.out.println("Yipeeeee... I WON !!");
		System.out.println("Yipeeeee... I FOUND MY CAR");
		System.exit(0);
	}

	@ViewMapping(name = "hero.lose")
	public void displayHeroLoseMessage(Model model) {

		Statistics statistics = (Statistics) model.getAttribute("statistics");

		ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE);
		System.out.println(statistics);
		ViewUtil.resetConsoleText();
		
		System.out.println("Oops.... TheVillain has crushed my car");
		System.out.println("Now I will go and buy a second hand Car from www.acompany.com");
		System.out.println();
		System.exit(0);
	}
}

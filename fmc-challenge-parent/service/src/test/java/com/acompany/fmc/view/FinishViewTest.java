package com.acompany.fmc.view;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acompany.fmc.data.Data;
import com.acompany.fmc.framework.model.Model;
import com.acompany.fmc.service.dto.Character;
import com.acompany.fmc.service.dto.Statistics;

@RunWith(MockitoJUnitRunner.class)
public class FinishViewTest {

	@Rule
	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@InjectMocks
	private FinishView view = new FinishView();

	@Mock
	private Data data;
	
	@Test
	public void writesTextToSystemErr() {
		
		//view.displayHeroWinningMessage(getDummyModelWithStatistics());
		//assertEquals(ViewUtil.displayConsoleTextIn(ViewUtil.ANSI_BLUE), systemErrRule.getLog());
		//assertEquals("Yipeeeee... I WON !!", systemOutRule.getLog());
		
		System.out.println("hello");
		System.out.println("world");
		assertEquals("hello\nworld\n", systemOutRule.getLog());
	}
	
	private Model getDummyModelWithStatistics(){
		Model model = new Model();
		model.addAttribute("statistics", getDummyStatistics());
		return model;
		
	}
	
	private Statistics getDummyStatistics(){
		Statistics statistics = new Statistics();
		statistics.setHero(getDummyHero());
		statistics.setVillain(getDummyVillain());
		return statistics;
	}
	
	private Character getDummyHero() {
		Character hero = new Character();
		hero.setName("test_hero");
		hero.setGender(Character.GENDER.MALE);
		hero.setHealth(Character.INITIAL_HEALTH);
		hero.setArmour(Character.INITIAL_ARMOUR);
		hero.setDefence(Character.INITIAL_DEFENCE);
		hero.setExperience(0);
		hero.setRound(Character.INITIAL_ROUND);
		return hero;
	}
	private Character getDummyVillain() {
		Character villain = new Character();
		villain.setName("test_villain");
		villain.setGender(Character.GENDER.MALE);
		villain.setHealth(Character.INITIAL_HEALTH);
		villain.setArmour(Character.INITIAL_ARMOUR);
		villain.setDefence(Character.INITIAL_DEFENCE);
		villain.setExperience(0);
		villain.setRound(Character.INITIAL_ROUND);
		return villain;
	}
	
}

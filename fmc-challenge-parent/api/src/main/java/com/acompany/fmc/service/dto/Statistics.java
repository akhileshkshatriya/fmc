package com.acompany.fmc.service.dto;

import java.util.Arrays;

public class Statistics {


	private Character hero;


	private Character villain;

	public static void main(String[] args) {
		Statistics test = new Statistics();
		Character hero = new Character();
		hero.setName("Akhilesh");
		hero.setExperience(100);
		hero.setGender(Character.GENDER.FEMALE);
		test.setHero(hero);
		Character villain = new Character();
		test.setVillain(villain);
		System.out.println(test);
	}

	
	@Override
	public String toString() {
		char[] hyphens = new char[30];
		Arrays.fill(hyphens, 0, 30, '-');
		StringBuilder statisticsBuilder = new StringBuilder();
		statisticsBuilder.append(String.format("%-30s", "Hero Details"));
		statisticsBuilder.append("\t");
		statisticsBuilder.append(String.format("%-30s", "Villain Details"));
		statisticsBuilder.append("\n");
		statisticsBuilder.append(hyphens);
		statisticsBuilder.append("\t");
		statisticsBuilder.append(hyphens);
		statisticsBuilder.append("\n");
		statisticsBuilder.append("| Name: " + String.format("%20s", hero.getName()) + "|");
		statisticsBuilder.append("\t");
		statisticsBuilder.append("| Name: " + String.format("%20s", villain.getName()) + "|");
		statisticsBuilder.append("\n");
		statisticsBuilder.append("| Health: " + String.format("%18s", hero.getHealth()) + "|");
		statisticsBuilder.append("\t");
		statisticsBuilder.append("| Health: " + String.format("%18s", villain.getHealth()) + "|");
		statisticsBuilder.append("\n");
		statisticsBuilder.append("| Armour: " + String.format("%18s", hero.getArmour()) + "|");
		statisticsBuilder.append("\t");
		statisticsBuilder.append("| Armour: " + String.format("%18s", villain.getArmour()) + "|");
		statisticsBuilder.append("\n");
		statisticsBuilder.append("| Experience: " + String.format("%14s", hero.getExperience()) + "|");
		statisticsBuilder.append("\t");
		statisticsBuilder.append(hyphens);
		statisticsBuilder.append("\n");
		statisticsBuilder.append(hyphens);
		return statisticsBuilder.toString();
	}

	public Character getHero() {
		return hero;
	}

	public void setHero(Character hero) {
		this.hero = hero;
	}

	public Character getVillain() {
		return villain;
	}

	public void setVillain(Character villain) {
		this.villain = villain;
	}
	
}

package com.acompany.fmc.service.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Character implements Serializable {
	
	
	public static enum GENDER{
		MALE, FEMALE
	}
	
	private static final long serialVersionUID = 1L;
	private String name;
	private GENDER gender;
	private int health;
	private int experience;
	private int armour;
	private int defence;
	private int round;
	
	private Map<String, Power> powers = new HashMap<>(); 
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Map<String, Power> getPowers() {
		return powers;
	}

	public void setPowers(Map<String, Power> powers) {
		this.powers = powers;
	}

}

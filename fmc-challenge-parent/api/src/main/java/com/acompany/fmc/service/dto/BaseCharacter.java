package com.acompany.fmc.service.dto;

import java.util.HashMap;
import java.util.Map;

public class BaseCharacter {
	
	private String name;
	private String description;
	private int health;
	private int money;
	private Map<String, Weapon> weapons = new HashMap<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Map<String, Weapon> getWeapons() {
		return weapons;
	}
	public void setWeapons(Map<String, Weapon> weapons) {
		this.weapons = weapons;
	}

}

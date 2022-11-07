package com.cognixia.jump.model;

public class Cards {

	private String name;
	private String manaCost;
	private String keywords;
	private String power;
	private String toughness;
	private int inventory;
	private double cost;
	
	
	public Cards() {
		
	}
	
	public Cards(String name, String manaCost, String keywords, String power, String toughness, int inventory,
			double cost) {
		super();
		this.name = name;
		this.manaCost = manaCost;
		this.keywords = keywords;
		this.power = power;
		this.toughness = toughness;
		this.inventory = inventory;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Cards [name=" + name + ", manaCost=" + manaCost + ", Keywords=" + keywords + ", power=" + power
				+ ", toughness=" + toughness + ", inventory=" + inventory + ", cost=" + cost + "]";
	}
	
	
}

package com.in.limit_service.bean;

public class Limit {
	private int minimam;
	private int maximum;
	public int getMinimam() {
		return minimam;
	}
	public void setMinimam(int minimam) {
		this.minimam = minimam;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public Limit(int minimam, int maximum) {
		super();
		this.minimam = minimam;
		this.maximum = maximum;
	}
	@Override
	public String toString() {
		return "Limit [minimam=" + minimam + ", maximum=" + maximum + "]";
	}

}

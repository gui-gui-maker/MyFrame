package com.guido.model;

import java.io.Serializable;

public class Mango extends Fruit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String color;
	private Float weight;
	
	
	public Mango() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mango(String name, String color, Float weight) {
		super();
		this.name = name;
		this.color = color;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Mango [name=" + name + ", color=" + color + ", weight=" + weight + "]";
	}
	
	
}

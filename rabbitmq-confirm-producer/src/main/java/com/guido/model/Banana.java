package com.guido.model;

import java.io.Serializable;

public class Banana extends Fruit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String color;
	
	private Float length;
	
	
	public Banana() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Banana(String name, String color, Float length) {
		super();
		this.name = name;
		this.color = color;
		this.length = length;
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
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "Banana [name=" + name + ", color=" + color + ", length=" + length + "]";
	}

}

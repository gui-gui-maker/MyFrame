package com.guido.model;

import java.io.Serializable;


public class Apple extends Fruit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String name;

    private String color;

    public Apple() {
		super();
	}
    
    public Apple(String name, String color) {
		super();
		this.name = name;
		this.color = color;
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

	@Override
	public String toString() {
		return "Apple [name=" + name + ", color=" + color + "]";
	}


}

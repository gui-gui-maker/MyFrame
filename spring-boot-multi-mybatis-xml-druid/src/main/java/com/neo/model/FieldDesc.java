package com.neo.model;

import java.io.Serializable;

public class FieldDesc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String fieldType;
	private String fieldLen;
	private String fieldDec;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldLen() {
		return fieldLen;
	}
	public void setFieldLen(String fieldLen) {
		this.fieldLen = fieldLen;
	}
	public String getFieldDec() {
		return fieldDec;
	}
	public void setFieldDec(String fieldDec) {
		this.fieldDec = fieldDec;
	}
}

package com.khnt.bpm.communal;

import java.util.Map;

/**
 * 一个默认的流程参与者（用户）实现
 * 
 * @author hansel
 * 
 */
public class BpmUserImpl implements BpmUser {

	private String id;
	private String name;
	private BpmOrg unit;
	private BpmOrg parentUnit;
	private BpmOrg department;
	private Map<String, String> role;

	public BpmUserImpl(String id, String name, BpmOrg unit, BpmOrg department, Map<String, String> role) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.parentUnit = unit != null ? unit.getParent() : null;
		this.department = department;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BpmOrg getUnit() {
		return unit;
	}

	public void setUnit(BpmOrg unit) {
		this.unit = unit;
	}

	public BpmOrg getDepartment() {
		return department;
	}

	public void setDepartment(BpmOrg department) {
		this.department = department;
	}

	public Map<String, String> getRole() {
		return role;
	}

	public void setRole(Map<String, String> role) {
		this.role = role;
	}

	@Override
	public BpmOrg getParentUnit() {
		return this.parentUnit;
	}

	public void setParentUnit(BpmOrg parentUnit) {
		this.parentUnit = parentUnit;
	}

}

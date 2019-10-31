package com.fwxm.dining.bean;

import java.util.HashSet;
import java.util.Set;
/**
 * 菜单发布者根据时间发布菜单
 * @author zgz
 *
 */
public class FoodPuboExt extends FoodPubo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Set<FoodExt> fs = new HashSet<FoodExt>();
	
	
	public FoodPuboExt() {
		
	}

	public FoodPuboExt(FoodPubo fpo) {
		this.setId(fpo.getId());
		this.setUse_time(fpo.getUse_time());
		this.setMeal_name(fpo.getMeal_name());
		this.setPub_status(fpo.getPub_status());
		this.setMark(fpo.getMark());
	}

	public Set<FoodExt> getFs() {
		return fs;
	}

	public void setFs(Set<FoodExt> fs) {
		this.fs = fs;
	}

	

	

	
	
	
}

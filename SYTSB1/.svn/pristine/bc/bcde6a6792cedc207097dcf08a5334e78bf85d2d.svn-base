package com.fwxm.dining.bean;

import java.util.HashSet;
import java.util.Set;

import com.khnt.pub.fileupload.bean.Attachment;

public class FoodExt extends Food{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<Attachment> atts = new HashSet<Attachment>();
	//发布菜单的id
	private String fpmId;

	public FoodExt(Food f) {
		super();
		this.setId(f.getId());
		this.setName(f.getName());
		this.setFdesc(f.getFdesc());
		this.setFtype(f.getFtype());
	}
	

	public FoodExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getFpmId() {
		return fpmId;
	}


	public void setFpmId(String fpmId) {
		this.fpmId = fpmId;
	}


	public Set<Attachment> getAtts() {
		return atts;
	}

	public void setAtts(Set<Attachment> atts) {
		this.atts = atts;
	}
	
	
	
}

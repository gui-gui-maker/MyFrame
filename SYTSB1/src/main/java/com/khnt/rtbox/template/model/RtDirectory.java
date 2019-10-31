package com.khnt.rtbox.template.model;

import java.io.Serializable;

/**
 * @author ZQ
 * @version 2016年3月22日 下午5:02:21 目录管理
 */
public class RtDirectory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;// DIR目录CODE
	private String name;// DIR目录名称
	private String pageName;// 页面名称
	private String pageContent;// 页面内容
	private String copyCode;// 副本code
	private String fieldSub;// 字段下标

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getCopyCode() {
		return copyCode;
	}

	public void setCopyCode(String copyCode) {
		this.copyCode = copyCode;
	}

	public String getFieldSub() {
		return fieldSub;
	}

	public void setFieldSub(String fieldSub) {
		this.fieldSub = fieldSub;
	}

}

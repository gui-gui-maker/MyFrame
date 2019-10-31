package com.khnt.rtbox.template.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.khnt.rtbox.config.bean.RtPage;

/**
 * @author ZQ
 * @version 2016年3月14日 下午5:07:38 配置基础类
 */
public class RtEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//
	private String name;//
	private String type;// 字段类型
	private String bind;// 绑定JS类型 为空则为click
	private String js;// 触发JS
	private Boolean isHidden;// 是否隐藏
	private Boolean nullable;// 能否为空
	private String dataType;//1.code,2.sql,3.string
	private String sql;
	private String code;// 码表
	private String jsonData;//用于设置绑定json字符串
	private Boolean tree;// 是否树形
	private RtDbCol dbCol;// 数据库字段 用于数据储存
	private String defaultValue;// 默认值,别名default
	private RtPage rtPage;
	private LinkedHashMap<String, String> attr;// 属性和属性VALUE,别名attr,结合jquery
												// validate
												// ,可以用maxLength,minLenth 等等属性
	private LinkedHashMap<String, String> ligerui;// 集成ligerUI所有属性

	public RtEntity() {
	}

	public RtEntity(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Boolean getTree() {
		return tree;
	}

	public void setTree(Boolean tree) {
		this.tree = tree;
	}

	public RtDbCol getDbCol() {
		return dbCol;
	}

	public void setDbCol(RtDbCol dbCol) {
		this.dbCol = dbCol;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public LinkedHashMap<String, String> getAttr() {
		return attr;
	}

	public void setAttr(LinkedHashMap<String, String> attr) {
		this.attr = attr;
	}

	public LinkedHashMap<String, String> getLigerui() {
		return ligerui;
	}

	public void setLigerui(LinkedHashMap<String, String> ligerui) {
		this.ligerui = ligerui;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public RtPage getRtPage() {
		return rtPage;
	}

	public void setRtPage(RtPage rtPage) {
		this.rtPage = rtPage;
	}
	

}

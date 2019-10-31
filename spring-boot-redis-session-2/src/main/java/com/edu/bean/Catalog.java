package com.edu.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 目录
 * @author Guido
 *
 */
@Entity
@Table(name = "t_mulu")
public class Catalog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ml")
    private String    id;
	@Column(name="msg")
	private String 	  text ;
	private String 	  sflb ;
	private String 	  msql ;//关联sql
	private String 	  cc ;
	private String 	  pc ;
	@Transient
	private boolean hidden = true;

	private String icon;
	
	@Column(name="selectedicon")
	private String selectedIcon;

	private String color;
	@Column(name="backcolor")
	private String backColor;

	private String href;

	private boolean selectable;
	@Transient
	private CatalogState state = new CatalogState();
	@Transient
	private String[] tags = new String[] {};
	
	@Transient
	@Column(name = "pml")
	private String 	pid ;//父目录
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)  
	@JoinColumn(name="pml") 
	private Catalog pNode;
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE},mappedBy="pNode") 
	private List<Catalog> nodes = new ArrayList<Catalog>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSflb() {
		return sflb;
	}

	public void setSflb(String sflb) {
		this.sflb = sflb;
	}

	public String getMsql() {
		return msql;
	}

	public void setMsql(String msql) {
		this.msql = msql;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Catalog> getNodes() {
		return nodes;
	}

	public void setNodes(List<Catalog> nodes) {
		this.nodes = nodes;
	}

	public Catalog getpNode() {
		return pNode;
	}

	public void setpNode(Catalog pNode) {
		this.pNode = pNode;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public CatalogState getState() {
		return state;
	}

	public void setState(CatalogState state) {
		this.state = state;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	
}

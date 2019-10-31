package com.khnt.rtbox.config.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.Map;

import javax.persistence.Transient;

import com.khnt.pub.fileupload.bean.Attachment;

/**
 * @author ZQ
 * @version 2016年7月7日 上午10:46:23 导出时传值的数据BEAN
 */
public class RtExportData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 待用
	private String name;// 字段名称
	private String value;// 字段值
	private Map<String, Object> funcMap;// 功能键值对
	private String remark;
	private String type;// 待用
	private String fkReportId;
	private String cx;
	private String cy;
	private String bold;
	private String italic;
	private String size;
	private String family;
	private String image;
	private String markContent;// 标注内容
	private String pageNo;//页码
	
	private byte[] signByte;

	public Attachment attachment;// 用于导出图片

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, Object> getFuncMap() {
		return funcMap;
	}

	public void setFuncMap(Map<String, Object> funcMap) {
		this.funcMap = funcMap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFkReportId() {
		return fkReportId;
	}

	public void setFkReportId(String fkReportId) {
		this.fkReportId = fkReportId;
	}

	public String getCx() {
		return cx;
	}

	public String getCy() {
		return cy;
	}

	public String getBold() {
		return bold;
	}

	public void setBold(String bold) {
		this.bold = bold;
	}

	public String getItalic() {
		return italic;
	}

	public void setItalic(String italic) {
		this.italic = italic;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMarkContent() {
		return markContent;
	}

	public void setMarkContent(String markContent) {
		this.markContent = markContent;
	}

	public Object deepClone() throws IOException, OptionalDataException, ClassNotFoundException {// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Transient
	public byte[] getSignByte() {
		return signByte;
	}

	public void setSignByte(byte[] signByte) {
		this.signByte = signByte;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}

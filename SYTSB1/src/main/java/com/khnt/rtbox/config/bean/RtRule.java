package com.khnt.rtbox.config.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtRule
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Entity
@Table(name = "RT_RULE")
public class RtRule implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String title;// 标题
	private String content;// 规则内容
	private String rtType;// 大分类
	private String rtSType;// 小分类
	private Date createTime;// 创建时间

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "RT_TYPE")
	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	@Column(name = "RT_S_TYPE")
	public String getRtSType() {
		return rtSType;
	}

	public void setRtSType(String rtSType) {
		this.rtSType = rtSType;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "RT_RULE:ID=" + id;

	}
}

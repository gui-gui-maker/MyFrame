package com.fwxm.library.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Cupboard
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LIB_CUPBOARD")
public class Cupboard implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String qrcode;//二维码
	private String description;//描述
	private String createBy;//
	private Date createTime;//
	private String status;//状态
	
	private List<Book> books = new ArrayList<Book>();

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	@Transient
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Column(name="QRCODE")
	public String getQrcode(){
		return qrcode;
	}
		
	public void setQrcode(String qrcode){
		this.qrcode=qrcode;
	}

	@Column(name="DESCRIPTION")
	public String getDescription(){
		return description;
	}
		
	public void setDescription(String description){
		this.description=description;
	}

	@Column(name="CREATE_BY")
	public String getCreateBy(){
		return createBy;
	}
		
	public void setCreateBy(String createBy){
		this.createBy=createBy;
	}

	@Column(name="CREATE_TIME")
	public Date getCreateTime(){
		return createTime;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "LIB_CUPBOARD:ID="+id;

	}
}

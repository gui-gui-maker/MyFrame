package com.fwxm.library.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName Book
 * @JDK 1.7
 * @author CODER_V3.0
 * @java.util.Date 2018-10-23 10:57:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LIB_BOOK")
public class Book implements BaseEntity,Cloneable {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String name;//书名
	private String category;//种类
	private String qrcode;//二维码
	private String identifier;//标识（1可领取/0不可领取）
	private String content;//类型
	private String currentPosition;//当前位置（id）
	private String createBy;//录入人（id）
	private java.util.Date createTime;//录入时间
	private String receiveBy;
	private String receiveRecordBy;
	private java.util.Date receiveTime;//领取时间
	private String invalidBy;//作废人
	private java.util.Date invalidTime;//作废时间
	private String invalidReason;//作废说明
	private String status;//状态0`新录入`1`已上架`2`已下架`3`已借出`4`已归还`5`领取`6`作废
	private java.util.Date lastOpTime;//最后操作时间
	private String lastOpBy;//最后操作人
	private String remark;//备注
	

	private String fk_receive_id;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="NAME")
	public String getName(){
		return name;
	}
		
	public void setName(String name){
		this.name=name;
	}

	@Column(name="CATEGORY")
	public String getCategory(){
		return category;
	}
		
	public void setCategory(String category){
		this.category=category;
	}

	@Column(name="QRCODE")
	public String getQrcode(){
		return qrcode;
	}
		
	public void setQrcode(String qrcode){
		this.qrcode=qrcode;
	}

	@Column(name="IDENTIFIER")
	public String getIdentifier(){
		return identifier;
	}
		
	public void setIdentifier(String identifier){
		this.identifier=identifier;
	}

	@Column(name="CONTENT")
	public String getContent(){
		return content;
	}
		
	public void setContent(String content){
		this.content=content;
	}

	@Column(name="CURRENT_POSITION")
	public String getCurrentPosition(){
		return currentPosition;
	}
		
	public void setCurrentPosition(String currentPosition){
		this.currentPosition=currentPosition;
	}

	@Column(name="CREATE_BY")
	public String getCreateBy(){
		return createBy;
	}
		
	public void setCreateBy(String createBy){
		this.createBy=createBy;
	}

	@Column(name="CREATE_TIME")
	public java.util.Date getCreateTime(){
		return createTime;
	}
		
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}


	@Column(name="RECEIVE_TIME")
	public java.util.Date getReceiveTime(){
		return receiveTime;
	}
		
	public void setReceiveTime(java.util.Date receiveTime){
		this.receiveTime=receiveTime;
	}

	@Column(name="INVALID_BY")
	public String getInvalidBy(){
		return invalidBy;
	}
		
	public void setInvalidBy(String invalidBy){
		this.invalidBy=invalidBy;
	}

	@Column(name="INVALID_TIME")
	public java.util.Date getInvalidTime(){
		return invalidTime;
	}
		
	public void setInvalidTime(java.util.Date invalidTime){
		this.invalidTime=invalidTime;
	}

	@Column(name="INVALID_REASON")
	public String getInvalidReason(){
		return invalidReason;
	}
		
	public void setInvalidReason(String invalidReason){
		this.invalidReason=invalidReason;
	}

	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}
	
	@Column(name="last_op_time")
	public java.util.Date getLastOpTime() {
		return lastOpTime;
	}

	public void setLastOpTime(java.util.Date lastOpTime) {
		this.lastOpTime = lastOpTime;
	}


	public String getFk_receive_id() {
		return fk_receive_id;
	}

	public void setFk_receive_id(String fk_receive_id) {
		this.fk_receive_id = fk_receive_id;
	}
	
	@Column(name="receive_by")
	public String getReceiveBy() {
		return receiveBy;
	}

	public void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}
	@Column(name="receive_record_by")
	public String getReceiveRecordBy() {
		return receiveRecordBy;
	}

	public void setReceiveRecordBy(String receiveRecordBy) {
		this.receiveRecordBy = receiveRecordBy;
	}
	@Column(name="last_op_by")
	public String getLastOpBy() {
		return lastOpBy;
	}

	public void setLastOpBy(String lastOpBy) {
		this.lastOpBy = lastOpBy;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "LIB_BOOK:ID="+id;

	}
	//克隆方法

	@Override
	public Object clone() throws CloneNotSupportedException {
		Book o = null;
        try {
            o = (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
  
        return o;
	}
	
}

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
 * @ClassName Borrow
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "LIB_BORROW")
public class Borrow implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String bookId;//书（id）
	private String bookName;//书名
	private String bookQrcode;//图书二维码
	private String borrowedMan;//借阅人（id）
	private Date borrowedTime;//借阅时间
	private String pullOffShelf;//书架（下）（id）
	private String borrowRecordBy;//借出人（id）
	private String returnedMan;//归还人（id）
	private Date returnedTime;//归还时间
	private String returnRecordBy;//接收人（id）
	private String putOnShelf;//书架（上）（id）
	private Integer timeLimit;//期限
	private Integer reborrowTimeLimit;//续借期限
	
	private String isSendMail;//是否发送信息
	private String remarks;//
	
	private List<Book> books = new ArrayList<Book>();
	
	@Transient
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	@Column(name="BOOK_ID")
	public String getBookId(){
		return bookId;
	}
		
	public void setBookId(String bookId){
		this.bookId=bookId;
	}

	@Column(name="BORROWED_MAN")
	public String getBorrowedMan(){
		return borrowedMan;
	}
		
	public void setBorrowedMan(String borrowedMan){
		this.borrowedMan=borrowedMan;
	}

	@Column(name="BORROWED_TIME")
	public Date getBorrowedTime(){
		return borrowedTime;
	}
		
	public void setBorrowedTime(Date borrowedTime){
		this.borrowedTime=borrowedTime;
	}

	@Column(name="PULL_OFF_SHELF")
	public String getPullOffShelf(){
		return pullOffShelf;
	}
		
	public void setPullOffShelf(String pullOffShelf){
		this.pullOffShelf=pullOffShelf;
	}

	
	

	@Column(name="RETURNED_MAN")
	public String getReturnedMan(){
		return returnedMan;
	}

	public void setReturnedMan(String returnedMan){
		this.returnedMan=returnedMan;
	}
	
	@Column(name="BORROW_RECORD_BY")	
	public String getBorrowRecordBy() {
		return borrowRecordBy;
	}

	public void setBorrowRecordBy(String borrowRecordBy) {
		this.borrowRecordBy = borrowRecordBy;
	}

	@Column(name="returned_time")
	public Date getReturnedTime() {
		return returnedTime;
	}

	public void setReturnedTime(Date returnedTime) {
		this.returnedTime = returnedTime;
	}

	@Column(name="PUT_ON_SHELF")
	public String getPutOnShelf(){
		return putOnShelf;
	}

	public void setPutOnShelf(String putOnShelf){
		this.putOnShelf=putOnShelf;
	}

	@Column(name="TIME_LIMIT")
	public Integer getTimeLimit(){
		return timeLimit;
	}
		
	public void setTimeLimit(Integer timeLimit){
		this.timeLimit=timeLimit;
	}

	@Column(name="IS_SEND_MAIL")
	public String getIsSendMail(){
		return isSendMail;
	}
		
	public void setIsSendMail(String isSendMail){
		this.isSendMail=isSendMail;
	}

	@Column(name="REMARKS")
	public String getRemarks(){
		return remarks;
	}
		
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	
	@Column(name="book_name")
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name="book_qrcode")
	public String getBookQrcode() {
		return bookQrcode;
	}

	public void setBookQrcode(String bookQrcode) {
		this.bookQrcode = bookQrcode;
	}

	@Column(name="reborrow_time_limit")
	public Integer getReborrowTimeLimit() {
		return reborrowTimeLimit;
	}

	public void setReborrowTimeLimit(Integer reborrowTimeLimit) {
		this.reborrowTimeLimit = reborrowTimeLimit;
	}
	@Column(name="return_record_by")
	public String getReturnRecordBy() {
		return returnRecordBy;
	}

	public void setReturnRecordBy(String returnRecordBy) {
		this.returnRecordBy = returnRecordBy;
	}

	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "LIB_BORROW:ID="+id;

	}
}

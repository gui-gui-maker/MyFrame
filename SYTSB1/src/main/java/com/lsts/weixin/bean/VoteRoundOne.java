package com.lsts.weixin.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName VoteRoundOne
 * @JDK 1.6
 * @author 
 * @date 2
 */
@Entity
@Table(name = "TJY2_VOTE_ROUND_ONE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VoteRoundOne implements BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;//
	private String userId;
	private String vote_01;
	private String vote_02;
	private String vote_03;
	private String vote_04;
	private String vote_05;
	private String vote_06;
	private String vote_07;
	private String vote_08;
	private String vote_09;
	private String vote_10;
	private String vote_11;
	private String vote_12;
	private String vote_13;
	private String vote_14;
	private String vote_15;
	private String vote_16;
	private String vote_17;
	private String vote_18;
	private String vote_19;
	private String vote_20;
	private String vote_21;
	private String vote_22;
	private Date vote_time;
	private String status;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}
	
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "未说明";

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVote_01() {
		return vote_01;
	}

	public void setVote_01(String vote_01) {
		this.vote_01 = vote_01;
	}

	public String getVote_02() {
		return vote_02;
	}

	public void setVote_02(String vote_02) {
		this.vote_02 = vote_02;
	}

	public String getVote_03() {
		return vote_03;
	}

	public void setVote_03(String vote_03) {
		this.vote_03 = vote_03;
	}

	public String getVote_04() {
		return vote_04;
	}

	public void setVote_04(String vote_04) {
		this.vote_04 = vote_04;
	}

	public String getVote_05() {
		return vote_05;
	}

	public void setVote_05(String vote_05) {
		this.vote_05 = vote_05;
	}

	public String getVote_06() {
		return vote_06;
	}

	public void setVote_06(String vote_06) {
		this.vote_06 = vote_06;
	}

	public String getVote_07() {
		return vote_07;
	}

	public void setVote_07(String vote_07) {
		this.vote_07 = vote_07;
	}

	public String getVote_08() {
		return vote_08;
	}

	public void setVote_08(String vote_08) {
		this.vote_08 = vote_08;
	}

	public String getVote_09() {
		return vote_09;
	}

	public void setVote_09(String vote_09) {
		this.vote_09 = vote_09;
	}

	public String getVote_10() {
		return vote_10;
	}

	public void setVote_10(String vote_10) {
		this.vote_10 = vote_10;
	}

	public String getVote_11() {
		return vote_11;
	}

	public void setVote_11(String vote_11) {
		this.vote_11 = vote_11;
	}

	public String getVote_12() {
		return vote_12;
	}

	public void setVote_12(String vote_12) {
		this.vote_12 = vote_12;
	}

	public String getVote_13() {
		return vote_13;
	}

	public void setVote_13(String vote_13) {
		this.vote_13 = vote_13;
	}

	public String getVote_14() {
		return vote_14;
	}

	public void setVote_14(String vote_14) {
		this.vote_14 = vote_14;
	}

	public String getVote_15() {
		return vote_15;
	}

	public void setVote_15(String vote_15) {
		this.vote_15 = vote_15;
	}

	public String getVote_16() {
		return vote_16;
	}

	public void setVote_16(String vote_16) {
		this.vote_16 = vote_16;
	}

	public String getVote_17() {
		return vote_17;
	}

	public void setVote_17(String vote_17) {
		this.vote_17 = vote_17;
	}

	public String getVote_18() {
		return vote_18;
	}

	public void setVote_18(String vote_18) {
		this.vote_18 = vote_18;
	}

	public String getVote_19() {
		return vote_19;
	}

	public void setVote_19(String vote_19) {
		this.vote_19 = vote_19;
	}

	public String getVote_20() {
		return vote_20;
	}

	public void setVote_20(String vote_20) {
		this.vote_20 = vote_20;
	}

	public String getVote_21() {
		return vote_21;
	}

	public void setVote_21(String vote_21) {
		this.vote_21 = vote_21;
	}

	public String getVote_22() {
		return vote_22;
	}

	public void setVote_22(String vote_22) {
		this.vote_22 = vote_22;
	}

	public Date getVote_time() {
		return vote_time;
	}

	public void setVote_time(Date vote_time) {
		this.vote_time = vote_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

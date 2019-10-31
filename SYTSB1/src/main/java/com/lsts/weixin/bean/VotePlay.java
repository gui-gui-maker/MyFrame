package com.lsts.weixin.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/** 
 * @author 作者 PingZhou
 * @JDK 1.6
 * @version 创建时间：2016年2月25日 下午4:01:34 
 * 节目投票
 */
@Entity
@Table(name="TJY2_VOTE_PLAY")
public class VotePlay implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;  // ID
	private String userid;  // 投票人ID
	private String vote_01;  // 候选节目01投票结果
	private String vote_02;  // 
	private String vote_03;  // 
	private String vote_04;  // 
	private String vote_05;  // 
	private String vote_06;  // 
	private String vote_07;  // 
	private String vote_08;  // 
	private String vote_09;  // 
	private String vote_10;  // 
	private String vote_11;  // 
	private String vote_12;  // 候选节目12投票结果
	private String vote_time;  // 投票时间
	private String status;  // 是否有效票
	private String username;  // N
	private String phone;  // N
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getVote_time() {
		return vote_time;
	}

	public void setVote_time(String vote_time) {
		this.vote_time = vote_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

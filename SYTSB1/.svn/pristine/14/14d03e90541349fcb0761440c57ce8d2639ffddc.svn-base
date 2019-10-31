package com.lsts.weixin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;
/** 
 * @author 作者 QuincyXu
 * @JDK 1.7
 * @version 创建时间：2016年8月13日09:52:17
 * 最喜爱的图标投票
 */
@Entity
@Table(name = "TJY2_VOTE_FAVORITEICO")
public class VoteFavoriteIco implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//ID

    private String userid;//投票人ID

    private java.math.BigDecimal vote01;//候选图标01投票结果

    private java.math.BigDecimal vote02;//${columnb.remarks}

    private java.math.BigDecimal vote03;//候选图标03投票结果

    private java.util.Date voteTime;//投票时间

    private String status;//是否有效票

    private String username;//N

    private String phone;//N

    public void setId(String value){
        this.id = value;
    }
    public void setUserid(String value){
        this.userid = value;
    }
    public void setVote01(java.math.BigDecimal value){
        this.vote01 = value;
    }
    public void setVote02(java.math.BigDecimal value){
        this.vote02 = value;
    }
    public void setVote03(java.math.BigDecimal value){
        this.vote03 = value;
    }
    public void setVoteTime(java.util.Date value){
        this.voteTime = value;
    }
    public void setStatus(String value){
        this.status = value;
    }
    public void setUsername(String value){
        this.username = value;
    }
    public void setPhone(String value){
        this.phone = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="USERID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUserid(){
        return this.userid;
    }
    @Column(name ="VOTE_01",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getVote01(){
        return this.vote01;
    }
    @Column(name ="VOTE_02",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getVote02(){
        return this.vote02;
    }
    @Column(name ="VOTE_03",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getVote03(){
        return this.vote03;
    }
    @Column(name ="VOTE_TIME",unique=false,nullable=true,insertable=true,updatable=true,length=7)
    public java.util.Date getVoteTime(){
        return this.voteTime;
    }
    @Column(name ="STATUS",unique=false,nullable=true,insertable=true,updatable=true,length=1)
    public String getStatus(){
        return this.status;
    }
    @Column(name ="USERNAME",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getUsername(){
        return this.username;
    }
    @Column(name ="PHONE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getPhone(){
        return this.phone;
    }


} 

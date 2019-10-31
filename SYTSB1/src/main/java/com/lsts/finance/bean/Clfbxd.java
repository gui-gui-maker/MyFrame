package com.lsts.finance.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CLFBXD")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Clfbxd implements BaseEntity{
	
	
	private String handleName;
	private String abolishName;//作废处理人
	private String abolish;//作废状态

	private java.util.Date abolishTime; //作废时间

	private String identifier;//编号
    private String clCcid;
	private String handleId;
	
	private java.util.Date handleTime;
	
	private String handleOPinion;

    private String id;//id
    
    private String accessory;//附件

    private String clDw;//单位
    
    private String unitId;
    
    private String departmentId;//部门ID

    private String department;//部门

    private java.util.Date clSj;//登记时间

    private String clSy;//事由

    private String clCcr;//出差人

    private String clQdR1;//起点 日

    private String clQdR2;//${columnb.remarks}

    private String clQdR3;//${columnb.remarks}

    private String clQdR4;//${columnb.remarks}
    
    private String clQdR5;//${columnb.remarks}

    private String clQdDm1;//起点 地名

    private String clQdDm2;//${columnb.remarks}

    private String clQdDm3;//${columnb.remarks}

    private String clQdDm4;//${columnb.remarks}
    
    private String clQdDm5;//${columnb.remarks}

    private String clZdR1;//终点 日

    private String clZdR2;//${columnb.remarks}

    private String clZdR3;//${columnb.remarks}

    private String clZdR4;//${columnb.remarks}
    
    private String clZdR5;//${columnb.remarks}

    private String clZdDd1;//终点 地点

    private String clZdDd2;//${columnb.remarks}

    private String clZdDd3;//${columnb.remarks}

    private String clZdDd4;//${columnb.remarks}
    
    private String clZdDd5;//${columnb.remarks}

    private java.math.BigDecimal clJtfDj1;//交通费 单据 张

    private java.math.BigDecimal clJtfDj2;//${columnb.remarks}

    private java.math.BigDecimal clJtfDj3;//${columnb.remarks}

    private java.math.BigDecimal clJtfDj4;//${columnb.remarks}
    
    private java.math.BigDecimal clJtfDj5;//${columnb.remarks}

    private java.math.BigDecimal clJtfJe1;//交通费 金额

    private java.math.BigDecimal clJtfJe2;//${columnb.remarks}

    private java.math.BigDecimal clJtfJe3;//${columnb.remarks}

    private java.math.BigDecimal clJtfJe4;//${columnb.remarks}
    
    private java.math.BigDecimal clJtfJe5;//${columnb.remarks}

    private java.math.BigDecimal clZsfDj1;//住宿费 单据 张

    private java.math.BigDecimal clZsfDj2;//${columnb.remarks}

    private java.math.BigDecimal clZsfDj3;//${columnb.remarks}

    private java.math.BigDecimal clZsfDj4;//${columnb.remarks}
    
    private java.math.BigDecimal clZsfDj5;//${columnb.remarks}

    private java.math.BigDecimal clZsfJe1;//住宿费 金额 张

    private java.math.BigDecimal clZsfJe2;//${columnb.remarks}

    private java.math.BigDecimal clZsfJe3;//${columnb.remarks}

    private java.math.BigDecimal clZsfJe4;//${columnb.remarks}
    
    private java.math.BigDecimal clZsfJe5;//${columnb.remarks}

    private java.math.BigDecimal clHsbzTs1;//伙食补助 天数

    private java.math.BigDecimal clHsbzTs2;//${columnb.remarks}

    private java.math.BigDecimal clHsbzTs3;//${columnb.remarks}

    private java.math.BigDecimal clHsbzTs4;//${columnb.remarks}
    
    private java.math.BigDecimal clHsbzTs5;//${columnb.remarks}

    private java.math.BigDecimal clHsbzJe1;//伙食补助 金额

    private java.math.BigDecimal clHsbzJe2;//${columnb.remarks}

    private java.math.BigDecimal clHsbzJe3;//${columnb.remarks}

    private java.math.BigDecimal clHsbzJe4;//${columnb.remarks}
    
    private java.math.BigDecimal clHsbzJe5;//${columnb.remarks}

    private java.math.BigDecimal clGzfTs1;//公杂费 天数

    private java.math.BigDecimal clGzfTs2;//${columnb.remarks}

    private java.math.BigDecimal clGzfTs3;//${columnb.remarks}

    private java.math.BigDecimal clGzfTs4;//${columnb.remarks}
    
    private java.math.BigDecimal clGzfTs5;//${columnb.remarks}

    private java.math.BigDecimal clGzfJe1;//公杂费 金额

    private java.math.BigDecimal clGzfJe2;//${columnb.remarks}

    private java.math.BigDecimal clGzfJe3;//${columnb.remarks}

    private java.math.BigDecimal clGzfJe4;//${columnb.remarks}
    
    private java.math.BigDecimal clGzfJe5;//${columnb.remarks}

    private java.math.BigDecimal clQtDj1;//其他 单据 张

    private java.math.BigDecimal clQtDj2;//${columnb.remarks}

    private java.math.BigDecimal clQtDj3;//${columnb.remarks}

    private java.math.BigDecimal clQtDj4;//${columnb.remarks}
    
    private java.math.BigDecimal clQtDj5;//${columnb.remarks}

    private java.math.BigDecimal clQtJe1;//其他  金额

    private java.math.BigDecimal clQtJe2;//${columnb.remarks}

    private java.math.BigDecimal clQtJe3;//${columnb.remarks}

    private java.math.BigDecimal clQtJe4;//${columnb.remarks}
    
    private java.math.BigDecimal clQtJe5;//${columnb.remarks}

    private java.math.BigDecimal clJexj1;//金额小计

    private java.math.BigDecimal clJexj2;//${columnb.remarks}

    private java.math.BigDecimal clJexj3;//${columnb.remarks}

    private java.math.BigDecimal clJexj4;//${columnb.remarks}
    
    private java.math.BigDecimal clJexj5;

    private java.math.BigDecimal clHjJtfDj;//合计 交通费 单据 张

    private java.math.BigDecimal clHjJtfJe;//合计 交通费 金额

    private java.math.BigDecimal clHjZsfDj;//合计 住宿费 单据 张

    private java.math.BigDecimal clHjZsfJe;//合计住宿费 金额

    private java.math.BigDecimal clHjHsbzTs;//合计 伙食补助 天数

    private java.math.BigDecimal clHjHsbzJe;//合计 伙食补助 金额

    private java.math.BigDecimal clHjGzfTs;//合计 公杂费 天数

    private java.math.BigDecimal clHjGzfJe;//合计 公杂费 金额

    private java.math.BigDecimal clHjQtDj;//合计 其他 单据 张

    private java.math.BigDecimal clHjQtJe;//合计 其他 金额 

    private java.math.BigDecimal clHjJexj;//合计 金额小计合

    private String clHjRmbdx;//合计  大写

    private String clXj;//现金

    private String clGwk;//公务卡

    private String clZz;//转账
    
    private String clCz;//转账

    private String user;//${columnb.remarks}

    private java.util.Date userDate;//${columnb.remarks}

    private String userId;//${columnb.remarks}

    private String statue = "REGISTER";//${columnb.remarks}
    
    private String clQdR1Year;//起点 年份
    
    private String clQdR2Year;//起点 年份
    
    private String clQdR3Year;//起点 年份
    
    private String clQdR4Year;//起点 年份
    
    private String clQdR5Year;//起点 年份
    
    private String clZdR1Year;//终点 年份
    
    private String clZdR2Year;//终点 年份
    
    private String clZdR3Year;//终点 年份
    
    private String clZdR4Year;//终点 年份
    
    private String clZdR5Year;//终点 年份

    private String clType;//差旅费类型，0：普通，1：车辆，2：科研
    
    private String itemId;//派车单id/科研ID
    
    private String itemName;//派车单号/科研名
    
  private String projectId;//科研ID
    
    private String projectName;//科研name
    
    private String bxQrId;//处理人ID报销确认
    private String bxQrName;//处理人报销确认
    private java.util.Date bxQrTime;//处理时间报销确认

    public void setId(String value){
        this.id = value;
    }
    public void setClDw(String value){
        this.clDw = value;
    }
    public void setHandleName(String value){
        this.handleName = value;
    }
    public void setHandleId(String value){
        this.handleId = value;
    }
    public void setHandleOPinion(String value){
        this.handleOPinion = value;
    }
    public void setDepartment(String value){
        this.department = value;
    }
    public void setDepartmentId(String value){
        this.departmentId = value;
    }
    public void setClSj(Date value){
        this.clSj = value;
    }
    public void setClSy(String value){
        this.clSy = value;
    }
    public void setClCcr(String value){
        this.clCcr = value;
    }
  
  
    public void setClQdR1(String value){
        this.clQdR1 = value;
    }
    public void setClQdR2(String value){
        this.clQdR2 = value;
    }
    public void setClQdR3(String value){
        this.clQdR3 = value;
    }
    public void setClQdR4(String value){
        this.clQdR4 = value;
    }
    public void setClQdDm1(String value){
        this.clQdDm1 = value;
    }
    public void setHandleTime(java.util.Date value){
        this.handleTime = value;
    }
    public void setClQdDm2(String value){
        this.clQdDm2 = value;
    }
    public void setClQdDm3(String value){
        this.clQdDm3 = value;
    }
    public void setClQdDm4(String value){
        this.clQdDm4 = value;
    }
  
    public void setClCcid(String value){
        this.clCcid = value;
    }
    public void setClZdR1(String value){
        this.clZdR1 = value;
    }
    public void setClZdR2(String value){
        this.clZdR2 = value;
    }
    public void setClZdR3(String value){
        this.clZdR3 = value;
    }
    public void setClZdR4(String value){
        this.clZdR4 = value;
    }
    public void setClZdDd1(String value){
        this.clZdDd1 = value;
    }
    public void setUnitId(String value){
        this.unitId = value;
    }
    
    
    public void setClZdDd2(String value){
        this.clZdDd2 = value;
    }
    public void setClZdDd3(String value){
        this.clZdDd3 = value;
    }
    public void setClZdDd4(String value){
        this.clZdDd4 = value;
    }
    public void setClJtfDj1(java.math.BigDecimal value){
        this.clJtfDj1 = value;
    }
    public void setClJtfDj2(java.math.BigDecimal value){
        this.clJtfDj2 = value;
    }
    public void setClJtfDj3(java.math.BigDecimal value){
        this.clJtfDj3 = value;
    }
    public void setClJtfDj4(java.math.BigDecimal value){
        this.clJtfDj4 = value;
    }
    public void setClJtfJe1(java.math.BigDecimal value){
        this.clJtfJe1 = value;
    }
    public void setClJtfJe2(java.math.BigDecimal value){
        this.clJtfJe2 = value;
    }
    public void setClJtfJe3(java.math.BigDecimal value){
        this.clJtfJe3 = value;
    }
    public void setClJtfJe4(java.math.BigDecimal value){
        this.clJtfJe4 = value;
    }
    public void setAccessory(String value){
        this.accessory = value;
    }
    public void setClZsfDj1(java.math.BigDecimal value){
        this.clZsfDj1 = value;
    }
    public void setClZsfDj2(java.math.BigDecimal value){
        this.clZsfDj2 = value;
    }
    public void setClZsfDj3(java.math.BigDecimal value){
        this.clZsfDj3 = value;
    }
    public void setClZsfDj4(java.math.BigDecimal value){
        this.clZsfDj4 = value;
    }
    public void setClZsfJe1(java.math.BigDecimal value){
        this.clZsfJe1 = value;
    }
    public void setClZsfJe2(java.math.BigDecimal value){
        this.clZsfJe2 = value;
    }
    public void setClZsfJe3(java.math.BigDecimal value){
        this.clZsfJe3 = value;
    }
    public void setClZsfJe4(java.math.BigDecimal value){
        this.clZsfJe4 = value;
    }
    public void setClHsbzTs1(java.math.BigDecimal value){
        this.clHsbzTs1 = value;
    }
    public void setClHsbzTs2(java.math.BigDecimal value){
        this.clHsbzTs2 = value;
    }
    public void setClHsbzTs3(java.math.BigDecimal value){
        this.clHsbzTs3 = value;
    }
    public void setClHsbzTs4(java.math.BigDecimal value){
        this.clHsbzTs4 = value;
    }
    public void setClHsbzJe1(java.math.BigDecimal value){
        this.clHsbzJe1 = value;
    }
    public void setClHsbzJe2(java.math.BigDecimal value){
        this.clHsbzJe2 = value;
    }
    public void setClHsbzJe3(java.math.BigDecimal value){
        this.clHsbzJe3 = value;
    }
    public void setClHsbzJe4(java.math.BigDecimal value){
        this.clHsbzJe4 = value;
    }
    public void setClGzfTs1(java.math.BigDecimal value){
        this.clGzfTs1 = value;
    }
    public void setClGzfTs2(java.math.BigDecimal value){
        this.clGzfTs2 = value;
    }
    public void setClGzfTs3(java.math.BigDecimal value){
        this.clGzfTs3 = value;
    }
    public void setClGzfTs4(java.math.BigDecimal value){
        this.clGzfTs4 = value;
    }
    public void setClGzfJe1(java.math.BigDecimal value){
        this.clGzfJe1 = value;
    }
    public void setClGzfJe2(java.math.BigDecimal value){
        this.clGzfJe2 = value;
    }
    public void setClGzfJe3(java.math.BigDecimal value){
        this.clGzfJe3 = value;
    }
    public void setClGzfJe4(java.math.BigDecimal value){
        this.clGzfJe4 = value;
    }
    public void setClQtDj1(java.math.BigDecimal value){
        this.clQtDj1 = value;
    }
    public void setClQtDj2(java.math.BigDecimal value){
        this.clQtDj2 = value;
    }
    public void setClQtDj3(java.math.BigDecimal value){
        this.clQtDj3 = value;
    }
    public void setClQtDj4(java.math.BigDecimal value){
        this.clQtDj4 = value;
    }
    public void setClQtJe1(java.math.BigDecimal value){
        this.clQtJe1 = value;
    }
    public void setClQtJe2(java.math.BigDecimal value){
        this.clQtJe2 = value;
    }
    public void setClQtJe3(java.math.BigDecimal value){
        this.clQtJe3 = value;
    }
    public void setClQtJe4(java.math.BigDecimal value){
        this.clQtJe4 = value;
    }
    public void setClJexj1(java.math.BigDecimal value){
        this.clJexj1 = value;
    }
    public void setClJexj2(java.math.BigDecimal value){
        this.clJexj2 = value;
    }
    public void setClJexj3(java.math.BigDecimal value){
        this.clJexj3 = value;
    }
    public void setClJexj4(java.math.BigDecimal value){
        this.clJexj4 = value;
    }
    public void setClHjJtfDj(java.math.BigDecimal value){
        this.clHjJtfDj = value;
    }
    public void setClHjJtfJe(java.math.BigDecimal value){
        this.clHjJtfJe = value;
    }
    public void setClHjZsfDj(java.math.BigDecimal value){
        this.clHjZsfDj = value;
    }
    public void setClHjZsfJe(java.math.BigDecimal value){
        this.clHjZsfJe = value;
    }
    public void setClHjHsbzTs(java.math.BigDecimal value){
        this.clHjHsbzTs = value;
    }
    public void setClHjHsbzJe(java.math.BigDecimal value){
        this.clHjHsbzJe = value;
    }
    public void setClHjGzfTs(java.math.BigDecimal value){
        this.clHjGzfTs = value;
    }
    public void setClHjGzfJe(java.math.BigDecimal value){
        this.clHjGzfJe = value;
    }
    public void setClHjQtDj(java.math.BigDecimal value){
        this.clHjQtDj = value;
    }
    public void setClHjQtJe(java.math.BigDecimal value){
        this.clHjQtJe = value;
    }
    public void setClHjJexj(java.math.BigDecimal value){
        this.clHjJexj = value;
    }
    public void setClHjRmbdx(String value){
        this.clHjRmbdx = value;
    }
    public void setClXj(String value){
        this.clXj = value;
    }
    public void setClGwk(String value){
        this.clGwk = value;
    }
    public void setClZz(String value){
        this.clZz = value;
    }
    public void setClCz(String value){
        this.clCz = value;
    }
    public void setUser(String value){
        this.user = value;
    }
    public void setUserDate(Date value){
        this.userDate = value;
    }
    public void setUserId(String value){
        this.userId = value;
    }
    public void setStatue(String value){
        this.statue = value;
    }
    public void setClQdR1Year(String value){
        this.clQdR1Year = value;
    }
    public void setClQdR2Year(String value){
        this.clQdR2Year = value;
    }
    public void setClQdR3Year(String value){
        this.clQdR3Year = value;
    }
    public void setClQdR4Year(String value){
        this.clQdR4Year = value;
    }
    public void setClZdR1Year(String value){
        this.clZdR1Year = value;
    }
    public void setClZdR2Year(String value){
        this.clZdR2Year = value;
    }
    public void setClZdR3Year(String value){
        this.clZdR3Year = value;
    }
    public void setClZdR4Year(String value){
        this.clZdR4Year = value;
    }
    public void setClType(String value){
        this.clType = value;
    }
    public void setItemId(String value){
        this.itemId = value;
    }
    public void setItemName(String value){
        this.itemName = value;
    }
    public void setBxQrId(String value){
        this.bxQrId = value;
    }
    public void setBxQrName(String value){
        this.bxQrName = value;
    }
    public void setBxQrTime(java.util.Date value){
        this.bxQrTime = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true)
    public String getId(){
        return this.id;
    }
    @Column(name ="CL_DW",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClDw(){
        return this.clDw;
    }
    @Column(name ="UNIT_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUnitId(){
            return this.unitId;
    }
    @Column(name ="DEPARTMENT",unique=false,nullable=true,insertable=true,updatable=true)
    public String getDepartment(){
        return this.department;
    }
    @Column(name ="DEPARTMENT_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getDepartmentId(){
        return this.departmentId;
    }
    @Column(name ="CL_SJ",unique=false,nullable=true,insertable=true,updatable=true)
    public Date getClSj(){
        return this.clSj;
    }
    @Column(name ="ACCESSORY",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAccessory(){
        return this.accessory;
    }
    @Column(name ="CL_SY",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClSy(){
        return this.clSy;
    }
    @Column(name ="CL_CCR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClCcr(){
        return this.clCcr;
    }
   
    @Column(name ="CL_QD_R1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR1(){
        return this.clQdR1;
    }
    @Column(name ="CL_QD_R2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR2(){
        return this.clQdR2;
    }
    @Column(name ="CL_QD_R3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR3(){
        return this.clQdR3;
    }
    @Column(name ="CL_QD_R4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR4(){
        return this.clQdR4;
    }
    @Column(name ="CL_QD_DM1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdDm1(){
        return this.clQdDm1;
    }
    @Column(name ="CL_QD_DM2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdDm2(){
        return this.clQdDm2;
    }
    @Column(name ="CL_QD_DM3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdDm3(){
        return this.clQdDm3;
    }
    @Column(name ="CL_QD_DM4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdDm4(){
        return this.clQdDm4;
    }
 
    @Column(name ="CL_ZD_R1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR1(){
        return this.clZdR1;
    }
    @Column(name ="CL_ZD_R2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR2(){
        return this.clZdR2;
    }
    @Column(name ="CL_ZD_R3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR3(){
        return this.clZdR3;
    }
    @Column(name ="CL_ZD_R4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR4(){
        return this.clZdR4;
    }
    @Column(name ="CL_ZD_DD1",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdDd1(){
        return this.clZdDd1;
    }
    @Column(name ="CL_ZD_DD2",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdDd2(){
        return this.clZdDd2;
    }
    @Column(name ="CL_ZD_DD3",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdDd3(){
        return this.clZdDd3;
    }
    @Column(name ="CL_ZD_DD4",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdDd4(){
        return this.clZdDd4;
    }
    @Column(name ="CL_JTF_DJ1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfDj1(){
        return this.clJtfDj1;
    }
    @Column(name ="CL_JTF_DJ2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfDj2(){
        return this.clJtfDj2;
    }
    @Column(name ="CL_JTF_DJ3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfDj3(){
        return this.clJtfDj3;
    }
    @Column(name ="CL_JTF_DJ4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfDj4(){
        return this.clJtfDj4;
    }
    @Column(name ="CL_JTF_JE1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfJe1(){
        return this.clJtfJe1;
    }
    @Column(name ="CL_JTF_JE2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfJe2(){
        return this.clJtfJe2;
    }
    @Column(name ="CL_JTF_JE3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfJe3(){
        return this.clJtfJe3;
    }
    @Column(name ="CL_JTF_JE4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJtfJe4(){
        return this.clJtfJe4;
    }
    @Column(name ="CL_ZSF_DJ1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfDj1(){
        return this.clZsfDj1;
    }
    @Column(name ="CL_ZSF_DJ2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfDj2(){
        return this.clZsfDj2;
    }
    @Column(name ="CL_ZSF_DJ3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfDj3(){
        return this.clZsfDj3;
    }
    @Column(name ="CL_ZSF_DJ4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfDj4(){
        return this.clZsfDj4;
    }
    @Column(name ="CL_ZSF_JE1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfJe1(){
        return this.clZsfJe1;
    }
    @Column(name ="CL_ZSF_JE2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfJe2(){
        return this.clZsfJe2;
    }
    @Column(name ="CL_ZSF_JE3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfJe3(){
        return this.clZsfJe3;
    }
    @Column(name ="CL_ZSF_JE4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClZsfJe4(){
        return this.clZsfJe4;
    }
    @Column(name ="CL_HSBZ_TS1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzTs1(){
        return this.clHsbzTs1;
    }
    @Column(name ="CL_HSBZ_TS2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzTs2(){
        return this.clHsbzTs2;
    }
    @Column(name ="CL_HSBZ_TS3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzTs3(){
        return this.clHsbzTs3;
    }
    @Column(name ="CL_HSBZ_TS4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzTs4(){
        return this.clHsbzTs4;
    }
    @Column(name ="CL_HSBZ_JE1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzJe1(){
        return this.clHsbzJe1;
    }
    @Column(name ="CL_HSBZ_JE2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzJe2(){
        return this.clHsbzJe2;
    }
    @Column(name ="CL_HSBZ_JE3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzJe3(){
        return this.clHsbzJe3;
    }
    @Column(name ="CL_HSBZ_JE4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHsbzJe4(){
        return this.clHsbzJe4;
    }
    @Column(name ="CL_GZF_TS1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfTs1(){
        return this.clGzfTs1;
    }
    @Column(name ="CL_GZF_TS2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfTs2(){
        return this.clGzfTs2;
    }
    @Column(name ="CL_GZF_TS3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfTs3(){
        return this.clGzfTs3;
    }
    @Column(name ="CL_GZF_TS4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfTs4(){
        return this.clGzfTs4;
    }
    @Column(name ="CL_GZF_JE1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfJe1(){
        return this.clGzfJe1;
    }
    @Column(name ="CL_GZF_JE2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfJe2(){
        return this.clGzfJe2;
    }
    @Column(name ="CL_GZF_JE3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfJe3(){
        return this.clGzfJe3;
    }
    @Column(name ="CL_GZF_JE4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClGzfJe4(){
        return this.clGzfJe4;
    }
    @Column(name ="CL_QT_DJ1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtDj1(){
        return this.clQtDj1;
    }
    @Column(name ="CL_QT_DJ2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtDj2(){
        return this.clQtDj2;
    }
    @Column(name ="CL_QT_DJ3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtDj3(){
        return this.clQtDj3;
    }
    @Column(name ="CL_QT_DJ4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtDj4(){
        return this.clQtDj4;
    }
    @Column(name ="CL_QT_JE1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtJe1(){
        return this.clQtJe1;
    }
    @Column(name ="CL_QT_JE2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtJe2(){
        return this.clQtJe2;
    }
    @Column(name ="CL_QT_JE3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtJe3(){
        return this.clQtJe3;
    }
    @Column(name ="CL_QT_JE4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClQtJe4(){
        return this.clQtJe4;
    }
    @Column(name ="CL_JEXJ1",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJexj1(){
        return this.clJexj1;
    }
    @Column(name ="CL_JEXJ2",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJexj2(){
        return this.clJexj2;
    }
    @Column(name ="CL_JEXJ3",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJexj3(){
        return this.clJexj3;
    }
    @Column(name ="CL_JEXJ4",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClJexj4(){
        return this.clJexj4;
    }
    @Column(name ="CL_HJ_JTF_DJ",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjJtfDj(){
        return this.clHjJtfDj;
    }
    @Column(name ="CL_HJ_JTF_JE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjJtfJe(){
        return this.clHjJtfJe;
    }
    @Column(name ="CL_HJ_ZSF_DJ",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjZsfDj(){
        return this.clHjZsfDj;
    }
    @Column(name ="CL_HJ_ZSF_JE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjZsfJe(){
        return this.clHjZsfJe;
    }
    @Column(name ="CL_HJ_HSBZ_TS",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjHsbzTs(){
        return this.clHjHsbzTs;
    }
    @Column(name ="CL_HJ_HSBZ_JE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjHsbzJe(){
        return this.clHjHsbzJe;
    }
    @Column(name ="CL_HJ_GZF_TS",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjGzfTs(){
        return this.clHjGzfTs;
    }
    @Column(name ="CL_HJ_GZF_JE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjGzfJe(){
        return this.clHjGzfJe;
    }
    @Column(name ="CL_HJ_QT_DJ",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjQtDj(){
        return this.clHjQtDj;
    }
    @Column(name ="CL_HJ_QT_JE",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjQtJe(){
        return this.clHjQtJe;
    }
    @Column(name ="CL_HJ_JEXJ",unique=false,nullable=true,insertable=true,updatable=true)
    public java.math.BigDecimal getClHjJexj(){
        return this.clHjJexj;
    }
    @Column(name ="CL_HJ_RMBDX",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClHjRmbdx(){
        return this.clHjRmbdx;
    }
    @Column(name ="CL_XJ",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClXj(){
        return this.clXj;
    }
    @Column(name ="CL_GWK",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClGwk(){
        return this.clGwk;
    }
    @Column(name ="CL_ZZ",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZz(){
        return this.clZz;
    }
    @Column(name ="CL_CZ",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClCz(){
        return this.clCz;
    }
    @Column(name ="USER_",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUser(){
        return this.user;
    }
    @Column(name ="USER_DATE",unique=false,nullable=true,insertable=true,updatable=true)
    public Date getUserDate(){
        return this.userDate;
    }
    @Column(name ="USER_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getUserId(){
        return this.userId;
    }
    @Column(name ="STATUE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getStatue(){
        return this.statue;
    }
    @Column(name ="CL_CCID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClCcid(){
        return this.clCcid;
    }
    @Column(name ="HANDLE_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getHandleTime(){
        return this.handleTime;
    }
    @Column(name ="HANDLE_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleId(){
        return this.handleId;
    }
    @Column(name ="HANDLE_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleName(){
        return this.handleName;
    }
    @Column(name ="HANDLE_OPINION",unique=false,nullable=true,insertable=true,updatable=true)
    public String getHandleOPinion(){
        return this.handleOPinion;
    }
    
    public void setIdentifier(String value){
        this.identifier = value;
    }
    @Column(name ="IDENTIFIER",unique=false,nullable=true,insertable=true,updatable=true)
    public String getIdentifier(){
        return this.identifier;
    }
    public void setAbolishTime(java.util.Date value){
        this.abolishTime = value;
    }
    @Column(name ="ABOLISH_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getAbolishTime(){
        return this.abolishTime;
    }
    public void setAbolish(String value){
        this.abolish = value;
    }
    @Column(name ="ABOLISH",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAbolish(){
        return this.abolish;
    }
    @Column(name ="ABOLISH_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getAbolishName(){
        return this.abolishName;
    }
    @Column(name ="CL_QD_R1_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR1Year(){
        return this.clQdR1Year;
    }
    @Column(name ="CL_QD_R2_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR2Year(){
        return this.clQdR2Year;
    }
    @Column(name ="CL_QD_R3_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR3Year(){
        return this.clQdR3Year;
    }
    @Column(name ="CL_QD_R4_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClQdR4Year(){
        return this.clQdR4Year;
    }
    @Column(name ="CL_ZD_R1_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR1Year(){
        return this.clZdR1Year;
    }
    @Column(name ="CL_ZD_R2_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR2Year(){
        return this.clZdR2Year;
    }
    @Column(name ="CL_ZD_R3_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR3Year(){
        return this.clZdR3Year;
    }
    @Column(name ="CL_ZD_R4_YEAR",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClZdR4Year(){
        return this.clZdR4Year;
    }
    @Column(name ="CL_TYPE",unique=false,nullable=true,insertable=true,updatable=true)
    public String getClType(){
        return this.clType;
    }
    @Column(name ="ITEM_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getItemId(){
        return this.itemId;
    }
    @Column(name ="ITEM_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getItemName(){
        return this.itemName;
    }
    @Column(name ="BX_QR_TIME",unique=false,nullable=true,insertable=true,updatable=true)
    public java.util.Date getBxQrTime(){
        return this.bxQrTime;
    }
    @Column(name ="BX_QR_ID",unique=false,nullable=true,insertable=true,updatable=true)
    public String getBxQrId(){
        return this.bxQrId;
    }
    @Column(name ="BX_QR_NAME",unique=false,nullable=true,insertable=true,updatable=true)
    public String getBxQrName(){
        return this.bxQrName;
    }
    public void setAbolishName(String value){
        this.abolishName = value;
    }
    
    public String getClQdR5() {
		return clQdR5;
	}
	public void setClQdR5(String clQdR5) {
		this.clQdR5 = clQdR5;
	}
	public String getClQdDm5() {
		return clQdDm5;
	}
	public void setClQdDm5(String clQdDm5) {
		this.clQdDm5 = clQdDm5;
	}
	public String getClZdR5() {
		return clZdR5;
	}
	public void setClZdR5(String clZdR5) {
		this.clZdR5 = clZdR5;
	}
	public String getClZdDd5() {
		return clZdDd5;
	}
	public void setClZdDd5(String clZdDd5) {
		this.clZdDd5 = clZdDd5;
	}
	public java.math.BigDecimal getClJtfDj5() {
		return clJtfDj5;
	}
	public void setClJtfDj5(java.math.BigDecimal clJtfDj5) {
		this.clJtfDj5 = clJtfDj5;
	}
	public java.math.BigDecimal getClZsfDj5() {
		return clZsfDj5;
	}
	public void setClZsfDj5(java.math.BigDecimal clZsfDj5) {
		this.clZsfDj5 = clZsfDj5;
	}
	public java.math.BigDecimal getClZsfJe5() {
		return clZsfJe5;
	}
	public void setClZsfJe5(java.math.BigDecimal clZsfJe5) {
		this.clZsfJe5 = clZsfJe5;
	}
	public java.math.BigDecimal getClHsbzTs5() {
		return clHsbzTs5;
	}
	public void setClHsbzTs5(java.math.BigDecimal clHsbzTs5) {
		this.clHsbzTs5 = clHsbzTs5;
	}
	public java.math.BigDecimal getClHsbzJe5() {
		return clHsbzJe5;
	}
	public void setClHsbzJe5(java.math.BigDecimal clHsbzJe5) {
		this.clHsbzJe5 = clHsbzJe5;
	}
	public java.math.BigDecimal getClGzfTs5() {
		return clGzfTs5;
	}
	public void setClGzfTs5(java.math.BigDecimal clGzfTs5) {
		this.clGzfTs5 = clGzfTs5;
	}
	public java.math.BigDecimal getClGzfJe5() {
		return clGzfJe5;
	}
	public void setClGzfJe5(java.math.BigDecimal clGzfJe5) {
		this.clGzfJe5 = clGzfJe5;
	}
	public java.math.BigDecimal getClQtDj5() {
		return clQtDj5;
	}
	public void setClQtDj5(java.math.BigDecimal clQtDj5) {
		this.clQtDj5 = clQtDj5;
	}
	public java.math.BigDecimal getClQtJe5() {
		return clQtJe5;
	}
	public void setClQtJe5(java.math.BigDecimal clQtJe5) {
		this.clQtJe5 = clQtJe5;
	}
	public java.math.BigDecimal getClJexj5() {
		return clJexj5;
	}
	public void setClJexj5(java.math.BigDecimal clJexj5) {
		this.clJexj5 = clJexj5;
	}
	public String getClQdR5Year() {
		return clQdR5Year;
	}
	public void setClQdR5Year(String clQdR5Year) {
		this.clQdR5Year = clQdR5Year;
	}
	public String getClZdR5Year() {
		return clZdR5Year;
	}
	public void setClZdR5Year(String clZdR5Year) {
		this.clZdR5Year = clZdR5Year;
	}
	
	public java.math.BigDecimal getClJtfJe5() {
		return clJtfJe5;
	}
	public void setClJtfJe5(java.math.BigDecimal clJtfJe5) {
		this.clJtfJe5 = clJtfJe5;
	}
	
	
	@Column(name ="PROJECT_ID",unique=false,nullable=true,insertable=true,updatable=true)
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Column(name ="PROJECT_NAME",unique=false,nullable=true,insertable=true,updatable=true)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Map<String,String> beanToMap(){
    	Map<String,String> map = new HashMap<String,String>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	map.put("ID",id);
    	map.put("CL_DW",clDw);
    	map.put("DEPARTMENT",department);
    	map.put("CL_SJ",clSj!=null?sdf.format(clSj):"");
    	map.put("CL_SY",clSy);
    	map.put("CL_CCR",clCcr);
    	map.put("CL_QD_R1",clQdR1);
    	map.put("CL_QD_R2",clQdR2);
    	map.put("CL_QD_R3",clQdR3);
    	map.put("CL_QD_R4",clQdR4);
    	map.put("CL_QD_DM1",clQdDm1);
    	map.put("CL_QD_DM2",clQdDm2);
    	map.put("CL_QD_DM3",clQdDm3);
    	map.put("CL_QD_DM4",clQdDm4);
    	map.put("CL_ZD_R1",clZdR1);
    	map.put("CL_ZD_R2",clZdR2);
    	map.put("CL_ZD_R3",clZdR3);
    	map.put("CL_ZD_R4",clZdR4);
    	map.put("CL_ZD_DD1",clZdDd1);
    	map.put("CL_ZD_DD2",clZdDd2);
    	map.put("CL_ZD_DD3",clZdDd3);
    	map.put("CL_ZD_DD4",clZdDd4);
    	map.put("CL_JTF_DJ1",clJtfDj1!=null?clJtfDj1.toString():"");
    	map.put("CL_JTF_DJ2",clJtfDj2!=null?clJtfDj2.toString():"");
    	map.put("CL_JTF_DJ3",clJtfDj3!=null?clJtfDj3.toString():"");
    	map.put("CL_JTF_DJ4",clJtfDj4!=null?clJtfDj4.toString():"");
    	map.put("CL_JTF_JE1",clJtfJe1!=null?clJtfJe1.toString():"");
    	map.put("CL_JTF_JE2",clJtfJe2!=null?clJtfJe2.toString():"");
    	map.put("CL_JTF_JE3",clJtfJe3!=null?clJtfJe3.toString():"");
    	map.put("CL_JTF_JE4",clJtfJe4!=null?clJtfJe4.toString():"");
    	map.put("CL_ZSF_DJ1",clZsfDj1!=null?clZsfDj1.toString():"");
    	map.put("CL_ZSF_DJ2",clZsfDj2!=null?clZsfDj2.toString():"");
    	map.put("CL_ZSF_DJ3",clZsfDj3!=null?clZsfDj3.toString():"");
    	map.put("CL_ZSF_DJ4",clZsfDj4!=null?clZsfDj4.toString():"");
    	map.put("CL_ZSF_JE1",clZsfJe1!=null?clZsfJe1.toString():"");
    	map.put("CL_ZSF_JE2",clZsfJe2!=null?clZsfJe2.toString():"");
    	map.put("CL_ZSF_JE3",clZsfJe3!=null?clZsfJe3.toString():"");
    	map.put("CL_ZSF_JE4",clZsfJe4!=null?clZsfJe4.toString():"");
    	map.put("CL_HSBZ_TS1",clHsbzTs1!=null?clHsbzTs1.toString():"");
    	map.put("CL_HSBZ_TS2",clHsbzTs2!=null?clHsbzTs2.toString():"");
    	map.put("CL_HSBZ_TS3",clHsbzTs3!=null?clHsbzTs3.toString():"");
    	map.put("CL_HSBZ_TS4",clHsbzTs4!=null?clHsbzTs4.toString():"");
    	map.put("CL_HSBZ_JE1",clHsbzJe1!=null?clHsbzJe1.toString():"");
    	map.put("CL_HSBZ_JE2",clHsbzJe2!=null?clHsbzJe2.toString():"");
    	map.put("CL_HSBZ_JE3",clHsbzJe3!=null?clHsbzJe3.toString():"");
    	map.put("CL_HSBZ_JE4",clHsbzJe4!=null?clHsbzJe4.toString():"");
    	map.put("CL_GZF_TS1",clGzfTs1!=null?clGzfTs1.toString():"");
    	map.put("CL_GZF_TS2",clGzfTs2!=null?clGzfTs2.toString():"");
    	map.put("CL_GZF_TS3",clGzfTs3!=null?clGzfTs3.toString():"");
    	map.put("CL_GZF_TS4",clGzfTs4!=null?clGzfTs4.toString():"");
    	map.put("CL_GZF_JE1",clGzfJe1!=null?clGzfJe1.toString():"");
    	map.put("CL_GZF_JE2",clGzfJe2!=null?clGzfJe2.toString():"");
    	map.put("CL_GZF_JE3",clGzfJe3!=null?clGzfJe3.toString():"");
    	map.put("CL_GZF_JE4",clGzfJe4!=null?clGzfJe4.toString():"");
    	map.put("CL_QT_DJ1",clQtDj1!=null?clQtDj1.toString():"");
    	map.put("CL_QT_DJ2",clQtDj2!=null?clQtDj2.toString():"");
    	map.put("CL_QT_DJ3",clQtDj3!=null?clQtDj3.toString():"");
    	map.put("CL_QT_DJ4",clQtDj4!=null?clQtDj4.toString():"");
    	map.put("CL_QT_JE1",clQtJe1!=null?clQtJe1.toString():"");
    	map.put("CL_QT_JE2",clQtJe2!=null?clQtJe2.toString():"");
    	map.put("CL_QT_JE3",clQtJe3!=null?clQtJe3.toString():"");
    	map.put("CL_QT_JE4",clQtJe4!=null?clQtJe4.toString():"");
    	map.put("CL_JEXJ1",clJexj1!=null?clJexj1.toString():"");
    	map.put("CL_JEXJ2",clJexj2!=null?clJexj2.toString():"");
    	map.put("CL_JEXJ3",clJexj3!=null?clJexj3.toString():"");
    	map.put("CL_JEXJ4",clJexj4!=null?clJexj4.toString():"");
    	map.put("CL_HJ_JTF_DJ",clHjJtfDj!=null?clHjJtfDj.toString():"");
    	map.put("CL_HJ_JTF_JE",clHjJtfJe!=null?clHjJtfJe.toString():"");
    	map.put("CL_HJ_ZSF_DJ",clHjZsfDj!=null?clHjZsfDj.toString():"");
    	map.put("CL_HJ_ZSF_JE",clHjZsfJe!=null?clHjZsfJe.toString():"");
    	map.put("CL_HJ_HSBZ_TS",clHjHsbzTs!=null?clHjHsbzTs.toString():"");
    	map.put("CL_HJ_HSBZ_JE",clHjHsbzJe!=null?clHjHsbzJe.toString():"");
    	map.put("CL_HJ_GZF_TS",clHjGzfTs!=null?clHjGzfTs.toString():"");
    	map.put("CL_HJ_GZF_JE",clHjGzfJe!=null?clHjGzfJe.toString():"");
    	map.put("CL_HJ_QT_DJ",clHjQtDj!=null?clHjQtDj.toString():"");
    	map.put("CL_HJ_QT_JE",clHjQtJe!=null?clHjQtJe.toString():"");
    	map.put("CL_HJ_JEXJ",clHjJexj!=null?clHjJexj.toString():"");
    	map.put("CL_HJ_RMBDX",clHjRmbdx);
    	map.put("CL_XJ",clXj);
    	map.put("CL_GWK",clGwk);
    	map.put("CL_ZZ",clZz);
    	map.put("USER",user);
    	
    	map.put("IDENTIFIER",identifier);
    	map.put("CL_QD_R1_YEAR",clQdR1Year);
    	map.put("CL_QD_R2_YEAR",clQdR2Year);
    	map.put("CL_QD_R3_YEAR",clQdR3Year);
    	map.put("CL_QD_R4_YEAR",clQdR4Year);
    	map.put("CL_ZD_R1_YEAR",clZdR1Year);
    	map.put("CL_ZD_R2_YEAR",clZdR2Year);
    	map.put("CL_ZD_R3_YEAR",clZdR3Year);
    	map.put("CL_ZD_R4_YEAR",clZdR4Year);

    	map.put("CL_TYPE",clType);
    	map.put("ITEM_ID",itemId);
    	map.put("ITEM_NAME",itemName);
    	map.put("PROJECT_ID",projectId);
    	map.put("PROJECT_NAME",projectName);
    	return map;
    }
} 

package com.scts.discipline.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

/**
 * 主动介入
 * @author Administrator
 *
 */
@Entity
@Table(name="TJY2_DISCIPLINE_ZDSX_ZDJR")
public class DisciplineZdjr implements BaseEntity {
//	private static final long serialVersionUID = 1L;
	private String id;
	private String szbm;//所在部门
	private String szbm_id;//所在部门id
	private String jdlb;//监督类别
	private String jdfs;//监督方式
	private String jdgzsy;//监督工作事由
	private Date jdsj_start;//监督时间
	private Date jdsj_end;//监督时间
	
	private String bmyj;//部门意见
	private String bmyj_fzr;//部门意见负责人
	private String jjgzyj;//纪检工作安排意见-负责人
	private String yld;//纪检工作安排意见-院领导
	private String fzrxf;//纪检工作安排意见-负责人下发
	private String jjgzyj_fzr;//纪检工作安排意见-负责人
	private String bljg;//办理结果
	private Date bljg_time;//办理结果日期
	private Date bmyj_time;//填写部门意见时间
	private Date jjgzyj_time;//填写纪检工作安排意见时间
	private String create_user_id;
	private String create_user_name;
	private String create_org_id;
	private String create_org_name;
	private Date create_time;
	private String sn;//编号
	private String state;//状态：0`未提交`1`部门负责人审核`2`纪检负责人审核`3`纪检分管院领导审核`4`审核通过`5`审核不通过`6`完结`99删除
	private String cz_user_ids;//纪检检查分配可操作人员id
	private String cz_user_names;//纪检检查分配可操作人员name
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="YLD")
	public String getYld() {
		return yld;
	}
	public void setYld(String yld) {
		this.yld = yld;
	}
	@Column(name="FZRXF")
	public String getFzrxf() {
		return fzrxf;
	}
	public void setFzrxf(String fzrxf) {
		this.fzrxf = fzrxf;
	}
	@Column(name="CZ_USER_IDS")
	public String getCz_user_ids() {
		return cz_user_ids;
	}
	public void setCz_user_ids(String cz_user_ids) {
		this.cz_user_ids = cz_user_ids;
	}
	@Column(name="CZ_USER_NAMES")
	public String getCz_user_names() {
		return cz_user_names;
	}
	public void setCz_user_names(String cz_user_names) {
		this.cz_user_names = cz_user_names;
	}
	@Column(name="SZBM")
	public String getSzbm() {
		return szbm;
	}
	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	@Column(name="SZBM_ID")
	public String getSzbm_id() {
		return szbm_id;
	}
	public void setSzbm_id(String szbm_id) {
		this.szbm_id = szbm_id;
	}
	@Column(name="JDLB")
	public String getJdlb() {
		return jdlb;
	}
	public void setJdlb(String jdlb) {
		this.jdlb = jdlb;
	}
	@Column(name="JDFS")
	public String getJdfs() {
		return jdfs;
	}
	public void setJdfs(String jdfs) {
		this.jdfs = jdfs;
	}
	@Column(name="JDGZSY")
	public String getJdgzsy() {
		return jdgzsy;
	}
	public void setJdgzsy(String jdgzsy) {
		this.jdgzsy = jdgzsy;
	}
	@Column(name="JDSJ_START")
	public Date getJdsj_start() {
		return jdsj_start;
	}
	public void setJdsj_start(Date jdsj_start) {
		this.jdsj_start = jdsj_start;
	}
	@Column(name="JDSJ_END")
	public Date getJdsj_end() {
		return jdsj_end;
	}
	public void setJdsj_end(Date jdsj_end) {
		this.jdsj_end = jdsj_end;
	}
	public String getBmyj() {
		return bmyj;
	}
	public void setBmyj(String bmyj) {
		this.bmyj = bmyj;
	}
	public String getJjgzyj() {
		return jjgzyj;
	}
	public void setJjgzyj(String jjgzyj) {
		this.jjgzyj = jjgzyj;
	}
	public String getBljg() {
		return bljg;
	}
	public void setBljg(String bljg) {
		this.bljg = bljg;
	}
	public Date getBmyj_time() {
		return bmyj_time;
	}
	public void setBmyj_time(Date bmyj_time) {
		this.bmyj_time = bmyj_time;
	}
	public Date getJjgzyj_time() {
		return jjgzyj_time;
	}
	public void setJjgzyj_time(Date jjgzyj_time) {
		this.jjgzyj_time = jjgzyj_time;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public String getCreate_org_id() {
		return create_org_id;
	}
	public void setCreate_org_id(String create_org_id) {
		this.create_org_id = create_org_id;
	}
	public String getCreate_org_name() {
		return create_org_name;
	}
	public void setCreate_org_name(String create_org_name) {
		this.create_org_name = create_org_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getBljg_time() {
		return bljg_time;
	}
	public void setBljg_time(Date bljg_time) {
		this.bljg_time = bljg_time;
	}
	public String getBmyj_fzr() {
		return bmyj_fzr;
	}
	public void setBmyj_fzr(String bmyj_fzr) {
		this.bmyj_fzr = bmyj_fzr;
	}
	public String getJjgzyj_fzr() {
		return jjgzyj_fzr;
	}
	public void setJjgzyj_fzr(String jjgzyj_fzr) {
		this.jjgzyj_fzr = jjgzyj_fzr;
	}
	
	
}

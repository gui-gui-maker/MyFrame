package com.fwxm.scientific.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_SCIENTIFIC_REMARK")
public class Tjy2ScientificRemark implements BaseEntity{

    private String id;//${columnb.remarks}
   private String remark;//审核说明
   private String fk_scientific_id;//申报书id
   private String status;//状态
   private Date create_date;//创建时间
   private String create_man;//创建人
   private String project_name;//项目名称
   private String process;//操作步骤

    @Id
   	@GeneratedValue(generator = "system-uuid")
   	@GenericGenerator(name = "system-uuid", strategy = "uuid")
   	public String getId() {
   		return id;
   	}

   	public void setId(String id) {
   		this.id = id;
   	}
   
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFk_scientific_id() {
		return fk_scientific_id;
	}

	public void setFk_scientific_id(String fk_scientific_id) {
		this.fk_scientific_id = fk_scientific_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_man() {
		return create_man;
	}

	public void setCreate_man(String create_man) {
		this.create_man = create_man;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@SuppressWarnings("unchecked")
	public Map to_Map() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("status", status);
        map.put("fk_scientific_id", fk_scientific_id);
        map.put("remark", remark);
        map.put("project_name", project_name);
        map.put("process", process);
        
        return map;
    }

} 

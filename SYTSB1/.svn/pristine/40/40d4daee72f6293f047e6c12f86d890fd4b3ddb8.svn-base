package com.lsts.humanresources.bean;

import com.khnt.core.crud.bean.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TJY2_RL_POSITION_T_REMIND")
public class PositionTitleRemind implements BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;//主键

    private String remindId;//提醒人ID

    private String remindName;//提醒人姓名

    private java.util.Date createDate;//创建时间

    private String createId;//创建人ID

    private String createBy;//创建人

    private java.util.Date lastModifyDate;//最后更新时间

    private String lastModifyId;//最后更新人ID

    private String lastModifyBy;//最后更新人

    public void setId(String value) {
        this.id = value;
    }

    public void setRemindId(String value) {
        this.remindId = value;
    }

    public void setRemindName(String value) {
        this.remindName = value;
    }

    public void setCreateDate(java.util.Date value) {
        this.createDate = value;
    }

    public void setCreateId(String value) {
        this.createId = value;
    }

    public void setCreateBy(String value) {
        this.createBy = value;
    }

    public void setLastModifyDate(java.util.Date value) {
        this.lastModifyDate = value;
    }

    public void setLastModifyId(String value) {
        this.lastModifyId = value;
    }

    public void setLastModifyBy(String value) {
        this.lastModifyBy = value;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return this.id;
    }

    @Column(name = "REMIND_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
    public String getRemindId() {
        return this.remindId;
    }

    @Column(name = "REMIND_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
    public String getRemindName() {
        return this.remindName;
    }

    @Column(name = "CREATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    @Column(name = "CREATE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getCreateId() {
        return this.createId;
    }

    @Column(name = "CREATE_BY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateBy() {
        return this.createBy;
    }

    @Column(name = "LAST_MODIFY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
    public java.util.Date getLastModifyDate() {
        return this.lastModifyDate;
    }

    @Column(name = "LAST_MODIFY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    public String getLastModifyId() {
        return this.lastModifyId;
    }

    @Column(name = "LAST_MODIFY_BY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastModifyBy() {
        return this.lastModifyBy;
    }


} 

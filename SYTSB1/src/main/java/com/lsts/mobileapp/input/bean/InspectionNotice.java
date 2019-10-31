package com.lsts.mobileapp.input.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TZSB_INSPECTION_NOTICE")
public class InspectionNotice implements BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String fk_inspect_info_id;//报告id
    private String com_name;//使用单位
    private String device_name;//设备名称
    private String device_type;//设备品种
    private String device_code;//设备代码
    private String internal_num;//单位内编号
    private String registration_num;//使用登记证编号
    private Date create_date;//出具时间
    private Date end_date;//整改截止日期
    private String inspect_op;//检验员
    private String item_op;//检验单位技术负责人
    private String inspect_op_id;//检验员
    private String item_op_id;//检验单位技术负责人
    private String com_op;//使用单位接收人
    private String status;//0 起草 1提交 2整改完成 3整改确认 99删除
    private String enter_op;
    private String enter_op_id;
    private String file_id;//附件id
    private String remark;//问题及意见
    private String notice_type;//1检验意见书1  2：检验意见书2
    private String fk_com_id;//单位id
    private String fk_device_id;//设备id
    private String feedback_file_id;//反馈附件id
    private String feedback_file_name;//反馈附件名称
    private String feed_that;//反馈说明


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFk_inspect_info_id() {
        return fk_inspect_info_id;
    }

    public void setFk_inspect_info_id(String fk_inspect_info_id) {
        this.fk_inspect_info_id = fk_inspect_info_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }

    public String getInternal_num() {
        return internal_num;
    }

    public void setInternal_num(String internal_num) {
        this.internal_num = internal_num;
    }

    public String getRegistration_num() {
        return registration_num;
    }

    public void setRegistration_num(String registration_num) {
        this.registration_num = registration_num;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getInspect_op() {
        return inspect_op;
    }

    public void setInspect_op(String inspect_op) {
        this.inspect_op = inspect_op;
    }

    public String getItem_op() {
        return item_op;
    }

    public void setItem_op(String item_op) {
        this.item_op = item_op;
    }

    public String getInspect_op_id() {
        return inspect_op_id;
    }

    public void setInspect_op_id(String inspect_op_id) {
        this.inspect_op_id = inspect_op_id;
    }

    public String getItem_op_id() {
        return item_op_id;
    }

    public void setItem_op_id(String item_op_id) {
        this.item_op_id = item_op_id;
    }

    public String getCom_op() {
        return com_op;
    }

    public void setCom_op(String com_op) {
        this.com_op = com_op;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnter_op() {
        return enter_op;
    }

    public void setEnter_op(String enter_op) {
        this.enter_op = enter_op;
    }

    public String getEnter_op_id() {
        return enter_op_id;
    }

    public void setEnter_op_id(String enter_op_id) {
        this.enter_op_id = enter_op_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getFk_com_id() {
        return fk_com_id;
    }

    public void setFk_com_id(String fk_com_id) {
        this.fk_com_id = fk_com_id;
    }

    public String getFk_device_id() {
        return fk_device_id;
    }

    public void setFk_device_id(String fk_device_id) {
        this.fk_device_id = fk_device_id;
    }

    public String getFeedback_file_id() {
        return feedback_file_id;
    }

    public void setFeedback_file_id(String feedback_file_id) {
        this.feedback_file_id = feedback_file_id;
    }

    public String getFeed_that() {
        return feed_that;
    }

    public void setFeed_that(String feed_that) {
        this.feed_that = feed_that;
    }

    public String getFeedback_file_name() {
        return feedback_file_name;
    }

    public void setFeedback_file_name(String feedback_file_name) {
        this.feedback_file_name = feedback_file_name;
    }
}

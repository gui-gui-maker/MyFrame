package com.lsts.inspection.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.lsts.inspection.bean.ReportBHGRecord;
import com.lsts.inspection.dao.ReportBHGRecordDao;
/**
 * 报告检验项目不合格问题来源记录表业务逻辑对象
 * @ClassName ReportBHGRecordService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-01-06 上午11:32:00
 */
@Service("reportBHGRecordService")
public class ReportBHGRecordService extends EntityManageImpl<ReportBHGRecord, ReportBHGRecordDao> {
	@Autowired
	private ReportBHGRecordDao reportBHGRecordDao;

	// 获取报告检验项目参数
	public List<ReportBHGRecord> queryByInfoId(String info_id){
		return reportBHGRecordDao.queryByInfoId(info_id);
	}

	// 更新报告检验项目不合格问题来源记录
	public void updateBhgRecord(String item_id, String info_id, String item_name, String item_value){
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			ReportBHGRecord reportBHGRecord = reportBHGRecordDao.get(item_id);
			reportBHGRecord.setFk_inspection_info_id(info_id);
			reportBHGRecord.setBhg_name(item_name);		// 问题来源key
			reportBHGRecord.setBhg_value(item_value);	// 问题来源值
			reportBHGRecord.setItem_type("string");
			reportBHGRecord.setLast_mdy_uid(user.getId());
			reportBHGRecord.setLast_mdy_uname(user.getName());
			reportBHGRecord.setLast_mdy_time(DateUtil.getCurrentDateTime());
			reportBHGRecord.setData_status("1");		// 数据状态，默认0（0：新建 1：修改 99：删除）
			reportBHGRecordDao.save(reportBHGRecord);	
			//reportItemRecordDao.createSQLQuery("update tzsb_report_item_record set fk_report_id='"+report_id+"', fk_inspection_info_id='"+info_id+"', item_name='"+item_name+"',item_value='"+item_value+"',item_type='String' where id='"+item_id+"'").executeUpdate();
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
		}
	}
	
	// 删除原始记录检验项目参数记录
	public void delBhgRecord(String info_id) {
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			reportBHGRecordDao.createSQLQuery("update tzsb_report_bhg_record set DATA_STATUS='99',last_mdy_uid=?,last_mdy_uname=?,last_mdy_time=? where fk_inspection_info_id =?", user.getId(), user.getName(), DateUtil.getCurrentDateTime(), info_id ).executeUpdate();
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 保存修改问题来源
	 * @param id
	 * @param bhg_name
	 * @param bhg_value
	 */
	public void addErrorResource(String id, String bhg_name, String bhg_value) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String [] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			//删除之前的
			reportBHGRecordDao.deleteAllByInfoid(ids[i]);
			
			String [] bhg_names = bhg_name.split(",");
			String [] bhg_values = bhg_value.split(",");
			for (int j = 0; j < bhg_names.length; j++) {
				
				ReportBHGRecord bhgRecord = new ReportBHGRecord();
				bhgRecord.setBhg_name(bhg_names[j]);
				bhgRecord.setBhg_value(bhg_values[j]);
				bhgRecord.setData_status("0");
				bhgRecord.setFk_inspection_info_id(ids[i]);
				bhgRecord.setItem_type("string");
				bhgRecord.setLast_mdy_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				bhgRecord.setLast_mdy_uid(user.getId());
				bhgRecord.setLast_mdy_uname(user.getName());
				
				reportBHGRecordDao.save(bhgRecord);
			}
			
		}
		
	}

	public List<ReportBHGRecord> queryResourceByInfoid(String id) {
		List<ReportBHGRecord> list = reportBHGRecordDao.queryResourceByInfoId(id);
		return list;
	}
	
	public List<ReportBHGRecord> queryBHGRecords(String inspectionInfoId){
		return reportBHGRecordDao.queryBHGRecords(inspectionInfoId);
	}

	public void del(String ids) {
		String [] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {
			System.out.println("------------------"+idss[i]);
			//删除
			reportBHGRecordDao.delete(idss[i]);
		}
	}
}

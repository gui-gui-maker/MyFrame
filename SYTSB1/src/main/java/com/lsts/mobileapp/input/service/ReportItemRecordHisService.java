package com.lsts.mobileapp.input.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportItemRecord;
import com.lsts.inspection.dao.ReportItemRecordDao;
import com.lsts.mobileapp.input.bean.ReportItemRecordHis;
import com.lsts.mobileapp.input.dao.ReportItemRecordHisDao;

@Service("reportItemRecordHisService")
public class ReportItemRecordHisService extends EntityManageImpl<ReportItemRecordHis, ReportItemRecordHisDao> {

	@Autowired
	ReportItemRecordHisDao itemRecordHisDao;
	@Autowired
	ReportItemRecordDao itemRecordDao;
	
	public void copyHisRecord(String infoId,String reportId) throws ParseException {
		List<ReportItemRecord> oldList =  itemRecordDao.getItemRecordListByInfoId(infoId,reportId);
		String max = itemRecordHisDao.getMaxStatus(infoId);
		int newStatus = 0;
		if(max==null||StringUtil.isEmpty(max)) {
			newStatus = 1;
		}else {
			newStatus = Integer.parseInt(max)+1;
		}
		for (int i = 0; i < oldList.size(); i++) {
			ReportItemRecord old = oldList.get(i);
			ReportItemRecordHis his = new ReportItemRecordHis();
			his.setDataStatus(newStatus+"");
			his.setFkInspectionInfoId(old.getFk_inspection_info_id());
			his.setFkReportId(old.getFk_report_id());
			his.setImage(old.getImage());
			his.setItemName(old.getItem_name());
			his.setItemType(old.getItem_type());
			his.setItemValue(old.getItem_value());
			his.setLastMdyTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(old.getLast_mdy_time()));
			his.setLastMdyUid(old.getLast_mdy_uid());
			his.setLastMdyUname(old.getLast_mdy_uname());
			his.setPageNo(old.getPage_no());
			itemRecordHisDao.save(his);
		}
		
	}
	
}

package com.lsts.report.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.IdFormat;
import com.lsts.report.bean.ReportTransferRecord;
import com.lsts.report.bean.ReportTransferRecordDTO;

/**
 * 业务服务部前后台报告交接明细表数据处理对象
 * @ClassName ReportTransferRecordDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-03 11:38:00
 */
@Repository("reportTransferRecordDao")
public class ReportTransferRecordDao extends EntityDaoImpl<ReportTransferRecord> {

	@SuppressWarnings("unchecked")
	public List<ReportTransferRecord> getInfos(
			String report_transfer_id) {
		List<ReportTransferRecord> list = new ArrayList<ReportTransferRecord>();
		String hql = "from ReportTransferRecord i where i.reportTransfer.id=? and i.data_status != '99'";
		list = this.createQuery(hql, report_transfer_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTransferRecord> queryInfos(
			String report_transfer_record_id) {
		List<ReportTransferRecord> list = new ArrayList<ReportTransferRecord>();
		String hql = "from ReportTransferRecord i where i.id=? and i.data_status != '99'";
		list = this.createQuery(hql, report_transfer_record_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTransferRecord> getInfos(
			String com_name, String report_sn) {
		List<ReportTransferRecord> list = new ArrayList<ReportTransferRecord>();
		String hql = "from ReportTransferRecord i where i.com_name=? and i.report_sn=? and i.data_status != '99'";
		list = this.createQuery(hql, com_name, report_sn).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTransferRecord> getInfoList(String ids) {
		List<ReportTransferRecord> list = new ArrayList<ReportTransferRecord>();
		ids = IdFormat.formatIdStr(ids);
		String hql = "from ReportTransferRecord i where i.id in ("+ids+") and i.data_status != '99'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 根据明细ID查询明细列表
	 * 
	 * @param ids
	 * @return
	 * @author GaoYa
	 * @date 2015-01-23 下午05:26:00
	 */
	public List<ReportTransferRecordDTO> queryReportInfos(String ids) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();// 获取当前用户登录信息
		List<ReportTransferRecordDTO> infoList = new ArrayList<ReportTransferRecordDTO>();
		ids = IdFormat.formatIdStr(ids);
		String sql = "select t.report_sn,t.report_com_name,t.check_unit_id,o.org_name,t.id,t.report_type,t.fk_tsjc_device_document_id,t.advance_fees"
				+ " from tzsb_inspection_info t,sys_org o where t.id in ("
				+ ids
				+ ") and t.check_unit_id=o.id(+)";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				ReportTransferRecordDTO reportTransferRecordDTO = new ReportTransferRecordDTO();
				reportTransferRecordDTO.setReport_sn(String.valueOf(obj[0]));
				reportTransferRecordDTO.setCom_name(String.valueOf(obj[1]));
				reportTransferRecordDTO.setOrg_id(String.valueOf(obj[2]));
				reportTransferRecordDTO.setOrg_name(String.valueOf(obj[3]));
				reportTransferRecordDTO.setInfo_id(String.valueOf(obj[4]));
				reportTransferRecordDTO.setReport_id(String.valueOf(obj[5]));
				reportTransferRecordDTO.setDevice_id(String.valueOf(obj[6]));
				reportTransferRecordDTO.setAdvance_fees(Double.valueOf(String.valueOf(obj[7])));
				reportTransferRecordDTO.setReport_count(1);
				reportTransferRecordDTO.setCommit_user_name(user.getName());
				reportTransferRecordDTO.setCommit_time(new Date());
				infoList.add(reportTransferRecordDTO);
			}
		}
		return infoList;
	}
}

package com.lsts.report.service;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.dao.DeviceDao;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportTransfer;
import com.lsts.report.bean.ReportTransferRecord;
import com.lsts.report.bean.ReportTransferRecordDTO;
import com.lsts.report.dao.ReportTransferDao;
import com.lsts.report.dao.ReportTransferRecordDao;

import util.TS_Util;

/**
 * 业务服务部前后台报告交接业务逻辑对象
 * @ClassName ReportTransferService
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-03 11:44:00
 */
@Service("reportTransferService")
public class ReportTransferService extends EntityManageImpl<ReportTransfer, ReportTransferDao> {
	@Autowired
	private ReportTransferDao reportTransferDao;
	@Autowired
	private ReportTransferRecordDao reportTransferRecordDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;

	@Autowired
	private DeviceDao deviceDao;
	
	/**
	 * 
	 * 保存并提交交接业务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReportTransfer commitBasic(ReportTransfer reportTransfer,
			HttpServletRequest request) throws Exception {
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User)user.getSysUser();
		// 获取业务报检表单号
		try {
			int count = 0;
			String com_name = "";
			String report_sn = "";
			String org_id = "";
			String org_name = "";
			for (ReportTransferRecord info : reportTransfer.getReportTransferRecord()) {
				count++;
				if (StringUtil.isEmpty(info.getSn())) {
					// 获取业务流水号
					String sn = TS_Util.getReportTransferRecordSn(Integer.valueOf(count),
							conn);
					info.setSn(sn);
				}
				if(info.getReport_count()==null || info.getReport_count()==0){
					info.setReport_count(1);
				}
				info.setData_status("1"); // 数据状态（1：已提交）				
				//info.setOrg_id(user.getDepartment().getId());
				//info.setOrg_name(user.getDepartment().getOrgName());
				if(StringUtil.isNotEmpty(info.getCom_name())){
					if(StringUtil.isNotEmpty(com_name)){
						com_name += ",";
					}
					com_name += info.getCom_name();
				}
				if(StringUtil.isNotEmpty(info.getReport_sn())){
					if(StringUtil.isNotEmpty(report_sn)){
						report_sn += ",";
					}
					report_sn += info.getReport_sn();
				}
				if(StringUtil.isEmpty(org_id)){
					org_id = info.getOrg_id();
				}
				if(StringUtil.isEmpty(org_name)){
					org_name = info.getOrg_name();
				}
				if(StringUtil.isEmpty(info.getInfo_id()) || StringUtil.isEmpty(info.getDevice_id())){
					InspectionInfo inspectionInfo = inspectionInfoDao.getInfos(report_sn, com_name);
					if(StringUtil.isEmpty(info.getInfo_id())){
						info.setInfo_id(inspectionInfo.getId());
					}
					if(StringUtil.isEmpty(info.getDevice_id())){
						info.setDevice_id(inspectionInfo.getFk_tsjc_device_document_id());
					}
				}
				info.setCommit_user_id(uu.getId());
				info.setCommit_user_name(uu.getName());
				info.setCommit_time(new Date());
				info.setReportTransfer(reportTransfer);
			}
			
			reportTransfer.setData_status("1"); // 数据状态（1：已提交）
			reportTransfer.setEnter_op_id(uu.getId()); // 此处不用到电子签名，所以不使用employee
			reportTransfer.setEnter_op_name(uu.getName());
			reportTransfer.setEnter_date(new Date());
			reportTransfer.setCommit_user_id(uu.getId());
			reportTransfer.setCommit_user_name(uu.getName());
			reportTransfer.setCommit_date(new Date());
			reportTransfer.setOrg_id(org_id);
			reportTransfer.setOrg_name(org_name);
			reportTransfer.setCom_name(com_name);	// 记录单位名称，方便查询
			reportTransfer.setReport_sn(report_sn);	// 记录报告编号，方便查询
			if (StringUtil.isEmpty(reportTransfer.getSn())) {
				// 获取业务流水号
				String sn = TS_Util.getReportTransferSn(Integer.valueOf(count), conn);
				reportTransfer.setSn(sn);
			}
			reportTransferDao.save(reportTransfer);			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		// 释放连接
		Factory.getDB().freeConnetion(conn);
		return reportTransfer;
	}
	
	/**
	 * 获取报告前后台交接业务信息
	 * 
	 * @param id --
	 *            交接业务主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询前后台交接业务主表
			ReportTransfer reportTransfer = reportTransferDao.get(id);
			// 查询报送打印明细表
			List<ReportTransferRecord> list = reportTransferRecordDao
					.getInfos(id);
			map.put("data", reportTransfer);
			map.put("reportTransferRecord", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	/**
	 * 获取报告前后台交接业务信息
	 * 
	 * @param id --
	 *            报告前后台交接业务主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getPrintDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReportTransferRecordDTO> list = new ArrayList<ReportTransferRecordDTO>();
			// 查询报告前后台交接业务明细表
			List<ReportTransferRecord> reportTransferRecordList = reportTransferRecordDao
					.getInfos(id);
			for(ReportTransferRecord reportTransferRecord : reportTransferRecordList){
				ReportTransferRecordDTO reportTransferRecordDTO = new ReportTransferRecordDTO();
				BeanUtils.copyProperties(reportTransferRecord, reportTransferRecordDTO);
				reportTransferRecordDTO.setCommit_time_str(DateUtil.getDateTime("yyyy-MM-dd", reportTransferRecord.getCommit_time()));
				reportTransferRecordDTO.setReceive_time_str(DateUtil.getDateTime("yyyy-MM-dd", reportTransferRecord.getReceive_time()));
				list.add(reportTransferRecordDTO);
			}
			//map.put("data", reportPrint);
			map.put("reportTransferRecord", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			// 查询报告前后台交接业务明细表
			List<ReportTransferRecord> list = reportTransferRecordDao
					.getInfos(id);
			if (!list.isEmpty()) {
				for (ReportTransferRecord reportTransferRecord : list) {
					// 1、删除报告前后台交接业务明细表
					reportTransferRecordDao
							.createSQLQuery(
									"update TZSB_REPORT_TRANSFER_RECORD set data_status='99' where id = ? ",
									reportTransferRecord.getId()).executeUpdate();
					// 写入日志
					logService.setLogs(reportTransferRecord
							.getId(), "删除报告前后台交接业务", "删除报告前后台交接业务明细",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
				}
			}
			// 2、删除报告前后台交接业务主表
			reportTransferRecordDao
					.createSQLQuery(
							"update TZSB_REPORT_TRANSFER set data_status='99' where id = ? ",
							id).executeUpdate();
			logService.setLogs(id, "删除报告前后台交接业务", "删除报告前后台交接业务",
					user.getId(), user.getName(), new Date(), request
							.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 提交
	public boolean commit(String ids, HttpServletRequest request) {
		boolean isSuccess = true;

		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				ReportTransfer reportTransfer = reportTransferDao.get(idArr[i]);
				// 查询报告前后台交接业务明细表
				List<ReportTransferRecord> list = reportTransferRecordDao
						.getInfos(idArr[i]);
				if (!list.isEmpty()) {
					for (ReportTransferRecord reportTransferRecord : list) {
						// 1、提交报告前后台交接业务明细表
						reportTransferRecord.setData_status("1");
						reportTransferRecord.setCommit_user_id(user.getSysUser().getId());
						reportTransferRecord.setCommit_user_name(user.getSysUser().getName());
						reportTransferRecord.setCommit_time(new Date());
						reportTransferRecordDao.save(reportTransferRecord);
						// 写入日志
						logService.setLogs(reportTransferRecord
								.getId(), "报告前后台交接业务提交", "报告前后台交接业务提交",
								user.getId(), user.getName(), new Date(), request
										.getRemoteAddr());
					}
				}

				reportTransfer.setData_status("1");
				reportTransfer.setCommit_user_id(user.getSysUser().getId());
				reportTransfer.setCommit_user_name(user.getSysUser().getName());
				reportTransfer.setCommit_date(new Date()); // 提交时间
				reportTransferDao.save(reportTransfer);				
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			log.debug(e.toString());
		}
		return isSuccess;
	}
	
	/**
	 * 
	 * 签收报告前后台交接业务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReportTransfer receive(ReportTransfer reportTransfer,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 获取报告前后台交接业务表单号
		try {
			for (ReportTransferRecord info : reportTransfer.getReportTransferRecord()) {
				info.setData_status("2"); // 数据状态（2：已签收）		
				info.setReceive_user_id(user.getId());
				info.setReceive_user_name(user.getName());
				info.setReceive_time(new Date());
				info.setReportTransfer(reportTransfer);
			}
			
			reportTransfer.setData_status("2"); // 数据状态（2：已签收）
			reportTransfer.setReceive_user_id(user.getId());
			reportTransfer.setReceive_user_name(user.getName());
			reportTransfer.setReceive_time(new Date());
			reportTransferDao.save(reportTransfer);

			logService.setLogs("", "签收报告前后台交接业务信息", "签收报告前后台交接业务", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return reportTransfer;
	}
	
	// 选择签收
	public HashMap<String, Object> receiveChoose(HttpServletRequest request, String ids, String report_transfer_id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String info_ids = "";
		String device_ids = "";
		String report_types = "";
		String bigClasss = "";
		String bhg_report_sn = "";
		try {
			List<ReportTransferRecord> list = reportTransferRecordDao.getInfoList(ids);
			if (!list.isEmpty()) {
				for (ReportTransferRecord reportTransferRecord : list) {
					if (StringUtil.isNotEmpty(reportTransferRecord.getInfo_id())) {
						InspectionInfo info = inspectionInfoDao.get(reportTransferRecord.getInfo_id());
						DeviceDocument device = deviceDao.get(info.getFk_tsjc_device_document_id());
						if ("合格".equals(info.getInspection_conclusion())
								|| "复检合格".equals(info.getInspection_conclusion())) {
							if (StringUtil.isNotEmpty(info_ids)) {
								info_ids += ",";
								device_ids += ",";
								report_types+= ",";
								bigClasss+= ",";
							}
							info_ids += reportTransferRecord.getInfo_id();
							device_ids += info.getFk_tsjc_device_document_id();
							report_types += info.getReport_type();
							bigClasss += device.getDevice_sort_code().substring(0,1);
						} else if ("不合格".equals(info.getInspection_conclusion())
								|| "复检不合格".equals(info.getInspection_conclusion())) {
							if (StringUtil.isNotEmpty(bhg_report_sn)) {
								bhg_report_sn += ",";
							}
							bhg_report_sn += info.getReport_sn();
						}
						info.setIs_receive("1");
						info.setReceive_user_id(user.getSysUser().getId());
						info.setReceive_user_name(user.getSysUser().getName());
						info.setReceive_date(new Date());
						inspectionInfoDao.save(info);	// 1、保存报告前台签收信息
					}
					// 2、保存前后台报送签收明细表状态
					reportTransferRecord.setData_status("2"); // 2：已签收
					reportTransferRecord.setReceive_user_id(user.getSysUser().getId());
					reportTransferRecord.setReceive_user_name(user.getSysUser().getName());
					reportTransferRecord.setReceive_time(new Date());
					reportTransferRecordDao.save(reportTransferRecord);

					// 3、写入日志
					logService.setLogs(reportTransferRecord.getId(), "签收后台检验报告", "签收后台检验报告", user.getId(),
							user.getName(), new Date(), request.getRemoteAddr());
				}
			}

			List<ReportTransferRecord> recordlist = reportTransferRecordDao.getInfos(report_transfer_id);
			if (!recordlist.isEmpty()) {
				boolean allReceive = true;
				for (ReportTransferRecord reportTransferRecord : recordlist) {
					// 1：已提交未签收
					if ("1".equals(reportTransferRecord.getData_status())) {
						allReceive = false;
					}
				}
				if (allReceive) {
					ReportTransfer reportTransfer = reportTransferDao.get(report_transfer_id);
					reportTransfer.setData_status("2"); // 2：已签收
					reportTransfer.setReceive_user_id(user.getSysUser().getId());
					reportTransfer.setReceive_user_name(user.getSysUser().getName());
					reportTransfer.setReceive_time(new Date()); // 签收时间
					reportTransferDao.save(reportTransfer);		// 4、保存报告前后台交接业务主表签收状态		
					// 5、写入日志
					logService.setLogs(reportTransfer.getId(), "签收后台检验报告", "签收后台检验报告", user.getId(), user.getName(),
							new Date(), request.getRemoteAddr());
				}
			}
			map.put("success", true);
			map.put("info_ids", info_ids);
			map.put("device_ids", device_ids);
			map.put("report_types", report_types);
			map.put("bigClasss", bigClasss);
			map.put("bhg_report_sn", bhg_report_sn);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 批量签收
	public HashMap<String, Object> receives(String ids, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String info_ids = "";
		String device_ids = "";
		String report_types = "";
		String bigClasss = "";
		String bhg_report_sn = "";
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				ReportTransfer reportTransfer = reportTransferDao.get(idArr[i]);
				// 查询报告前后台交接业务明细表
				List<ReportTransferRecord> list = reportTransferRecordDao
						.getInfos(idArr[i]);
				if (!list.isEmpty()) {
					for (ReportTransferRecord reportTransferRecord : list) {
						if (StringUtil.isNotEmpty(reportTransferRecord.getInfo_id())) {
							InspectionInfo info = inspectionInfoDao.get(reportTransferRecord.getInfo_id());
							DeviceDocument device = deviceDao.get(info.getFk_tsjc_device_document_id());
							if ("合格".equals(info.getInspection_conclusion())
									|| "复检合格".equals(info.getInspection_conclusion())) {
								if (StringUtil.isNotEmpty(info_ids)) {
									info_ids += ",";
									device_ids += ",";
									report_types+= ",";
									bigClasss+= ",";
								}
								info_ids += reportTransferRecord.getInfo_id();
								device_ids += info.getFk_tsjc_device_document_id();
								report_types += info.getReport_type();
								bigClasss += device.getDevice_sort_code().substring(0,1);
							} else if ("不合格".equals(info.getInspection_conclusion())
									|| "复检不合格".equals(info.getInspection_conclusion())) {
								if (StringUtil.isNotEmpty(bhg_report_sn)) {
									bhg_report_sn += ",";
								}
								bhg_report_sn += info.getReport_sn();
							}
							
							info.setIs_receive("1");
							info.setReceive_user_id(user.getSysUser().getId());
							info.setReceive_user_name(user.getSysUser().getName());
							info.setReceive_date(new Date());
							inspectionInfoDao.save(info);	// 1、保存报告前台签收信息		
						}
						// 2、保存报告前后台交接业务明细签收状态
						reportTransferRecord.setData_status("2"); // 2：已签收
						reportTransferRecord.setReceive_user_id(user.getSysUser().getId());
						reportTransferRecord.setReceive_user_name(user.getSysUser().getName());
						reportTransferRecord.setReceive_time(new Date());
						reportTransferRecordDao.save(reportTransferRecord);
						// 3、写入日志
						logService.setLogs(reportTransferRecord.getId(), "批量签收报告前后台交接业务信息", "批量签收报告前后台交接业务信息",
								user.getId(), user.getName(), new Date(), request.getRemoteAddr());
					}
				}
				reportTransfer.setData_status("2");			// 2：已签收
				reportTransfer.setReceive_user_id(user.getSysUser().getId());
				reportTransfer.setReceive_user_name(user.getSysUser().getName());
				reportTransfer.setReceive_time(new Date()); // 签收时间
				reportTransferDao.save(reportTransfer);		// 4、保存报告前后台交接业务主表签收状态	
				// 5、写入日志
				logService.setLogs(reportTransfer.getId(), "签收后台检验报告", "签收后台检验报告", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			map.put("info_ids", info_ids);
			map.put("device_ids", device_ids);
			map.put("report_types", report_types);
			map.put("bigClasss", bigClasss);
			map.put("bhg_report_sn", bhg_report_sn);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	// 根据业务信息ID查询业务详细信息
	public List<ReportTransferRecordDTO> queryReportInfos(String ids) throws Exception{
		return reportTransferRecordDao.queryReportInfos(ids);
	}
	
	public List<Map<String, String>> gzfwTj(String name,String beginTime,String endTime) throws HibernateException, ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql="select to_char(t.pulldown_time,'yyyy/mm') sj, "
				+ "sum(CASE  WHEN t.evaluate ='1' OR t.evaluate IS NULL then 1 else 0 end) fcmy,  "
				+ " sum(CASE  WHEN t.evaluate ='2' then 1 else 0 end) my, "
				+ " sum(CASE  WHEN t.evaluate ='3' then 1 else 0 end) bmy"
				+ " from tzsb_report_draw t "
				+ " where 1=1 and   t.pulldown_time >=? and  t.pulldown_time<=?";
				if(StringUtil.isNotEmpty(name)){
					sql+=" and t.pulldown_op= '"+name+"'";
				}
				sql+= " group by to_char(t.pulldown_time,'yyyy/mm')";
		
		 List<Map<String, String>> list=reportTransferRecordDao.createSQLQuery(sql,sdf.parse(beginTime),sdf.parse(endTime)).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
}

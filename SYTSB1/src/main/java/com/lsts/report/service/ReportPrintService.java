package com.lsts.report.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportErrorRecordInfo;
import com.lsts.report.bean.ReportPrint;
import com.lsts.report.bean.ReportPrintRecord;
import com.lsts.report.bean.ReportPrintRecordDTO;
import com.lsts.report.dao.ReportErrorDao;
import com.lsts.report.dao.ReportErrorRecordInfoDao;
import com.lsts.report.dao.ReportPrintDao;
import com.lsts.report.dao.ReportPrintRecordDao;

import util.TS_Util;

/**
 * 检验资料报送打印签收业务逻辑对象
 * @ClassName ReportPrintService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-06 13:25:00
 */
@Service("reportPrintService")
public class ReportPrintService extends EntityManageImpl<ReportPrint, ReportPrintDao> {
	@Autowired
	private ReportPrintDao reportPrintDao;
	@Autowired
	private ReportPrintRecordDao reportPrintRecordDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private ReportErrorDao reportErrorDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private ReportErrorRecordInfoDao reportErrorRecordInfoDao;
	
	/**
	 * 
	 * 保存报送打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveBasic(ReportPrint reportPrint,
			HttpServletRequest request) throws Exception {
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 获取业务报检表单号
		try {
			int count = 0;
			String com_name = "";
			String report_sns = "";
			for (ReportPrintRecord info : reportPrint.getReportPrintRecord()) {
				if (StringUtil.isNotEmpty(info.getReport_sn())) {
					// 验证同一设备是否有老报告正在纠错，如纠错申请里未填写新报告编号，则新报告无法提交报送打印申请
					if (StringUtil.isNotEmpty(info.getInfo_id())) {
						InspectionInfo inspectionInfo = inspectionInfoDao.get(info.getInfo_id());
						if (inspectionInfo != null
								&& StringUtil.isNotEmpty(inspectionInfo.getFk_tsjc_device_document_id())
								&& !"11111111111111111111111111111111".equals(inspectionInfo.getFk_tsjc_device_document_id())) {
							List<InspectionInfo> list = inspectionInfoDao.queryInfosByDeviceId(
									inspectionInfo.getFk_tsjc_device_document_id(), info.getInfo_id());
							for (InspectionInfo old_info : list) {
								if (StringUtil.isNotEmpty(old_info.getReport_sn())) {
									List<ReportErrorRecordInfo> errorInfos = reportErrorRecordInfoDao
											.getErrorInfos(old_info.getId(), old_info.getReport_sn());
									if (!errorInfos.isEmpty()) {
										String msg = "";
										ReportErrorRecordInfo errorRecordInfo = errorInfos.get(0);
										if(errorRecordInfo != null){
											if(StringUtil.isNotEmpty(errorRecordInfo.getReport_sn())){
												msg += "（"+errorRecordInfo.getReport_sn()+"）";
											}
										}
										return JsonWrapper.failureWrapperMsg("报告（" + info.getReport_sn()
												+ "）有往年的报告"+msg+"是纠错报告，请先完成纠错流程，再提交新报告报送打印申请！");
									}
								}
							}
						}
					}

					List<ReportPrintRecord> list = reportPrintRecordDao.getRecords(info.getReport_sn());
					if (!list.isEmpty()) {
						if (StringUtil.isNotEmpty(reportPrint.getId())) {
							for(ReportPrintRecord record : list){
								if (!record.getReportPrint().getId().equals(reportPrint.getId())) {
									return JsonWrapper
											.failureWrapperMsg("本次填写的报告（" + info.getReport_sn() + "）不能重复报送哦，请检查！");
								}
							}
						}else{
							return JsonWrapper
									.failureWrapperMsg("本次填写的报告（" + info.getReport_sn() + "）不能重复报送哦，请检查！");
						}
					}

					count++;
					if (StringUtil.isEmpty(info.getSn())) {
						// 获取业务流水号
						String sn = TS_Util.getReportPrintRecordSn(Integer.valueOf(count), conn);
						info.setSn(sn);
					}
					if (info.getReport_count() == null || info.getReport_count() == 0) {
						info.setReport_count(1);
					}
					info.setData_status("0"); // 数据状态（0：创建）
					info.setOrg_id(user.getDepartment().getId());
					info.setOrg_name(user.getDepartment().getOrgName());
					if (StringUtil.isNotEmpty(info.getCom_name())) {
						if (StringUtil.isNotEmpty(com_name)) {
							com_name += ",";
						}
						com_name += info.getCom_name();
					}
					if (StringUtil.isNotEmpty(info.getReport_sn())) {
						if (StringUtil.isNotEmpty(report_sns)) {
							report_sns += ",";
						}
						report_sns += info.getReport_sn();
					}
					info.setReportPrint(reportPrint);
					if(info.getId()==null&&reportPrint.getId()!=null&&StringUtil.isNotEmpty(reportPrint.getId())) {
						reportPrintRecordDao.save(info);
					}
				}
			}
			if(reportPrint.getId()!=null&&StringUtil.isNotEmpty(reportPrint.getId())) {
				reportPrint = reportPrintDao.get(reportPrint.getId());
			}
			
			reportPrint.setData_status("0"); // 数据状态（0：创建）
			reportPrint.setEnter_op_id(user.getId()); // 此处不用到电子签名，所以不使用employee
			reportPrint.setEnter_op_name(user.getName());
			reportPrint.setEnter_date(new Date());
			reportPrint.setOrg_id(user.getDepartment().getId());
			reportPrint.setOrg_name(user.getDepartment().getOrgName());
			reportPrint.setCom_name(com_name); 		// 记录使用/制造/安装单位名称，方便查询
			reportPrint.setReport_sn(report_sns); 	// 报告书编号，方便查询
			if (StringUtil.isEmpty(reportPrint.getSn())) {
				// 获取业务流水号
				String sn = TS_Util.getReportPrintSn(Integer.valueOf(count), conn);
				reportPrint.setSn(sn);
			}
			reportPrintDao.save(reportPrint);

			logService.setLogs("", "报送打印录入", "检验资料报送打印签收录入", user.getId(), user.getName(), new Date(),
					request.getRemoteAddr());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return JsonWrapper.successWrapper(reportPrint);
	}
	
	/**
	 * 
	 * 保存并提交报送打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> commitBasic(ReportPrint reportPrint,
			HttpServletRequest request) throws Exception {
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 获取业务报检表单号
		try {
			int count = 0;
			String com_name = "";
			String report_sns = "";
			for (ReportPrintRecord info : reportPrint.getReportPrintRecord()) {
				if(StringUtil.isNotEmpty(info.getReport_sn())){
					// 验证同一设备是否有老报告正在纠错，如纠错申请里未填写新报告编号，则新报告无法提交报送打印申请
					if (StringUtil.isNotEmpty(info.getInfo_id())) {
						InspectionInfo inspectionInfo = inspectionInfoDao.get(info.getInfo_id());
						if (inspectionInfo != null
								&& StringUtil.isNotEmpty(inspectionInfo.getFk_tsjc_device_document_id())
								&& !"11111111111111111111111111111111".equals(inspectionInfo.getFk_tsjc_device_document_id())) {
							List<InspectionInfo> list = inspectionInfoDao.queryInfosByDeviceId(
									inspectionInfo.getFk_tsjc_device_document_id(), info.getInfo_id());
							for (InspectionInfo old_info : list) {
								if (StringUtil.isNotEmpty(old_info.getReport_sn())) {
									List<ReportErrorRecordInfo> errorInfos = reportErrorRecordInfoDao
											.getErrorInfos(old_info.getId(), old_info.getReport_sn());
									if (!errorInfos.isEmpty()) {
										String msg = "";
										ReportErrorRecordInfo errorRecordInfo = errorInfos.get(0);
										if(errorRecordInfo != null){
											if(StringUtil.isNotEmpty(errorRecordInfo.getReport_sn())){
												msg += "（"+errorRecordInfo.getReport_sn()+"）";
											}
										}
										return JsonWrapper.failureWrapperMsg("报告（" + info.getReport_sn()
												+ "）有往年的报告"+msg+"是纠错报告，请先完成纠错流程，再提交新报告报送打印申请！");
									}
								}
							}
						}
					}
					
					List<ReportPrintRecord> list = reportPrintRecordDao.getRecords(info.getReport_sn());
					if(!list.isEmpty()){
						if (StringUtil.isNotEmpty(reportPrint.getId())) {
							for(ReportPrintRecord record : list){
								if (!record.getReportPrint().getId().equals(reportPrint.getId())) {
									return JsonWrapper
											.failureWrapperMsg("本次填写的报告（" + info.getReport_sn() + "）不能重复报送哦，请检查！");
								}
							}
						}else{
							return JsonWrapper
									.failureWrapperMsg("本次填写的报告（" + info.getReport_sn() + "）不能重复报送哦，请检查！");
						}
					}
					
					count++;
					if (StringUtil.isEmpty(info.getSn())) {
						// 获取业务流水号
						String sn = TS_Util.getReportPrintRecordSn(Integer.valueOf(count),
								conn);
						info.setSn(sn);
					}
					if(info.getReport_count()==null || info.getReport_count()==0){
						info.setReport_count(1);
					}
					info.setData_status("1"); // 数据状态（1：已提交）				
					info.setOrg_id(user.getDepartment().getId());
					info.setOrg_name(user.getDepartment().getOrgName());
					if(StringUtil.isNotEmpty(info.getCom_name())){
						if(StringUtil.isNotEmpty(com_name)){
							com_name += ",";
						}
						com_name += info.getCom_name();
					}
					if (StringUtil.isNotEmpty(info.getReport_sn())) {
						if (StringUtil.isNotEmpty(report_sns)) {
							report_sns += ",";
						}
						report_sns += info.getReport_sn();
					}
					info.setReportPrint(reportPrint);
					if(info.getId()==null&&reportPrint.getId()!=null&&StringUtil.isNotEmpty(reportPrint.getId())) {
						reportPrintRecordDao.save(info);
					}
				}			
			}
			if(reportPrint.getId()!=null&&StringUtil.isNotEmpty(reportPrint.getId())) {
				reportPrint = reportPrintDao.get(reportPrint.getId());
			}
			
			reportPrint.setData_status("1"); // 数据状态（1：已提交）
			reportPrint.setEnter_op_id(user.getId()); // 此处不用到电子签名，所以不使用employee
			reportPrint.setEnter_op_name(user.getName());
			reportPrint.setEnter_date(new Date());
			reportPrint.setOrg_id(user.getDepartment().getId());
			reportPrint.setOrg_name(user.getDepartment().getOrgName());
			reportPrint.setCom_name(com_name);		// 记录使用/制造/安装单位名称，方便查询
			reportPrint.setReport_sn(report_sns); 	// 报告书编号，方便查询
			if (StringUtil.isEmpty(reportPrint.getSn())) {
				// 获取业务流水号
				String sn = TS_Util.getReportPrintSn(Integer.valueOf(count), conn);
				reportPrint.setSn(sn);
			}
			reportPrintDao.save(reportPrint);

			logService.setLogs("", "报送打印录入", "检验资料报送打印签收录入", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return JsonWrapper.successWrapper(reportPrint);
	}
	
	/**
	 * 获取报送打印信息
	 * 
	 * @param id --
	 *            报送打印主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询报送打印主表
			ReportPrint reportPrint = reportPrintDao.get(id);
			// 查询报送打印明细表
			List<ReportPrintRecord> list = reportPrintRecordDao
					.getReportPrintRecords(id);
			map.put("data", reportPrint);
			map.put("reportPrintRecord", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
	
	/**
	 * 获取报送打印信息
	 * 
	 * @param id --
	 *            报送打印主表ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getPrintDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReportPrintRecordDTO> list = new ArrayList<ReportPrintRecordDTO>();
			// 查询报送打印主表
			//ReportPrint reportPrint = reportPrintDao.get(id);
			// 查询报送打印明细表
			List<ReportPrintRecord> reportPrintRecordList = reportPrintRecordDao
					.getReportPrintRecords(id);
			for(ReportPrintRecord reportPrintRecord : reportPrintRecordList){
				ReportPrintRecordDTO reportPrintRecordDTO = new ReportPrintRecordDTO();
				BeanUtils.copyProperties(reportPrintRecord, reportPrintRecordDTO);
				reportPrintRecordDTO.setCommit_time_str(DateUtil.getDateTime("yyyy-MM-dd", reportPrintRecord.getCommit_time()));
				reportPrintRecordDTO.setReceive_time_str(DateUtil.getDateTime("yyyy-MM-dd", reportPrintRecord.getReceive_time()));
				list.add(reportPrintRecordDTO);
			}
			//map.put("data", reportPrint);
			map.put("reportPrintRecord", list);
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
			// 查询报送打印明细明细表
			List<ReportPrintRecord> list = reportPrintRecordDao
					.getReportPrintRecords(id);
			if (!list.isEmpty()) {
				for (ReportPrintRecord reportPrintRecord : list) {
					// 1、删除报送打印明细明细表（TZSB_REPORT_PRINT_RECORD）
					reportPrintRecordDao
							.createSQLQuery(
									"update TZSB_REPORT_PRINT_RECORD set data_status='99' where id = ? ",
									reportPrintRecord.getId()).executeUpdate();
					// 写入日志
					logService.setLogs(reportPrintRecord
							.getId(), "删除报送打印", "删除报送打印明细",
							user.getId(), user.getName(), new Date(), request
									.getRemoteAddr());
				}
			}
			// 2、删除报送打印主表（TZSB_REPORT_PRINT）
			reportPrintRecordDao
					.createSQLQuery(
							"update TZSB_REPORT_PRINT set data_status='99' where id = ? ",
							id).executeUpdate();
			logService.setLogs(id, "删除报送打印", "删除报送打印",
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
				ReportPrint reportPrint = reportPrintDao.get(idArr[i]);
				// 查询报送打印明细明细表
				List<ReportPrintRecord> list = reportPrintRecordDao
						.getReportPrintRecords(idArr[i]);
				if (!list.isEmpty()) {
					for (ReportPrintRecord reportPrintRecord : list) {
						// 1、提交报送打印明细表（TZSB_REPORT_PRINT_RECORD）
						reportPrintRecord.setData_status("1");						
						reportPrintRecordDao.save(reportPrintRecord);
						// 写入日志
						logService.setLogs(reportPrintRecord
								.getId(), "报送打印提交", "检验资料报送打印提交",
								user.getId(), user.getName(), new Date(), request
										.getRemoteAddr());
					}
				}

				reportPrint.setData_status("1");
				reportPrintDao.save(reportPrint);				
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
	 * 签收报送打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReportPrint receive(ReportPrint reportPrint,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 获取业务报检表单号
		try {
			for (ReportPrintRecord info : reportPrint.getReportPrintRecord()) {
				info.setData_status("2"); // 数据状态（2：已签收）		
				info.setReceive_user_id(user.getId());
				info.setReceive_user_name(user.getName());
				info.setReceive_time(new Date());
				info.setReportPrint(reportPrint);
			}
			
			reportPrint.setData_status("2"); // 数据状态（2：已签收）
			reportPrint.setReceive_user_id(user.getId());
			reportPrint.setReceive_user_name(user.getName());
			reportPrint.setReceive_time(new Date());
			reportPrintDao.save(reportPrint);

			logService.setLogs("", "签收报送打印信息", "签收检验资料报送打印", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return reportPrint;
	}
	
	// 批量签收
	public boolean receives(String ids, HttpServletRequest request) {
		boolean isSuccess = true;
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				ReportPrint reportPrint = reportPrintDao.get(idArr[i]);
				// 查询报送打印明细明细表
				List<ReportPrintRecord> list = reportPrintRecordDao
						.getReportPrintRecords(idArr[i]);
				if (!list.isEmpty()) {
					for (ReportPrintRecord reportPrintRecord : list) {
						// 1、提交报送打印明细表（TZSB_REPORT_PRINT_RECORD）
						reportPrintRecord.setData_status("2");	// 2：已签收
						reportPrintRecord.setReceive_user_id(user.getSysUser().getId());
						reportPrintRecord.setReceive_user_name(user.getSysUser().getName());
						reportPrintRecord.setReceive_time(new Date());
						reportPrintRecordDao.save(reportPrintRecord);
						// 写入日志
						logService.setLogs(reportPrintRecord
								.getId(), "批量签收报送打印", "批量签收检验资料报送打印",
								user.getId(), user.getName(), new Date(), request
										.getRemoteAddr());
					}
				}

				reportPrint.setData_status("2");	// 2：已签收
				reportPrint.setReceive_user_id(user.getSysUser().getId());
				reportPrint.setReceive_user_name(user.getSysUser().getName());
				reportPrint.setReceive_time(new Date()); // 签收时间
				reportPrintDao.save(reportPrint);				
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			log.debug(e.toString());
		}
		return isSuccess;
	}
	
	// 根据业务信息ID查询业务详细信息（报告信息）
	public List<ReportPrintRecordDTO> queryReportInfos(String ids) throws Exception{
		return reportPrintRecordDao.queryReportInfos(ids);
	}
	
	// 根据业务信息ID查询验证报告是否报送且已签收状态
	public HashMap<String, Object> validateInfos(String ids) throws Exception {
		return reportPrintRecordDao.validateInfos(ids);
	}
	
	// 机电部门原始记录报送提醒
	public void reportPrintCheck() {
		try {
			// 获取已提交待签收的报送打印申请
			List<ReportPrint> reportPrintList = reportPrintDao.getReportPrints();
			for (ReportPrint reportPrint : reportPrintList) {
				if (StringUtil.isNotEmpty(reportPrint.getOrg_id())) {
					Org org = orgManager.get(reportPrint.getOrg_id());
					if (org != null) {
						if (StringUtil.isNotEmpty(org.getOrgCode())) {
							if (org.getOrgCode().contains("jd")) {
								Calendar c1 = Calendar.getInstance();
								Calendar c2 = Calendar.getInstance();
								c1.setTime(reportPrint.getEnter_date()); // 录入日期
								c2.setTime(new Date()); // 当前日期
								// 设置时间为0时
								c1.set(java.util.Calendar.HOUR_OF_DAY, 0);
								c1.set(java.util.Calendar.MINUTE, 0);
								c1.set(java.util.Calendar.SECOND, 0);
								c2.set(java.util.Calendar.HOUR_OF_DAY, 0);
								c2.set(java.util.Calendar.MINUTE, 0);
								c2.set(java.util.Calendar.SECOND, 0);

								// 计算两个日期相差的天数
								int days = ((int) (c2.getTime().getTime() / 1000)
										- (int) (c1.getTime().getTime() / 1000)) / 3600 / 24;

								if (days > 3) {
									int report_count = 0;
									String com_name = "";
									List<ReportPrintRecord> recordList = reportPrintRecordDao
											.getPrintRecords(reportPrint.getId());
									for (ReportPrintRecord reportPrintRecord : recordList) {
										if (StringUtil.isNotEmpty(reportPrintRecord.getCom_name())) {
											if (!com_name.contains(reportPrintRecord.getCom_name())) {
												if (StringUtil.isNotEmpty(com_name)) {
													com_name += "、";
												}
												com_name += reportPrintRecord.getCom_name();
											}
											report_count += reportPrintRecord.getReport_count();
										}
									}

									if (report_count > 0 && StringUtil.isNotEmpty(com_name)) {
										String[] userId = reportPrint.getEnter_op_id().split(",");
										for (String userIds : userId) { // 循环人员ID
											// 获取电话
											// Employee employee =
											// employeesService.get(userIds);
											String user = reportErrorDao.gettol(userIds).toString();
											String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
													.matcher(user.toString().substring(1, user.toString().length() - 1))
													.replaceAll("'$1'");

											// 获取发送内容
											String content = Constant.getReportPrintNoticeContent(
													reportPrint.getEnter_op_name(), com_name, report_count);

											// 发送微信
											messageService.sendWxMsg(null, reportPrint.getId(),
													Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content,
													destNumber);
											// 发送短信
											// messageService.sendMoMsg(null,
											// reportPrint.getId(), content,
											// destNumber);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查询报告报送打印信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryReportPrints(String id,String reportType) throws Exception {
		if(StringUtil.isNotEmpty(id)) {
			try {
				List<ReportPrint> reportPrints = reportPrintDao.queryReportPrints(id);
				List<InspectionInfo> infos = new ArrayList<InspectionInfo>();
				if(reportPrints != null && reportPrints.size()>0) {
					for(ReportPrint reportPrint : reportPrints) {
						Collection<ReportPrintRecord> reportPrintRecords = reportPrint.getReportPrintRecord();
						if(reportPrintRecords != null && reportPrintRecords.size()>0) {
							for(ReportPrintRecord reportPrintRecord : reportPrintRecords) {
								if(!"99".equals(reportPrintRecord.getData_status())) {
									InspectionInfo info = inspectionInfoDao.getInfo(reportPrintRecord.getInfo_id(), reportType);
									if(info !=null) {
										infos.add(info);
									}
								}
							}
						}
					}
				}
				return JsonWrapper.successWrapper(infos);
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.toString());
				return JsonWrapper.failureWrapperMsg("获取报送打印信息失败！");
			}
		}else {
			return JsonWrapper.failureWrapperMsg("业务ID为空，查询失败！");
		}
	}
}

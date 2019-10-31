package com.lsts.inspection.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.inspection.dao.InspectionZZJDInfoDao;
import com.lsts.log.service.SysLogService;

/**
 * 制造监督检验报检业务明细逻辑对象
 * 
 * @ClassName InspectoinZZJDInfoService
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-13 上午09:09:00
 */
@Service("inspectoinZZJDInfoService")
public class InspectionZZJDInfoService extends
		EntityManageImpl<InspectionZZJDInfo, InspectionZZJDInfoDao> {
	@Autowired
	private InspectionZZJDInfoDao inspectoinZZJDInfoDao;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private SysLogService logService;

	// 根据业务信息ID查询制造监督检验明细表数据
	public InspectionZZJDInfo getByInfoId(String inspection_info_id) {
		return inspectoinZZJDInfoDao.getByInfoId(inspection_info_id);
	}
	
	// 根据明细ID查询明细列表
	public List<InspectionZZJDInfo> queryInfos(String ids) throws Exception{
		return inspectoinZZJDInfoDao.queryInfos(ids);
	}
	
	// 删除
	public void del(HttpServletRequest request, String id){
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			InspectionZZJDInfo inspectionZZJDInfo = inspectoinZZJDInfoDao.get(id);
			if (inspectionZZJDInfo!=null) {
				// 1、先删除业务信息表（tzsb_inspection_info）
				if (StringUtil.isNotEmpty(inspectionZZJDInfo.getFk_inspection_info_id())) {
					inspectionInfoDao
					.createSQLQuery(
							"update tzsb_inspection_info set data_status='99' where id = ? ",
							inspectionZZJDInfo.getFk_inspection_info_id())
					.executeUpdate();
				}
				// 2、再删除制造监督检验报检业务明细表（tzsb_inspection_zzjd_info）
				inspectoinZZJDInfoDao
						.createSQLQuery(
								"update tzsb_inspection_zzjd_info set data_status='99' where id = ? ",
								id).executeUpdate();
			}
			
			// 3、写入日志
			logService.setLogs(inspectionZZJDInfo.getFk_inspection_info_id(), "报告作废", "报告录入时，报告删除", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		
	}
	
	// 导出报告
	public List<InspectionZZJDInfo> exportReport(String info_ids) {
		return inspectoinZZJDInfoDao.exportReport(info_ids);
	}
}

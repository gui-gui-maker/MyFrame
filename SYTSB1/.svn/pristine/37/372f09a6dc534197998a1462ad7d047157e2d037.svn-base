package com.lsts.inspection.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.inspection.bean.InspectionInfoPay;
import com.lsts.inspection.dao.InspectionInfoPayDao;
import com.lsts.log.service.SysLogService;
/**
 * 检验检测收费标准配置表业务逻辑对象
 * @ClassName InspectionInfoPayService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-12-30 上午09:42:00
 */
@Service("inspectionInfoPayService")
public class InspectionInfoPayService extends EntityManageImpl<InspectionInfoPay, InspectionInfoPayDao> {
	
	@Autowired
	private InspectionInfoPayDao inspectionInfoPayDao;
	@Autowired
	private SysLogService logService;
	
	/**
	 * 删除
	 * @param ids 
	 * @param request 
	 * @return 
	 * @throws Exception
	 */
	public void del(HttpServletRequest request, String ids) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();	
		String info_ids[] = ids.split(",");
		for (int i = 0; i < info_ids.length; i++) {
			// 1、删除与原始记录对应的检验业务信息
			inspectionInfoPayDao
			.createSQLQuery(
					"update tzsb_inspection_pay set data_status='99' where id = ?",
					info_ids[i]).executeUpdate();
			
			// 2、写入日志
			logService.setLogs(info_ids[i], "删除检验检测收费标准配置", "检验检测收费标准配置作废", user.getId(), user
					.getName(), new Date(), request.getRemoteAddr());
		}
	}
	
	/**
	 * 根据device_sort_code获取收费标准配置信息
	 * @param device_sort_code -- 设备名称代码
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-05 下午14:29:00
	 */
	public List<InspectionInfoPay> getByDeviceSortCodes(String device_sort_code) {
		return inspectionInfoPayDao.getByDeviceSortCodes(device_sort_code);
	}
}

package com.lsts.inspection.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.ImageTool;
import com.lsts.inspection.bean.ReportPageCheckInfo;
import com.lsts.inspection.bean.ReportPerAudit;
import com.lsts.inspection.dao.ReportPageCheckInfoDao;
import com.lsts.inspection.dao.ReportPerDao;
import com.lsts.log.service.SysLogService;

/**
 * 报告页单独审核业务逻辑对象
 * @ClassName ReportPageCheckInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-10-17 下午04:07:00
 */
@Service("reportPageCheckInfoService")
public class ReportPageCheckInfoService extends
		EntityManageImpl<ReportPageCheckInfo, ReportPageCheckInfoDao> {
	@Autowired
	private ReportPageCheckInfoDao reportPageCheckInfoDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private ReportPerDao perDao;
	@Autowired
	private ImageTool imageTool;
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getReportPageInfo(String id, String report_id) 
		throws Exception{
		// 获取报告页单独检验审核信息表 begin
		// 获取报告配置项目表
		Map<String,Object> page_check_imgMap  = new HashMap<String,Object>();	// 报告页单独检验审核人员图片集
		List<ReportPerAudit> perlist = perDao.createQuery("from ReportPerAudit t where t.fk_inspection_info_id='"+id+"' and t.fk_report_id='"+report_id+"'").list();
		for(ReportPerAudit reportPerAudit : perlist){
			String item_name = reportPerAudit.getItem_name();
			if (item_name.startsWith("INSPECT_MAN_PTR")) {
				String image_name = "INSPECT_MAN_PICTURE" + item_name.substring("INSPECT_MAN_PTR".length());
				page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
			}
			if (item_name.startsWith("AUDIT_MAN_PTR")) {
				String image_name = "AUDIT_MAN_PICTURE" + item_name.substring("AUDIT_MAN_PTR".length());
				page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
			}
			if (item_name.startsWith("EVAL_PIC_MAN_PTR")) {
				String image_name = "EVAL_PIC_MAN_PICTURE" + item_name.substring("EVAL_PIC_MAN_PTR".length());
				page_check_imgMap.put(image_name, imageTool.getEmployeeImg(reportPerAudit.getItem_value_id()));
			}
		}
		// 获取报告页单独检验审核信息表 end 
		return page_check_imgMap;
	}	
	
	// 获取报告分页单独审核信息
	@SuppressWarnings("unchecked")
	public List queryPageInfo(String infoId, String report_id, String userId) {
		return reportPageCheckInfoDao.queryPageInfo(infoId, report_id, userId);
	}

	// 获取报告分页审核记录信息表
	public List<ReportPageCheckInfo> queryPageInfos(String infoId, String report_id, String userId){
		return reportPageCheckInfoDao.queryPageInfos(infoId, report_id, userId);
	}
	
	// 获取报告分页审核记录信息表（待审核）
	public List<ReportPageCheckInfo> queryUnCheckPageInfos(String infoId, String report_id, String userId){
		return reportPageCheckInfoDao.queryUnCheckPageInfos(infoId, report_id, userId);
	}
	
	// 获取报告分页审核记录信息表
	public List<ReportPageCheckInfo> queryPageInfos(String infoId, String report_id){
		return reportPageCheckInfoDao.queryPageInfos(infoId, report_id);
	}
}
